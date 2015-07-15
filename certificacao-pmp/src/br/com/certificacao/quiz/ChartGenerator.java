package br.com.certificacao.quiz;

import java.util.List;

import br.com.certificacao.entidades.Question;
import br.com.certificacao.entidades.QuestionHistory;
import br.com.certificacao.entidades.dao.QuestionHistoryDao;

import com.google.gson.Gson;

public class ChartGenerator {

	public String getChartByUser(QuestionHistoryDao questionHistoryDao) {
		List<QuestionHistory> lista = questionHistoryDao.listarQuestionHistorys();

		String[] labels = new String[lista.size()];
		Integer[] dataSet1 = new Integer[lista.size()];
		Integer[] dataSet2 = new Integer[lista.size()];

		for (int i = 0; i < lista.size(); i++) {
			QuestionHistory qh = lista.get(i);

			labels[i] = qh.getId().toString() + "P";
			dataSet1[i] = qh.getQuestions().size();

			int data = 0;
			for (int j = 0; j < qh.getQuestions().size(); j++) {
				Question q = qh.getQuestions().get(j);

				if (correctResponse(q))
					data++;
			}

			dataSet2[i] = data;
		}

		Gson gson = new Gson();
		ChartData cd = new ChartData();
		cd.setLabels(labels);
		cd.setDataSet1(dataSet1);
		cd.setDataSet2(dataSet2);

		return gson.toJson(cd);
	}

	private boolean correctResponse(Question q) {
		for (int i = 0; i < q.getQuestion().getRespostas().size(); i++) {
			if (q.getQuestion().getRespostas().get(i).getCorreta()) {
				if (q.getAnswer().getId() == q.getQuestion().getRespostas().get(i).getId())
					return true;

			}
		}

		return false;
	}
}
