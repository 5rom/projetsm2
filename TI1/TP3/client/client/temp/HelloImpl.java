package test;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloImpl extends UnicastRemoteObject implements Hello {
	private static final long serialVersionUID = 1L;

	protected HelloImpl() throws RemoteException {
		super();
	}

	public String sayHello(String nom) throws RemoteException{
		return "Hello " + nom + "!!!";
	}
}
