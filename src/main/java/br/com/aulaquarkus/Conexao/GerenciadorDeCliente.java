package br.com.aulaquarkus.Conexao;

import oracle.jdbc.pool.OracleDataSource;
import java.sql.Connection;
import java.sql.SQLException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GerenciadorDeCliente {
    private static final Logger logger = Logger.getLogger(GerenciadorDeCliente.class.getName());
    //jdbc:oracle:thin:@localhost:port:service
    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
    private static Connection conn;

    public static Connection conexao() {
        try {
            // Criação do Driver
            OracleDataSource ods = new OracleDataSource();
            // Configurando a url
            ods.setURL(URL);
            // Configurando o user
            ods.setUser(credenciais.user);
            // Configurando a senha
            ods.setPassword(credenciais.pwd);

            // Obtendo uma conexão com o jdbc
            conn = ods.getConnection();
            logger.info("Conectado!");
            return conn;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException ao tentar conectar: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro desconhecido ao tentar conectar: " + e.getMessage(), e);
        }
        return null;
    }

    public static void fecharConexao() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                logger.info("Conexão fechada.");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao fechar conexão: " + e.getMessage(), e);
        }
    }
}
