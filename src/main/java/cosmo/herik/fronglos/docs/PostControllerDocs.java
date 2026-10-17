package cosmo.herik.fronglos.docs;

import cosmo.herik.fronglos.dto.request.PostRequest;
import cosmo.herik.fronglos.dto.response.PostResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.UUID;

@SecurityRequirement(name = "bearerAuth")
public interface PostControllerDocs {
    @Operation(
            summary = "Criar Post",
            description = "Como usuário autenticado, quero publicar um post de texto, para compartilhar algo com meus seguidores."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Post criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    PostResponse savePost(PostRequest request);

    @Operation(
            summary = "Ver um post",
            description = "Como usuário autenticado, quero abrir um post específico, para ler seu conteúdo completo."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Requisição bem sucedida"),
            @ApiResponse(responseCode = "404", description = "Post inexistente")
    })
    PostResponse getPostById(UUID id);

    @Operation(
            summary = "Deletar post",
            description = "Como autor de um post, quero apagá-lo, para remover algo que não quero mais exibir."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Post apagado com sucesso"),
            @ApiResponse(responseCode = "403", description = "Post de outro usuário não pode ser deletado"),
            @ApiResponse(responseCode = "404", description = "Post inexistente")
    })
    void deletePostById(UUID id);
}
