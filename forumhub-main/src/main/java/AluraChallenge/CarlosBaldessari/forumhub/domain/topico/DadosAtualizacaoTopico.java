package AluraChallenge.CarlosBaldessari.forumhub.domain.topico;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoTopico(
        @NotNull(message = "O ID do tópico é obrigatório.")
        Long id,

        @NotBlank(message = "O título não pode ser vazio.")
        String titulo,

        @NotBlank(message = "A mensagem não pode ser vazia.")
        String mensagem
) {
}
