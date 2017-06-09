package ChatApplication;

//import java.io.*;
//import java.net.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import java.io.IOException;
//import java.lang.NumberformatException;
import java.util.Arrays;

public class TCPChatServer {

	private static final int PORT = 3000;

	public static void main(String[] args) throws Exception {
		System.out.println("Server is now running...");
		String clientSentence;
		String capitalizedSentence;

		ServerSocket serversock = new ServerSocket(TCPChatServer.PORT);
		Socket connectionSocket = null;

		try {
			while (true) {
				connectionSocket = serversock.accept();
				BufferedReader inFromClient = new BufferedReader(
						new InputStreamReader(connectionSocket.getInputStream()));
				DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
				clientSentence = inFromClient.readLine(); // read to end of line
				System.out.println("Received: " + clientSentence);
				capitalizedSentence = convertAndSort(clientSentence);
				outToClient.writeBytes(capitalizedSentence);
				connectionSocket.close();
			}
		} catch (IOException ex) {
			System.out.println("Client connection dropped or IO error encountered.");
			System.out.println(ex);
			connectionSocket.close();
			serversock.close();
			return;
		}
	}

	public static String convertAndSort(String numbers) throws Exception {
		String[] arrayNumString = numbers.split("\\s"); // remove spaces and
														// load into int array
														// for sorting
		int[] arrayNum = new int[arrayNumString.length];
		try {
			for (int i = 0; i < arrayNumString.length; i++) {
				arrayNum[i] = Integer.parseInt(arrayNumString[i]);
			}
			Arrays.sort(arrayNum);
			String x = Arrays.toString(arrayNum);
			x = x.substring(1, x.length() - 1); // remove brackets
			return x.replaceAll(",", " ");
		} catch (NumberFormatException ex) {
			System.out.println(ex);
			return "Invalid input. Must enter numbers or Quit to end the program.";
		}
	}
}
