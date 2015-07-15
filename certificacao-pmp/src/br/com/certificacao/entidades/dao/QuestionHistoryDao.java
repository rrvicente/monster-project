package br.com.certificacao.entidades.dao;

import java.util.List;

import br.com.certificacao.entidades.QuestionHistory;

public interface QuestionHistoryDao {

	void inserir(QuestionHistory questionHistory);

	void remover(QuestionHistory questionHistory);

	void removerPorId(Integer id);

	void atualizar(QuestionHistory questionHistory);

	QuestionHistory pesquisarPorId(Integer id);

	List<QuestionHistory> listarQuestionHistorys();
}
