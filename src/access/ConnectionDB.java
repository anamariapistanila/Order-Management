package access;

import java.sql.*;
import java.util.logging.Logger;

public class ConnectionDB {

	private static final Logger LOGGER = Logger.getLogger(ConnectionDB.class.getName());
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/schooldb?autoReconnect=true&useSSL=false";
	private static final String USER = "root";
	private static final String PASS = "tema3_tp_sql6";

	private static Connection con = null;

	private ConnectionDB() {
		try {
			Class.forName(DRIVER);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Prin aceasta metoda se efectueaza conectarea la baza de date
	 * 
	 * @return un obiect de tipul Connection care asigura suport pentru controlul
	 *         tranzactiilor din memorie catre baza de date
	 */
	public static Connection getConnection() {

		try {
			if (con == null) {
				con = DriverManager.getConnection(DBURL, USER, PASS);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return con;
	}

	/**
	 * Prin aceasta metoda se realizeaza inchiderea conexiunii
	 *
	 */
	public static void close(Connection connection) {
		try {
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
