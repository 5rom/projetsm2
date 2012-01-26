package sic.services.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.model.PrefixManager;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import org.semanticweb.owlapi.util.SimpleIRIMapper;

import sic.services.utils.fao.ExpressDBUtils;

import fr.univ_lyon1.master_info.m2ti.tiw5.services_fao.owl.FAOOwl;

/**
 * Classe d'implementation du service FAOOwlService
 * Permet de traduire un fichier EXPRESS de produits en un fichier d'ontologie OWL 
 * Créée par Sébastien Faure et David Crescence
 * @author David CRESCENCE <crescence.david@gmail.com> et Sébastien FAURE <sebastien.faure3@gmail.com>
 * UCBL M2TI 2011-2012 
 */
public class FAOOwlService implements FAOOwl{

	/**
	 * Methode de traduction du fichier EXPRESS en ontologie OWL
	 * @param filepath le chemin du fichier EXPRESS à traduire
	 * @return le chemin du fichier OWL créé
	 */
	@Override
	public String parseOWL(String filepath) {
		String targeturl = new String("file:/tmp/StyloFAO.owl");
		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        IRI ontologyIRI = IRI.create("http://masterinfo.univ-lyon1.fr/ontologies/StyloFAO");

        IRI documentIRI = IRI.create(targeturl);	            

        SimpleIRIMapper mapper = new SimpleIRIMapper(ontologyIRI, documentIRI);
        manager.addIRIMapper(mapper);

        OWLDataFactory factory = manager.getOWLDataFactory();
        
        try {
			OWLOntology ontology = manager.createOntology(IRI.create("http://masterinfo.univ-lyon1.fr/ontologies/StyloFAO"));
			PrefixManager pm = new DefaultPrefixManager("http://masterinfo.univ-lyon1.fr/ontologies/StyloFAO#");
	        
	        HashMap<String,String> produits = new HashMap<String,String>();
	    	HashMap<String,String[]> relations_line = new HashMap<String,String[]>();
	    	HashMap<String,String> line_name = new HashMap<String,String>();
	        
	        ExpressDBUtils parser = ExpressDBUtils.getParser(filepath, produits, relations_line, line_name);
	        parser.parseFichier();
	        
	        OWLClass clsProduit = factory.getOWLClass(":ProduitFAO", pm);
	        OWLDeclarationAxiom declarationAxiom = factory.getOWLDeclarationAxiom(clsProduit);
	    	manager.addAxiom(ontology, declarationAxiom);
	    	
	    	Set<Map.Entry<String,String>>listeproduits = produits.entrySet();
	    	Iterator<Map.Entry<String,String>> it = listeproduits.iterator();
	    	while(it.hasNext()){
	    		Map.Entry<String,String> produit = it.next();
	    		OWLClass clsSousProduit = factory.getOWLClass(":"+produit.getValue(), pm);
            	OWLDeclarationAxiom declarationAxiomSsP = factory.getOWLDeclarationAxiom(clsSousProduit);
            	manager.addAxiom(ontology, declarationAxiomSsP);
            	
	        	OWLAxiom axiom = factory.getOWLSubClassOfAxiom(clsSousProduit, clsProduit);
	        	AddAxiom addAxiom = new AddAxiom(ontology, axiom);
	        	// We now use the manager to apply the change
	        	manager.applyChange(addAxiom);
	        	String[] compositions = new String[0];
	        	
	        	String key = new String("");
	        	Set<Map.Entry<String,String>>keys = line_name.entrySet();
		    	Iterator<Map.Entry<String,String>> it1 = keys.iterator();
		    	while(it1.hasNext()){
		    		Map.Entry<String,String> search_key = it1.next();
		    		if (search_key.getValue().equals(produit.getKey())){
		    			key = search_key.getKey();
		    			compositions = relations_line.get(key);
		    		}
		    		
		    	}
	        	
	        	
	        	if(compositions!=null){
	        		for (int j=0;j<compositions.length;j++){
	        			OWLClass clsComposant =  factory.getOWLClass(":"+produits.get(line_name.get(compositions[j])), pm);
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
	        	
	    	}
	    	manager.saveOntology(ontology);
	    	
	    	
		} catch (OWLOntologyCreationException e) {
			//e.printStackTrace();
		} catch (OWLOntologyStorageException e) {
			e.printStackTrace();
		}
        
        
        
        return targeturl;
	}

}
