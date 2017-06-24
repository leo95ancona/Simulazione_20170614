package it.polito.tdp.artsmia.model;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;





public class ArtSimulazione {
	
	private PriorityQueue<Event> queue;
	private DirectedGraph <Exhibition, DefaultEdge> grafo;
	private Map<Integer,Studente> mappaStudenti;
	private ArtsmiaDAO dao;
	
	public ArtSimulazione(DirectedGraph<Exhibition,DefaultEdge> grafo){
		this.grafo = grafo;
		queue = new PriorityQueue<Event>();
		mappaStudenti = new HashMap<Integer,Studente>();
		dao = new ArtsmiaDAO();
	}
	
	
	
	public void addStudenti(int numeroStudenti, Exhibition ex, int year){
		for (int i=0; i<numeroStudenti; i++){
			
			Studente studente = new Studente(i);
			
			mappaStudenti.put(studente.getId(), studente);
			
			Event e = new Event(studente, ex, year);
			
			queue.add(e);
			System.out.println(e);
		}
	}
	
	public void run(){
		while (!queue.isEmpty()) {
			Event e = queue.poll();
			
			//numero oggetti visti
			int numeroOggetti = dao.contaObject(e.getExhibition());
			
			e.getStudente().setOggetti(numeroOggetti);
			
			//System.out.println(e);
			
			//scelgo la prossimo mostra
			
			for (DefaultEdge de : grafo.outgoingEdgesOf(e.getExhibition())){
				Exhibition ex2 = grafo.getEdgeTarget(de);
				//System.out.println(ex2);
				if (ex2.getBegin() == e.getTime()+1){
					Event e2 = new Event (e.getStudente(), ex2, e.getTime()+1);
					queue.add(e2);
					System.out.println(e2);
				}
			}
			
			
		}
	}

}
