package br.com.certificacao.quiz;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import br.com.certificacao.entidades.Pergunta;
import br.com.certificacao.entidades.dao.PerguntaDao;

public class Questions implements Serializable {
	private static final long serialVersionUID = -7148843668107920897L;
	private String question;
	private Question[] questions = new Question[2];

	public Questions(PerguntaDao perguntaDao) {
		List<Pergunta> perguntas = perguntaDao.listarPerguntas();

		questions = new Question[2];

		for (int i = 0; i < questions.length; i++) {
			Collections.shuffle(perguntas);
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