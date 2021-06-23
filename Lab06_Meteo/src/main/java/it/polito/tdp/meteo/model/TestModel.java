package it.polito.tdp.meteo.model;

public class TestModel {

	public static void main(String[] args) {
		
		Model m = new Model();
		
		Citta genova = new Citta("Genova");
		Citta milano = new Citta("Milano");
		Citta torino = new Citta("Torino");
		
		System.out.println(m.getUmiditaMedia(12, torino));
		
		System.out.println(m.trovaSequenza(5));
		

		System.out.println(m.getLeCitta());
	}

}
