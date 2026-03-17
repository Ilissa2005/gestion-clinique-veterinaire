package spa;

import data.IData;
import data.fieldType;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Classe Box
 * Représente un box du projet AMS
 */
public class Box implements IData, Serializable {

    private int idBox;
    private int numero;
    private String typeBox;
    private int capaciteMax;

    private HashMap<String, fieldType> structure;

    /**
     * Constructeur
     */
    public Box(int idBox, int numero, String typeBox, int capaciteMax) {
        this.idBox = idBox;
        this.numero = numero;
        this.typeBox = typeBox;
        this.capaciteMax = capaciteMax;

        structure = new HashMap<>();
        getStruct();
    }

    /**
     * Définit la structure de l'objet
     */
    @Override
    public void getStruct() {
        structure.clear();
        structure.put("IDBOX", fieldType.INT4);
        structure.put("NUMERO", fieldType.INT4);
        structure.put("TYPE_BOX", fieldType.VARCHAR);
        structure.put("CAPACITE_MAX", fieldType.INT4);
    }

    /**
     * Retourne les valeurs pour l'insertion JDBC
     */
    @Override
    public String getValues() {
        return idBox + "," + numero + ",'" + typeBox + "'," + capaciteMax;
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
