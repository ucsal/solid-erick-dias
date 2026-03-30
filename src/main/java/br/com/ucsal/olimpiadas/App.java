package br.com.ucsal.olimpiadas;

import br.com.ucsal.olimpiadas.services.ParticipanteService;
import java.util.Scanner;

public class App {

    static long proximaProvaId = 1;
    static long proximaQuestaoId = 1;
    static long proximaTentativaId = 1;

    static final ParticipanteService participanteService = new ParticipanteService();
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

    static Long escolherParticipante() {
        System.out.println("\nParticipantes:");
        for (var p : participanteService.listarParticipantes()) {
            System.out.printf("  %d) %s%n", p.getId(), p.getNome());
        }
        System.out.print("Escolha o id do participante: ");

        try {
            long id = Long.parseLong(in.nextLine());
            var participante = participanteService.buscarParticipantePorId(id);
            if (participante == null) {
                System.out.println("ID inválido");
                return null;
            }
            return id;
        } catch (Exception e) {
            System.out.println("Entrada inválida");
            return null;
        }
    }

    static void seed() {

        var prova = new Prova();
        prova.setId(proximaProvaId++);
        prova.setTitulo("Olimpíada 2026 • Nível 1 • Prova A");
        provas.add(prova);

        var q1 = new Questao();
        q1.setId(proximaQuestaoId++);
        q1.setProvaId(prova.getId());

        q1.setEnunciado("""
                Questão 1 — Mate em 1.
                É a vez das brancas.
                Encontre o lance que dá mate imediatamente.
                """);

        q1.setFenInicial("6k1/5ppp/8/8/8/7Q/6PP/6K1 w - - 0 1");

        q1.setAlternativas(new String[] { "A) Qh7#", "B) Qf5#", "C) Qc8#", "D) Qh8#", "E) Qe6#" });

        q1.setAlternativaCorreta('C');

        questoes.add(q1);
    }

    static void cadastrarProva() {
        System.out.print("Título da prova: ");
        var titulo = in.nextLine();

        if (titulo == null || titulo.isBlank()) {
            System.out.println("título inválido");
            return;
        }

        var prova = new Prova();
        prova.setId(proximaProvaId++);
        prova.setTitulo(titulo);

        provas.add(prova);
        System.out.println("Prova criada: " + prova.getId());
    }

    static void cadastrarQuestao() {
        if (provas.isEmpty()) {
            System.out.println("não há provas cadastradas");
            return;
        }

        var provaId = escolherProva();
        if (provaId == null)
            return;

        System.out.println("Enunciado:");
        var enunciado = in.nextLine();

        var alternativas = new String[5];
        for (int i = 0; i < 5; i++) {
            char letra = (char) ('A' + i);
            System.out.print("Alternativa " + letra + ": ");
            alternativas[i] = letra + ") " + in.nextLine();
        }

        System.out.print("Alternativa correta (A–E): ");
        char correta;
        try {
            correta = Questao.normalizar(in.nextLine().trim().charAt(0));
        } catch (Exception e) {
            System.out.println("alternativa inválida");
            return;
        }

        var q = new Questao();
        q.setId(proximaQuestaoId++);
        q.setProvaId(provaId);
        q.setEnunciado(enunciado);
        q.setAlternativas(alternativas);
        q.setAlternativaCorreta(correta);

        questoes.add(q);

        System.out.println("Questão cadastrada: " + q.getId() + " (na prova " + provaId + ")");
    }


    static void aplicarProva() {
        if (participantes.isEmpty()) {
            System.out.println("cadastre participantes primeiro");
            return;
        }
        if (provas.isEmpty()) {
            System.out.println("cadastre provas primeiro");
            return;
        }

        var participanteId = escolherParticipante();
        if (participanteId == null)
            return;

        var provaId = escolherProva();
        if (provaId == null)
            return;

        var questoesDaProva = questoes.stream().filter(q -> q.getProvaId() == provaId).toList();

        if (questoesDaProva.isEmpty()) {
            System.out.println("esta prova não possui questões cadastradas");
            return;
        }

        var tentativa = new Tentativa();
        tentativa.setId(proximaTentativaId++);
        tentativa.setParticipanteId(participanteId);
        tentativa.setProvaId(provaId);

        System.out.println("\n--- Início da Prova ---");

        for (var q : questoesDaProva) {
            System.out.println("\nQuestão #" + q.getId());
            System.out.println(q.getEnunciado());

            System.out.println("Posição inicial:");
            imprimirTabuleiroFen(q.getFenInicial());

            for (var alt : q.getAlternativas()) {
                System.out.println(alt);
            }

            System.out.print("Sua resposta (A–E): ");
            char marcada;
            try {
                marcada = Questao.normalizar(in.nextLine().trim().charAt(0));
            } catch (Exception e) {
                System.out.println("resposta inválida (marcando como errada)");
                marcada = 'X';
            }

            var r = new Resposta();
            r.setQuestaoId(q.getId());
            r.setAlternativaMarcada(marcada);
            r.setCorreta(q.isRespostaCorreta(marcada));

            tentativ
