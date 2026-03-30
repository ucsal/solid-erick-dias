package br.com.ucsal.olimpiadas;

import br.com.ucsal.olimpiadas.services.ParticipanteService;
import br.com.ucsal.olimpiadas.services.ProvaService;
import br.com.ucsal.olimpiadas.services.TentativaService;
import java.util.Scanner;

public class App {

    static long proximaProvaId = 1;
    static long proximaQuestaoId = 1;
    static long proximaTentativaId = 1;

    static final ParticipanteService participanteService = new ParticipanteService();
    static final ProvaService provaService = new ProvaService();
    static final TentativaService tentativaService = new TentativaService();
    static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        seed();

        while (true) {
            System.out.println("\n=== OLIMPÍADA DE QUESTÕES (V1) ===");
            System.out.println("1) Cadastrar participante");
            System.out.println("2) Cadastrar prova");
            System.out.println("3) Cadastrar questão (A–E) em uma prova");
            System.out.println("4) Aplicar prova (selecionar participante + prova)");
            System.out.println("5) Listar tentativas (resumo)");
            System.out.println("0) Sair");
            System.out.print("> ");

            switch (in.nextLine()) {
                case "1" -> cadastrarParticipante();
                case "2" -> cadastrarProva();
                case "3" -> cadastrarQuestao();
                case "4" -> aplicarProva();
                case "5" -> listarTentativas();
                case "0" -> {
                    System.out.println("Saindo...");
                    return;
                }
                default -> System.out.println("Opção inválida");
            }
        }
    }

    static void cadastrarParticipante() {
        System.out.print("Nome: ");
        var nome = in.nextLine();

        System.out.print("Email (opcional): ");
        var email = in.nextLine();

        if (nome == null || nome.isBlank()) {
            System.out.println("Nome inválido");
            return;
        }

        var participante = new Participante();
        participante.setId(participanteService.listarParticipantes().size() + 1);
        participante.setNome(nome);
        participante.setEmail(email);

        participanteService.adicionarParticipante(participante);
        System.out.println("Participante cadastrado: " + participante.getId());
    }

    static void cadastrarProva() {
        System.out.print("Título da prova: ");
        var titulo = in.nextLine();

        if (titulo == null || titulo.isBlank()) {
            System.out.println("Título inválido");
            return;
        }

        var prova = new Prova();
        prova.setId(provaService.listarProvas().size() + 1);
        prova.setTitulo(titulo);

        provaService.adicionarProva(prova);
        System.out.println("Prova cadastrada: " + prova.getId());
    }

    static void listarTentativas() {
        System.out.println("\n--- Tentativas ---");
        // Implementação futura para listar tentativas
    }

    static void cadastrarQuestao() {
        System.out.println("Questão cadastrada com sucesso.");
    }

    static void aplicarProva() {
        if (participanteService.listarParticipantes().isEmpty()) {
            System.out.println("Nenhum participante cadastrado.");
            return;
        }

        if (provaService.listarProvas().isEmpty()) {
            System.out.println("Nenhuma prova cadastrada.");
            return;
        }

        System.out.println("Selecione um participante:");
        participanteService.listarParticipantes().forEach(participante ->
            System.out.println(participante.getId() + ") " + participante.getNome())
        );
        System.out.print("> ");
        long participanteId = Long.parseLong(in.nextLine());
        var participante = participanteService.buscarParticipantePorId(participanteId);

        if (participante == null) {
            System.out.println("Participante não encontrado.");
            return;
        }

        System.out.println("Selecione uma prova:");
        provaService.listarProvas().forEach(prova ->
            System.out.println(prova.getId() + ") " + prova.getTitulo())
        );
        System.out.print("> ");
        long provaId = Long.parseLong(in.nextLine());
        var prova = provaService.buscarProvaPorId(provaId);

        if (prova == null) {
            System.out.println("Prova não encontrada.");
            return;
        }

        var tentativa = new Tentativa();
        tentativa.setId(proximaTentativaId++);
        tentativa.setParticipanteId(participanteId);
        tentativa.setProvaId(provaId);

        tentativaService.adicionarTentativa(tentativa);
        System.out.println("Prova aplicada com sucesso para o participante " + participante.getNome() + ".");
    }

    static void seed() {
        System.out.println("Dados iniciais carregados.");
    }
}
