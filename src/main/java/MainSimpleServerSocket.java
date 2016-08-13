import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.*;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

/**
 * Created by Rom8 on 12.08.2016.
 */
public class MainSimpleServerSocket {

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(2016);
//        new Thread(new Client()).start();

        int i = 0;
        while (true){
            System.out.println("Waiting 4 connection...");
            Socket socket = serverSocket.accept();
            System.out.println("-----------------accepted------------------");

            BufferedInputStream in = new BufferedInputStream(socket.getInputStream());
            byte[] buffer = new byte[1024];
            in.read(buffer);
            System.out.println(new String(buffer, Charset.defaultCharset()).trim());

            BufferedOutputStream out = new BufferedOutputStream(socket.getOutputStream());
            String line = String.valueOf(i++);
            out.write(line.getBytes());
            out.flush();
            socket.close();
//            TimeUnit.SECONDS.sleep(5);
        }

    }

    private static class Client implements Runnable {

        InetAddress inetAddress;
        Socket socket;

        public Client() throws IOException {
            socket = new Socket();
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(3);
                socket.connect(new InetSocketAddress(2016));
                BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());
                while (true){
                    byte[] buffer = new byte[1024];
                    bufferedInputStream.read(buffer);
                    String s = new String(buffer, "UTF-8");
                    System.out.println(s);
                }
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }

}
