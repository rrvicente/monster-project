package br.com.certificacao.entidades.daoimpl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.certificacao.entidades.QuestionHistory;
import br.com.certificacao.entidades.dao.GenericoDao;
import br.com.certificacao.entidades.dao.QuestionHistoryDao;

public class QuestionHistoryDaoImpl implements QuestionHistoryDao, Serializable {

	private static final long serialVersionUID = 1L;
	private GenericoDao<QuestionHistory, Integer> genericoDao;

	@Inject
	QuestionHistoryDaoImpl(EntityManager entityManager) {
		genericoDao = new GenericoDaoImpl<QuestionHistory, Integer>(QuestionHistory.class, entityManager);
	}

	@Override
	public void inserir(QuestionHistory questionHistory) {
		genericoDao.inserir(questionHistory);

	}

	@Override
	public void remover(QuestionHistory questionHistory) {
		genericoDao.remover(questionHistory);
	}

	@Override
	public void removerPorId(Integer id) {
		genericoDao.removerPorId(id);
	}

	@Override
	public void atualizar(QuestionHistory questionHistory) {
		genericoDao.atualizar(questionHistory);
	}

	@Override
	public QuestionHistory pesquisarPorId(Integer id) {
		return genericoDao.pesquisarPorID(id);
	}

	@Override
	public List<QuestionHistory> listarQuestionHistorys() {
		return genericoDao.listarTodos();
	}

}
