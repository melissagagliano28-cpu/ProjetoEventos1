import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

class Usuario {
    String nomeCompleto;
    String email;
    String senha;
    String cpf;
}

class Evento {
    String nome;
    String endereco;
    String categoria;
    String horario;
    String descricao;
}

public class Sistema {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        ArrayList<Usuario> listaDeUsuarios = new ArrayList<>();
        ArrayList<Evento> listaDeEventos = new ArrayList<>();

        try {
            File arquivo = new File("events.data");
            Scanner leitorDeArquivo = new Scanner(arquivo);

            while (leitorDeArquivo.hasNextLine()) {
                String linhaDoEvento = leitorDeArquivo.nextLine();
                String[] dadosDoEvento = linhaDoEvento.split(";");

                Evento eventoCarregado = new Evento();
                eventoCarregado.nome = dadosDoEvento[0];
                eventoCarregado.endereco = dadosDoEvento[1];
                eventoCarregado.categoria = dadosDoEvento[2];
                eventoCarregado.horario = dadosDoEvento[3];
                eventoCarregado.descricao = dadosDoEvento[4];

                listaDeEventos.add(eventoCarregado);
            }
            leitorDeArquivo.close();
            System.out.println("Eventos salvos anteriormente foram carregados!");
        } catch (Exception e) {
            System.out.println("Nenhum evento salvo anteriormente encontrado. Começando do zero.");
        }

        while (true) {
            System.out.println("\n===========================");
            System.out.println("MENU PRINCIPAL DE EVENTOS");
            System.out.println("===========================");
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Criar perfil de usuário");
            System.out.println("2 - Ver todos os eventos");
            System.out.println("3 - Cadastrar novo evento");
            System.out.println("0 - Sair do programa");
            System.out.println("===========================");

            System.out.print("Digite sua opção: ");
            int opcao = leitor.nextInt();
            leitor.nextLine();

            if (opcao == 0) {
                System.out.println("Saindo do programa. Até mais!");
                break;
            }

            switch (opcao) {
                case 1:
                    System.out.println("--- Cadastro de Novo Usuário ---");
                    Usuario novoUsuario = new Usuario();

                    System.out.print("Digite o nome completo: ");
                    novoUsuario.nomeCompleto = leitor.nextLine();

                    System.out.print("Digite o email: ");
                    novoUsuario.email = leitor.nextLine();

                    System.out.print("Digite uma senha: ");
                    novoUsuario.senha = leitor.nextLine();

                    System.out.print("Digite seu cpf: ");
                    novoUsuario.cpf = leitor.nextLine();

                    listaDeUsuarios.add(novoUsuario);
                    System.out.println("Usuário cadastrado com sucesso!");
                    break;
                case 2:
                    System.out.println("\n--- Lista de Eventos Cadastrados ---");
                    if (listaDeEventos.isEmpty()) {
                        System.out.println("Nenhum evento cadastrado no momento.");
                    } else {
                        for (Evento eventoDaVez : listaDeEventos) {
                            System.out.println("Nome: " + eventoDaVez.nome);
                            System.out.println("Endereço: " + eventoDaVez.endereco);
                            System.out.println("Categoria: " + eventoDaVez.categoria);
                            System.out.println("Horário: " + eventoDaVez.horario);
                            System.out.println("Descrição: " + eventoDaVez.descricao);
                            System.out.println("------------------------------------");
                        }
                    }
                    break;
                case 3:
                    System.out.println("--- Cadastro de Novo Evento ---");
                    Evento novoEvento = new Evento();

                    System.out.print("Digite o nome do Evento: ");
                    novoEvento.nome = leitor.nextLine();

                    System.out.print("Digite o endereco: ");
                    novoEvento.endereco = leitor.nextLine();

                    System.out.print("Digite a categoria: ");
                    novoEvento.categoria = leitor.nextLine();

                    System.out.print("Digite a descricao: ");
                    novoEvento.descricao = leitor.nextLine();

                    System.out.print("Digite o horario: ");
                    novoEvento.horario = leitor.nextLine();

                    listaDeEventos.add(novoEvento);

                    try {
                        FileWriter fileWriter = new FileWriter("events.data", true);
                        PrintWriter arquivoDeEventos = new PrintWriter(fileWriter);
                        String linhaParaSalvar = novoEvento.nome + ";" + novoEvento.endereco + ";" + novoEvento.categoria + ";" + novoEvento.horario + ";" + novoEvento.descricao;
                        arquivoDeEventos.println(linhaParaSalvar);
                        arquivoDeEventos.close();
                        System.out.println("Evento cadastrado e salvo com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Ocorreu um erro ao salvar o evento no arquivo.");
                    }
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
        }
        leitor.close();
    }
}
