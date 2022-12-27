package access;


import java.sql.Connection;
import java.sql.PreparedStatement;


import model.Order;


public class VerificareComanda {

	private final static String StatementString = "Insert schooldb.order(nume,prenume,produs,cantitate) values(?,?,?,?)";
/**
 * Metoda introduce in baza de date o comanda	
 * @param o reprezinta un obiect de tipul Order care va fi adaugat
 */

	public void addComanda(Order o) {
		Connection db = ConnectionDB.getConnection();
		PreparedStatement statement = null;
		try {
			statement = db.prepareStatement(StatementString);
			statement.setString(1, o.getNume());
			statement.setString(2, o.getPrenume());
			statement.setString(3, o.getProdus());
			statement.setInt(4, o.getCantitate());
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	
}
