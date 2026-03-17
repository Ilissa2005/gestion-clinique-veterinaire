package spa;

import data.IData;
import data.fieldType;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Classe Benevole
 * Représente un bénévole du projet AMS
 */
public class Benevole implements IData, Serializable {

    private int idPers;
    private String nom;
    private String prenom;

    private HashMap<String, fieldType> structure;

    /**
     * Constructeur
     */
    public Benevole(int idPers, String nom, String prenom) {
        this.idPers = idPers;
        this.nom = nom;
        this.prenom = prenom;

        structure = new HashMap<>();
        getStruct();
    }

    /**
     * Définit la structure de l'objet
     */
    @Override
    public void getStruct() {
        structure.clear();
        structure.put("IDPERS", fieldType.INT4);
        structure.put("NOM", fieldType.VARCHAR);
        structure.put("PRENOM", fieldType.VARCHAR);
    }

    /**
     * Retourne les valeurs pour l'insertion JDBC
     */
    @Override
    public String getValues() {
        return idPers + ",'" + nom + "','" + prenom + "'";
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
