package be.intecbrussel;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainApp {
    public static void main(String[] args) {
        oefening1();
    }

    private static void oefening1() {
        //Schrijf een programma dat kijkt of een file bestaat en wanneer het voor het laatst is gemodificeerd.
        Path path1 = Paths.get("resources/");

        try {
            if (!path1.toFile().exists()) //this is the line of code that checks whether it exists
                Files.createDirectories(path1);

            //creating and writing to a file
            path1 = path1.resolve("quote.txt");
            try (FileWriter fileWriter = new FileWriter(path1.toFile())) {
                fileWriter.write("I'm so hip I have difficulty looking over my pelvis!");
            }

            //printing details of file
            System.out.printf("The file %s was last modified %s", path1.getFileName(), Files.getLastModifiedTime(path1));
        } catch (IOException e) {
            System.out.println("uh oh something went wrong :(\n" + e.getMessage());
            e.printStackTrace();
        }


    }

//    private static void oefening2() {
//        //We gaan nu werken met bestanden en mappen.
//        //We gaan een programma schrijven dat een nieuwe map aanmaakt met een bestand daarin.
//    }
}
