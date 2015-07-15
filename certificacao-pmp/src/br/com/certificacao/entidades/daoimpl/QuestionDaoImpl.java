package br.com.certificacao.entidades.daoimpl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.certificacao.entidades.Question;
import br.com.certificacao.entidades.dao.GenericoDao;
import br.com.certificacao.entidades.dao.QuestionDao;

public class QuestionDaoImpl implements QuestionDao, Serializable {

	private static final long serialVersionUID = 1L;
	private GenericoDao<Question, Integer> genericoDao;

	@Inject
	QuestionDaoImpl(EntityManager entityManager) {
		genericoDao = new GenericoDaoImpl<Question, Integer>(Question.class, entityManager);
	}

	@Override
	public void inserir(Question question) {
		genericoDao.inserir(question);

	}

	@Override
	public void remover(Question question) {
		genericoDao.remover(question);
	}

	@Override
	public void removerPorId(Integer id) {
		genericoDao.removerPorId(id);
	}

	@Override
	public void atualizar(Question question) {
		genericoDao.atualizar(question);
	}

	@Override
	public Question pesquisarPorId(Integer id) {
		return genericoDao.pesquisarPorID(id);
	}

	@Override
	public List<Question> listarQuestions() {
		return genericoDao.listarTodos();
	}

}
