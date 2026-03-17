package exception;

/** Erreur liée à la base de données */
public class DatabaseException extends ProjectException {

    public DatabaseException(String message) {
        super(message);
    }
}
