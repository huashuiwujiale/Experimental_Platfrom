package com.wujiale.springbootsystem.service;

import com.wujiale.springbootsystem.entity.Container;
import com.wujiale.springbootsystem.mapper.ExperimentMapper;
import com.wujiale.springbootsystem.utils.FtpUtil;
import com.wujiale.springbootsystem.utils.SessionExecCommand;
import com.wujiale.springbootsystem.utils.SessionExecCommand2;
import com.wujiale.springbootsystem.utils.StringToFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * ClassName:JavacodeService 运行按钮
 * Date:2022/5/1816:02
 * 做的事情：
 * 1、将代码保存到本地
 * 2、将文件上传到linux系统用户的文件夹下
 * 3、执行编译、运行java文件命令
 * 4、向Controller返回执行结果
 * @autherWJL
 */
@Service
public class JavacodeService {
    @Autowired
    ExperimentMapper experimentMapper;
    @Autowired
    SessionExecCommand2 sessionExecCommand;

    String hostname = "119.3.86.125";//远程机器IP
    String name = "root";//登录用户名
    String password = "183920Wjl";//登录密码
    public StringBuffer codeRun(String username,String container_id,String code){
        // 1、拼接为本地储存路径，将代码保存到本地
        String filepath="F:\\"+username+"\\"+container_id+"\\"+"Main.java";
        StringToFile.string2File(code,filepath);
        //通过容器id获取对应镜像
        Container containerByContainerId = experimentMapper.getContainerByContainerId(container_id);
        String image_name = containerByContainerId.getImage_name();
        // 2、将文件上传到linux系统用户的文件夹下
        //定义上传文件的路径
        String upFilePath = "/"+username+"/"+username+image_name;
        File file = new File(filepath);
        try {
            InputStream is = new FileInputStream(file);
            Boolean bh = FtpUtil.uploadFile(hostname,22,name,password,"/home",upFilePath,"Main.java",is);
            System.out.println(bh);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 3、执行编译、运行java文件命令
        String start_command = "docker start "+container_id;
        sessionExecCommand.execCommand(start_command);
        String javac_command = "docker exec  "+container_id+" bash -c 'cd /javafiles&&javac Main.java'";
        sessionExecCommand.execCommand(javac_command);
        String java_command = "docker exec  "+container_id+" bash -c 'cd /javafiles&&java Main'";
        StringBuffer stringBuffer = sessionExecCommand.execCommand(java_command);
        return stringBuffer;
    }

    public String codeSave(String username,String container_id,String code){
        // 1、拼接为本地储存路径，将代码保存到本地
        String filepath="F:\\"+username+"\\"+container_id+"\\"+"Main.java";
        boolean b = StringToFile.string2File(code, filepath);
        String stop_command = "docker stop "+container_id;
        sessionExecCommand.execCommand(stop_command);
        if (b==true){
            return "保存成功！";
        }
        return "保存失败";
    }
    public String codeRead(String username,String container_id,String code){
        // 1、拼接为本地储存路径，将代码保存到本地
        String filepath="F:\\"+username+"\\"+container_id+"\\"+"Main.java";
        StringBuffer stringBuffer = readLocalfile(filepath);
        System.out.println(stringBuffer.toString());

        return stringBuffer.toString();
    }
//    读取本地的java文件，返回一个InputStream input流
    public StringBuffer readLocalfile(String fileName){
        StringBuffer stringBuffer = new StringBuffer();
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读一行，读入null时文件结束
            while ((tempString = reader.readLine()) != null) {
                Thread.sleep(500);
                System.out.println(tempString);
                stringBuffer.append(tempString);
                stringBuffer.append("\n");
                line++;
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return stringBuffer;
    }
}
