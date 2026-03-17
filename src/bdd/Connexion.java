package bdd;

/**
 * Classe Connexion.
 *
 * <p>
 * Cette classe permet d'établir une connexion à la base de données
 * PostgreSQL du projet AMS à l'aide de JDBC.
 * </p>
 *
 * @author Ilissa
 * @version 1.0
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Connexion {

	/**
	 * Établit une connexion à la base de données PostgreSQL.
	 *
	 * @return objet Connection si la connexion réussit, null sinon
	 */
	
    public static Connection connectDB() {

        Connection conn = null;

        try {
            Class.forName("org.postgresql.Driver");

            String url =
              "jdbc:postgresql://pedago01c.univ-avignon.fr:5432/etd";

            Properties props = new Properties();
            props.setProperty("user", "uapv2500288");
            props.setProperty("password", "5p4OO0");

            conn = DriverManager.getConnection(url, props);
            System.out.println("Connexion réussie !");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }
}
