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

/**
 * Classe d'implementation du service CAOOWLService
 * Permet la traduction de la base de données relationnelle de produits en ontologie OWL
 * Créée par Sébastien Faure et David Crescence
 * @author David CRESCENCE <crescence.david@gmail.com> et Sébastien FAURE <sebastien.faure3@gmail.com>
 * UCBL M2TI 2011-2012 
 */
public class CAOOWLService implements CAOOWL {

	/**
	 * Methode de traduction de la base en ontologie OWL
	 * @param filepath le chemin du fichier à créer
	 * @return le chemin du fichier créé
	 */
	@Override
	public String parseOWL(String filepath) {
		try {
				// URL hiérarchique
				String targeturl = new String("file:"+filepath);
	            // Création du manager d'ontologie
	            OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
	            IRI ontologyIRI = IRI.create("http://masterinfo.univ-lyon1.fr/ontologies/StyloCAO");
	            // Create de l'IRI du document
	            IRI documentIRI = IRI.create(targeturl);	            
	            // Mapper de l'ontologie avec l'IRI du document
	            SimpleIRIMapper mapper = new SimpleIRIMapper(ontologyIRI, documentIRI);
	            manager.addIRIMapper(mapper);

	            OWLDataFactory factory = manager.getOWLDataFactory();
	            
	            OWLOntology ontology = manager.createOntology(IRI.create("http://masterinfo.univ-lyon1.fr/ontologies/StyloCAO"));
	            
	            PrefixManager pm = new DefaultPrefixManager("http://masterinfo.univ-lyon1.fr/ontologies/StyloCAO#");
	            
	            RelDBUtils r = RelDBUtils.getConnexion();
	    		Statement st=null;
	    		
	    		try {
	    			st = r.getConnection().createStatement();
	    			ResultSet rs=st.executeQuery("SELECT * FROM PRODUIT ORDER BY Pnum");	

	    			HashMap<Long, String> hmProduit = new HashMap<Long, String>();

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
	                	OWLDeclarationAxiom declarationAxiomSsP = factory.getOWLDeclarationAxiom(clsSousProduit);
	                	manager.addAxiom(ontology, declarationAxiomSsP);
	                	
	     	        	OWLAxiom axiom = factory.getOWLSubClassOfAxiom(clsSousProduit, clsProduit);
	     	        	AddAxiom addAxiom = new AddAxiom(ontology, axiom);
	     	        	// On demande au manager d'appliquer les changements
	     	        	manager.applyChange(addAxiom);		                	
	                	
		    			st = r.getConnection().createStatement();
		    			ResultSet rs2=st.executeQuery("SELECT * FROM COMPOSITION WHERE Pmajeur="+aP.get(i).getPnum()+" ORDER BY Pmineur" );
		                // On parcourt les composants
		                while (rs2.next()){
		                		OWLClass clsComposant =  factory.getOWLClass(":"+hmProduit.get(Long.parseLong(rs2.getString("Pmineur"))), pm);
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
	               
	                // On sauvegarde l'ontologie
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
