package data;

import java.util.HashMap;

public interface IData {

    // Remplit la HashMap<String, fieldType> et la chaîne values
    public void getStruct();

    // Retourne la chaîne VALUES pour INSERT
    public String getValues();

    // Getter de la map
    public HashMap<String, fieldType> getMap();

    // Vérifie que table et instance ont la même structure
    public boolean check(HashMap<String, fieldType> tableStruct);
}
