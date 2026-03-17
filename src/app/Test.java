package app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;

/* === Sérialisation === */
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.ArrayList;

import bdd.Connexion;
import bdd.Gestion;

import exception.DatabaseException;
import exception.DataException;

import data.IData;

import spa.Box;
import spa.Famille;
import spa.Benevole;
import spa.Animal;
import spa.Creneau;
import spa.TypeActivite;
import spa.Sejour;
import spa.Placement;
import spa.Intervention;

/**
 * Projet AMS – Client JDBC interactif
 *
 * <p>
 * L'objectif de ce projet est de proposer un client interactif permettant
 * la création et la gestion de tables dans une base de données PostgreSQL.
 * Il constitue une base de travail évolutive pour le projet AMS.
 * </p>
 *
 * <p>
 * Le client permet :
 * <ul>
 *   <li>la création de tables</li>
 *   <li>l'insertion de données</li>
 *   <li>l'affichage des tables</li>
 *   <li>la suppression de lignes ou de tables</li>
 *   <li>la sauvegarde et le chargement d'objets</li>
 * </ul>
 * </p>
 *
 * <p>
 * Le projet utilise :
 * <ul>
 *   <li>l'interface IData</li>
 *   <li>l'enum fieldType</li>
 *   <li>des exceptions personnalisées</li>
 *   <li>la bibliothèque JDBC PostgreSQL</li>
 * </ul>
 * </p>
 *
 * @author ilissa
 * @version 1.0
 */

public class Test {

    public static void main(String[] args) {

        Connection conn = Connexion.connectDB();
        if (conn == null) {
            System.out.println("Connexion impossible.");
            return;
        }

        Gestion g = new Gestion(conn);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        /* ===== Collection pour sérialisation ===== */
        ArrayList<IData> sauvegarde = new ArrayList<IData>();

        System.out.println("=== CLIENT JDBC AMS ===");
        System.out.println("Commandes : CREATE | INSERT | DISPLAY | STRUCT | REMOVE | DROP | SAUVER | CHARGER | QUITTER");

        try {
            while (true) {

                System.out.print("\nCommande ? ");
                String cmd = br.readLine();
                if (cmd == null) continue;
                cmd = cmd.toUpperCase();

                /* ================= QUITTER ================= */
                if (cmd.equals("QUITTER")) {
                    System.out.println("Fin du programme.");
                    break;
                }

                /* ================= CREATE ================= */
                else if (cmd.equals("CREATE")) {

                    System.out.print("Quelle table voulez-vous créer ? ");
                    String table = br.readLine();
                    if (table == null) continue;
                    table = table.toUpperCase();

                    try {
                        if (table.equals("BOX"))
                            g.executer("CREATE TABLE IF NOT EXISTS BOX (IDBOX INT PRIMARY KEY, NUMERO INT, TYPE_BOX VARCHAR(50), CAPACITE_MAX INT)");
                        else if (table.equals("FAMILLE"))
                            g.executer("CREATE TABLE IF NOT EXISTS FAMILLE (IDFAM INT PRIMARY KEY, NOM VARCHAR(100), ADRESSE VARCHAR(255))");
                        else if (table.equals("BENEVOLE"))
                            g.executer("CREATE TABLE IF NOT EXISTS BENEVOLE (IDPERS INT PRIMARY KEY, NOM VARCHAR(100), PRENOM VARCHAR(100))");
                        else if (table.equals("ANIMAL"))
                            g.executer("CREATE TABLE IF NOT EXISTS ANIMAL (IDANIMAL INT PRIMARY KEY, NOM VARCHAR(100), TYPE_ANIMAL VARCHAR(50), RACE VARCHAR(50))");
                        else if (table.equals("CRENEAU"))
                            g.executer("CREATE TABLE IF NOT EXISTS CRENEAU (IDCRE INT PRIMARY KEY, HEURE_DEBUT VARCHAR(10), HEURE_FIN VARCHAR(10))");
                        else if (table.equals("TYPEACTIVITE"))
                            g.executer("CREATE TABLE IF NOT EXISTS TYPEACTIVITE (IDTYPE INT PRIMARY KEY, LIBELLE VARCHAR(100))");
                        else if (table.equals("SEJOUR"))
                            g.executer("CREATE TABLE IF NOT EXISTS SEJOUR (IDANIMAL INT PRIMARY KEY, DATE_DEBUT VARCHAR(20), DATE_FIN VARCHAR(20))");
                        else if (table.equals("PLACEMENT"))
                            g.executer("CREATE TABLE IF NOT EXISTS PLACEMENT (IDANIMAL INT PRIMARY KEY, IDFAM INT, DATE_DEBUT VARCHAR(20))");
                        else if (table.equals("INTERVENTION"))
                            g.executer("CREATE TABLE IF NOT EXISTS INTERVENTION (IDINT INT PRIMARY KEY, IDPERS INT, COMMENTAIRE VARCHAR(255))");
                        else {
                            System.out.println("Table inconnue.");
                            continue;
                        }

                        System.out.println("Table " + table + " créée.");
                    }
                    catch (DatabaseException e) {
                        System.out.println("Erreur lors de la création de la table.");
                    }
                }

                /* ================= INSERT ================= */
                else if (cmd.equals("INSERT")) {

                    System.out.print("Table ? ");
                    String table = br.readLine();
                    if (table == null) continue;
                    table = table.toUpperCase();

                    try {
                        IData obj = null;

                        if (table.equals("BOX")) {
                            System.out.print("IDBOX ? ");
                            int id = Integer.parseInt(br.readLine());
                            System.out.print("NUMERO ? ");
                            int num = Integer.parseInt(br.readLine());
                            System.out.print("TYPE_BOX ? ");
                            String type = br.readLine();
                            System.out.print("CAPACITE_MAX ? ");
                            int cap = Integer.parseInt(br.readLine());

                            obj = new Box(id, num, type, cap);
                            g.inserer(obj, "BOX");
                        }

                        else if (table.equals("FAMILLE")) {
                            System.out.print("IDFAM ? ");
                            int id = Integer.parseInt(br.readLine());
                            System.out.print("NOM ? ");
                            String nom = br.readLine();
                            System.out.print("ADRESSE ? ");
                            String adr = br.readLine();

                            obj = new Famille(id, nom, adr);
                            g.inserer(obj, "FAMILLE");
                        }

                        else if (table.equals("BENEVOLE")) {
                            System.out.print("IDPERS ? ");
                            int id = Integer.parseInt(br.readLine());
                            System.out.print("NOM ? ");
                            String nom = br.readLine();
                            System.out.print("PRENOM ? ");
                            String prenom = br.readLine();

                            obj = new Benevole(id, nom, prenom);
                            g.inserer(obj, "BENEVOLE");
                        }

                        else if (table.equals("ANIMAL")) {
                            System.out.print("IDANIMAL ? ");
                            int id = Integer.parseInt(br.readLine());
                            System.out.print("NOM ? ");
                            String nom = br.readLine();
                            System.out.print("TYPE_ANIMAL ? ");
                            String type = br.readLine();
                            System.out.print("RACE ? ");
                            String race = br.readLine();

                            obj = new Animal(id, nom, type, race);
                            g.inserer(obj, "ANIMAL");
                        }

                        else if (table.equals("CRENEAU")) {
                            System.out.print("IDCRE ? ");
                            int id = Integer.parseInt(br.readLine());
                            System.out.print("HEURE_DEBUT ? ");
                            String debut = br.readLine();
                            System.out.print("HEURE_FIN ? ");
                            String fin = br.readLine();

                            obj = new Creneau(id, debut, fin);
                            g.inserer(obj, "CRENEAU");
                        }

                        else if (table.equals("TYPEACTIVITE")) {
                            System.out.print("IDTYPE ? ");
                            int id = Integer.parseInt(br.readLine());
                            System.out.print("LIBELLE ? ");
                            String lib = br.readLine();

                            obj = new TypeActivite(id, lib);
                            g.inserer(obj, "TYPEACTIVITE");
                        }

                        else if (table.equals("SEJOUR")) {
                            System.out.print("IDANIMAL ? ");
                            int idAnimal = Integer.parseInt(br.readLine());
                            System.out.print("DATE_DEBUT ? ");
                            String d1 = br.readLine();
                            System.out.print("DATE_FIN ? ");
                            String d2 = br.readLine();

                            obj = new Sejour(idAnimal, d1, d2);
                            g.inserer(obj, "SEJOUR");
                        }

                        else if (table.equals("PLACEMENT")) {
                            System.out.print("IDANIMAL ? ");
                            int idAnimal = Integer.parseInt(br.readLine());
                            System.out.print("IDFAM ? ");
                            int idFam = Integer.parseInt(br.readLine());
                            System.out.print("DATE_DEBUT ? ");
                            String date = br.readLine();

                            obj = new Placement(idAnimal, idFam, date);
                            g.inserer(obj, "PLACEMENT");
                        }

                        else if (table.equals("INTERVENTION")) {
                            System.out.print("IDINT ? ");
                            int idInt = Integer.parseInt(br.readLine());
                            System.out.print("IDPERS ? ");
                            int idPers = Integer.parseInt(br.readLine());
                            System.out.print("COMMENTAIRE ? ");
                            String comm = br.readLine();

                            obj = new Intervention(idInt, idPers, comm);
                            g.inserer(obj, "INTERVENTION");
                        }

                        else {
                            System.out.println("Table inconnue.");
                            continue;
                        }

                        sauvegarde.add(obj);
                        System.out.println("Insertion effectuée.");
                    }
                    catch (DatabaseException e) {
                        System.out.println("Insertion impossible : table inexistante ou identifiant déjà utilisé.");
                    }
                    catch (DataException e) {
                        System.out.println("Insertion impossible : structure incompatible.");
                    }
                    catch (NumberFormatException e) {
                        System.out.println("Erreur : vous devez entrer un nombre pour l'identifiant.");
                    }
                }

                /* ================= DISPLAY ================= */
                else if (cmd.equals("DISPLAY")) {
                    System.out.print("Table ? ");
                    String t = br.readLine();
                    if (t == null) continue;
                    t = t.toUpperCase();

                    try {
                        g.afficherTable(t);
                    }
                    catch (DatabaseException e) {
                        System.out.println("La table " + t + " n'existe pas.");
                    }
                }

                /* ================= STRUCT ================= */
                else if (cmd.equals("STRUCT")) {
                    System.out.print("Table ? ");
                    String t = br.readLine();
                    if (t == null) continue;
                    t = t.toUpperCase();

                    try {
                        g.structTable(t, true);
                    }
                    catch (DatabaseException e) {
                        System.out.println("Impossible d'afficher la structure : table inexistante.");
                    }
                }

                /* ================= REMOVE ================= */
                else if (cmd.equals("REMOVE")) {
                    System.out.print("Table ? ");
                    String t = br.readLine();
                    if (t == null) continue;
                    t = t.toUpperCase();

                    System.out.print("ID ? ");
                    String id = br.readLine();

                    try {
                        if (t.equals("BOX")) g.executer("DELETE FROM BOX WHERE IDBOX=" + id);
                        else if (t.equals("FAMILLE")) g.executer("DELETE FROM FAMILLE WHERE IDFAM=" + id);
                        else if (t.equals("BENEVOLE")) g.executer("DELETE FROM BENEVOLE WHERE IDPERS=" + id);
                        else if (t.equals("ANIMAL")) g.executer("DELETE FROM ANIMAL WHERE IDANIMAL=" + id);
                        else if (t.equals("CRENEAU")) g.executer("DELETE FROM CRENEAU WHERE IDCRE=" + id);
                        else if (t.equals("TYPEACTIVITE")) g.executer("DELETE FROM TYPEACTIVITE WHERE IDTYPE=" + id);
                        else if (t.equals("SEJOUR")) g.executer("DELETE FROM SEJOUR WHERE IDANIMAL=" + id);
                        else if (t.equals("PLACEMENT")) g.executer("DELETE FROM PLACEMENT WHERE IDANIMAL=" + id);
                        else if (t.equals("INTERVENTION")) g.executer("DELETE FROM INTERVENTION WHERE IDINT=" + id);
                        else {
                            System.out.println("Table inconnue.");
                            continue;
                        }

                        System.out.println("Ligne supprimée.");
                    }
                    catch (DatabaseException e) {
                        System.out.println("Erreur lors de la suppression.");
                    }
                }

                /* ================= DROP ================= */
                else if (cmd.equals("DROP")) {
                    System.out.print("Table ? ");
                    String t = br.readLine();
                    if (t == null) continue;
                    t = t.toUpperCase();

                    try {
                        g.executer("DROP TABLE IF EXISTS " + t);
                        System.out.println("Table supprimée.");
                    }
                    catch (DatabaseException e) {
                        System.out.println("Erreur lors de la suppression de la table.");
                    }
                }

                /* ================= SAUVER ================= */
                else if (cmd.equals("SAUVER")) {
                    try {
                        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("ams.ser"));
                        oos.writeObject(sauvegarde);
                        oos.close();
                        System.out.println("Données sauvegardées dans ams.ser");
                    }
                    catch (Exception e) {
                        System.out.println("Erreur lors de la sauvegarde.");
                    }
                }

                /* ================= CHARGER ================= */
                else if (cmd.equals("CHARGER")) {
                    try {
                        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("ams.ser"));
                        sauvegarde = (ArrayList<IData>) ois.readObject();
                        ois.close();
                        System.out.println("Données chargées depuis ams.ser");
                        System.out.println("Nombre d'objets en mémoire : " + sauvegarde.size());
                    }
                    catch (Exception e) {
                        System.out.println("Erreur lors du chargement.");
                    }
                }

                else {
                    System.out.println("Commande inconnue.");
                }
            }
        }
        catch (Exception e) {
            System.out.println("Erreur dans le client JDBC.");
        }
    }
}
