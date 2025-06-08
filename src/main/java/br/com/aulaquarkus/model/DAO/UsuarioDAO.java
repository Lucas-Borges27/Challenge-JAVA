package br.com.aulaquarkus.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.aulaquarkus.Conexao.GerenciadorDeCliente;
import br.com.aulaquarkus.model.VO.UsuarioVO;

/**
 * Classe DAO para operações de banco de dados relacionadas a usuários.
 */
public class UsuarioDAO {

    /**
     * Cadastra um novo usuário no banco de dados.
     * @param usuario Objeto UsuarioVO com os dados do usuário.
     * @throws SQLException em caso de erro no banco de dados.
     */
    public void cadastrarUsuario(UsuarioVO usuario) throws SQLException {
        String sql = "INSERT INTO USUARIOS (NOME, EMAIL, SENHA) VALUES (?, ?, ?)";

        try (Connection conn = GerenciadorDeCliente.conexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setString(3, usuario.getSenha());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }

    /**
     * Autentica um usuário pelo email e senha.
     * @param emailUsuario Email do usuário.
     * @param senha Senha do usuário.
     * @return Objeto UsuarioVO do usuário autenticado ou null se falhar.
     * @throws SQLException em caso de erro no banco de dados.
     */
    public UsuarioVO autenticar(String emailUsuario, String senha) throws SQLException {
        String sqlVerificarEmail = "SELECT * FROM USUARIOS WHERE EMAIL = ?";

        System.out.println("\n=== INÍCIO DA AUTENTICAÇÃO ===");
        System.out.println("Email recebido: " + emailUsuario);
        System.out.println("Senha recebida: " + senha);

        try (Connection conn = GerenciadorDeCliente.conexao();
             PreparedStatement stmt = conn.prepareStatement(sqlVerificarEmail)) {

            stmt.setString(1, emailUsuario);
            System.out.println("\nVerificando email...");
            System.out.println("Query: " + stmt.toString());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Email encontrado no banco!");
                String senhaArmazenada = rs.getString("SENHA");
                System.out.println("Senha do banco: " + senhaArmazenada);
                System.out.println("Senha fornecida: " + senha);

                if (senha.equals(senhaArmazenada)) {
                    System.out.println("Senha corresponde!");
                    UsuarioVO usuario = new UsuarioVO(
                        rs.getString("NOME"),
                        rs.getString("EMAIL"),
                        rs.getString("SENHA")
                    );
                    usuario.setId(rs.getInt("ID"));
                    return usuario;
                } else {
                    System.out.println("Senha não corresponde!");
                    return null;
                }
            } else {
                System.out.println("Email não encontrado no banco!");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro SQL: " + e.getMessage());
            e.printStackTrace();
            throw new SQLException("Erro ao autenticar usuário: " + e.getMessage());
        }
    }

    /**
     * Lista os nomes de todos os usuários cadastrados.
     * @return Lista de nomes de usuários.
     * @throws SQLException em caso de erro no banco de dados.
     */
    public List<String> listarNomesUsuarios() throws SQLException {
        String sql = "SELECT NOME FROM USUARIOS";
        List<String> nomesUsuarios = new ArrayList<>();

        try (Connection conn = GerenciadorDeCliente.conexao();
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                nomesUsuarios.add(resultSet.getString("NOME"));
            }
            return nomesUsuarios;
        } catch (SQLException e) {
            throw new SQLException("Erro ao listar nomes de usuários: " + e.getMessage());
        }
    }

    /**
     * Busca um usuário pelo ID.
     * @param id ID do usuário.
     * @return Objeto UsuarioVO correspondente ou null se não encontrado.
     * @throws SQLException em caso de erro no banco de dados.
     */
    public UsuarioVO buscarUsuarioPorId(int id) throws SQLException {
        String sql = "SELECT * FROM USUARIOS WHERE ID = ?";
        try (Connection conn = GerenciadorDeCliente.conexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                UsuarioVO usuario = new UsuarioVO(
                        rs.getString("NOME"),
                        rs.getString("EMAIL"),
                        rs.getString("SENHA")
                );
                usuario.setId(rs.getInt("ID"));
                return usuario;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar usuário por ID: " + e.getMessage());
        }
    }

    /**
     * Atualiza os dados de um usuário.
     * @param usuario Objeto UsuarioVO com os dados atualizados.
     * @throws SQLException em caso de erro no banco de dados.
     */
    public void atualizarUsuario(UsuarioVO usuario) throws SQLException {
        String sql = "UPDATE USUARIOS SET NOME = ?, EMAIL = ?, SENHA = ?, PREFERENCIA_ACESSIBILIDADE = ? WHERE ID = ?";
        try (Connection conn = GerenciadorDeCliente.conexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setString(3, usuario.getSenha());
            pstmt.setString(4, usuario.getPreferenciaAcessibilidade());
            pstmt.setInt(6, usuario.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    /**
     * Busca os destinos do usuário pelo ID.
     * @param userId ID do usuário.
     * @return String com os destinos do usuário.
     * @throws SQLException em caso de erro no banco de dados.
     */
    public String buscarDestinosPorUsuarioId(int userId) throws SQLException {
        String sql = "SELECT DESTINO FROM USUARIOS WHERE ID = ?";
        try (Connection conn = GerenciadorDeCliente.conexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("DESTINO");
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar destinos do usuário: " + e.getMessage());
        }
    }

    /**
     * Remove um usuário pelo ID.
     * @param id ID do usuário.
     * @throws SQLException em caso de erro no banco de dados.
     */
    public void deletarUsuario(int id) throws SQLException {
        String sql = "DELETE FROM USUARIOS WHERE ID = ?";
        try (Connection conn = GerenciadorDeCliente.conexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar usuário: " + e.getMessage());
        }
    }
}
