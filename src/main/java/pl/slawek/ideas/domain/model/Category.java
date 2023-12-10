package pl.slawek.ideas.domain.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    private UUID id;

    @NotBlank(message = "{ideas.validation.name.NotBlank.message}")
    @Size(min = 3, max = 255)
    private String name;

    public Category() {
        this.id = UUID.randomUUID();
    }

    public Category(final String name) {
        this();
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    void setId(final UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
