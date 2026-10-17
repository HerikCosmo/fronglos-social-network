package cosmo.herik.fronglos.docs;

import cosmo.herik.fronglos.dto.request.LoginRequest;
import cosmo.herik.fronglos.dto.request.RegisterUserRequest;
import cosmo.herik.fronglos.dto.response.LoginResponse;
import cosmo.herik.fronglos.dto.response.RegisterUserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface AuthControllerDocs {
    @Operation(
            summary = "Realizar login",
            description = "Como usuário cadastrado, quero fazer login com email e senha, para receber um token de acesso."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Credenciais válidas"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    })
    LoginResponse login(LoginRequest request);

    @Operation(
            summary = "Cadastro de usuário",
            description = "Como visitante, quero criar uma conta, para poder usar a rede social."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "409", description = "Username ou email já cadastrados")

    })
    RegisterUserResponse register(RegisterUserRequest request);
}
