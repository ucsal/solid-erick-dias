package br.com.ucsal.olimpiadas.services;

import br.com.ucsal.olimpiadas.Tentativa;
import br.com.ucsal.olimpiadas.Resposta;
import br.com.ucsal.olimpiadas.repositories.TentativaRepositorio;
import java.util.List;

public class TentativaService {

    private final TentativaRepositorio tentativaRepositorio = new TentativaRepositorio();

    public void adicionarTentativa(Tentativa tentativa) {
        tentativaRepositorio.adicionarTentativa(tentativa);
    }

    public List<Tentativa> listarTentativas() {
        return tentativaRepositorio.listarTentativas();
    }

    public Tentativa buscarTentativaPorId(long id) {
        return tentativaRepositorio.buscarTentativaPorId(id);
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
