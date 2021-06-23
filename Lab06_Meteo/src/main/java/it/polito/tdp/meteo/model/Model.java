package it.polito.tdp.meteo.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.meteo.DAO.MeteoDAO;

public class Model {
	
	private final static int COST = 100;
	private final static int NUMERO_GIORNI_CITTA_CONSECUTIVI_MIN = 3;
	private final static int NUMERO_GIORNI_CITTA_MAX = 6;
	private final static int NUMERO_GIORNI_TOTALI = 15;


	private List <Citta> leCitta;
	private List <Citta> parziale;
	private List <Citta> best;
	private MeteoDAO dao;
	
	public Model() {
		dao = new MeteoDAO();
		leCitta = new LinkedList<Citta>();
		this.leCitta = dao.getAllCitta();
	}


	public List<Citta> getLeCitta() {
		return leCitta;
	}


	public Double getUmiditaMedia(Integer m, Citta c) {
		
		c.setRilevamenti(dao.getAllRilevamentiLocalitaMese(m, c));
		
		return dao.getAvgRilevamentiLocalitaMese(m, c);
	}


	public List<Citta> trovaSequenza(int mese) {
		
		parziale = new LinkedList<Citta>();
		this.best = null;
		
		recursive(parziale, 0);
		
		return best;
		
	}	
	
	public void recursive(List<Citta> parziale, int livello) {
		
		if(livello>15)
			return ;
		
		if(livello == 15) {
			if(best == null || calcolaCosto(parziale) < calcolaCosto(best)) {
				best = new ArrayList<Citta>(parziale);
			}
			return;
		}
		
		for(Citta c : leCitta) {
			
			if(aggiuntaLecita(parziale,c)) {	
				parziale.add(c);
				recursive(parziale, livello+1);
				parziale.remove(parziale.size()-1);
			}
			
		}
		
	}	
		
		
	

	public int calcolaCosto(List<Citta> citta) {
		//100 quando cambia citta
		// valore di umidità di ogni giorno
		
		if(citta.isEmpty() || citta == null)
			return 1000;
		
		int costo = 0;
		
		for(int i = 0; i < citta.size(); i++) {
			
			if(i>0 && !citta.get(i).equals(citta.get(i-1)))
				costo += 100;
			
			costo += citta.get(i).getRilevamenti().get(i+1).getUmidita();
		}
		
		return costo;
	}
	
	public boolean aggiuntaLecita(List<Citta> iniziale, Citta daAggiungere) {
		
		//max 6 volte
		int cont = 0;
		for(Citta c : iniziale) {
			if(c.getNome().equals(daAggiungere.getNome()))
				cont++;
		}if(cont >= NUMERO_GIORNI_CITTA_MAX)
			return false;
		
		//prima città che aggiungo va sempre bene
		if(iniziale.isEmpty())
			return true;
		
		//almeno tre giorni nella stessa citta
		//Se daAggiungere è diversa dall'ultima controllo che le ultime tre siano uguali
		if(iniziale.size()<3) {
			return daAggiungere.equals(iniziale.get(iniziale.size()-1));
		}
		if(iniziale.get(iniziale.size()-1).equals(daAggiungere))
			return true;
		if(iniziale.get(iniziale.size()-1).equals(iniziale.get(iniziale.size()-2)) && 
				iniziale.get(iniziale.size()-2).equals(iniziale.get(iniziale.size()-3))) {
			return true;
		}
		
		return false;
	}

}
