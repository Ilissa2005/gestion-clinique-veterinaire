package spa;

import data.IData;
import data.fieldType;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Classe Creneau
 * Représente un créneau du projet AMS
 */
public class Creneau implements IData, Serializable {

    private int idCre;
    private String heureDebut;
    private String heureFin;

    private HashMap<String, fieldType> structure;

    /**
     * Constructeur
     */
    public Creneau(int idCre, String heureDebut, String heureFin) {
        this.idCre = idCre;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;

        structure = new HashMap<>();
        getStruct();
    }

    /**
     * Définit la structure de l'objet
     */
    @Override
    public void getStruct() {
        structure.clear();
        structure.put("IDCRE", fieldType.INT4);
        structure.put("HEURE_DEBUT", fieldType.VARCHAR);
        structure.put("HEURE_FIN", fieldType.VARCHAR);
    }

    /**
     * Retourne les valeurs pour l'insertion JDBC
     */
    @Override
    public String getValues() {
        return idCre + ",'" + heureDebut + "','" + heureFin + "'";
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
