package Serveur;

import java.io.*;
import java.net.*;
import java.util.Vector;

//Passé à un thread donc implémente la classe Runnable
public class Accepter_connexion implements Runnable{

	private ServerSocket socketserver = null;
	private Socket socket = null;
	private Vector<Couple> SocketVector = null;

	public Thread t1;
	
	public Accepter_connexion (ServerSocket ss, Vector<Couple> sv){
		socketserver = ss;
		SocketVector = sv;
	}
	
	public void run() {
		
		try {
			//Tant que true
			while(true){
			//instruction bloquante : tant qu'il n'y a pas de client on reste bloqué
			socket = socketserver.accept();
			//Il y a un client d'arrivé
			System.out.println("Un client veut se connecter  ");
			
			//On créé un nouveau Thread pour l'authentification.
			t1 = new Thread(new Authentification(socket, SocketVector));
			t1.start();
			
			}
		} 
		//levée par accept()
		catch (IOException e) { 
			System.err.println("Erreur serveur");
		}
		
	}
}