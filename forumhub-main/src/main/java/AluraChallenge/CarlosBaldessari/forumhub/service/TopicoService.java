package AluraChallenge.CarlosBaldessari.forumhub.service;

import AluraChallenge.CarlosBaldessari.forumhub.domain.topico.DadosAtualizacaoTopico;
import AluraChallenge.CarlosBaldessari.forumhub.domain.curso.Curso;
import AluraChallenge.CarlosBaldessari.forumhub.domain.curso.CursoRepository;
import AluraChallenge.CarlosBaldessari.forumhub.domain.topico.TopicoRepository;
import AluraChallenge.CarlosBaldessari.forumhub.domain.usuario.Usuario;
import AluraChallenge.CarlosBaldessari.forumhub.domain.usuario.UsuarioRepository;
import AluraChallenge.CarlosBaldessari.forumhub.infra.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public Topico cadastrar(DadosCadastroTopico dados) {
        // Verificar se o autor existe
        Usuario autor = usuarioRepository.findById(dados.autorId())
                .orElseThrow(() -> new IllegalArgumentException("Autor não encontrado"));

        // Verificar se o curso existe
        Curso curso = cursoRepository.findById(dados.cursoId())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));

        // Verificar duplicidade
        if (topicoRepository.findByTituloAndMensagem(dados.titulo(), dados.mensagem()).isPresent()) {
            throw new IllegalArgumentException("Tópico com o mesmo título e mensagem já existe");
        }

        // Criar e salvar o tópico
        Topico topico = new Topico();
        topico.setTitulo(dados.titulo());
        topico.setMensagem(dados.mensagem());
        topico.setAutor(autor);
        topico.setCurso(curso);
        topico.setDataCriacao(LocalDateTime.now());
        topico.setStatus(true); // Supondo que 'true' significa ativo

        return topico;
    }

    public Page<DadosListagemTopico> listar(Pageable paginacao) {
        return topicoRepository.findAllByStatusTrue(paginacao)
                .map(DadosListagemTopico::new);
    }

    public Page<DadosListagemTopico> listarPorCursoEAno(String cursoNome, int ano, Pageable paginacao) {
        return topicoRepository.findAllByCursoNomeAndDataCriacaoYear(cursoNome, ano, paginacao)
                .map(DadosListagemTopico::new);
    }

    public DadosDetalhamentoTopico detalhar(Long id) {
        var topico = topicoRepository.findByIdAndStatusTrue(id);
        if (topico == null) {
            throw new IllegalArgumentException("Tópico com ID " + id + " não encontrado ou está inativo.");
        }
        return new DadosDetalhamentoTopico(topico);
    }

    public Topico atualizar(DadosAtualizacaoTopico dados) {
        // Verificar se o tópico existe
        var topicoOptional = topicoRepository.findById(dados.id());
        if (topicoOptional.isEmpty()) {
            throw new ResourceNotFoundException("Tópico com ID " + dados.id() + " não encontrado.");
        }

        // Atualizar os campos do tópico
        var topico = topicoOptional.get();
        topico.setTitulo(dados.titulo());
        topico.setMensagem(dados.mensagem());

        // Salvar o tópico atualizado
        return topicoRepository.save(topico);
    }
}
