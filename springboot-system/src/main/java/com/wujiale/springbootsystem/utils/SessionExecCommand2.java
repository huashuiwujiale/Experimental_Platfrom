package com.wujiale.springbootsystem.utils;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * ClassName:SessionExecCommand2
 * Date:2022/5/237:00
 * 执行运行代码命令
 * @autherWJL
 */
@Service
public class SessionExecCommand2 {
    public StringBuffer execCommand(String command) {
        String hostname = "119.3.86.125";//远程机器IP
        String username = "root";//登录用户名
        String password = "183920Wjl";//登录密码
        System.out.println("接收到的指令是"+command);
        StringBuffer stringBuffer = new StringBuffer();
        try {
            Connection conn = new Connection(hostname);
            conn.connect();
            boolean isAuthenticated = conn.authenticateWithPassword(username, password);
            ///是否登录成功
            if (isAuthenticated == false) {
                throw new IOException("连接失败！！！");
            }
            Session sess = conn.openSession();
            //执行命令
            //只允许使用一行命令，即ssh对象只能使用一次execCommand这个方法，
            //多次使用则会出现异常
            //使用多个命令用分号隔开
            sess.execCommand(command);
            System.out.println("Here is some information about the remote host:");
            //将Terminal屏幕上的文字全部打印出来
            //创建输入流,StreamGobbler的作用是把session的标准输出包装成InputStream，用于接收目标服务器上的控制台返回结果，
            //读取br中的内容。然后在循环中，把每一行的内容添加到StringBuffer里面。
            InputStream stdout = new StreamGobbler(sess.getStdout());
            //字符流
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout));

            while (true) {
                String line = br.readLine();
                if (line == null)
                    break;
                stringBuffer.append(line);
                stringBuffer.append("\n");
                System.out.println(line);
            }
            System.out.println("ExitCode: " + sess.getExitStatus());
            //关闭连接
            sess.close();
            conn.close();
        } catch (IOException e) {
            e.printStackTrace(System.err);
            System.exit(2);
        }
        return stringBuffer;
    }
}
