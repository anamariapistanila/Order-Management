package model;

/**
 * 
 * @author ANA 
 * Clasa Product populeaza campurile produs, cantitate, pret ale
 * produselor inserate din fisier
 */
public class Product {
	private String produs;
	private int cantitate;
	private float pret;

	public Product(String produs, int cantitate, float pret) {
		super();
		this.produs = produs;
		this.cantitate = cantitate;
		this.pret = pret;
	}

	public String getProdus() {
		return produs;
	}

	public void setProdus(String produs) {
		this.produs = produs;
	}

	public int getCantitate() {
		return cantitate;
	}

	public void setCantitate(int cantitate) {
		this.cantitate = cantitate;
	}

	public float getPret() {
		return pret;
	}

	public void setPret(float pret) {
		this.pret = pret;
	}

}
