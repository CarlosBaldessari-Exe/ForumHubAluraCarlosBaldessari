package AluraChallenge.CarlosBaldessari.forumhub.domain.topico;

import java.time.LocalDateTime;

public record DadosListagemTopico(
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        String status,
        String autor,
        String curso
) {
    public DadosListagemTopico(Topico topico) {
        this(topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(),  topico.getStatus() ? "Ativo" : "Inativo",topico.getAutor().getNome(),topico.getCurso().getNome());
    }
}
