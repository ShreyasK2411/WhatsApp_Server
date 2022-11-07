import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TestServer {

	public static void main(String[] args) {
		InetAddress ip;
		
		try {
			System.out.println("ip: "+InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		try(ServerSocket server=new ServerSocket(5051);Scanner sc=new Scanner(System.in)) {
			System.out.println("Waiting for connection.....");
			Socket socket=server.accept();
			System.out.println("Connected.");
			
			DataInputStream din=new DataInputStream(socket.getInputStream());
			DataOutputStream dout=new DataOutputStream(socket.getOutputStream());
			
			String send="",recv="";
			
			while(!recv.equals("stop")) {
				recv=din.readUTF();
				System.out.println("Server: "+recv);
				
				send=sc.nextLine();
				dout.writeUTF(send);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
