package br.com.certificacao.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class Question implements Serializable {

	private static final long serialVersionUID = 1284490087332362658L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne
	private Pergunta question;
	@OneToOne
	private Resposta answer = new Resposta();

	@Transient
	private boolean markedAsRevision = false;
	@Transient
	private boolean unswered = false;

	public Question() {
	}

	public Question(Pergunta question) {
		this.question = question;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setQuestion(Pergunta question) {
		this.question = question;
	}

	public void setAnswer(Resposta answer) {
		this.answer = answer;
	}

	public Resposta getAnswer() {
		return answer;
	}

	public Pergunta getQuestion() {
		return question;
	}

	public boolean isMarkedAsRevision() {
		return markedAsRevision;
	}

	public void setMarkedAsRevision(boolean markedAsRevision) {
		this.markedAsRevision = markedAsRevision;
	}

	public boolean isUnswered() {
		return unswered;
	}

	public void setUnswered(boolean unswered) {
		this.unswered = unswered;
	}

}