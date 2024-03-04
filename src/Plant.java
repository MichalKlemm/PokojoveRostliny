import java.io.Serializable;
import java.time.LocalDate;

public class Plant implements Serializable, Comparable<Plant> {

    private String name;
    private String description;
    private LocalDate planted;
    private LocalDate watering;
    private int wateringFrequency;

    public Plant(String name, String description, LocalDate planted) throws PlantException {
        this(name, description, planted, LocalDate.now(), 7); // Default watering frequency set to 7 days
    }

    public Plant(String name, String description, LocalDate planted, LocalDate watering, int wateringFrequency)
            throws PlantException {
        if (wateringFrequency <= 0) {
            throw new PlantException("Frequency of watering must be a positive number!");
        }
        if (watering.isBefore(planted)) {
            throw new PlantException("Last watering date can't be earlier than planting date!");
        }
        this.name = name;
        this.description = description;
        this.planted = planted;
        this.watering = watering;
        this.wateringFrequency = wateringFrequency;
    }

    public Plant(String name, LocalDate planted) throws PlantException {
        this(name, "", planted); // Default description is an empty string
    }

    public void waterPlant() {
        this.watering = LocalDate.now();
    }

    public String getWateringInfo() {
        LocalDate nextWateringDate = this.watering.plusDays(this.wateringFrequency);
        return "Name: " + this.name + ", Last Watering: " + this.watering + ", Next Watering: " + nextWateringDate;
    }

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getPlanted() {
        return planted;
    }

    public void setPlanted(LocalDate planted) {
        this.planted = planted;
    }

    public LocalDate getWatering() {
        return watering;
    }

    public void setWatering(LocalDate watering) {
        this.watering = watering;
    }

    public int getWateringFrequency() {
        return wateringFrequency;
    }

    public void setWateringFrequency(int wateringFrequency) {
        this.wateringFrequency = wateringFrequency;
    }

    @Override
    public int compareTo(Plant other) {
        return this.name.compareTo(other.getName());
    }
}



