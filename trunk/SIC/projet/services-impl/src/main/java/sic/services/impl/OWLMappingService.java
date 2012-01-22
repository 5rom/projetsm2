package sic.services.impl;

import java.io.File;
import java.util.List;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

import fr.univ_lyon1.master_info.m2ti.tiw5.services_owl.OWLMapping;

public class OWLMappingService implements OWLMapping {

	//@Override ???
	public String mapOWL(String filepath1, String filepath2) {
		// TODO Auto-generated method stub
		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		File file1 = new File("StyloFAO.owl");
		File file2 = new File("StyloCAO.owl");
		try {
			OWLOntology styloFAO = manager.loadOntologyFromOntologyDocument(file1);
			OWLOntology styloCAO = manager.loadOntologyFromOntologyDocument(file2);
			
		} catch (OWLOntologyCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String mapOWL(String filepath1) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
