package tools;

import javax.sound.sampled.Clip;
import java.util.HashMap;
import java.util.Map;

public class MusicControl {
    public static Map<String, Clip> clips = new HashMap<String,Clip>();
    //加载音频
    /*
    path:音频路径
    name:加载后的别称
     */
    public void loadSound(String Path, String name){}

    //卸载音频
    /*
    加载后的别称
     */
    public void unloadSound(String name){}

    //播放音频
    /*
    name:别称
    isLoop：是否循环播放
     */
    public void playSound(String name,boolean isLoop){}

    //停止音频播放
    /*
    name：音频加载后的别称
     */
    public void pauseSound(String name){}

    //恢复播放
    /*
    name：音频加载后的别称
     */
    public void resumeSound(String name){}

    //修改音量大小
    /*
    name：音频加载后的别称
    num:修改后的音量大小；（有效范围0~100）
     */
    public void setSoundVolume(String name,int num){}
}
