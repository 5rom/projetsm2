package sic.services.impl;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.util.SimpleIRIMapper;

import fr.univ_lyon1.master_info.m2ti.tiw5.services_owl.OWLMapping;

public class OWLMappingService implements OWLMapping {
	
	@Override
	public String mapOWL(String filepath1,String filepath2) {
		// TODO Auto-generated method stub
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
						classeComp = factory.getOWLClass(IRI.create(namespace+type_composant+"#"+nom_composant));
						AddAxiom axiom = new AddAxiom(styloOntology, factory.getOWLSubClassOfAxiom(classeComp, classeAssoc));
						manager.applyChange(axiom);

					}
					
				}
				
				
				
			}
			br.close();
			manager.saveOntology(styloOntology);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
