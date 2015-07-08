package br.com.certificacao.mb;

import java.io.Serializable;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.faces.component.html.HtmlSelectOneRadio;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.certificacao.entidades.Resposta;
import br.com.certificacao.entidades.dao.PerguntaDao;
import br.com.certificacao.entidades.dao.RespostaDao;
import br.com.certificacao.quiz.Question;
import br.com.certificacao.quiz.Questions;

@Named()
@ConversationScoped()
public class Wizard implements Serializable {
	private static final long serialVersionUID = 1L;
	private Questions questions;
	private int cursor = 0;
	private int time = 30;

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	@Inject
	private Conversation conversation;

	@Inject
	private PerguntaDao perguntaDao;

	@Inject
	private RespostaDao respostaDao;

	@Produces
	@Named
	public Question[] getQuestions() {
		if (questions == null) {
			questions = new Questions(perguntaDao);
		}

		return questions.getQuestions();
	}

	public void nextButtonClicked(ActionEvent e) {
		questions.getQuestions()[cursor].setAnswered(true);
		incrementCursor();
	}

	public void previousButtonClicked(ActionEvent e) {
		decrementCursor();
	}

	public void incrementCursor() {
		++cursor;
	}

	public void decrementCursor() {
		--cursor;
	}

	public int getCursor() {
		return cursor;
	}

	public void resetCursor() {
		cursor = 0;
	}

	public boolean getNextButtonEnabled() {
		return cursor != questions.size() - 1 && (questions.getQuestions())[cursor].isAnswered();
	}

	public boolean getPreviousButtonEnabled() {
		return cursor > 0;
	}

	public boolean getFinishButtonEnabled() {
		return cursor == questions.size() - 1 && (questions.getQuestions())[cursor].isAnswered();
	}

	public String start() {
		conversation.begin();
		return "quizWizard/wizard";
	}

	public String end() {
		conversation.end();
		return "/finish";
	}

	private void setCurrentQuestionUnanswered() {
		Question currentQuestion = (questions.getQuestions())[cursor];
		currentQuestion.setAnswered(false);
	}

	public void valueChange(AjaxBehaviorEvent evt) {
		HtmlSelectOneRadio o = ((HtmlSelectOneRadio) evt.getSource());
		String id = (String) o.getSubmittedValue();

		Resposta resposta = respostaDao.pesquisarPorId(Integer.parseInt(id));

		if (resposta != null) {
			(questions.getQuestions())[cursor].setAnswer(resposta);
		}
	}
}