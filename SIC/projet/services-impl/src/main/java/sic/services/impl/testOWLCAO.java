package sic.services.impl;

import org.coode.owlapi.obo.parser.OWLOBOParser;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.*;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import org.semanticweb.owlapi.util.SimpleIRIMapper;

import sic.modele.Produit;
import sic.services.utils.cao.RelDBUtils;
import fr.univ_lyon1.master_info.m2ti.tiw5.services.PnumPnom;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class testOWLCAO {
	   public static void main(String[] args) {
  	     try {
	            // Create the manager that we will use to load ontologies.
	            OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
	            IRI ontologyIRI = IRI.create("http://masterinfo.univ-lyon1.fr/ontologies/StyloCAO");
	            // Create the document IRI for our ontology
	            IRI documentIRI = IRI.create("file:/tmp/StyloCAO.owl");	            
	            // Set up a mapping, which maps the ontology to the document IRI
	            SimpleIRIMapper mapper = new SimpleIRIMapper(ontologyIRI, documentIRI);
	            manager.addIRIMapper(mapper);

	            OWLDataFactory factory = manager.getOWLDataFactory();
	            
	            OWLOntology ontology = manager.createOntology(IRI.create("http://masterinfo.univ-lyon1.fr/ontologies/StyloCAO"));
	            
	            PrefixManager pm = new DefaultPrefixManager("http://masterinfo.univ-lyon1.fr/ontologies/StyloCAO#");
	            
	            //ArrayList<PnumPnom> list = new ArrayList<PnumPnom>();
	            RelDBUtils r = new RelDBUtils();
	    		Statement st=null;
	    		
	    		try {
	    			st = r.getConnection().createStatement();
	    			ResultSet rs=st.executeQuery("SELECT * FROM PRODUIT ORDER BY Pnum");	

	    			HashMap<Long, String> hmProduit = new HashMap<Long, String>();
	    			//HashMap<Long, Long> hmComposition = new HashMap<Long, Long>();
	    			ArrayList<Produit> aP = new ArrayList<Produit>();
	    			
	    			
	                // On parcourt les enregistrements de Produit
	                while (rs.next()){
	                		aP.add(new Produit(Long.parseLong(rs.getString("Pnum")), rs.getString("Pnom").replaceAll(" ", "_").replaceAll("'", "")));
	                		hmProduit.put(Long.parseLong(rs.getString("Pnum")), rs.getString("Pnom").replaceAll(" ", "_").replaceAll("'", ""));
	                }
	                
	                // on parcourt l'arraylist et on requete composition sur le pnum courant
	                for (int i=0;i<aP.size();i++){
	                	OWLClass clsProduit = factory.getOWLClass(":"+aP.get(i).getPnom(), pm);
	                	//OWLClass clsProduit = factory.getOWLClass(IRI.create(ontologyIRI + "#"+aP.get(i).getPnom()));
	                	OWLDeclarationAxiom declarationAxiom = factory.getOWLDeclarationAxiom(clsProduit);
	                	manager.addAxiom(ontology, declarationAxiom);
		    			st = r.getConnection().createStatement();
		    			ResultSet rs2=st.executeQuery("SELECT * FROM COMPOSITION WHERE Pmajeur="+aP.get(i).getPnum()+" ORDER BY Pmineur" );
		                // On parcourt les composants
		                while (rs2.next()){
		                		OWLClass clsComposant =  factory.getOWLClass(":"+hmProduit.get(Long.parseLong(rs2.getString("Pmineur"))), pm);
		                		//OWLClass clsComposant = factory.getOWLClass(IRI.create(ontologyIRI + "#"+hmProduit.get(Long.parseLong(rs2.getString("Pmineur")))));
		        	            OWLDeclarationAxiom declarationAxiom2 = factory.getOWLDeclarationAxiom(clsComposant);
		        	            manager.addAxiom(ontology, declarationAxiom2);
		        	            OWLObjectProperty estComposeDe = manager.getOWLDataFactory().getOWLObjectProperty(IRI.create(ontologyIRI +"#estComposeDe"));
		        	            /*OWLObjectPropertyDomainAxiom oPDA= factory.getOWLObjectPropertyDomainAxiom(estComposeDe, clsProduit);
		        	            manager.addAxiom(ontology, oPDA);
		        	            OWLObjectPropertyRangeAxiom oPRA= factory.getOWLObjectPropertyRangeAxiom(estComposeDe, clsComposant);
		        	            manager.addAxiom(ontology, oPRA);*/
		        	            OWLDeclarationAxiom declarationAxiom3 = factory.getOWLDeclarationAxiom(estComposeDe);
		        	            manager.addAxiom(ontology, declarationAxiom3);
		                }
	                }
	                
	                // Création d'une instance de Stylo : bic
	                OWLNamedIndividual bic = factory.getOWLNamedIndividual(":bic", pm);
	                OWLClassAssertionAxiom classAssertion = factory.getOWLClassAssertionAxiom(factory.getOWLClass(":Stylo",pm), bic);
	                manager.addAxiom(ontology, classAssertion);
	                
	                OWLNamedIndividual bouchonBleu = factory.getOWLNamedIndividual(":bouchonBleu", pm);
	                OWLClassAssertionAxiom classAssertion2 = factory.getOWLClassAssertionAxiom(factory.getOWLClass(":Bouchon",pm), bouchonBleu);
	                manager.addAxiom(ontology, classAssertion2);
	                
	                
	                OWLObjectPropertyAssertionAxiom assertion = factory.getOWLObjectPropertyAssertionAxiom(manager.getOWLDataFactory().getOWLObjectProperty(IRI.create(ontologyIRI +"#estComposeDe")), bic, bouchonBleu);	                
	                AddAxiom addAxiomChange = new AddAxiom(ontology, assertion);
	                manager.applyChange(addAxiomChange);
	                
	                manager.saveOntology(ontology);
	                
	    		} catch (SQLException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}

	    		try {
	    			r.close();
	    		} catch (SQLException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}	        	
	    		/**/
	       
	    		
	    		
	    		////////////////////


	    	            // Ontologies cna have an IRI, which is used to identify the ontology.  You should
	    	            // think of the ontology IRI as the "name" of the ontology.  This IRI frequently
	    	            // resembles a Web address (i.e. http://...), but it is important to realise that
	    	            // the ontology IRI might not necessarily be resolvable.  In other words, we
	    	            // can't necessarily get a document from the URL corresponding to the ontology
	    	            // IRI, which represents the ontology.
	    	            // In order to have a concrete representation of an ontology (e.g. an RDF/XML
	    	            // file), we MAP the ontology IRI to a PHYSICAL URI.  We do this using an IRIMapper

	    	            // Let's create an ontology and name it "http://www.co-ode.org/ontologies/testont.owl"
	    	            // We need to set up a mapping which points to a concrete file where the ontology will
	    	            // be stored. (It's good practice to do this even if we don't intend to save the ontology).
	    	            /*IRI ontologyIRI = IRI.create("http://www.co-ode.org/ontologies/testont.owl");
	    	            // Create the document IRI for our ontology
	    	            IRI documentIRI = IRI.create("file:/tmp/MyOnt.owl");
	    	            // Set up a mapping, which maps the ontology to the document IRI
	    	            SimpleIRIMapper mapper = new SimpleIRIMapper(ontologyIRI, documentIRI);
	    	            manager.addIRIMapper(mapper);

	    	            // Now create the ontology - we use the ontology IRI (not the physical URI)
	    	            OWLOntology ontology = manager.createOntology(ontologyIRI);
	    	            // Now we want to specify that A is a subclass of B.  To do this, we add a subclass
	    	            // axiom.  A subclass axiom is simply an object that specifies that one class is a
	    	            // subclass of another class.
	    	            // We need a data factory to create various object from.  Each manager has a reference
	    	            // to a data factory that we can use.
	    	            OWLDataFactory factory = manager.getOWLDataFactory();
	    	            // Get hold of references to class A and class B.  Note that the ontology does not
	    	            // contain class A or classB, we simply get references to objects from a data factory that represent
	    	            // class A and class B
	    	            OWLClass clsA = factory.getOWLClass(IRI.create(ontologyIRI + "#A"));
	    	            OWLClass clsB = factory.getOWLClass(IRI.create(ontologyIRI + "#B"));
	    	            // Now create the axiom
	    	            OWLAxiom axiom = factory.getOWLSubClassOfAxiom(clsA, clsB);
	    	            // We now add the axiom to the ontology, so that the ontology states that
	    	            // A is a subclass of B.  To do this we create an AddAxiom change object.
	    	            // At this stage neither classes A or B, or the axiom are contained in the ontology. We have to
	    	            // add the axiom to the ontology.
	    	            AddAxiom addAxiom = new AddAxiom(ontology, axiom);
	    	            // We now use the manager to apply the change
	    	            manager.applyChange(addAxiom);

	    	            // The ontology will now contain references to class A and class B - that is, class A and class B
	    	            // are contained within the SIGNATURE of the ontology let's print them out
	    	            for (OWLClass cls : ontology.getClassesInSignature()) {
	    	                System.out.println("Referenced class: " + cls);
	    	            }
	    	            // We should also find that B is an ASSERTED superclass of A
	    	            Set<OWLClassExpression> superClasses = clsA.getSuperClasses(ontology);
	    	            System.out.println("Asserted superclasses of " + clsA + ":");
	    	            for (OWLClassExpression desc : superClasses) {
	    	                System.out.println(desc);
	    	            }

	    	            // Now save the ontology.  The ontology will be saved to the location where
	    	            // we loaded it from, in the default ontology format
	    	            manager.saveOntology(ontology);
	    	            */
	    	        }
	    	        catch (OWLException e) {
	    	            e.printStackTrace();
	    	        }

	    }

}
