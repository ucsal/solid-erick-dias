package br.com.ucsal.olimpiadas.services;

import br.com.ucsal.olimpiadas.Participante;
import br.com.ucsal.olimpiadas.repositories.ParticipanteRepositorio;
import java.util.List;

public class ParticipanteService {

    private final ParticipanteRepositorio participanteRepositorio = new ParticipanteRepositorio();

    public void adicionarParticipante(Participante participante) {
        participanteRepositorio.adicionarParticipante(participante);
    }

    public Participante buscarParticipantePorId(long id) {
        return participanteRepositorio.buscarParticipantePorId(id);
    }

    public List<Participante> listarParticipantes() {
        return participanteRepositorio.listarParticipantes();
    }
}
