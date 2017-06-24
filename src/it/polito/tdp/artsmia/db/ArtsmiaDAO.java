package it.polito.tdp.artsmia.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.artsmia.model.ArtObject;
import it.polito.tdp.artsmia.model.Exhibition;
import it.polito.tdp.artsmia.model.ExhibitionPair;

public class ArtsmiaDAO {

	public List<ArtObject> listObject() {
		
		String sql = "SELECT * from objects";

		List<ArtObject> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				result.add(new ArtObject(res.getInt("object_id"), res.getString("classification"), res.getString("continent"), 
						res.getString("country"), res.getInt("curator_approved"), res.getString("dated"), res.getString("department"), 
						res.getString("medium"), res.getString("nationality"), res.getString("object_name"), res.getInt("restricted"), 
						res.getString("rights_type"), res.getString("role"), res.getString("room"), res.getString("style"), res.getString("title")));
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public List<ExhibitionPair> getExhibitionsPair(Integer year){
		String sql = "SELECT e1.exhibition_id , e1.begin, e1.end, e2.exhibition_id, e2.begin, e2.end " + 
				"FROM exhibitions as e1, exhibitions as e2 " + 
				"WHERE e1.begin = ? " + 
				"AND e1.begin <= e2.begin " + 
				"AND e1.end >= e2.begin";
		
		List<ExhibitionPair> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, year);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				ExhibitionPair ep = new ExhibitionPair(res.getInt("e1.exhibition_id"), res.getInt("e2.exhibition_id"));
				result.add(ep);
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	public List<Integer> getAllYears(){
		String sql = "SELECT distinct begin " + 
				"FROM exhibitions";
		
		List<Integer> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				int year = res.getInt("begin");
				result.add(year);
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	public List<Exhibition> getAllExhibitions(Integer year){
		String sql = "SELECT  * " + 
				"FROM exhibitions " + 
				"WHERE begin = ?;";
		
		
		List<Exhibition> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, year);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Exhibition e = new Exhibition(res.getInt("exhibition_id"), res.getString("exhibition_department"), res.getString("exhibition_title"), res.getInt("begin"), res.getInt("end"));
				result.add(e);
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public Integer contaObject(Exhibition ex){
		String sql = "SELECT  count(*) " + 
				"FROM exhibition_objects " + 
				"WHERE exhibition_id = ?";
		
		int result=0;

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, ex.getId());
			ResultSet res = st.executeQuery();

			res.next();
			result = res.getInt("count(*)");

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	
}
