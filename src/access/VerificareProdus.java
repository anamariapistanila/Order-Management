package access;

import java.sql.Connection;
import java.sql.PreparedStatement;

import model.Product;

public class VerificareProdus {

	private final static String insertStatementString = "Insert into schooldb.product values(?,?,?)";
	private final static String deleteStatementString = "Delete from schooldb.product where produs=?";
	private final static String updateStatementString = " Update schooldb.product set cantitate=? where produs=?";

	/**
	 * Metoda introduce in baza de date un produs(numele produsului, cantitatea si
	 * pretul)
	 * 
	 * @param p reprezinta un obiect de tipul Product care urmeaza a fi adaugat
	 */
	public void addProdus(Product p) {
		Connection db = ConnectionDB.getConnection();
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(insertStatementString);
			statement.setString(1, p.getProdus());
			statement.setInt(2, p.getCantitate());
			statement.setFloat(3, p.getPret());
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metoda sterge din baza de date un produs dupa un anumit nume
	 * 
	 * @param p reprezinta un obiect de tipul String care va fi numele produsului
	 *          luat din fisier, ce urmeaza a fi sters
	 */

	public void deleteProdus(String p) {
		Connection db = ConnectionDB.getConnection();
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(deleteStatementString);
			statement.setString(1, p);
			statement.executeUpdate();
			statement.clearParameters();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metoda modifica cantitatea unui produs din baza de date
	 * 
	 * @param p reprezinta un obiect de tipul Product, adica obiectul caruia dorim
	 *          sa ii facem modificarea
	 */
	public void updateProdus(Product p) {
		Connection db = ConnectionDB.getConnection();
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(updateStatementString);
			statement.setInt(1, p.getCantitate());
			statement.setString(2, p.getProdus());
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
