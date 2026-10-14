package cosmo.herik.fronglos.dto.response;

import java.util.Map;

public record ApiErrorResponse(int status, String message, Map<String, String> errors) {
}
