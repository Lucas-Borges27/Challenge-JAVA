package br.com.aulaquarkus.model.view;

import java.util.Scanner;

public class LoginView {

    private Scanner scanner;

    public LoginView(){
        scanner = new Scanner(System.in);
    }

    public String obterEmailUsuario(){
        System.out.println("Email: ");
        return  scanner.nextLine();
    }

    public String obterSenha(){
        System.out.println("Senha: ");
        return  scanner.nextLine();
    }

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}
