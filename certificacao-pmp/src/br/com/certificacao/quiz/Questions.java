package br.com.certificacao.quiz;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import br.com.certificacao.entidades.Pergunta;
import br.com.certificacao.entidades.Question;
import br.com.certificacao.entidades.dao.PerguntaDao;

public class Questions implements Serializable {
	private static final long serialVersionUID = -7148843668107920897L;
	private String question;
	private Question[] questions = new Question[10];

	public Questions(PerguntaDao perguntaDao, int qtd) {
		List<Pergunta> perguntas = perguntaDao.listarPerguntas();

		if (perguntas.size() < qtd) {
			qtd = perguntas.size();
		}

		questions = new Question[qtd];

		Collections.shuffle(perguntas);
		for (int i = 0; i < questions.length; i++) {
			questions[i] = new Question(perguntas.get(0));
			perguntas.remove(0);
		}
	}

	public int size() {
		return questions.length;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Question[] getQuestions() {
		return questions;
	}
}