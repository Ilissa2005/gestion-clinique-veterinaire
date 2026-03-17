package spa;

import data.IData;
import data.fieldType;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Classe Sejour
 * Représente un séjour du projet AMS
 */
public class Sejour implements IData, Serializable {

    private int idAnimal;
    private String dateDebut;
    private String dateFin;

    private HashMap<String, fieldType> structure;

    /**
     * Constructeur
     */
    public Sejour(int idAnimal, String dateDebut, String dateFin) {
        this.idAnimal = idAnimal;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;

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
        structure.put("DATE_DEBUT", fieldType.VARCHAR);
        structure.put("DATE_FIN", fieldType.VARCHAR);
    }

    /**
     * Retourne les valeurs pour l'insertion JDBC
     */
    @Override
    public String getValues() {
        return idAnimal + ",'" + dateDebut + "','" + dateFin + "'";
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
