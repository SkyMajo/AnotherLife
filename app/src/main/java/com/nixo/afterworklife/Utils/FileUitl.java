package com.nixo.afterworklife.Utils;


import android.util.Base64;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zhanghongyu on 2018/7/30.
 */

public class FileUitl {
    /**
     * 文件转base64字符串
     *
     * @param file
     * @return
     */
    public static String fileToBase64(File file) {
        String base64 = null;
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            byte[] bytes = new byte[in.available()];
            int length = in.read(bytes);
            base64 = android.util.Base64.encodeToString(bytes, 0, length, android.util.Base64.DEFAULT);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return base64;
    }

    /**
     * 文件转base64字符串
     *
     * @param base64Str
     * @return
     */
    public static boolean base64ToFile(String base64Str, String filePath) {

        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;

        try {
            byte[] bytes = Base64.decode(base64Str, Base64.DEFAULT);//base64编码内容转换为字节数组
            ByteArrayInputStream byteInputStream = new ByteArrayInputStream(bytes);
            bis = new BufferedInputStream(byteInputStream);
            File file = new File(filePath);
            File path = file.getParentFile();
            if (!path.exists()) {
                path.mkdirs();
            }
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);

            byte[] buffer = new byte[1024];
            int length = bis.read(buffer);
            while (length != -1) {
                bos.write(buffer, 0, length);
                length = bis.read(buffer);
            }
            bos.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                bis.close();
                fos.close();
                bos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    // 生成文件
    public static File makeFilePath(File files, String filePath, String fileName) {
        File file = null;
        makeRootDirectory(filePath);
        try {
            file = new File(filePath + fileName);
            file.delete();
            if (!file.exists()) {
                file.createNewFile();
                FileInputStream in = new FileInputStream(files);
                FileOutputStream fo = new FileOutputStream(file);
                int read = in.read();
                while (read != -1) {
                    fo.write(read);
                    read = in.read();
                }
                //关闭流
                fo.flush();
                fo.close();
                in.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    // 生成文件夹
    public static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {

        }
    }
}
