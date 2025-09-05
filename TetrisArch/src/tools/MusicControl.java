package tools;

import sound.SoundResource;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class MusicControl {
    public static Map<String, Clip> clips = new HashMap<String,Clip>();
    private Map<String, FloatControl> volumeControls = new HashMap<>();



    public void loadAllSounds() {
        SoundResource resource = new SoundResource();
        Map<String, String> soundPaths = resource.readToMap();

        for (Map.Entry<String, String> entry : soundPaths.entrySet()) {
            loadSound(entry.getValue(), entry.getKey());
        }
    }
    //加载音频
    /*
    path:音频路径
    name:加载后的别称
     */
    public void loadSound(String path, String name) {
        if (name == null || name.isEmpty()) {
            System.err.println("音频名称不能为空");
            return;
        }

        try {
            InputStream is = getClass().getResourceAsStream(path);
            if (is == null) {
                System.err.println("找不到音频资源: " + path);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(is);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControls.put(name, control);

            clips.put(name, clip);
            System.out.println("成功加载音频: " + name + " (" + path + ")");

        } catch (UnsupportedAudioFileException e) {
            System.err.println("不支持的音频格式: " + path);
        } catch (LineUnavailableException e) {
            System.err.println("音频设备不可用: " + path);
        } catch (IOException e) {
            System.err.println("加载音频失败: " + path + " - " + e.getMessage());
        }
    }

    //卸载音频
    /*
    加载后的别称
     */
    public void unloadSound(String name) {
        Clip clip = clips.remove(name);
        if (clip != null) {
            clip.stop();
            clip.close();
            volumeControls.remove(name);
            System.out.println("已卸载音频: " + name);
        } else {
            System.out.println("音频不存在: " + name);
        }
    }

    //播放音频
    /*
    name:别称
    isLoop：是否循环播放
     */
    public void playSound(String name, boolean isLoop) {
        Clip clip = clips.get(name);
        if (clip == null) {
            System.err.println("播放失败，音频未加载: " + name);
            return;
        }

        if (clip.isRunning()) {
            clip.stop();
        }
        clip.setFramePosition(0);
        clip.loop(isLoop ? Clip.LOOP_CONTINUOUSLY : 0);
        clip.start();
    }

    //停止音频播放
    /*
    name：音频加载后的别称
     */
    public void pauseSound(String name) {
        Clip clip = clips.get(name);
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    //恢复播放
    /*
    name：音频加载后的别称
     */
    public void resumeSound(String name) {
        Clip clip = clips.get(name);
        if (clip != null && !clip.isRunning()) {
            clip.start();
        }
    }

    //修改音量大小
    /*
    name：音频加载后的别称
    num:修改后的音量大小；（有效范围0~100）
     */
    public void setSoundVolume(String name, int num) {
        if (num < 0 || num > 100) {
            System.err.println("音量值必须在0-100之间");
            return;
        }

        FloatControl control = volumeControls.get(name);
        if (control == null) {
            System.err.println("无法设置音量，音频未加载: " + name);
            return;
        }

        float min = control.getMinimum();
        float max = control.getMaximum();
        float gain = min + (max - min) * (num / 100.0f);
        control.setValue(gain);
    }
}

