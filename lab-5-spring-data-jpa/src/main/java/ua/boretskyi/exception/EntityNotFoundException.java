package ua.boretskyi.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entity, Integer id) {
        super("Could not find '" + entity + "' with id=" + id);
    }
}
