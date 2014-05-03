package server;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import serverCom.AuthenMessage;

public class Server {

	public Server() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int portNumber = 18641;

		
		@SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(portNumber);
		while(true){
			Socket clientSocket = serverSocket.accept();
			InputStream is = clientSocket.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			AuthenMessage msg = (AuthenMessage) ois.readObject();
			if(msg.type == 1){
				//sign in
			}
			if(msg.type == 2){
			//sign up
			}
			if(msg.type == 3){
			//find friend
			}
			if(msg.type == 4){
			//add friend
			}
			if(msg.type == 5){
			//list friends
			}
		}
	}

}
