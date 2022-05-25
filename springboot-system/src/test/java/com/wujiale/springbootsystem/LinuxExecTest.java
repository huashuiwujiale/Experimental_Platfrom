package com.wujiale.springbootsystem;

import com.wujiale.springbootsystem.utils.SessionExecCommand;
import org.junit.Test;

/**
 * ClassName:LinuxExecTest
 * Date:2022/5/1623:20
 *
 * @autherWJL
 */

public class LinuxExecTest {
    SessionExecCommand sessionExecCommand = new SessionExecCommand();

    @Test
    public void testLinuxExec(){
//        sessionExecCommand.execCommand("docker exec  3aaad44b2a58 bash -c 'java /javafiles/Main'");
//        sessionExecCommand.execCommand("docker exec  3aaad44b2a58 bash -c 'cd /javafiles/&&java Main'");
        sessionExecCommand.execCommand("docker exec  3aaad44b2a58 bash -c 'cd /javafiles&&javac Main.java'");
//        sessionExecCommand.execCommand("javac /javafiles/Main.java;java /javafiles/Main");
//        sessionExecCommand.execCommand("java /javafiles/Main");

    }

}
