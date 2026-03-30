package br.com.ucsal.olimpiadas;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;

public class ExemploTest {

    @Test
    void teste() {
        assertTrue(true);
    }

    @Test
    void testCalcularNota() {
        // Criar questões
        Questao questao1 = new Questao();
        questao1.setId(1);
        questao1.setAlternativaCorreta('A');

        Questao questao2 = new Questao();
        questao2.setId(2);
        questao2.setAlternativaCorreta('B');

        List<Questao> questoes = new ArrayList<>();
        questoes.add(questao1);
        questoes.add(questao2);

        // Criar tentativa
        Tentativa tentativa = new Tentativa();
        Resposta resposta1 = new Resposta();
        resposta1.setQuestaoId(1);
        resposta1.setAlternativaMarcada('A');

        Resposta resposta2 = new Resposta();
        resposta2.setQuestaoId(2);
        resposta2.setAlternativaMarcada('C');

        tentativa.getRespostas().add(resposta1);
        tentativa.getRespostas().add(resposta2);

        // Calcular nota
        CorretorProva corretor = new CorretorProva();
        int nota = corretor.calcularNota(tentativa, questoes);

        // Verificar resultado
        assertEquals(1, nota);
    }
}
