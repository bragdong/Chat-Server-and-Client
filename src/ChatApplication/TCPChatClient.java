package ChatApplication;

//import java.io.*;
//import java.net.*;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import java.net.UnknownHostException;
import java.io.IOException;

public class TCPChatClient {
	private static final int PORT = 3000;

	public static void main(String argv[]) throws Exception {

		System.out.println("Client is now running...");
		System.out.println("Type Quit to end the program.");
		String sentence;
		String modifiedSentence;
		Socket clientSocket = null;

		while (true) {
			try {
				clientSocket = new Socket("localhost", PORT);
				BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
				sentence = inFromUser.readLine().toLowerCase(); // Read until
																// end of line
				if (sentence.equals("quit")) {
					break;
				}
				DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
				try {
					outToServer.writeBytes(sentence + '\n');
				} catch (IOException ex) {
					System.out.println("Input Output error.");
				}

				// Write out Server response
				BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				modifiedSentence = inFromServer.readLine();
				System.out.println("FROM SERVER: " + modifiedSentence);

				clientSocket.close();
			} catch (UnknownHostException e) {
				System.out.println("Unknown Host exception.");
				e.printStackTrace();
				clientSocket.close();
				return;
			} catch (IOException e) {
				System.out.println("IO Exception encountered.");
				e.printStackTrace();
				clientSocket.close();
				return;
			}

		}

	}
}
