package com.wujiale.springbootsystem.utils;

import org.springframework.stereotype.Service;

import java.io.*;

/**
 * ClassName:string2File
 * Date:2022/5/1816:00
 *
 * @autherWJL
 */
public class StringToFile {
    /**
     * 将字符串写入指定文件(当指定的父路径中文件夹不存在时，会最大限度去创建，以保证保存成功！)
     *
     * @param res      原字符串
     * @param filePath 文件路径
     * @return 成功标记
     */
    public static boolean string2File(String res, String filePath) {
        boolean flag = true;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            File distFile = new File(filePath);
            if (!distFile.getParentFile().exists()) distFile.getParentFile().mkdirs();
            bufferedReader = new BufferedReader(new StringReader(res));
            bufferedWriter = new BufferedWriter(new FileWriter(distFile));
            String len;
            while ((len = bufferedReader.readLine()) != null) {
                // 这里因为 单引号 ' 有些问题, 做了下面特殊处理
                len = len.replace("&apos;", "'");
                bufferedWriter.write(len);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            bufferedReader.close();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            flag = false;
            return flag;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

}
