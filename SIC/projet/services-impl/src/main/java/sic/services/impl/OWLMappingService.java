package sic.services.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Set;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.reasoner.Node;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import org.semanticweb.owlapi.util.SimpleIRIMapper;

import com.clarkparsia.pellet.owlapiv3.PelletReasonerFactory;

import fr.univ_lyon1.master_info.m2ti.tiw5.services_owl.OWLMapping;

/**
 * Classe d'implementation du service OWLMappingService
 * Permet de faire le mapping entre deux ontologies de produits
 * Créée par Sébastien Faure et David Crescence
 * @author David CRESCENCE <crescence.david@gmail.com> et Sébastien FAURE <sebastien.faure3@gmail.com>
 * UCBL M2TI 2011-2012 
 */
public class OWLMappingService implements OWLMapping {	

	/**
	 * Le raisonneur pellet 
	 */
	OWLReasoner reasoner;	
	
	/**
	 * Les des classes inférées
	 */
	String listeClassesInferees = "";			
	
	/**
	 * Préfixe
	 */
	DefaultPrefixManager pm;	
	
	/**
	 * Methode de mapping de deux ontologies
	 * @param filepath1 le chemin du fichier de description du mapping (equivalence, composition) entre ontologies
	 * @param filepath2 le chemin du fichier OWL à créer
	 * @return le résultat du mapping obtenu a partir du raisonneur Pellet
	 */		
	@Override
	public String mapOWL(String filepath1, String filepath2, String fileCAOOWL,
			String fileFAOOWL) {
		


		
		/** La racine de la hiérarchie de classe inférée */
		Node<OWLClass> topNode;
		
		/** La liste des classes listeClassesInsatisfiabless */
		String listeClassesInsatisfiables = "";
		
	
		
		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		try {
			String namespace = "http://masterinfo.univ-lyon1.fr/ontologies/Stylo";
			IRI ontologyIRI = IRI.create(namespace);
			IRI documentIRI = IRI.create("file:"+filepath2);
			FileInputStream in = new FileInputStream(filepath1);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			SimpleIRIMapper mapper = new SimpleIRIMapper(ontologyIRI,documentIRI);
			manager.addIRIMapper(mapper);
			OWLOntology styloOntology = manager.createOntology(ontologyIRI);
			
			OWLDataFactory factory = manager.getOWLDataFactory();
			
			OWLClass classeAssoc = null;
			OWLClass classeComp = null;
			String strLine;
			String compose = new String();
			String composant = new String();
			while ((strLine = br.readLine()) != null)   {
				String[] literal = strLine.split("=");
					compose = literal[0];
					composant = literal[1];
				String type_compose = compose.split(":")[0].replace(" ", "");
				String nom_compose = compose.split(":")[1].replace(" ", "");
				String[] nbcomposant = composant.split("\\+");
				if (nbcomposant.length < 2){
					String[] equivalent = composant.split(":");
					String type_equivalent = equivalent[0].replace(" ", "");
					String nom_equivalent = equivalent[1].replace(" ", "");
					classeAssoc = factory.getOWLClass(IRI.create(namespace+type_compose+"#"+nom_compose));
					classeComp = factory.getOWLClass(IRI.create(namespace+type_equivalent+"#"+nom_equivalent));
					AddAxiom axiom = new AddAxiom(styloOntology, factory.getOWLEquivalentClassesAxiom(classeAssoc, classeComp));
					manager.applyChange(axiom);
					
				}
				else {
					for(int i=0;i<nbcomposant.length;i++){
						String type_composant = nbcomposant[i].split(":")[0].replace(" ", "");
						String nom_composant = nbcomposant[i].split(":")[1].replace(" ", "");
						classeAssoc = factory.getOWLClass(IRI.create(namespace+type_compose+"#"+nom_compose));
						/*classeComp = factory.getOWLClass(IRI.create(namespace+type_composant+"#"+nom_composant));
						AddAxiom axiom = new AddAxiom(styloOntology, factory.getOWLSubClassOfAxiom(classeComp, classeAssoc));
						manager.applyChange(axiom);*/
						
						//
						classeComp =  factory.getOWLClass(IRI.create(namespace+type_composant+"#"+nom_composant));
        	            OWLDeclarationAxiom declarationAxiom2 = factory.getOWLDeclarationAxiom(classeComp);
        	            manager.addAxiom(styloOntology, declarationAxiom2);
        	            
        	            //Propriete de composition, utiliser Restriction
        	            OWLObjectProperty estComposeDe = factory.getOWLObjectProperty(IRI.create(ontologyIRI + "#estComposeDe"));
        	            // Subpropertyof
        	            OWLClassExpression hasPartSomeProduit = factory.getOWLObjectSomeValuesFrom(estComposeDe, classeComp);
        	            OWLSubClassOfAxiom ax = factory.getOWLSubClassOfAxiom(classeAssoc, hasPartSomeProduit);
        	            
        	            AddAxiom addAx = new AddAxiom(styloOntology, ax);
        	            manager.applyChange(addAx);

					}
					
				}
				
				
				
			}
			br.close();
			manager.saveOntology(styloOntology);
			/*try{*/
			// On a maintenant notre fichier de règles de correspondance
			// Maintenant, on va merger les deux ontologies CAO et FAO puis tester avec nos règles s'i y a des problemes de correspondance
			String CAO_IRI ="file:"+fileCAOOWL;
			String FAO_IRI ="file:"+fileFAOOWL;

			
	    	listeClassesInsatisfiables = "";
	    	listeClassesInferees = "";
	    	
	        try {

	            OWLOntologyManager manager2 = OWLManager.createOWLOntologyManager();

	            /**
	             * On charge les trois ontologies : StyloCAO, StyloFAO et l'ontologie contenant les règles du mapping précédemment obtenues
	             */
	            OWLOntology ontologieCAO = manager2.loadOntologyFromOntologyDocument(IRI.create(CAO_IRI));
	            OWLOntology ontologieFAO = manager2.loadOntologyFromOntologyDocument(IRI.create(FAO_IRI));	            
	            OWLOntology ontologie = manager2.loadOntologyFromOntologyDocument(IRI.create("file:"+filepath2));
				
	            /*
	            // debug : affichage des axiomes de l'ontologie
	            for (OWLAxiom ax : ontologie.getAxioms()) {
					System.out.println(ax);
				}
				*/
	            
	            pm = new DefaultPrefixManager(""+ontologie.getOntologyID());
	            
	            PelletReasonerFactory pelletFactory = PelletReasonerFactory.getInstance();
	            
	            reasoner = pelletFactory.createReasoner(ontologie);
	            
	            
	            reasoner.precomputeInferences();
	   
	            Node<OWLClass> bottomNode = reasoner.getUnsatisfiableClasses();
	            Set<OWLClass> unsatisfiable = bottomNode.getEntitiesMinusBottom();
	            //OWLDataFactory fac = manager2.getOWLDataFactory();
	            //OWLClass stylo = fac.getOWLClass(IRI.create("http://masterinfo.univ-lyon1.fr/ontologies/StyloCAO#Corps"));
	            //NodeSet<OWLClass> subClses = reasoner.getSubClasses(stylo, true);
	
	            // The reasoner returns a NodeSet, which represents a set of Nodes.
	            // Each node in the set represents a subclass of vegetarian pizza. A node of classes contains classes,
	            // where each class in the node is equivalent. For example, if we asked for the
	            // subclasses of some class A and got back a NodeSet containing two nodes {B, C} and {D}, then A would have
	            // two proper subclasses. One of these subclasses would be equivalent to the class D, and the other would
	            // be the class that is equivalent to class B and class C.
	            
            	// In this case, we don't particularly care about the equivalences, so we will flatten this
            	// set of sets and print the result
            	/*Set<OWLClass> clses = subClses.getFlattened();
            	System.out.println("Subclasses of stylo: ");
	            for(OWLClass cls : clses) {
	            	System.out.println(" " + cls);
	            }
	            System.out.println("\n");*/
	            
	            listeClassesInsatisfiables += "La liste des classes insatisfiables : \n";
	            
	            if (!unsatisfiable.isEmpty())
	            {
	            	listeClassesInsatisfiables += "PROBLEME DE MAPPING : Les classes insatisfiables : \n";
	                
	                for(OWLClass cls : unsatisfiable)
	                	listeClassesInsatisfiables += "    " + cls;
	            }
	            else listeClassesInsatisfiables += "Pas de classe insatisfiable. LA CORRESPONDANCE EST BONNE! PAS DE PROBLEME DE MAPPING";  
	            
	            listeClassesInsatisfiables +="\n";

	            topNode = reasoner.getTopClassNode();
	            
	            // On construit l'affichage
	            toStringArbre(topNode,0);

	            
	        }
	        catch(UnsupportedOperationException exception) {
	            System.out.println("Erreur raisonneur.");
	        }
	        catch (OWLOntologyCreationException e) {
	            System.out.println(e.getMessage());
	        }			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listeClassesInsatisfiables+"\n"+"Les classes inferees: "+listeClassesInferees;
	}

	/**
	 * On utilise le tutoriel de Stanford University pour l'affichage des classes inférées : http://smi-protege.stanford.edu/repos/protege/web-protege/branches/owlapi3/owlapi-mt/examples/src/main/java/org/coode/owlapi/examples/Example8.java
	 */
	
	//Affichage de l'arbre
    private void toStringArbre(Node<OWLClass> parent, int depth) {
    	
        // We don't want to print out the bottom node (containing owl:Nothing and unsatisfiable classes)
        // because this would appear as a leaf node everywhere
    	
        if(parent.isBottomNode()) {
            return;
        }
        
        // Print an indent to denote parent-child relationships
        printIndent(depth);
        
        // Now print the node (containing the child classes)
        printNode(parent);
        
        for(Node<OWLClass> child : reasoner.getSubClasses(parent.getRepresentativeElement(), true)) {
            // Recurse to do the children.  Note that we don't have to worry about cycles as there
            // are non in the inferred class hierarchy graph - a cycle gets collapsed into a single
            // node since each class in the cycle is equivalent.
            toStringArbre(child, depth + 1);
        }
        
    }

    // Indentation de l'affichage
    private void printIndent(int depth) {
        for(int i = 0; i < depth; i++) {
        	listeClassesInferees += "    ";
        }
    }

    // Affichage d'un noeud
    private void printNode(Node<OWLClass> node) {

    	listeClassesInferees += "{";
        for(Iterator<OWLClass> it = node.getEntities().iterator(); it.hasNext(); ) {
            OWLClass cls = it.next();

            listeClassesInferees += pm.getShortForm(cls) + "";
            if (it.hasNext()) {
            	listeClassesInferees += "  ";
            }
        }
        listeClassesInferees += "}\n";
    }

}
