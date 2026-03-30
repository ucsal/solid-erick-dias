package br.com.ucsal.olimpiadas.services;

import br.com.ucsal.olimpiadas.Prova;
import br.com.ucsal.olimpiadas.repositories.ProvaRepositorio;
import java.util.List;

public class ProvaService {

    private final ProvaRepositorio provaRepositorio = new ProvaRepositorio();

    public void adicionarProva(Prova prova) {
        provaRepositorio.adicionarProva(prova);
    }

    public Prova buscarProvaPorId(long id) {
        return provaRepositorio.buscarProvaPorId(id);
    }

    public List<Prova> listarProvas() {
        return provaRepositorio.listarProvas();
    }
}
