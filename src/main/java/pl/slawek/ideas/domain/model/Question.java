package pl.slawek.ideas.domain.model;


import java.util.UUID;

public class Question {

    private UUID id;

    private String name;

    public Question() {
    }

    public Question(final String name) {
        this.name = name;
        this.id = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    void setName(final String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}