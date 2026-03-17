package spa;

import data.IData;
import data.fieldType;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Classe Famille
 * Représente une famille du projet AMS
 */
public class Famille implements IData, Serializable {

    private int idFam;
    private String nom;
    private String adresse;

    private HashMap<String, fieldType> structure;

    /**
     * Constructeur
     */
    public Famille(int idFam, String nom, String adresse) {
        this.idFam = idFam;
        this.nom = nom;
        this.adresse = adresse;

        structure = new HashMap<>();
        getStruct();
    }

    /**
     * Définit la structure de l'objet
     */
    @Override
    public void getStruct() {
        structure.clear();
        structure.put("IDFAM", fieldType.INT4);
        structure.put("NOM", fieldType.VARCHAR);
        structure.put("ADRESSE", fieldType.VARCHAR);
    }

    /**
     * Retourne les valeurs pour l'insertion JDBC
     */
    @Override
    public String getValues() {
        return idFam + ",'" + nom + "','" + adresse + "'";
    }

    /**
     * Retourne la structure de l'objet
     */
    @Override
    public HashMap<String, fieldType> getMap() {
        return structure;
    }

    /**
     * Vérifie la compatibilité entre la table et l'objet
     */
    @Override
    public boolean check(HashMap<String, fieldType> tableStruct) {
        return structure.equals(tableStruct);
    }
}
