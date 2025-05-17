package br.com.aulaquarkus;

import br.com.aulaquarkus.Conexao.GerenciadorDeCliente;
import br.com.aulaquarkus.model.view.App;
import br.com.aulaquarkus.model.VO.Acessibilidade;
import br.com.aulaquarkus.model.DAO.UsuarioDAO;
import br.com.aulaquarkus.model.BO.Estacao;
import br.com.aulaquarkus.model.BO.Linha;
import br.com.aulaquarkus.model.VO.UsuarioVO;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        App app = new App("AppMobi", "1.0");
        app.iniciar();
        app.carregarDados();

        // Instantiation of Client Manager and User Registration
        GerenciadorDeCliente.conexao();

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        UsuarioVO usuario = new UsuarioVO("NomeUsuario", "email@exemplo.com", "senha123");

        usuarioDAO.cadastrarUsuario(usuario);

        // Instantiation of Stations and Line
        Estacao estacao1 = new Estacao();
        estacao1.nome = "Luz";
        estacao1.localizacao = "Centro";
        estacao1.temAcessibilidade = true;

        Estacao estacao2 = new Estacao();
        estacao2.nome = "República";
        estacao2.localizacao = "Centro";
        estacao2.temAcessibilidade = true;

        Estacao estacao3 = new Estacao();
        estacao3.nome = "Higienópolis-Mackenzie";
        estacao3.localizacao = "Zona Central";
        estacao3.temAcessibilidade = true;

        Estacao estacao4 = new Estacao();
        estacao4.nome = "Paulista";
        estacao4.localizacao = "Zona Central";
        estacao4.temAcessibilidade = true;

        Estacao estacao5 = new Estacao();
        estacao5.nome = "Fradique Coutinho";
        estacao5.localizacao = "Zona Oeste";
        estacao5.temAcessibilidade = true;

        Estacao estacao6 = new Estacao();
        estacao6.nome = "Faria Lima";
        estacao6.localizacao = "Zona Oeste";
        estacao6.temAcessibilidade = true;

        Estacao estacao7 = new Estacao();
        estacao7.nome = "Pinheiros";
        estacao7.localizacao = "Zona Oeste";
        estacao7.temAcessibilidade = true;

        Estacao estacao8 = new Estacao();
        estacao8.nome = "Butantã";
        estacao8.localizacao = "Zona Oeste";
        estacao8.temAcessibilidade = true;

        Estacao estacao9 = new Estacao();
        estacao9.nome = "São Paulo-Morumbi";
        estacao9.localizacao = "Zona Oeste";
        estacao9.temAcessibilidade = true;

        Estacao estacao10 = new Estacao();
        estacao10.nome = "Vila Sônia";
        estacao10.localizacao = "Zona Oeste";
        estacao10.temAcessibilidade = true;

        Linha linha4 = new Linha();
        linha4.nome = "Linha 4 - Amarela";
        linha4.estacoes = new java.util.ArrayList<>();
        linha4.adicionarEstacao(estacao1);
        linha4.adicionarEstacao(estacao2);
        linha4.adicionarEstacao(estacao3);
        linha4.adicionarEstacao(estacao4);
        linha4.adicionarEstacao(estacao5);
        linha4.adicionarEstacao(estacao6);
        linha4.adicionarEstacao(estacao7);
        linha4.adicionarEstacao(estacao8);
        linha4.adicionarEstacao(estacao9);
        linha4.adicionarEstacao(estacao10);

        // Accessibility Configuration
        Acessibilidade acessibilidade = new Acessibilidade("Accessibility options at Osasco station - Line 9 Esmeralda: " +
                "Adapted Bathroom, Accessible Station, Access Ramps, Platform Crossing, Escalator and Elevator Access",
                true, true);

        // App Configuration
        app.setUsuario(usuario);
        app.setLinha(linha4);
        app.setAcessibilidade(acessibilidade);

        // Display App Interface
        app.exibirInterface();
    }
}
