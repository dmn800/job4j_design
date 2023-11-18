package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());

                    String textBye = in.readLine();
                    System.out.println(textBye);
                    if (textBye.contains("msg=Hello")) {
                        out.write("Hello\r\n\r\n".getBytes());
                    } else if (textBye.contains("msg=Exit")) {
                        server.close();
                    } else {
                        out.write("What\r\n\r\n".getBytes());
                    }

                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                    }
                    out.flush();
                }
            }
            throw new Exception("Example exception");
        } catch (Exception e) {
            LOG.error("Exception server", e);
        }
    }
}
