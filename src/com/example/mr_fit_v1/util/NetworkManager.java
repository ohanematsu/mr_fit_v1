package com.example.mr_fit_v1.util;

import java.io.*;
import java.net.*;

import android.util.Log;

public class NetworkManager {
	
	private static final String LOGTAG = "NetworkManager";
	
	private static Socket socket;
	private static ObjectOutputStream outputStream;
	private static ObjectInputStream inputStream;
	
	private static final String SERVER_IP_ADDR = "127.0.0.1";
	private static final int PORT = 9999;
	
	 
    public static boolean sendPacket(Packet packet) {
    	if (socket == null) {
    		if (!openConnection()) {
    			return false;
    		}
    	}
    	
    	try {
			outputStream.writeObject(packet);
			return true;
		} catch (IOException e) {
			Log.i(LOGTAG, "IOException occurs during sending packet...");
			e.printStackTrace();
			return false;
		}
    }
    
    public static Packet receivePacket() {
    	Object packet;
		try {
			packet = inputStream.readObject();
		} catch (OptionalDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
    	if (packet instanceof Packet) {
    		return (Packet)packet;
    	} else {
    		return null;
    	}
    }
    
    public static boolean openConnection() {
        // Create socket
    	try {
			socket = new Socket(SERVER_IP_ADDR, PORT);
		} catch (UnknownHostException e) {
			Log.i(LOGTAG, "Unknown host error -- " + e.getMessage());
			return false;
		} catch (IOException e) {
			Log.i(LOGTAG, "IOException error -- " + e.getMessage());
			return false;
		}
        
        // Set up output stream
        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
        	Log.i(LOGTAG, "Create socket output stream error -- " + e.getMessage());
            return false;
        }
        
        // Set up input stream
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            Log.i(LOGTAG, "Create socket input stream error -- " + e.getMessage());
            return false;
        }
        
        return true;
    }

    public static void closeConnection() {
        try {
            inputStream = null;
            outputStream = null;
            socket.close();
        }
        catch (IOException e){
        	Log.i(LOGTAG, "IOException -- " + e.getMessage());
        }       
    }
}
