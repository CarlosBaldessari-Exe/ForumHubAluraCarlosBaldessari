package AluraChallenge.CarlosBaldessari.forumhub.controller;


import com.rangelmrk.forumhub.domain.topico.*;
import AluraChallenge.CarlosBaldessari.forumhub.infra.exception.ResourceNotFoundException;
import AluraChallenge.CarlosBaldessari.forumhub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;
    @Autowired
    private TopicoService service;

    @PostMapping
    @Transactional
    public ResponseEntity criar(@RequestBody @Valid DadosCadastroTopico dados, UriComponentsBuilder uriBuilder){
        var topico = service.cadastrar(dados);
        repository.save(topico);

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoTopico(topico));

    }
    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listar(
            @PageableDefault(size = 10, sort = {"dataCriacao"}) Pageable paginacao) {
        var page = service.listar(paginacao);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoTopico> detalhar(@PathVariable Long id) {
        var detalhes = service.detalhar(id);
        return ResponseEntity.ok(detalhes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid DadosAtualizacaoTopico dados) {
        // Garantir que o ID do PathVariable corresponda ao do DTO
        if (!id.equals(dados.id())) {
            return ResponseEntity.badRequest()
                    .body("O ID do corpo da requisição não corresponde ao ID da URL.");
        }

        var topicoAtualizado = new DadosDetalhamentoTopico(service.atualizar(dados));

        return ResponseEntity.ok(topicoAtualizado);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        var topico = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tópico com ID " + id + " não encontrado."));

        topico.excluir();
        return ResponseEntity.noContent().build();
    }




}
