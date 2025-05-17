package br.com.aulaquarkus.service;

import br.com.aulaquarkus.model.VO.UsuarioVO;
import br.com.aulaquarkus.model.BO.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheEntity_;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class UsuarioService {

    /**
     * Lista todos os usuários cadastrados no sistema.
     *
     * @return Lista de objetos UsuarioVO representando os usuários.
     */
    public List<UsuarioVO> listarTodos() {
        List<Usuario> usuarios = Usuario.listAll();
        return usuarios.stream().map(this::toVO).toList();
    }

    /**
     * Busca um usuário pelo seu ID.
     *
     * @param id ID do usuário a ser buscado.
     * @return Objeto UsuarioVO correspondente ao usuário encontrado.
     * @throws NotFoundException se o usuário não for encontrado.
     */
    public UsuarioVO buscarPorId(long id) {
        Usuario usuario = (Usuario) Usuario.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Usuário com ID " + id + " não encontrado"));
        return toVO(usuario);
    }

    /**
     * Cria um usuário no sistema.
     *
     * @param voUsuario Objeto UsuarioVO contendo os dados do usuário a ser criado.
     * @return Objeto UsuarioVO do usuário criado.
     */
    @Transactional
    public UsuarioVO criar(UsuarioVO voUsuario) {
        System.out.println("Dados recebidos: " + voUsuario); // Log dos dados de entrada
        Usuario usuario = toBO(voUsuario);
        System.out.println("Após conversão para BO: " + usuario); // Log após conversão
        usuario.persist();
        System.out.println("Após persistência: " + usuario); // Log após persistir
        return toVO(usuario);
    }

    /**
     * Atualiza os dados de um usuário existente.
     *
     * @param id                  ID do usuário a ser atualizado.
     * @param voUsuarioAtualizado Objeto UsuarioVO com os dados atualizados.
     * @return Objeto UsuarioVO atualizado.
     * @throws NotFoundException se o usuário não for encontrado.
     */
    @Transactional
    public UsuarioVO atualizar(Long id, UsuarioVO voUsuarioAtualizado) {
        Usuario usuarioExistente = (Usuario) Usuario.findByIdOptional(PanacheEntity_.id)
                .orElseThrow(() -> new NotFoundException("Usuário com ID " + PanacheEntity_.id + " não encontrado"));

        usuarioExistente.nome = voUsuarioAtualizado.getNome();
        usuarioExistente.senha = voUsuarioAtualizado.getSenha();
        usuarioExistente.email = voUsuarioAtualizado.getEmail();

        return toVO(usuarioExistente);
    }

    /**
     * Remove um usuário do sistema pelo seu ID.
     *
     * @param id ID do usuário a ser removido.
     * @return true se a remoção foi bem-sucedida, false caso contrário.
     */
    @Transactional
    public boolean deletar(Long id) {
        return Usuario.deleteById(id);
    }

    private br.com.aulaquarkus.model.DAO.UsuarioDAO usuarioDAO;

    /**
     * Construtor da classe UsuarioService.
     * Inicializa o objeto UsuarioDAO para operações de banco de dados.
     */
    public UsuarioService() {
        this.usuarioDAO = new br.com.aulaquarkus.model.DAO.UsuarioDAO();
    }

    /**
     * Autentica um usuário pelo email e senha.
     *
     * @param email Email do usuário.
     * @param senha Senha do usuário.
     * @return Objeto UsuarioVO do usuário autenticado.
     * @throws SQLException em caso de erro no banco de dados.
     */
    public UsuarioVO autenticar(String email, String senha) throws SQLException {
        if (email == null || email.isEmpty() || senha == null || senha.isEmpty()) {
            throw new IllegalArgumentException("Email e senha não podem ser vazios.");
        }
        return usuarioDAO.autenticar(email, senha);
    }

    /**
     * Converte um objeto Usuario para UsuarioVO.
     *
     * @param usuario Objeto Usuario a ser convertido.
     * @return Objeto UsuarioVO correspondente.
     * @throws IllegalArgumentException se o usuário for nulo.
     */
    private UsuarioVO toVO(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo");
        }

        // Validando e definindo valores padrão se necessário
        String nome = (usuario.nome != null) ? usuario.nome : "";
        String email = (usuario.email != null) ? usuario.email : "";
        String senha = (usuario.senha != null) ? usuario.senha : "";

        UsuarioVO vo = new UsuarioVO(nome, email, senha);
        vo.setId(Math.toIntExact(usuario.id));
        return vo;
    }

    /**
     * Converte um objeto UsuarioVO para Usuario.
     *
     * @param vo Objeto UsuarioVO a ser convertido.
     * @return Objeto Usuario correspondente.
     */
    private Usuario toBO(UsuarioVO vo) {
        Usuario usuario = new Usuario();
        usuario.nome = vo.getNome();
        usuario.email = vo.getEmail();
        usuario.senha = vo.getSenha();

        if (vo.getId() > 0) {
            usuario.id = (long) vo.getId();
        }
        return usuario;
    }

    /**
     * Realiza o login de um usuário com email e senha.
     *
     * @param email Email do usuário.
     * @param senha Senha do usuário.
     * @return Objeto UsuarioVO do usuário autenticado.
     * @throws RuntimeException em caso de erro no login.
     */
    @Transactional
    public UsuarioVO login(String email, String senha) {
        try {
            // Validações básicas
            if (email == null || email.trim().isEmpty()) {
                throw new IllegalArgumentException("O email não pode estar vazio");
            }
            if (senha == null || senha.trim().isEmpty()) {
                throw new IllegalArgumentException("A senha não pode estar vazia");
            }

            System.out.println("Tentando autenticar com email: " + email);
            // Tenta autenticar o usuário
            UsuarioVO usuario = autenticar(email, senha);
            System.out.println("Resultado da autenticação: " + (usuario != null ? "usuário encontrado" : "usuário não encontrado"));

            if (usuario == null) {
                throw new SecurityException("Email ou senha inválidos");
            }

            return usuario;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao realizar login: " + e.getMessage(), e);
        }
    }
}

