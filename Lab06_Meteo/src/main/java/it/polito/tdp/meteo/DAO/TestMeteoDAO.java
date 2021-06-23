package it.polito.tdp.meteo.DAO;

import java.util.List;

import it.polito.tdp.meteo.model.Citta;
import it.polito.tdp.meteo.model.Rilevamento;

public class TestMeteoDAO {

	public static void main(String[] args) {
		
		Citta genova = new Citta("Genova");
		Citta milano = new Citta("Milano");
		Citta torino = new Citta("Torino");
		
		MeteoDAO dao = new MeteoDAO();

		List<Rilevamento> list = dao.getAllRilevamenti();

		// STAMPA: localita, giorno, mese, anno, umidita (%)
		for (Rilevamento r : list) {
		//	System.out.format("%-10s %2td/%2$2tm/%2$4tY %3d%%\n", r.getLocalita(), r.getData(), r.getUmidita());
		}
		
		System.out.println(dao.getAllRilevamentiLocalitaMese(1, genova));
		System.out.println(dao.getAvgRilevamentiLocalitaMese(1, genova));
		
		System.out.println(dao.getAllRilevamentiLocalitaMese(5, milano));
		System.out.println(dao.getAvgRilevamentiLocalitaMese(5, milano));
		
		System.out.println(dao.getAllRilevamentiLocalitaMese(5, torino));
		System.out.println(dao.getAvgRilevamentiLocalitaMese(5, torino));
		
		for(Citta c : dao.getAllCitta())
			System.out.println(c);

	}

}
