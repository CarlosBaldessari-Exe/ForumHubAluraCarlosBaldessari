package AluraChallenge.CarlosBaldessari.forumhub.domain.topico;

import AluraChallenge.CarlosBaldessari.forumhub.domain.curso.Curso;
import AluraChallenge.CarlosBaldessari.forumhub.domain.resposta.Resposta;
import AluraChallenge.CarlosBaldessari.forumhub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Topico")
@Table(name = "topico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private Boolean status;

    @ManyToOne
    private Usuario autor;

    @ManyToOne
    private Curso curso;

    @OneToMany(mappedBy = "topico")
    private List<Resposta> respostas;

    public void excluir() {
        this.status = false;
    }

}
