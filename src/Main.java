import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FlowerList flowerList = new FlowerList();

        // 1. Načtěte seznam květin ze souboru kvetiny.txt
        try (BufferedReader reader = new BufferedReader(new FileReader("kvetiny.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                String name = parts[0];
                String description = parts[1];
                LocalDate planted = LocalDate.parse(parts[3]);
                LocalDate watering = LocalDate.now();  // Aktuální datum pro poslední zavlažování
                int wateringFrequency = Integer.parseInt(parts[2]);

                // Vytvoření objektu Plant s aktuálním datem pro zavlažování
                Plant plant = new Plant(name, description, planted, watering, wateringFrequency);
                flowerList.addFlower(plant);
            }
        } catch (IOException | PlantException e) {
            e.printStackTrace();
        }

        // 2. Vypíšu informace o zálivce pro všechny květiny
        System.out.println("\nInformace o zálévání pro všechny květiny:");
        for (int i = 0; i < flowerList.getSize(); i++) {
            Plant flower = flowerList.getFlower(i);
            System.out.println(flower.getWateringInfo());
        }
        System.out.println();

        // 3. Přidám dvě nové květiny do seznamu
        try {
            flowerList.addFlower(new Plant("Avokádo", "Krásné velké listy", LocalDate.now()));
            flowerList.addFlower(new Plant("Citrónovník", "Krásná rostlina, listy voní po citrónech", LocalDate.now()));
        } catch (PlantException e) {
            System.err.println("Chyba při přidávání nové květiny.");
            e.printStackTrace();
        }

        // 4. Odeberu jednu květinu ze seznamu
        flowerList.removeFlower(0); // Odebíráme první květinu ze seznamu

        // 5. Uložím seznam květin do nového souboru
        try {
            flowerList.exportToFile("novy_seznam_kvetin.txt");
        } catch (IOException e) {
            System.err.println("Chyba při ukládání seznamu květin do souboru.");
            e.printStackTrace();
        }

        // 6. Ověřím, že obsah nového souboru je správný
        System.out.println("Obsah nového souboru:");
        try (BufferedReader reader = new BufferedReader(new FileReader("novy_seznam_kvetin.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Chyba při čtení nového souboru.");
            e.printStackTrace();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Public\\IntelliJ\\cviceni\\PokojoveRostliny\\kvetiny.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Chyba při čtení souboru kvetiny.txt: " + e.getMessage());
        }
        System.out.println();

        // Seřazení květin podle názvu
        Collections.sort(flowerList.getFlower());

        // Vypsání seřazených květin
        System.out.println("Seřazené rostliny podle názvu:");
        for (Plant flower : flowerList.getFlower()) {
            System.out.println(flower.getName());
        }
    }
}



