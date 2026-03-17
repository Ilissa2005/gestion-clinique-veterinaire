package spa;

import data.IData;
import data.fieldType;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Classe Placement
 * Représente un placement du projet AMS
 */
public class Placement implements IData, Serializable {

    private int idAnimal;
    private int idFam;
    private String dateDebut;

    private HashMap<String, fieldType> structure;

    /**
     * Constructeur
     */
    public Placement(int idAnimal, int idFam, String dateDebut) {
        this.idAnimal = idAnimal;
        this.idFam = idFam;
        this.dateDebut = dateDebut;

        structure = new HashMap<>();
        getStruct();
    }

    /**
     * Définit la structure de l'objet
     */
    @Override
    public void getStruct() {
        structure.clear();
        structure.put("IDANIMAL", fieldType.INT4);
        structure.put("IDFAM", fieldType.INT4);
        structure.put("DATE_DEBUT", fieldType.VARCHAR);
    }

    /**
     * Retourne les valeurs pour l'insertion JDBC
     */
    @Override
    public String getValues() {
        return idAnimal + "," + idFam + ",'" + dateDebut + "'";
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
