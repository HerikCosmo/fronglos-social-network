package cosmo.herik.fronglos.exception;

public class NotFollowingException extends RuntimeException {
    public NotFollowingException() {
        super("You not are following this user");
    }
}
