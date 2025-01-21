package AluraChallenge.CarlosBaldessari.forumhub.domain.usuario;

public record DadosDetalhamentoUsuario(Long id, String email, String nome) {
    public DadosDetalhamentoUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getEmail(), usuario.getNome());
    }
}
