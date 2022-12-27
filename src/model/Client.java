package model;

/**
 * 
 * @author ANA 
 * Clasa Client populeaza campurile nume, prenume si adresa ale
 * clientilor inserati din fisier
 */
public class Client {
	private String nume;
	private String prenume;
	private String adresa;

	public Client(String nume, String prenume, String adresa) {
		super();
		this.nume = nume;
		this.prenume = prenume;
		this.adresa = adresa;

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

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

}
