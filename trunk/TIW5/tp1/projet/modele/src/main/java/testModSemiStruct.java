import java.io.File;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.validation.ValidatorHandler;

import junit.framework.TestCase;

import org.slf4j.LoggerFactory;

import tiw5.modele.Album;
import tiw5.modele.Artiste;
import tiw5.modele.Piste;

/**
 * Test unitaire modele semi structure: testModSemiStruct.java
 * Marshalling (object->xml) d'un album en utilisant JAXB
 * @author David CRESCENCE et Sebastien FAURE (M2TI)
 */
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

			// Instanciation d'un album
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
	   
	        // Instanciation du marshaller (objet faisant la generation du XML en fonction de nos annotations dans les classes metiers)
	        Marshaller marshaller = context.createMarshaller();
	   
	        // Formatage de la sortie
	        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	        StringWriter sw = new StringWriter();
	   
	        /*// Vérification que le xml généré est valide par rapport au schéma fourni	(VentesCd.xsd)
	        SchemaFactory sf = SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
	        Schema schema = sf.newSchema(new File("src\\main\\java\\VentesCd.xsd"));
	        marshaller.setSchema(schema);*/
	        
	        // Marshalling de l'objet en XML
	        marshaller.marshal(a2, sw);
	        
	        // A decommenter si on veut mettre dans un fichier xml le resultat
	        //marshaller.marshal(a1, new File("monalbum1.xml"));
	   
	        // Affichage du XML produit
	        logger.info(sw.toString());
	        
			        


	        
	    } catch (Exception ex) {
				logger.error("Erreur",ex);
		        ex.printStackTrace();
		        fail("Exception dans testPersistence()");
	    }
	}
}
