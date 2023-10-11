package pl.slawek.ideas.question.domain.model;


import java.util.UUID;

public class Category {

    private UUID id;
    private String name;

    public Category() {
    }

    public Category(final String name) {
        this.name = name;
        this.id = UUID.randomUUID();
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
