import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class RubricaIO {
    public static ArrayList<Persona> retrieveInformazioni() {
        ArrayList<Persona> result = new ArrayList<>();
        try {
            File informazioni = new File(".\\Informazioni\\");
            if (informazioni.exists() && informazioni.isDirectory()) {
            	for (File informazione : informazioni.listFiles(new FilenameFilter() {
                    public boolean accept(File dir, String name) {
                        return name.toLowerCase().endsWith(".txt");
                    }
                })) {
                    Scanner reader = new Scanner(informazione);
                    while (reader.hasNextLine()) {
                        String row = reader.nextLine();
                        String[] splitted = row.split(";");
                        String id = splitted[0];
                        String nome = splitted[1];
                        String cognome = splitted[2];
                        String indirizzo = splitted[3];
                        String telefono = splitted[4];
                        int eta = -1;
                        try {
                            eta = Integer.parseInt(splitted[5].trim());
                        } catch (Exception e) {
                            eta = -1;
                        }
                        Persona toAdd = new Persona(id, nome, cognome, indirizzo, telefono, eta);
                        result.add(toAdd);
                    }
                    reader.close();
            	}
            }
            else {
                System.out.println("\"Informazioni\" does not exist or it is not a directory!");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File 'informazioni.txt' non trovato");
        }
        return result;
    }
    public static void addPersona(Persona persona) {
        try {
            File informazioni = new File(".\\Informazioni\\");
            if (!informazioni.exists() || !informazioni.isDirectory()) {
                informazioni.mkdirs();
            }
            String fileName = persona.getNome().toUpperCase() + "-" + persona.getCognome().toUpperCase() + "_" + persona.getID();
            File file = new File(informazioni.getAbsolutePath() + "\\" + fileName + ".txt");
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            PrintStream ps = new PrintStream(fos);
            ps.println(persona.getID() + ";" + persona.getNome() + ";" + persona.getCognome() + ";" + persona.getIndirizzo() + ";" + persona.getTelefono() + ";" + persona.getEta());
            ps.close();
            fos.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public static void updatePersona(Persona persona) {
        try {
            File fileToUpdate = new File(".\\Informazioni\\" + persona.getNome().toUpperCase() + "-" + persona.getCognome().toUpperCase() + "_" + persona.getID() + ".txt");
            if (fileToUpdate.exists() && fileToUpdate.isFile()) {
                FileOutputStream fos = new FileOutputStream(fileToUpdate);
                PrintStream ps = new PrintStream(fos);
                ps.println(persona.getID() + ";" + persona.getNome() + ";" + persona.getCognome() + ";" + persona.getIndirizzo() + ";" + persona.getTelefono() + ";" + persona.getEta());
                ps.close();
                fos.close();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public static void deletePersona(Persona persona) {
        File fileToDelete = new File(".\\Informazioni\\" + persona.getNome().toUpperCase() + "-" + persona.getCognome().toUpperCase() + "_" + persona.getID() + ".txt");
        if (fileToDelete.exists() && fileToDelete.isFile()) {
            fileToDelete.delete();
        }
    }
    /*public static void updateInformazioni(List<Persona> persone) {
        File informazioni = new File("informazioni.txt");
        try {
            informazioni.createNewFile();
            FileOutputStream fos = new FileOutputStream("informazioni.txt");
            PrintStream ps = new PrintStream(fos);
            for (Persona p : persone) {
                String row = p.getNome() + ";" + p.getCognome() + ";" + p.getIndirizzo() + ";" + p.getTelefono() + ";" + p.getEta();
                ps.println(row);
            }
            ps.close();
            fos.close();
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        catch (SecurityException ex1) {
            System.out.println(ex1.getMessage());
        }
    }*/
}
