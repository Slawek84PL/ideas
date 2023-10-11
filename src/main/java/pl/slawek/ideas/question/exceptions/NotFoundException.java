package pl.slawek.ideas.question.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(final String category, final UUID id) {
        super(String.format("Nie znaleziono pozycj dla %s o id %s.", category, id));
    }
}
