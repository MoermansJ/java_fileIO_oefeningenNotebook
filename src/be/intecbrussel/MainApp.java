package be.intecbrussel;

import java.io.*;
import java.nio.file.*;
import java.util.stream.Stream;

public class MainApp {
    public static void main(String[] args) {
//        oefening1();
        oefening2();
    }

    private static void oefening1() {
        //Schrijf een programma dat kijkt of een file bestaat en wanneer het voor het laatst is gemodificeerd.
        Path path = Paths.get("resources/");

        try {
            if (!path.toFile().exists()) //this is the line of code that checks whether it exists
                Files.createDirectories(path);

            //creating and writing to a file
            path = path.resolve("quote.txt");
            try (FileWriter fileWriter = new FileWriter(path.toFile())) {
                fileWriter.write("I'm so hip I have difficulty looking over my pelvis!");
            }

            //printing details of file
            System.out.printf("The file %s was last modified %s", path.getFileName(), Files.getLastModifiedTime(path));
        } catch (IOException e) {
            System.out.println("uh oh something went wrong :(\n" + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void oefening2() {
        //We gaan nu werken met bestanden en mappen.
        //We gaan een programma schrijven dat een nieuwe map aanmaakt met een bestand daarin.
        Path path = Paths.get("resources/oefening2/");

        try {
            //creating directories
            if (!path.toFile().exists())
                Files.createDirectories(path);

            //Schrijf enkele regels tekst naar het bestand.
            path = path.resolve("text.txt");
            String messageLine1 = "So long\n";
            String messageLine2 = "And thanks for all the fish";

            //creating + writing to .txt-file
            try (FileWriter fileWriter = new FileWriter(path.toFile())) {
                fileWriter.write(messageLine1);
                fileWriter.write(messageLine2);
            }

            //Lees de tekst terug van het bestand.
            try (FileReader fileReader = new FileReader(path.toFile());
                 BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                bufferedReader.lines().forEach(System.out::println);
            }

            //Hernoem het bestand. Gebruik hiervoor de methode Files.move()
            Path sourcePath = path;
            Path destinationPath = Paths.get("resources/oefening2/newText.txt");
            Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

            //Druk wat kenmerken van een bestand af. Zoals bijvoorbeeld de size, is het readonly enz.
            path = destinationPath; //forgive the many cross assignments of variables
            System.out.printf("Filename: %s | Filesize: %s | Readonly: %s ", path.getFileName(), Files.size(path), Files.isWritable(path));

            //Druk de gegevens van de eigenaar van het bestand af. (Zoek zelf naar een manier om dit te doen)
            System.out.printf("| File owner: %s", Files.getOwner(path));

        } catch (IOException e) {
            System.out.println("An exception occurred!\n" + e.getMessage());
            e.printStackTrace();
        }

    }
}
