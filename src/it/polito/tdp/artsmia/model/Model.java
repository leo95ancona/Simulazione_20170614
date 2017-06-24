package it.polito.tdp.artsmia.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.KosarajuStrongConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedMultigraph;
import org.jgrapht.graph.SimpleDirectedGraph;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;


public class Model {
	
	private List<Integer> years;
	private ArtsmiaDAO dao;
	private DirectedGraph <Exhibition, DefaultEdge> grafo;
	private Map <Integer, Exhibition> mappaExhibitions;
	
	public Model(){
		dao = new ArtsmiaDAO();
		mappaExhibitions = new HashMap <Integer, Exhibition>();
	}
	
	public List<Integer> getYears(){
		years = dao.getAllYears();
		Collections.sort(years);
		return years;
	}
	
	
	public void creaGrafo(Integer year){
		
		grafo = new SimpleDirectedGraph <Exhibition, DefaultEdge>(DefaultEdge.class);
		
		//carico vertici
		
		Graphs.addAllVertices(grafo, dao.getAllExhibitions(year));
		
		for (Exhibition e : grafo.vertexSet()){
			mappaExhibitions.put(e.getId(), e);
		}
		
		//carico gli archi
		
		for (ExhibitionPair ep : dao.getExhibitionsPair(year)){
			
			Exhibition e1 = mappaExhibitions.get(ep.getId1());
			Exhibition e2 = mappaExhibitions.get(ep.getId2());
			
			if (e1!= null && e2!=null && e1!=e2)
				grafo.addEdge(e1, e2);
		}
		
		System.out.println(grafo);
		
	}
	
	public String getMostraPiuCool(){
		
		int best = Integer.MIN_VALUE;
		Exhibition bestEx = null;
		for (Exhibition e : grafo.vertexSet()){
			int cont = dao.contaObject(e);
			
			if (cont>best){
				best=cont;
				bestEx = e;
			}
		}
		
		
		
		return bestEx+", numero opere : "+best+"\n";
		
	}

	public boolean isStronglyConnected() {
		KosarajuStrongConnectivityInspector<Exhibition, DefaultEdge> ksci = new KosarajuStrongConnectivityInspector<Exhibition, DefaultEdge>(grafo);
		return ksci.isStronglyConnected();
	}
	
	public void simula(int numeroStudenti, int year){
		
		ArtSimulazione sim = new ArtSimulazione(grafo);
		
		List <Exhibition> lista = new ArrayList<Exhibition>(mappaExhibitions.values());
		
		Exhibition ex = lista.get(3);
		
		sim.addStudenti(numeroStudenti, ex , year);
		
		sim.run();
		
	}
	
	
	
}
