package br.com.ucsal.olimpiadas;

import java.util.List;

public class CorretorProva {

    public int calcularNota(Tentativa tentativa, List<Questao> questoes) {
        int nota = 0;

        for (Resposta resposta : tentativa.getRespostas()) {
            for (Questao questao : questoes) {
                if (questao.getId() == resposta.getQuestaoId() && questao.isRespostaCorreta(resposta.getAlternativaMarcada())) {
                    nota++;
                }
            }
        }

        return nota;
    }
}
