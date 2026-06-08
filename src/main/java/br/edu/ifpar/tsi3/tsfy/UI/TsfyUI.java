package br.edu.ifpar.tsi3.tsfy.UI;

import br.edu.ifpar.tsi3.tsfy.UI.fachada.FachadaFrontend;
import br.edu.ifpar.tsi3.tsfy.dominio.Musica;
import br.edu.ifpar.tsi3.tsfy.dominio.Playlist;
import br.edu.ifpar.tsi3.tsfy.dominio.Usuario;
import java.util.ArrayList;
import java.util.Scanner;

public class TsfyUI {

    private final Scanner sc = new Scanner(System.in);
    private FachadaFrontend fachada = new FachadaFrontend();
    private Usuario usuarioLogado = null;

    public void rodar() {
        int op;
        do {
            System.out.println("\n--- MENU SPOTIFY ---");
            if (usuarioLogado != null) {
                System.out.println("LOGADO COMO: " + usuarioLogado.getNome());
            } else {
                System.out.println("VOCÊ NÃO ESTÁ LOGADO.");
            }
            System.out.println("1 - Criar Usuario");
            System.out.println("2 - Login");
            System.out.println("3 - Criar Musica");
            System.out.println("4 - Editar Musica");
            System.out.println("5 - Listar Musicas do Sistema");
            System.out.println("6 - Buscar Musica por ID");
            System.out.println("7 - Remover Musica");
            System.out.println("8 - Criar Playlist");
            System.out.println("9 - Listar Minhas Playlists");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1: criarNovoUsuario(); break;
                case 2: fazerLogin(); break;
                case 3: criarMusica(); break;
                case 4: editarMusica(); break;
                case 5: listarMusicas(); break;
                case 6: buscarMusicaPorID(); break;
                case 7: removerMusica(); break;
                case 8: criarPlaylist(); break;
                case 9: listarMinhasPlaylists(); break;
                case 0: System.out.println("Saindo do sistema..."); break;
                default: System.out.println("Opção inválida! Tente novamente.");
            }
        } while (op != 0);
    }

    private void criarNovoUsuario() {
        System.out.println("Informe seu nome:");
        String nome = sc.nextLine();
        System.out.println("Informe seu cpf: ");
        String cpf = sc.nextLine();
        System.out.println("Informe sua senha: ");
        String senha = sc.nextLine();

        fachada.registrarUsuario(cpf, nome, senha);
        System.out.println("Usuário criado com sucesso!");
    }

    private void fazerLogin() {
        System.out.println("Informe seu cpf: ");
        String cpf = sc.nextLine();
        System.out.println("Informe sua senha: ");
        String senha = sc.nextLine();

        this.usuarioLogado = fachada.autenticar(cpf, senha);
        if (this.usuarioLogado != null) {
            System.out.println("Login feito com sucesso! Bem-vindo, " + usuarioLogado.getNome());
        } else {
            System.out.println("ERRO: CPF ou Senha incorretos!");
        }
    }

    private void criarMusica() {
        System.out.println("Informe o título:");
        String titulo = sc.nextLine();
        System.out.println("Informe o compositor:");
        String compositor = sc.nextLine();
        System.out.println("Informe o intérprete:");
        String interprete = sc.nextLine();
        System.out.println("Informe a duração (ex: 3.5):");

        String duracaoStr = sc.nextLine().replace(",", ".");
        double duracao = Double.parseDouble(duracaoStr);

        fachada.registrarMusica(titulo, compositor, interprete, duracao);
        System.out.println("Música adicionada com sucesso!");
    }

    private void listarMusicas() {
        ArrayList<Musica> lista = fachada.listarMusicas();

        if (lista.isEmpty()) {
            System.out.println("\nNenhuma música cadastrada no sistema ainda!");
            return;
        }

        System.out.println("\n--- Músicas do Sistema ---");
        for (int i = 0; i < lista.size(); i++) {
            Musica m = lista.get(i);
            System.out.println("ID [" + i + "] - " + m.getTitulo() + " (Intérprete: " + m.getInterprete() + ")");
        }
    }

    private void editarMusica() {
        ArrayList<Musica> lista = fachada.listarMusicas();
        if (lista.isEmpty()) {
            System.out.println("\nNão há músicas para editar!");
            return;
        }

        listarMusicas();
        System.out.println("\nInforme o ID da música para editar a duração: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println("Informe a nova duração: ");
        String duracaoStr = sc.nextLine().replace(",", ".");
        double novaDuracao = Double.parseDouble(duracaoStr);

        if(fachada.editarMusica(id, novaDuracao)) {
            System.out.println("Música editada com sucesso.");
        } else {
            System.out.println("ERRO: ID não encontrado.");
        }
    }

    private void buscarMusicaPorID() {
        ArrayList<Musica> lista = fachada.listarMusicas();
        if (lista.isEmpty()) {
            System.out.println("\nO sistema não possui músicas para buscar!");
            return;
        }

        System.out.println("Informe o ID da música: ");
        int id = Integer.parseInt(sc.nextLine());
        Musica m = fachada.buscarMusicaPorID(id);

        if (m != null) {
            System.out.println("\n--- Resultado da Busca ---");
            System.out.println("Título: " + m.getTitulo() + " | Compositor: " + m.getCompositor() + " | Duração: " + m.getDuracao());
        } else {
            System.out.println("ERRO: Música não encontrada. Verifique o ID.");
        }
    }

    private void removerMusica() {
        ArrayList<Musica> lista = fachada.listarMusicas();
        if (lista.isEmpty()) {
            System.out.println("\nNão há músicas para remover!");
            return;
        }

        listarMusicas();
        System.out.println("\nInforme o ID da música para remover: ");
        int id = Integer.parseInt(sc.nextLine());

        if(fachada.removerMusica(id)) {
            System.out.println("Música removida com sucesso!");
        } else {
            System.out.println("ERRO: ID inválido.");
        }
    }

    private void criarPlaylist() {
        if (usuarioLogado == null) {
            System.out.println("\nVocê precisa fazer Login antes de criar uma Playlist!");
            return;
        }
        System.out.println("Nome da Playlist:");
        String nome = sc.nextLine();
        System.out.println("Descrição:");
        String desc = sc.nextLine();

        if(fachada.criarPlaylist(usuarioLogado, nome, desc)) {
            System.out.println("Playlist criada com sucesso!");
        } else {
            System.out.println("ERRO: Limite de 10 playlists atingido!");
        }
    }

    private void listarMinhasPlaylists() {
        if (usuarioLogado == null) {
            System.out.println("\nFaça login para ver suas playlists.");
            return;
        }

        Playlist[] vetor = fachada.getMinhasPlaylists();
        int qtd = fachada.getQtdPlaylists();

        System.out.println("\n--- Minhas Playlists ---");
        if (qtd == 0) {
            System.out.println("Você ainda não tem playlists criadas.");
        } else {
            for (int i = 0; i < qtd; i++) {
                System.out.println("ID [" + i + "] " + vetor[i].getNome() + " - " + vetor[i].getDescricao());
            }
        }
    }
}