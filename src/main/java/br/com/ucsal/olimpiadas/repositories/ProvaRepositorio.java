package br.com.ucsal.olimpiadas.repositories;

import br.com.ucsal.olimpiadas.Prova;
import java.util.ArrayList;
import java.util.List;

public class ProvaRepositorio {

    private final List<Prova> provas = new ArrayList<>();

    public void adicionarProva(Prova prova) {
        provas.add(prova);
    }

    public Prova buscarProvaPorId(long id) {
        return provas.stream()
                .filter(prova -> prova.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Prova> listarProvas() {
        return new ArrayList<>(provas);
    }
}
