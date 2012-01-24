package sic.services.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;

import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.model.PrefixManager;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import org.semanticweb.owlapi.util.SimpleIRIMapper;


import sic.modele.Produit;
import sic.services.utils.cao.RelDBUtils;
import fr.univ_lyon1.master_info.m2ti.tiw5.services_cao.owl.CAOOWL;

public class CAOOWLService implements CAOOWL {

	@Override
	public String parseOWL(String filepath) {
		try {
				String targeturl = new String("file:"+filepath);
	            // Create the manager that we will use to load ontologies.
	            OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
	            IRI ontologyIRI = IRI.create("http://masterinfo.univ-lyon1.fr/ontologies/StyloCAO");
	            // Create the document IRI for our ontology
	            IRI documentIRI = IRI.create(targeturl);	            
	            // Set up a mapping, which maps the ontology to the document IRI
	            SimpleIRIMapper mapper = new SimpleIRIMapper(ontologyIRI, documentIRI);
	            manager.addIRIMapper(mapper);

	            OWLDataFactory factory = manager.getOWLDataFactory();
	            
	            OWLOntology ontology = manager.createOntology(IRI.create("http://masterinfo.univ-lyon1.fr/ontologies/StyloCAO"));
	            
	            PrefixManager pm = new DefaultPrefixManager("http://masterinfo.univ-lyon1.fr/ontologies/StyloCAO#");
	            
	            //ArrayList<PnumPnom> list = new ArrayList<PnumPnom>();
	            RelDBUtils r = RelDBUtils.getConnexion();
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
	                
	                
	                OWLClass clsProduit = factory.getOWLClass(":ProduitCAO", pm);
	                OWLDeclarationAxiom declarationAxiom = factory.getOWLDeclarationAxiom(clsProduit);
             	manager.addAxiom(ontology, declarationAxiom);
             	// on parcourt l'arraylist et on requete composition sur le pnum courant
	                for (int i=0;i<aP.size();i++){
	                	OWLClass clsSousProduit = factory.getOWLClass(":"+aP.get(i).getPnom(), pm);
	                	//OWLClass clsProduit = factory.getOWLClass(IRI.create(ontologyIRI + "#"+aP.get(i).getPnom()));
	                	OWLDeclarationAxiom declarationAxiomSsP = factory.getOWLDeclarationAxiom(clsSousProduit);
	                	manager.addAxiom(ontology, declarationAxiomSsP);
	                	
     	        	OWLAxiom axiom = factory.getOWLSubClassOfAxiom(clsSousProduit, clsProduit);
     	        	AddAxiom addAxiom = new AddAxiom(ontology, axiom);
     	        	// We now use the manager to apply the change
     	        	manager.applyChange(addAxiom);		                	
	                	
		    			st = r.getConnection().createStatement();
		    			ResultSet rs2=st.executeQuery("SELECT * FROM COMPOSITION WHERE Pmajeur="+aP.get(i).getPnum()+" ORDER BY Pmineur" );
		                // On parcourt les composants
		                while (rs2.next()){
		                		OWLClass clsComposant =  factory.getOWLClass(":"+hmProduit.get(Long.parseLong(rs2.getString("Pmineur"))), pm);
		                		//OWLClass clsComposant = factory.getOWLClass(IRI.create(ontologyIRI + "#"+hmProduit.get(Long.parseLong(rs2.getString("Pmineur")))));
		        	            OWLDeclarationAxiom declarationAxiom2 = factory.getOWLDeclarationAxiom(clsComposant);
		        	            manager.addAxiom(ontology, declarationAxiom2);

		        	            
		        	            //Propriete de composition, utiliser Restriction
		        	            OWLObjectProperty estComposeDe = factory.getOWLObjectProperty(IRI.create(ontologyIRI +"#estComposeDe"));
		        	            // Subpropertyof
		        	            OWLClassExpression hasPartSomeProduit = factory.getOWLObjectSomeValuesFrom(estComposeDe, clsComposant);
		        	            OWLSubClassOfAxiom ax = factory.getOWLSubClassOfAxiom(clsSousProduit, hasPartSomeProduit);
		        	            
		        	            AddAxiom addAx = new AddAxiom(ontology, ax);
		        	            manager.applyChange(addAx);
		                }
	                }
	               
	                manager.saveOntology(ontology);

	                return filepath;
	    		} catch (SQLException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}

	    	        }
	    	        catch (Exception e) {
	    	            e.printStackTrace();
	    	        }

	    	        return filepath;

	}

}
