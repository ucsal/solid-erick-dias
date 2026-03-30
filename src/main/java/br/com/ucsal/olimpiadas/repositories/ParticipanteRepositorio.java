package br.com.ucsal.olimpiadas.repositories;

import br.com.ucsal.olimpiadas.Participante;
import java.util.ArrayList;
import java.util.List;

public class ParticipanteRepositorio {

    private final List<Participante> participantes = new ArrayList<>();

    public void adicionarParticipante(Participante participante) {
        participantes.add(participante);
    }

    public Participante buscarParticipantePorId(long id) {
        return participantes.stream()
                .filter(participante -> participante.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Participante> listarParticipantes() {
        return new ArrayList<>(participantes);
    }
}
