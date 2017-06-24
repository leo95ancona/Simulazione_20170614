package it.polito.tdp.artsmia.model;

public class Studente {
	
	int id ;
	int oggetti;
	
	public Studente(int id) {
		
		this.id = id;
		oggetti=0;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOggetti() {
		return oggetti;
	}
	public void setOggetti(int oggetti) {
		this.oggetti += oggetti;
	}

	@Override
	public String toString() {
		return "Studente [id=" + id + ", oggetti=" + oggetti + "]";
	}
	
	

}
