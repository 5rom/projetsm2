package tp5.serveur;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import tp5.Factory;


public class Activator implements BundleActivator {
    
	@Override
	public void start(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("test_0");
		ServiceReference sr = context.getServiceReference(Factory.class.getName());
		if(sr!=null)
			System.out.println("test_1");
		Factory f = (Factory) context.getService(sr);
		System.out.println("test_2");
        context.registerService(Serveur.class.getName(), new ServeurImpl(f), null);
	}
    
	
	
	@Override
	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
        
	}
    
}
