package spa;

import data.IData;
import data.fieldType;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Classe Intervention
 * Représente une intervention du projet AMS
 */
public class Intervention implements IData, Serializable {

    private int idInt;
    private int idPers;
    private String commentaire;

    private HashMap<String, fieldType> structure;

    /**
     * Constructeur
     */
    public Intervention(int idInt, int idPers, String commentaire) {
        this.idInt = idInt;
        this.idPers = idPers;
        this.commentaire = commentaire;

        structure = new HashMap<>();
        getStruct();
    }

    /**
     * Définit la structure de l'objet
     */
    @Override
    public void getStruct() {
        structure.clear();
        structure.put("IDINT", fieldType.INT4);
        structure.put("IDPERS", fieldType.INT4);
        structure.put("COMMENTAIRE", fieldType.VARCHAR);
    }

    /**
     * Retourne les valeurs pour l'insertion JDBC
     */
    @Override
    public String getValues() {
        return idInt + "," + idPers + ",'" + commentaire + "'";
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
