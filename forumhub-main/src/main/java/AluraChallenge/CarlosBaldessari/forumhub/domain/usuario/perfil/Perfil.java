package AluraChallenge.CarlosBaldessari.forumhub.domain.usuario.perfil;

import AluraChallenge.CarlosBaldessari.forumhub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity(name = "Perfil")
@Table(name = "perfil")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @ManyToMany(mappedBy = "perfis")
    private Set<Usuario> usuarios = new HashSet<>();

}
