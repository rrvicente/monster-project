package br.com.certificacao.quiz;

import java.io.Serializable;

import br.com.certificacao.entidades.Pergunta;
import br.com.certificacao.entidades.Resposta;

public class Question implements Serializable {
	private static final long serialVersionUID = 1284490087332362658L;
	private Pergunta question;
	private Resposta answer;
	// private String question, answer;
	// private String[] choices;
	private boolean answered = false;

	// public Question(String question, String[] choices) {
	// this.question = question;
	// this.choices = choices;
	// }

	public Question(Pergunta question) {
		this.question = question;
	}

	public void setAnswer(Resposta answer) {
		this.answer = answer;
		answered = true;
	}

	public Resposta getAnswer() {
		return answer;
	}

	public Pergunta getQuestion() {
		return question;
	}

	// public String[] getChoices() {
	// return choices;
	// }

	public boolean isAnswered() {
		return answered;
	}

	public void setAnswered(boolean answered) {
		this.answered = answered;
	}
}