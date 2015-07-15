package br.com.certificacao.mb;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.faces.component.html.HtmlSelectOneRadio;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.certificacao.entidades.Question;
import br.com.certificacao.entidades.QuestionHistory;
import br.com.certificacao.entidades.Resposta;
import br.com.certificacao.entidades.dao.PerguntaDao;
import br.com.certificacao.entidades.dao.QuestionDao;
import br.com.certificacao.entidades.dao.QuestionHistoryDao;
import br.com.certificacao.entidades.dao.RespostaDao;
import br.com.certificacao.interceptadores.Transacional;
import br.com.certificacao.quiz.ChartGenerator;
import br.com.certificacao.quiz.Questions;

@Named
@ConversationScoped
public class Wizard implements Serializable {

	private static final long serialVersionUID = 1L;

	private Questions questions;
	private QuestionHistory questionHistory = new QuestionHistory();

	private int cursor = 0;
	private int minutes;
	private int seconds;
	private int qtd;

	private String chartData;

	@Inject
	private Conversation conversation;

	@Inject
	private PerguntaDao perguntaDao;

	@Inject
	private RespostaDao respostaDao;

	@Inject
	private QuestionDao questionDao;

	@Inject
	private QuestionHistoryDao questionHistoryDao;

	@PostConstruct
	public void init() {
		getQuestions();
	}

	@Produces
	@Named
	public Question[] getQuestions() {
		if (questions == null) {
			questions = new Questions(perguntaDao, 10);

			fetchTimer();
		}

		return questions.getQuestions();
	}

	private void fetchTimer() {
		minutes = (int) (questions.size() * 1.2);
		seconds = (int) ((questions.size() * 1.2 - minutes) * 100);

		if (seconds > 60) {
			minutes++;
			seconds = seconds - 60;
		}
	}

	private boolean isOnRevision = false;

	public void nextButtonClicked(ActionEvent e) {
		if (cursor == questions.size() - 1) {
			isOnRevision = true;
		}

		if (isOnRevision) {
			cursorOnRevision();
		} else {
			incrementCursor();
		}

		if (!questions.getQuestions()[cursor].isMarkedAsRevision())
			questions.getQuestions()[cursor].setUnswered(true);
	}

	public void revisionButtonClicked(ActionEvent e) {
		questions.getQuestions()[cursor].setMarkedAsRevision(true);
		// decrementCursor();
	}

	private void cursorOnRevision() {
		for (int i = 0; i < questions.getQuestions().length; i++) {
			if (questions.getQuestions()[i].isMarkedAsRevision()) {
				cursor = i;
				break;
			}
		}

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
		return (cursor >= 0 && cursor != questions.size() - 1) || hasRevision();
	}

	public boolean getRevisionButtonEnabled() {
		return cursor >= 0;
	}

	public boolean getFinishButtonEnabled() {
		return cursor == questions.size() - 1;
	}

	public String start() {
		conversation.begin();
		return "quizWizard/wizard";
	}

	@Transacional
	public String end() {
		conversation.end();

		persistQuestions();

		return "/finish";
	}

	public String endAuto() {
		conversation.end();

		return "/finish";
	}

	private void persistQuestions() {

		questionHistoryDao.inserir(questionHistory);

		for (int i = 0; i < questions.size(); i++) {
			Question question = questions.getQuestions()[i];
			questionDao.inserir(question);
			questionHistory.getQuestions().add(questions.getQuestions()[i]);
		}

		questionHistoryDao.atualizar(questionHistory);

	}

	private boolean hasRevision() {
		for (int i = 0; i < questions.getQuestions().length; i++) {
			if (questions.getQuestions()[i].isMarkedAsRevision())
				return true;
		}

		return false;
	}

	public boolean isRevision() {
		return questions.getQuestions()[cursor].isMarkedAsRevision() && !questions.getQuestions()[cursor].isUnswered();
	}

	public void valueChange(AjaxBehaviorEvent evt) {
		HtmlSelectOneRadio o = ((HtmlSelectOneRadio) evt.getSource());
		String id = (String) o.getSubmittedValue();

		Resposta resposta = respostaDao.pesquisarPorId(Integer.parseInt(id));// TODO retirar respostaDao, localizar a resposta pelo cursor

		if (resposta != null) {
			(questions.getQuestions())[cursor].setAnswer(resposta);
		}
	}

	public String chartGenerator() {
		ChartGenerator ch = new ChartGenerator();
		chartData = ch.getChartByUser(questionHistoryDao);

		return "/chart";
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public String getChartData() {
		return chartData;
	}

	public void setChartData(String chartData) {
		this.chartData = chartData;
	}

}