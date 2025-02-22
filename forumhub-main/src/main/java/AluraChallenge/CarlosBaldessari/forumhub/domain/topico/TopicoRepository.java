package AluraChallenge.CarlosBaldessari.forumhub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Optional<Topico> findByTituloAndMensagem(String titulo, String mensagem);

    Page<Topico> findAllByStatusTrue(Pageable paginacao);

    Page<Topico> findAllByCursoNomeAndDataCriacaoYear(String cursoNome, int ano, Pageable paginacao);

    Topico findByIdAndStatusTrue(Long id);
}
