package spa;

import data.IData;
import data.fieldType;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Classe TypeActivite
 * Représente un type d'activité du projet AMS
 */
public class TypeActivite implements IData, Serializable {

    private int idType;
    private String libelle;

    private HashMap<String, fieldType> structure;

    /**
     * Constructeur
     */
    public TypeActivite(int idType, String libelle) {
        this.idType = idType;
        this.libelle = libelle;

        structure = new HashMap<>();
        getStruct();
    }

    /**
     * Définit la structure de l'objet
     */
    @Override
    public void getStruct() {
        structure.clear();
        structure.put("IDTYPE", fieldType.INT4);
        structure.put("LIBELLE", fieldType.VARCHAR);
    }

    /**
     * Retourne les valeurs pour l'insertion JDBC
     */
    @Override
    public String getValues() {
        return idType + ",'" + libelle + "'";
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
