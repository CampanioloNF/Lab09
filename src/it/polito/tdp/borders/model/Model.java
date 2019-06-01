package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	private Map<String, Country> idCountryMap;
	private BordersDAO dao;
	
	//per il grafo
	private Graph<Country, DefaultEdge> grafo;
	private List<Country> vertici;

	public Model() {
		
		this.dao = new BordersDAO();
		this.idCountryMap = new HashMap<>();
		dao.loadAllCountries(idCountryMap);
	
	}

	

	public Map<Country, Integer> getCountryDegree(int anno) {
	     
		Map<Country, Integer> result = new TreeMap<>();
		
		creaGrafo(anno);
		
		for(Country vertice : grafo.vertexSet()) {
			result.put(vertice, grafo.degreeOf(vertice));
		}
		
		
		return result;
	}

	private void creaGrafo(int anno) {
		
		
		/*
	    Creo il grafo. Viene creato un nuovo grafo ogni volta si imposta un anno e si clicca sul bottone 'Calcola confini'
		 */
		grafo = new SimpleGraph<Country, DefaultEdge>(DefaultEdge.class);
		
		
		//carico i vertici
		vertici = dao.getVertici(anno, idCountryMap);
		Graphs.addAllVertices(grafo, vertici);
		
		//carico gli archi (non orientati e non pesati)
		dao.getArchi(grafo, anno, idCountryMap);
		
		
	}



	public List<Country> getVertici() {
		
	 if (grafo != null) {
		List<Country> result =  new LinkedList<>(grafo.vertexSet());
		Collections.sort(result);
		return result;
		}
	return null;
		
	}



	public int getNumeroStati() {
		if (grafo != null)
		  return grafo.vertexSet().size();
		return 0;
	}

	public int componenetiConnesse() {
		
		
		
		if (grafo != null) {
	        ConnectivityInspector<Country, DefaultEdge> ci
	                = new ConnectivityInspector<Country, DefaultEdge>(grafo);
	       
	        return ci.connectedSets().size();
	}
		
		return 0;
}
	
}
