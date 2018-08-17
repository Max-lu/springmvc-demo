package cn.maxlu.demo.springmvc.common.util;

import java.io.File;
import java.io.IOException;

public class FileUtils {
    public static boolean createFile(File file) {
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                boolean mkdirs = file.getParentFile().mkdirs();
                if (!mkdirs) {
                    return false;
                }
            }
            try {
                boolean newFile = file.createNewFile();
                if (!newFile) {
                    return false;
                }
            } catch (IOException e) {
                return false;
            }
        }
        return true;
    }
}
