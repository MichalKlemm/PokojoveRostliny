import java.time.LocalDate;

public class Plant {

    private String name;
    private String notes;
    private LocalDate planted;
    private LocalDate watering;
    private int wateringFrequency;

    // Konstruktor pro nastavení všech atributů
    public Plant(String name, String notes, LocalDate planted, LocalDate watering, int wateringFrequency) {
        this.name = name;
        this.notes = notes;
        this.planted = planted;
        this.watering = watering;
        this.wateringFrequency = wateringFrequency;
    }
    // Konstruktor pro nastavení výchozích hodnot
    public Plant(String name, String notes, LocalDate planted){
        this(name,notes,planted,LocalDate.now(), 7);
    }
    // Konstruktor pro nastavení výchozích hodnot s default frekvencí zalévání
    public Plant(String name, LocalDate planted){
        this(name, "", planted);
    }
    // Metoda pro aktualizaci data posledního zalévání
    public void waterPlant(){
        this.watering = LocalDate.now();
    }
    // Vrácení informací o zalévání
    public String getWateringInfo(){
        LocalDate nextWateringDate = this.watering.plusDays(this.wateringFrequency);
        return "Name: "+ this.name + ", Last Watering: " + this.watering+ ", Next Watering: " + nextWateringDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
}
