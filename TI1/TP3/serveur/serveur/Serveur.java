package serveur;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Classe serveur
 */
public class Serveur {

	/**
	 * Serveur RMI qui lance le servant et le bind dans l'annuaire JNDI
	 */
	public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        System.out.println("Security manager : " + System.getSecurityManager().toString());

        try {
            String name = "Hello";
            Hello hello = new HelloImpl();

			// Création d'un stub
			UnicastRemoteObject.unexportObject(hello, false);
            Hello stub = (Hello) UnicastRemoteObject.exportObject(hello, 0);

			// Ajout du stub dans l'annuaire JNDI
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);

            System.out.println("HelloImpl bound");
        } catch (RemoteException e) {
            System.err.println("RemoteException during HelloImpl binding:");
            e.printStackTrace();
        }
    }
}