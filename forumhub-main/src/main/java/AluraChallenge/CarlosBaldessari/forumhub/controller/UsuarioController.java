package AluraChallenge.CarlosBaldessari.forumhub.controller;



import AluraChallenge.CarlosBaldessari.forumhub.domain.usuario.DadosCadastroUsuario;
import AluraChallenge.CarlosBaldessari.forumhub.domain.usuario.DadosDetalhamentoUsuario;
import AluraChallenge.CarlosBaldessari.forumhub.domain.usuario.UsuarioRepository;
import AluraChallenge.CarlosBaldessari.forumhub.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private UsuarioService service;

    @PostMapping("/cadastro")
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder){
       var usuario = service.criarUsuario(dados);
       repository.save(usuario);

        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuario(usuario));
    }
}
