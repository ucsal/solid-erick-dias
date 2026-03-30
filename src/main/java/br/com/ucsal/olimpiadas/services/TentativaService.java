package br.com.ucsal.olimpiadas.services;

import br.com.ucsal.olimpiadas.Tentativa;
import br.com.ucsal.olimpiadas.Resposta;
import java.util.ArrayList;
import java.util.List;

public class TentativaService {

    private final List<Tentativa> tentativas = new ArrayList<>();

    public void adicionarTentativa(Tentativa tentativa) {
        tentativas.add(tentativa);
    }

    public List<Tentativa> listarTentativas() {
        return new ArrayList<>(tentativas);
    }

    public int calcularNota(Tentativa tentativa) {
        int nota = 0;
        for (Resposta resposta : tentativa.getRespostas()) {
            if (resposta.isCorreta()) {
                nota++;
            }
        }
        return nota;
    }
}
