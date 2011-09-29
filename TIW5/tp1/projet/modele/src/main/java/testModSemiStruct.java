import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import tiw5.modele.Album;
import tiw5.modele.Piste;

import junit.framework.TestCase;


public class testModSemiStruct extends TestCase {

	public testModSemiStruct() {

	}
	public testModSemiStruct(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		test();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void test() throws JAXBException{
	    
        Album a1 = new Album("The New Eye Scream - The Very Best Of");
        Album a2 = new Album("Wolfmother - Wolfmother");
        Album a3 = new Album("The Parlor Mob - Dogs");

        a1.addPiste(new Piste("Song1", 3));
        a1.addPiste(new Piste("Song2", 4));
	    JAXBContext context = JAXBContext.newInstance(testModSemiStruct.class) ;

	    Marshaller marshaller = context.createMarshaller() ;
	    marshaller.setProperty("jaxb.encoding", "UTF-8") ;
	    marshaller.setProperty("jaxb.formatted.output", true) ;
	    marshaller.marshal(a1, new File("album1.xml")) ;
	}
}
