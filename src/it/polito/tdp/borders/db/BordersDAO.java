package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;

public class BordersDAO {

	public void loadAllCountries(Map<String, Country> idCountryMap) {

		String sql = "SELECT ccode, StateAbb, StateNme FROM country ORDER BY StateAbb";
		
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				if(!idCountryMap.containsKey(rs.getString("StateAbb"))) {
				
					Country c = new Country(rs.getInt("ccode"), rs.getString("StateAbb"), rs.getString("StateNme"));
			     	idCountryMap.put(rs.getString("StateAbb"), c);
			    	
			}
		}
			
			conn.close();
			

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public List<Border> getCountryPairs(int anno) {

		System.out.println("TODO -- BordersDAO -- getCountryPairs(int anno)");
		return new ArrayList<Border>();
	}

	public List<Country> getVertici(int anno, Map<String, Country> idCountryMap) {
         
		String sql = "SELECT state1ab FROM contiguity c WHERE  c.YEAR<= ? AND conttype = 1 GROUP BY state1ab ";
		List <Country> result = new ArrayList<>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				if(idCountryMap.containsKey(rs.getString("state1ab"))) 
				    result.add(idCountryMap.get(rs.getString("state1ab")));
			    	
			
		}
			
		
			conn.close();
			return result;
			

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public void getArchi(Graph<Country, DefaultEdge> grafo, int anno, Map<String, Country> idCountryMap) {
	
		String sql = "SELECT state1ab, state2ab FROM contiguity c WHERE  c.YEAR<= ? AND conttype = 1 AND  state2ab > state1ab ORDER BY state1ab  ";
		
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				if(idCountryMap.containsKey(rs.getString("state1ab")) && idCountryMap.containsKey(rs.getString("state2ab")) ) 
				   grafo.addEdge(idCountryMap.get(rs.getString("state1ab")), idCountryMap.get(rs.getString("state2ab")));
			    	
			
		}
			
		
			conn.close();
			

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}
}
