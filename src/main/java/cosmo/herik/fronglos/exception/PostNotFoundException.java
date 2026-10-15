package cosmo.herik.fronglos.exception;

public class PostNotFoundException extends RuntimeException{
    public PostNotFoundException(String id) {
        super("Post (" + id + ") not found");
    }
}
