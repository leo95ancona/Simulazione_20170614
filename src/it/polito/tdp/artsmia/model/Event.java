package it.polito.tdp.artsmia.model;



public class Event implements Comparable<Event>{

	private Studente studente;
	private Exhibition exhibition;
	private int time;
	
	
	public Event(Studente studente, Exhibition exhibition, int time) {
		super();
		this.studente = studente;
		this.exhibition = exhibition;
		this.time = time;
	}


	public Studente getStudente() {
		return studente;
	}


	public void setStudente(Studente studente) {
		this.studente = studente;
	}


	public Exhibition getExhibition() {
		return exhibition;
	}


	public void setExhibition(Exhibition exhibition) {
		this.exhibition = exhibition;
	}


	public int getTime() {
		return time;
	}


	public void setTime(int time) {
		this.time = time;
	}


	@Override
	public int compareTo(Event o) {
		
		return this.getTime()-o.getTime();
	}


	@Override
	public String toString() {
		return "Event [studente=" + studente + ", exhibition=" + exhibition + ", time=" + time + "]";
	}
	
	
	
}
