package sound;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class SoundResource {
    // 配置文件名称（与当前类同包）
    private String filePath = "SoundSrc.txt";

    public Map<String, String> readToMap() {
        Map<String, String> soundMap = new HashMap<>();

        if (filePath == null || filePath.trim().isEmpty()) {
            System.err.println("音频文件路径不能为空");
            return soundMap;
        }

        try (InputStream is = getClass().getResourceAsStream(filePath);
             BufferedReader br = new BufferedReader(
                 new InputStreamReader(is, StandardCharsets.UTF_8)
             )) {

            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                int colonIndex = line.indexOf(':');
                if (colonIndex == -1) {
                    System.err.println("警告:缺少冒号" + line);
                    continue;
                }

                String key = line.substring(0, colonIndex).trim();
                String value = line.substring(colonIndex + 1).trim();


                soundMap.put(key, value);
            }


        } catch (IOException e) {
            System.err.println( e.getMessage());
            e.printStackTrace();
        }

        return soundMap;
    }
}
