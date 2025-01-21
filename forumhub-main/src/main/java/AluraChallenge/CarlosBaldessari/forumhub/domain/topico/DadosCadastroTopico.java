package AluraChallenge.CarlosBaldessari.forumhub.domain.topico;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record DadosCadastroTopico(

        @NotBlank(message = "Titulo é obrigatório")
        String titulo,

        @NotBlank(message = "Conteúdo é obrigatório")
        String mensagem,

        @NotNull
        Long autorId,

        @NotNull
        Long cursoId
) {
}
