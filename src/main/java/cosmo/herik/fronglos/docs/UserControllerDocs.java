package cosmo.herik.fronglos.docs;

import cosmo.herik.fronglos.dto.response.AuthUserProfileResponse;
import cosmo.herik.fronglos.dto.response.PostResponse;
import cosmo.herik.fronglos.dto.response.SimpleUserProfileResponse;
import cosmo.herik.fronglos.dto.response.UserProfileResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;

import java.util.UUID;

@SecurityRequirement(name = "bearerAuth")
public interface UserControllerDocs {
    @Operation(
            summary = "Ver meu perfil",
            description = "Como usuário autenticado, quero consultar meus próprios dados, para confirmar quem está logado."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Requisição bem sucedida"),
    })
    AuthUserProfileResponse getCurrentUser();

    @Operation(
            summary = "Ver perfil de outro usuário",
            description = "Como usuário autenticado, quero ver o perfil de outra pessoa, para decidir se quero segui-la."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Requisição bem sucedida"),
            @ApiResponse(responseCode = "404", description = "Usuário inexistente")
    })
    UserProfileResponse getUserById(UUID id);

    @Operation(
            summary = "Ver posts de um usuário",
            description = "Como usuário autenticado, quero ver os posts de uma pessoa específica, para conhecer o que ela publica."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Requisição bem sucedida"),
            @ApiResponse(responseCode = "404", description = "Usuário inexistente")
    })
    Page<PostResponse> getPostsFromUserId(UUID id, int page, int size);

    @Operation(
            summary = "Seguir usuário",
            description = "Como usuário autenticado, quero seguir outra pessoa, para ver os posts dela no meu feed."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário seguido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Usuário não pode seguir a si mesmo"),
            @ApiResponse(responseCode = "404", description = "Usuário inexistente"),
            @ApiResponse(responseCode = "409", description = "Usuário não pode seguir duas vezes a mesma pessoa")
    })
    void followerUser(UUID id);

    @Operation(
            summary = "Deixar de seguir",
            description = "Como usuário autenticado, quero parar de seguir alguém, para não ver mais os posts dessa pessoa."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuário parou de seguir com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário inexistente"),
            @ApiResponse(responseCode = "404", description = "Usuário não segue o usuário especificado")
    })
    void unfollowUser(UUID id);

    @Operation(
            summary = "Ver quem um usuário segue",
            description = "Como usuário autenticado, quero ver quem uma pessoa segue, para descobrir novos perfis."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Requisição bem sucedida"),
            @ApiResponse(responseCode = "404", description = "Usuário inexistente")
    })
    Page<SimpleUserProfileResponse> getFollowing(UUID id, int page, int size);

    @Operation(
            summary = "Ver seguidores de um usuário",
            description = "Como usuário autenticado, quero ver quem segue uma pessoa, para conhecer a rede dela."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Requisição bem sucedida"),
            @ApiResponse(responseCode = "404", description = "Usuário inexistente")
    })
    Page<SimpleUserProfileResponse> getFollowers(UUID id, int page, int size);

}
