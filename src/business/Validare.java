package business;

import access.VerificareClient;
import access.VerificareComanda;
import access.VerificareProdus;
import model.Client;
import model.Order;
import model.Product;

public class Validare {
	/**
	 * Metoda va verifica datele preluate din fisier si va adauga un client in baza
	 * de date apeland metoda din clasa VerificareClient
	 * 
	 * @param nume    reprezinta numele clientului
	 * @param prenume reprezinta prenumele clientului
	 * @param adresa  reprezinta orasul clientului
	 */
	public void adaugaClient(String nume, String prenume, String adresa) {
		boolean valid = true;
		if (nume.equals("") || prenume.equals("") || adresa.equals("")) {
			valid = false;

		}
		if (valid == true) {
			Client c = new Client(nume, prenume, adresa);
			VerificareClient verif = new VerificareClient();

			verif.addClient(c);

		}

	}

	/**
	 * Metoda va verifica datele preluate din fisier si va adauga un produs in baza
	 * de date apeland metoda din clasa VerificareProdus
	 * 
	 * @param produs    reprezinta numele produsului
	 * @param cantitate reprezinta cantitatea produsului
	 * @param pret      reprezinta pretul unui anumit produs
	 */
	public void adaugaProdus(String produs, int cantitate, float pret) {
		boolean valid = true;
		if (produs.equals("") || pret == 0 || cantitate == 0) {
			valid = false;

		}
		if (valid == true) {
			Product p = new Product(produs, cantitate, pret);
			VerificareProdus verif = new VerificareProdus();

			verif.addProdus(p);

		}

	}

	/**
	 * Metoda va verifica datele preluate din fisier si va adauga o comanda in baza
	 * de date apeland metoda din clasa VerificareComanda
	 * 
	 * @param nume      reprezinta numele clientului care faca comanda
	 * @param prenume   reprezinta prenumele clientului care face comanda
	 * @param produs    reprezinta numele produsului comandat
	 * @param cantitate reprezinta cantitatea comandata
	 */

	public void adaugaComanda(String nume, String prenume, String produs, int cantitate) {
		boolean valid = true;
		if (nume.equals("") || prenume.equals("") || produs.equals("") || cantitate == 0) {
			valid = false;

		}
		if (valid == true) {
			Order o = new Order(nume, prenume, produs, cantitate);
			VerificareComanda verif = new VerificareComanda();

			verif.addComanda(o);

		}

	}
}
