package org.example;
import com.jcraft.jsch.*;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, JSchException, SftpException {
        final JSch jsch = new JSch();
        Session session = null;
        ChannelSftp channelSftp = null;
        try{
            session = jsch.getSession("#username", "#host");
            session.setPassword("#password");
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            com.jcraft.jsch.Channel channel = session.openChannel("sftp");
            channel.connect();
            channelSftp = (ChannelSftp) channel;
            try{
                channelSftp.put("#sourcePath","#destinationPath");
                System.out.println(channelSftp.ls("#path"));
            }finally {
                channelSftp.disconnect();
            }
        } finally {
            session.disconnect();
        }
    }
}
