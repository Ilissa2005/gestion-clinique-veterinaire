package bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;

import data.IData;
import data.fieldType;
import exception.DatabaseException;
import exception.DataException;

/**
 * Classe Gestion
 * Gère les opérations JDBC du projet AMS
 
 */
public class Gestion {

    private Connection connexion;

    /**
     * Constructeur
     */
    public Gestion(Connection connexion) {
        this.connexion = connexion;
    }

    /**
     * Exécute une requête SQL hors INSERT
     * (CREATE, DROP, DELETE, etc.)
     */
    public void executer(String requete) throws DatabaseException {

        PreparedStatement ps = null;

        try {
            ps = connexion.prepareStatement(requete);
            ps.execute();
        }
        catch (SQLException e) {
            throw new DatabaseException(
                "Erreur lors de l'exécution de la requête SQL"
            );
        }
        finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                // ignorée 
            }
        }
    }

    /**
     * Retourne la structure d'une table :
     * <NomColonne, fieldType>
     */
    public HashMap<String, fieldType> structTable(String table, boolean afficher)
            throws DatabaseException {

        HashMap<String, fieldType> structure = new HashMap<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connexion.prepareStatement(
                "SELECT * FROM " + table + " LIMIT 1"
            );
            rs = ps.executeQuery();

            ResultSetMetaData meta = rs.getMetaData();
            int nbColonnes = meta.getColumnCount();

            for (int i = 1; i <= nbColonnes; i++) {

                String nomColonne = meta.getColumnName(i).toUpperCase();
                String typeSql = meta.getColumnTypeName(i).toUpperCase();

                if (typeSql.contains("INT")) {
                    structure.put(nomColonne, fieldType.INT4);
                } else {
                    structure.put(nomColonne, fieldType.VARCHAR);
                }
            }

            if (afficher) {
                System.out.println(structure);
            }
        }
        catch (SQLException e) {
            throw new DatabaseException(
                "Impossible de récupérer la structure de la table " + table
            );
        }
        finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                // ignorée
            }
        }

        return structure;
    }

    /**
     * Affiche le contenu complet d'une table
     */
    public void afficherTable(String table) throws DatabaseException {

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connexion.prepareStatement(
                "SELECT * FROM " + table
            );
            rs = ps.executeQuery();

            ResultSetMetaData meta = rs.getMetaData();
            int nbColonnes = meta.getColumnCount();

            boolean vide = true;

            while (rs.next()) {
                vide = false;
                for (int i = 1; i <= nbColonnes; i++) {
                    System.out.print(rs.getString(i) + " ");
                }
                System.out.println();
            }

            if (vide) {
                System.out.println("Table vide.");
            }
        }
        catch (SQLException e) {
            throw new DatabaseException(
                "Erreur lors de l'affichage de la table " + table
            );
        }
        finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                // ignorée
            }
        }
    }

    /**
     * Insère un objet dans une table SQL.
     *
     * @param data objet à insérer
     * @param table nom de la table
     * @throws DatabaseException si une erreur SQL survient
     * @throws DataException si l'objet est incompatible avec la table
     */
    public void inserer(IData data, String table)
            throws DatabaseException, DataException {

        HashMap<String, fieldType> structureTable =
                structTable(table, false);

        // Vérification de la compatibilité objet / table
        if (!data.check(structureTable)) {
            throw new DataException(
                "La structure de l'objet ne correspond pas à la table"
            );
        }

        PreparedStatement ps = null;

        try {
            ps = connexion.prepareStatement(
                "INSERT INTO " + table + " VALUES (" + data.getValues() + ")"
            );
            ps.execute();
        }
        catch (SQLException e) {
            throw new DatabaseException(
                "Insertion impossible : identifiant déjà existant ou table inexistante"
            );
        }
        finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                // ignorée
            }
        }
    }
}
