package access;

import java.sql.Connection;
import java.sql.PreparedStatement;

import model.Client;

public class VerificareClient {

	private final static String deleteStatementString = "Delete from schooldb.client where (nume=? and prenume=?)";
	private final static String insertStatementString = "Insert into schooldb.client(nume,prenume,adresa) values(?,?,?)";

	/**
	 * Metoda insereaza in baza de date un client cu nume, prenume si adresa
	 * 
	 * @param c reprezinta un obiect de tipul Client care urmeaza a fi adaugat
	 */
	public void addClient(Client c) {

		Connection db = ConnectionDB.getConnection();
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(insertStatementString);
			statement.setString(1, c.getNume());
			statement.setString(2, c.getPrenume());
			statement.setString(3, c.getAdresa());
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metoda sterge un client din baza de date cu un anumit nume si prenume
	 * 
	 * @param c reprezinta un obiect de tipul Client care urmeaza a fi sters
	 */
	public void deleteClient(Client c) {
		Connection db = ConnectionDB.getConnection();
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(deleteStatementString);
			statement.setString(1, c.getNume());
			statement.setString(2, c.getPrenume());
			statement.executeUpdate();
			statement.clearParameters();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
