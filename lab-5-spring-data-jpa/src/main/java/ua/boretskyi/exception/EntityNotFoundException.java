package ua.boretskyi.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entity, String id) {
        super("Could not find '" + entity + "' with id=" + id);
    }
}
