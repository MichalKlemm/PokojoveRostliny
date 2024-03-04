import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FlowerList {
    private List<Plant> flowers;

    public FlowerList() {
        flowers = new ArrayList<>();
    }

    public void addFlower(Plant flower) {
        flowers.add(flower);
    }

    public Plant getFlower(int index) {
        if (index >= 0 && index < flowers.size()) {
            return flowers.get(index);
        } else {
            return null;
        }
    }

    public void removeFlower(int index) {
        if (index >= 0 && index < flowers.size()) {
            flowers.remove(index);
        }
    }

    public void exportToFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Plant flower : flowers) {
                writer.write(flower.getName() + "\t" + flower.getDescription() + "\t" +
                        flower.getWateringFrequency() + "\t" + flower.getPlanted() + "\t" +
                        flower.getWatering());
                writer.newLine();
            }
        }
    }

    public void importFromFile(String filename) throws IOException, PlantException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length != 5) {
                    throw new PlantException("Invalid format in the input file.");
                }
                String name = parts[0];
                String description = parts[1];
                int wateringFrequency = Integer.parseInt(parts[2]);
                LocalDate planted = LocalDate.parse(parts[3]);
                LocalDate watering = LocalDate.parse(parts[4]);

                if (wateringFrequency == 365) {
                    // Pokud je frekvence zalévání 365 (což značí, že se rostlina nezalévá),
                    // nastavím datum zalévání na stejný den jako datum zasazení
                    watering = planted;
                }

                Plant plant = new Plant(name, description, planted, watering, wateringFrequency);
                flowers.add(plant);
            }
        }
    }

    public int getSize() {
        return flowers.size();
    }

    public List<Plant> getFlower() {
        return flowers;
    }
}





