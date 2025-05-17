package br.com.aulaquarkus.Conexao;

import oracle.jdbc.pool.OracleDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class GerenciadorDeCliente {
    //jdbc:oracle:thin:@localhost:port:service
    private static final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
    private static Connection conn;

    public static Connection conexao() throws SQLException {
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
        System.out.println("Conectado!");
        return conn;
    }

    public static void fecharConexao() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
            System.out.println("Conexão fechada.");
        }
    }
}
