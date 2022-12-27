package model;

/**
 * 
 * @author ANA
 * Clasa Order populeaza campurile nume, prenume, produs, cantitate
 *  ale clientilor care efectueaza comenzi din fisier
 */
public class Order {
	private String nume;
	private String prenume;
	private String produs;
	private int cantitate;

	public Order(String nume, String prenume, String produs, int cantitate) {
		super();
		this.nume = nume;
		this.prenume = prenume;
		this.produs = produs;
		this.cantitate = cantitate;

	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getPrenume() {
		return prenume;
	}

	public void setPrenume(String prenume) {
		this.prenume = prenume;
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

}
