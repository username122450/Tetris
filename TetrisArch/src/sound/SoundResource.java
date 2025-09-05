package sound;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class SoundResource {
    //这个包用来存储需要用到的音频文件
    private String filePath = "./soundsSrc.txt";

    public Map<String, String> readToMap() {
        Map<String, String> soundMap = new HashMap<>();

        if (filePath == null || filePath.trim().isEmpty()) {
            System.err.println("音频文件路径不能为空");
            return soundMap;
        }

        try (FileInputStream fis = new FileInputStream(filePath);
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader br = new BufferedReader(isr)) {

            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                int colonIndex = line.indexOf(':');
                if (colonIndex != -1) {
                    String key = line.substring(0, colonIndex).trim();
                    String value = line.substring(colonIndex + 1).trim();
                    soundMap.put(key, value);
                } else {
                    System.out.println("跳过：" + line);
                }
            }

        } catch (IOException e) {
            System.err.println("读取音频文件错误: " + e.getMessage());
            e.printStackTrace();
        }

        return soundMap;
    }
}

class SoundResource2 {
    // 这个包用来存储需要用到的音频文件


}
