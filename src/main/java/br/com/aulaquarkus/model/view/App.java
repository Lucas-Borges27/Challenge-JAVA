package br.com.aulaquarkus.model.view;

import br.com.aulaquarkus.model.VO.Acessibilidade;
import br.com.aulaquarkus.model.BO.Linha;
import br.com.aulaquarkus.model.VO.UsuarioVO;

import java.util.Scanner;

/**
 * Classe principal do aplicativo.
 * Gerencia o fluxo principal e a interface do usuário.
 */
public class App {
    /**
     * Nome do aplicativo.
     */
    private String nome;

    /**
     * Versão do aplicativo.
     */
    private String versao;

    /**
     * Objeto de acessibilidade associado ao aplicativo.
     */
    private Acessibilidade acessibilidade;

    /**
     * Usuário atual do aplicativo.
     */
    private UsuarioVO usuario;

    /**
     * Linha associada ao aplicativo.
     */
    private Linha linha;

    /**
     * Construtor da classe App.
     * @param nome Nome do aplicativo.
     * @param versao Versão do aplicativo.
     */
    public App(String nome, String versao) {
        this.nome = nome;
        this.versao = versao;
    }

    /**
     * Inicia o aplicativo.
     * Exibe mensagem de inicialização.
     */
    public void iniciar() {
        System.out.println("App " + nome + " iniciado.");
    }

    /**
     * Carrega os dados necessários para o aplicativo.
     * Exibe mensagem de carregamento.
     */
    public void carregarDados() {
        System.out.println("Carregando dados do app...");
    }

    /**
     * Exibe a interface do usuário com opções interativas.
     * Permite ao usuário escolher ações como mostrar acessibilidade, dados do usuário, estações da linha e simular trajeto.
     */
    public void exibirInterface() {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do { System.out.println("\n--- Menu de Opções ---");
            System.out.println("1. Mostrar acessibilidade");
            System.out.println("2. Mostrar dados do usuário");
            System.out.println("3. Mostrar estações da linha");
            System.out.println("4. Simular trecho de trajeto");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (opcao) {
                case 1: acessibilidade.mostrarOpcoes();
                    break;
                case 2: System.out.println(usuario);
                    break;
                case 3: linha.mostrarEstacoes();
                    break;
                case 4: linha.simularTrechoTrajeto();
                    break;
                case 0: System.out.println("Saindo..."); break;
                default: System.out.println("Opção inválida. Tente novamente."); break;
            }
        } while (opcao != 0);
        scanner.close();
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public Acessibilidade getAcessibilidade() {
        return acessibilidade;
    }

    public void setAcessibilidade(Acessibilidade acessibilidade) {
        this.acessibilidade = acessibilidade;
    }

    public UsuarioVO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioVO usuario) {
        this.usuario = usuario;
    }

    public Linha getLinha() {
        return linha;
    }

    public void setLinha(Linha linha) {
        this.linha = linha;
    }
}
