package spa;

import data.IData;
import data.fieldType;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Représente un animal du refuge.
 *
 * <p>
 * Cette classe implémente l'interface IData afin de permettre
 * l'insertion de l'objet dans une base de données.
 * Elle implémente également Serializable pour permettre
 * la sauvegarde et le chargement des objets.
 * </p>
 */
public class Animal implements IData, Serializable {

    private int idAnimal;
    private String nom;
    private String typeAnimal;
    private String race;

    private HashMap<String, fieldType> structure;

    /**
     * Constructeur
     */
    public Animal(int idAnimal, String nom, String typeAnimal, String race) {
        this.idAnimal = idAnimal;
        this.nom = nom;
        this.typeAnimal = typeAnimal;
        this.race = race;

        structure = new HashMap<>();
        getStruct();
    }

    /**
     * Définit la structure attendue de l'objet Animal.
     * Cette structure est utilisée pour vérifier la compatibilité
     * avec la table SQL avant l'insertion.
     */
    @Override
    public void getStruct() {
        structure.clear();
        structure.put("IDANIMAL", fieldType.INT4);
        structure.put("NOM", fieldType.VARCHAR);
        structure.put("TYPE_ANIMAL", fieldType.VARCHAR);
        structure.put("RACE", fieldType.VARCHAR);
    }

    /**
     * Retourne les valeurs de l'objet Animal sous forme de chaîne de caractères
     * afin de construire la requête SQL d'insertion.
     *
     * @return chaîne représentant les valeurs SQL de l'objet
     */
    @Override
    public String getValues() {
        return idAnimal + ",'" + nom + "','" + typeAnimal + "','" + race + "'";
    }

    /**
     * Retourne la structure de l'objet Animal.
     *
     * @return structure de l'objet sous forme de HashMap associant
     *         les noms des colonnes à leurs types
     */
    @Override
    public HashMap<String, fieldType> getMap() {
        return structure;
    }

    /**
     * Vérifie la compatibilité entre la structure de l'objet
     * et celle de la table SQL.
     *
     * @param tableStruct structure de la table SQL
     * @return true si les structures sont identiques, false sinon
     */
    @Override
    public boolean check(HashMap<String, fieldType> tableStruct) {
        return structure.equals(tableStruct);
    }
}
