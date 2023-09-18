package pl.slawek.ideas.question.domain.model;


import java.util.UUID;

class Category {

    private UUID id;
    private String name;

    Category() {
    }

    Category(final UUID id, final String name) {
        this.id = id;
        this.name = name;
    }

    UUID getId() {
        return id;
    }

    void setId(final UUID id) {
        this.id = id;
    }

    String getName() {
        return name;
    }

    void setName(final String name) {
        this.name = name;
    }
}
