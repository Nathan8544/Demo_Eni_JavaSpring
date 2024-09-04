package eni.demo.demo.module4.bo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Aliment {

    public Long id;

    @NotBlank( message = "Le titre doit être renseigné")
    @Size(min=2, max=250, message = "Doit avoir au moins 2 caractères")
    public String name;

    public Aliment() {
    }

    public Aliment(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
