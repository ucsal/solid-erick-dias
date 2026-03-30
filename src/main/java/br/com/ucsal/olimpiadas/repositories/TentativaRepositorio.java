package br.com.ucsal.olimpiadas.repositories;

import br.com.ucsal.olimpiadas.Tentativa;
import java.util.ArrayList;
import java.util.List;

public class TentativaRepositorio {

    private final List<Tentativa> tentativas = new ArrayList<>();

    public void adicionarTentativa(Tentativa tentativa) {
        tentativas.add(tentativa);
    }

    public Tentativa buscarTentativaPorId(long id) {
        return tentativas.stream()
                .filter(tentativa -> tentativa.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Tentativa> listarTentativas() {
        return new ArrayList<>(tentativas);
    }
}
