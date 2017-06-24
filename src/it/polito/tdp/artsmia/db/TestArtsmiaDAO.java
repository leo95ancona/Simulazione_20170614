package it.polito.tdp.artsmia.db;

import java.util.List;

import it.polito.tdp.artsmia.model.ArtObject;

public class TestArtsmiaDAO {

	public static void main(String[] args) {
		
		ArtsmiaDAO dao = new ArtsmiaDAO() ;
		
		List<ArtObject> objects = dao.listObject() ;
		System.out.println(objects.size());

		System.out.println(dao.getExhibitionsPair(2000).size());
		
		System.out.println(dao.getAllExhibitions(2000).size());
		
	}

}
