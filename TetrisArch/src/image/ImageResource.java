package image;

import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ImageResource {
    private String filePath = "./imagesSrc.txt";


    public Map<String, String> readToMap() {
        Map<String, String> resourceMap = new HashMap<>();

        if (filePath == null || filePath.trim().isEmpty()) {
            System.err.println("文件路径不能为空");
            return resourceMap;
        }

        try (InputStream is = new FileInputStream(filePath);
             InputStreamReader isr = new InputStreamReader(is);
             BufferedReader br = new BufferedReader(isr)) {

            StringBuilder paragraphBuilder = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    if (!paragraphBuilder.isEmpty()) {
                        processParagraph(paragraphBuilder.toString(), resourceMap);
                        paragraphBuilder.setLength(0); // 重置 StringBuilder
                    }
                } else {
                    // 非空行则添加到当前段落
                    if (!paragraphBuilder.isEmpty()) {
                        paragraphBuilder.append("\n"); // 保留段落内的换行
                    }
                    paragraphBuilder.append(line);
                }
            }

            // 处理最后一个段落（文件末尾可能没有空行）
            if (!paragraphBuilder.isEmpty()) {
                processParagraph(paragraphBuilder.toString(), resourceMap);
            }

        } catch (FileNotFoundException e) {
            System.err.println("文件未找到: " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("读取文件时发生错误: " + e.getMessage());
            e.printStackTrace();
        }

        return resourceMap;
    }

    private void processParagraph(String paragraph, Map<String, String> map) {
        int colonIndex = paragraph.indexOf(':');
        if (colonIndex != -1) {
            String key = paragraph.substring(0, colonIndex).trim();
            String value = paragraph.substring(colonIndex + 1).trim();

            // 添加到Map，如果有重复的key，后面的会覆盖前面的
            map.put(key, value);
        } else {
            // 没有冒号的段落，作为无效段落处理
            String preview = paragraph.length() > 30 ?
                paragraph.substring(0, 30) + "..." : paragraph;
            System.out.println("跳过无效段落（未找到冒号）: " + preview);
        }
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}


