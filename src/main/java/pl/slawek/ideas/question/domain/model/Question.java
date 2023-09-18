package pl.slawek.ideas.question.domain.model;


import java.util.UUID;

public class Question {

    private UUID id;

    private String name;

    Question() {
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

    UUID getId() {
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
