import java.io.File;
import java.io.StringWriter;

import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import tiw5.modele.*;

import junit.framework.TestCase;


public class testModSemiStruct extends TestCase {

    final static org.slf4j.Logger logger = LoggerFactory.getLogger(testModSemiStruct.class);	
	
	public testModSemiStruct() {

	}
	public testModSemiStruct(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void test() throws JAXBException{
		try{

			Album a2 = new Album("The Parlor Mob - Dogs",true);
    
            Piste p3 = new Piste("How It's Going To Be", 3, true);            
            Piste p4 = new Piste("Into The Sun", 4, true);            
            Piste p5 = new Piste("Fall Back", 3, true);            
            Piste p6 = new Piste("Pratice In Patience", 4, true);         



            Artiste ar2=new Artiste("Marc", "Melicia", true);
            Artiste ar3=new Artiste("Sam", "Bey", true);
            Artiste ar4=new Artiste("Dave", "Rosen", true);
            Artiste ar5=new Artiste("Anthony", "Chick", true);
            Artiste ar6=new Artiste("Paul", "Ritchie", true);
            

            a2.addArtiste(ar2);
            a2.addArtiste(ar3);
            a2.addArtiste(ar4);
            a2.addArtiste(ar5);
            a2.addArtiste(ar6);
            
            p3.addArtiste(ar2);
            p3.addArtiste(ar3);
            
            p4.addArtiste(ar4);
            p5.addArtiste(ar5);
            p6.addArtiste(ar6);

            p6.addArtiste(ar5);
            
            a2.addPiste(p3);
            a2.addPiste(p4);
            a2.addPiste(p5);
            a2.addPiste(p6);

	        // Recuperation du contexte JAXB pour l'album créé
	        JAXBContext context = JAXBContext.newInstance(a2.getClass());
	   
	        // To convert ex to XML, I need a JAXB Marshaller
	        Marshaller marshaller = context.createMarshaller();
	   
	        // Make the output pretty
	        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	        StringWriter sw = new StringWriter();
	   
	        // marshall the object to XML
	        marshaller.marshal(a2, sw);
	        //marshaller.marshal(a1, new File("monalbum1.xml"));
	   
	        // print it out for this example
	        logger.info(sw.toString());
	        
			        
		/**
		 * Vérification que le xml généré est valide par rapport au schéma fourni (todo)	        
		 */
	        //SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	        //Schema schema = factory.newSchema(new File("src\\main\\java\\VentesCd.xsd"));
	        
	        /*
	        SchemaFactory sf = SchemaFactory.newInstance(
	        	    javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
	        Schema schema = sf.newSchema(new File("src\\main\\java\\VentesCd.xsd"));
        	Unmarshaller unmarshaller = context.createUnmarshaller();
        	unmarshaller.setSchema(schema);
        	Album a2  = (Album) unmarshaller.unmarshal(new File("monalbum1.xml"));
        	logger.info("Marshmallow : "+ a2.getTitre());
        	*/
	        
	    } catch (Exception ex) {
				logger.error("Erreur",ex);
		        ex.printStackTrace();
		        fail("Exception dans testPersistence()");
	    }
	}
}
