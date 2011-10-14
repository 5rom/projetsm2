package test;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Hello extends Remote {
	public String sayHello(String nom) throws RemoteException;
}
