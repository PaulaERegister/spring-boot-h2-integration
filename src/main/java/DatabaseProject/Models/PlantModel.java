package DatabaseProject.Models;

import javax.persistence.*;

@Entity
public class PlantModel {
    @Id
    @Column(nullable = false, unique = true)
    private String species;

    @Column
    private String subkingdom;

    @Column
    private String superdivision;

    @Column
    private String division;

    @Column
    private String plantClass;

    @Column
    private String order;

    @Column
    private String family;

    @Column
    private String genus;

    @Column
    private String commonName;

    @Lob
    @Column(length = 100000)
    private byte[] images;

    public String getSpecies() {
        return species;
    }

    public String getDivision() {
        return division;
    }

    public String getSubkingdom() {
        return subkingdom;
    }

    public String getPlantClass() {
        return plantClass;
    }

    public String getSuperdivision() {
        return superdivision;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public byte[] getImages() {
        return images;
    }

    public String getFamily() {
        return family;
    }

    public String getOrder() {
        return order;
    }

    public String getGenus() {
        return genus;
    }

    public String getCommonName() {
        return commonName;
    }
}
