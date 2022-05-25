package com.wujiale.springbootsystem.utils;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * ClassName:LinuxConnection
 * Date:2022/5/1416:13
 *
 * @autherWJL
 */
public class LinuxConnection {
    public static void main(String[] args) {
        String hostname = "119.3.86.125";//远程机器IP
        String username = "root";//登录用户名
        String password = "183920Wjl";//登录密码

        try {
            Connection conn = new Connection(hostname);
            System.out.println(conn);
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
            sess.execCommand("uname -a && date && uptime && who; docker images");
            System.out.println("Here is some information about the remote host:");
            //将Terminal屏幕上的文字全部打印出来
            //创建输入流
            InputStream stdout = new StreamGobbler(sess.getStdout());
            //字符流
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout));

            while (true) {
                String line = br.readLine();
                if (line == null)
                    break;
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
    }
}
