package com.wujiale.springbootsystem.utils;
/**
 * ClassName:UDFUtils
 * Date:2022/5/1817:27
 * @autherWJL
 */
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * ClassName: UDFUtils
 * Description:
 *
 * @author pangyq
 * date: 2021/9/17 14:12
 */
@Component
public class UDFUtils {
    public static String host;
    public static int port;
    public static String user;
    public static String password;

    @Value("${remoteServer.url}")
    public void setHost(String serverHost) {
        host = serverHost;
    }

    @Value("${remoteServer.username}")
    public void setUser(String serverUser) {
        user = serverUser;
    }

    @Value("${remoteServer.password}")
    public void setPassword(String serverPassword) {
        password = serverPassword;
    }

    @Value("8088")
    public void setPort(Integer serverPort) {
        port = serverPort;
    }

    private Session session;

    /**
     * 创建一个连接
     */
    private void initialSession() throws Exception {
        if (session == null) {
            JSch jsch = new JSch();
            session = jsch.getSession(user, host, port);
            session.setUserInfo(new UserInfo() {

                @Override
                public String getPassphrase() {
                    return null;
                }

                @Override
                public String getPassword() {
                    return null;
                }

                @Override
                public boolean promptPassword(String arg0) {
                    return false;
                }

                @Override
                public boolean promptPassphrase(String arg0) {
                    return false;
                }

                @Override
                public boolean promptYesNo(String arg0) {
                    return true;
                }

                @Override
                public void showMessage(String arg0) {
                }

            });
            session.setPassword(password);
            session.connect();
        }
    }

    /**
     * 上传文件
     *
     * @param localPath  本地路径，若为空，表示当前路径
     * @param localFile  本地文件名，若为空或是“*”，表示目前下全部文件
     * @param remotePath 远程路径，若为空，表示当前路径，若服务器上无此目录，则会自动创建
     * @throws Exception
     */
    public void putFile(String localPath, String localFile, String remotePath)
            throws Exception {
        ChannelExec openChannel = null;
        this.initialSession();
        Channel channelSftp = session.openChannel("sftp");
        channelSftp.connect();
        ChannelSftp c = (ChannelSftp) channelSftp;
        String remoteFile = null;
        if (remotePath != null && remotePath.trim().length() > 0) {
            try {
                c.mkdir(remotePath);
            } catch (Exception e) {
            }
            remoteFile = remotePath + "/.";
        } else {
            remoteFile = ".";
        }
        String file = null;
        if (localFile == null || localFile.trim().length() == 0) {
            file = "*";
        } else {
            file = localFile;
        }
        if (localPath != null && localPath.trim().length() > 0) {
            if (localPath.endsWith("/")) {
                file = localPath + file;
            } else {
                file = localPath + "/" + file;
            }
        }
        c.put(file, remoteFile);
        //把jar包放到hdfs目录
        openChannel = (ChannelExec) session.openChannel("exec");
        openChannel.setCommand("su - hdfs");
        openChannel.setCommand("hdfs dfs -put " + remotePath + localFile + "  /udfjar");
        int exitStatus = openChannel.getExitStatus();
        openChannel.connect();
        channelSftp.disconnect();
    }
}