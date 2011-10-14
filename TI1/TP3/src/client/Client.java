package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import serveur.Hello;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "Hello";
            System.out.println("Trying to connect to " + args[0]);

            Registry registry = LocateRegistry.getRegistry(args[0]);
			
            Hello hello = (Hello) registry.lookup(name);
            String nom = "toto";
            String resultat = hello.sayHello(nom);
            System.out.println(resultat);
        } catch (RemoteException e) {
            System.err.println("RemoteException in client:");
            e.printStackTrace();
        } catch (NotBoundException e) {
            System.err.println("NotBoundException in client:");
            e.printStackTrace();
        }
	}
}
