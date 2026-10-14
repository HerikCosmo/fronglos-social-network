package cosmo.herik.fronglos.exception;

import cosmo.herik.fronglos.dto.response.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandlerException {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UsernameNotFoundException.class)
    public ApiErrorResponse handleUsernameNotFoundException(UsernameNotFoundException ex) {
        return new ApiErrorResponse(HttpStatus.NOT_FOUND.value(), "User (" + ex.getMessage() + ") not found", null);
    }
}
