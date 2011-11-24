package tp5;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;


public class Activator implements BundleActivator {

	public void start(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		Factory f = new Factory(new SiteXMLDAO("test.xml"));
		context.registerService(Factory.class.getName(), f, null);
	}

	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub

	}

}
