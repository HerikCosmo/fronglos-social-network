package cosmo.herik.fronglos.docs;

import cosmo.herik.fronglos.dto.response.PostResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;

@SecurityRequirement(name = "bearerAuth")
public interface FeedControllerDocs {
    @Operation(
            summary = "Ver feed geral",
            description = "Como usuário autenticado, quero ver uma lista única com meus posts e os de quem sigo, para acompanhar tudo em um só lugar."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Requisição bem sucedida"),
    })
    Page<PostResponse> getFeed(int page, int size);
}
