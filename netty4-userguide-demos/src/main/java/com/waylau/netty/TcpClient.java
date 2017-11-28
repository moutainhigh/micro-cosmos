package com.waylau.netty;

import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by thomas.su on 2017/11/21 21:30.
 */
public class TcpClient {
    public static void main(String[] args) {
        Socket socket = null;
        OutputStream out = null;
        try {
            socket = new Socket("localhost", 8023);
            String lines = "床前明月光\r\n疑是地上霜\r\n举头望明月\r\n低头思故乡\r\n";
            byte[] outputBytes = lines.getBytes("utf-8");
            out = socket.getOutputStream();
            out.write(outputBytes);
            out.flush();

        } catch (Exception e) {

        } finally {
            try {
                out.close();
                socket.close();
            } catch (Exception e) {

            }
        }

    }
}
