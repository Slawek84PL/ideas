package pl.slawek.ideas.question.domain.model;


import java.util.UUID;

public class Answer {

    private UUID id;

    private String name;

    Answer() {
    }

    public Answer(final String name) {
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
        return "Answer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
