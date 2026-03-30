package br.com.ucsal.olimpiadas;

import java.util.ArrayList;
import java.util.List;

public class Prova {

	private long id;
	private String titulo;
	private final List<Questao> questoes = new ArrayList<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void adicionarQuestao(Questao questao) {
		if (questao == null) {
			throw new IllegalArgumentException("Questão não pode ser nula.");
		}
		questoes.add(questao);
	}

	public List<Questao> getQuestoes() {
		return new ArrayList<>(questoes);
	}
}
