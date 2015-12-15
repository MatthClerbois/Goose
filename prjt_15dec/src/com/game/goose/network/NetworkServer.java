package com.game.goose.network;

import java.io.IOException;
import java.net.ServerSocket;
/**
 * class NetworServer extend ServerSocket
 * @author Max
 *
 */
public class NetworkServer extends ServerSocket {
	private int portNum;
	/**
	 * NextworkServer constructor
	 * @throws IOException
	 */
	public NetworkServer() throws IOException {
		super(Parameter.BASEPORTNUM);
		portNum=Parameter.BASEPORTNUM;
		// TODO Auto-generated constructor stub
	}

	public static void main(String args[]){
		
	}
}
