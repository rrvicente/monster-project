package br.com.certificacao.entidades.dao;

import java.util.List;

import br.com.certificacao.entidades.Question;

public interface QuestionDao {

	void inserir(Question question);

	void remover(Question question);

	void removerPorId(Integer id);

	void atualizar(Question question);

	Question pesquisarPorId(Integer id);

	List<Question> listarQuestions();
}
