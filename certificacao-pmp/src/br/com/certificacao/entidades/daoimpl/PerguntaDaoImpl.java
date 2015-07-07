package br.com.certificacao.entidades.daoimpl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.certificacao.entidades.Pergunta;
import br.com.certificacao.entidades.dao.GenericoDao;
import br.com.certificacao.entidades.dao.PerguntaDao;

public class PerguntaDaoImpl implements PerguntaDao, Serializable {

	private static final long serialVersionUID = 1L;
	private GenericoDao<Pergunta, Integer> genericoDao;

	@Inject
	PerguntaDaoImpl(EntityManager entityManager) {
		genericoDao = new GenericoDaoImpl<Pergunta, Integer>(Pergunta.class, entityManager);
	}

	@Override
	public void inserir(Pergunta pergunta) {
		genericoDao.inserir(pergunta);

	}

	@Override
	public void remover(Pergunta pergunta) {
		genericoDao.remover(pergunta);
	}

	@Override
	public void removerPorId(Integer id) {
		genericoDao.removerPorId(id);
	}

	@Override
	public void atualizar(Pergunta pergunta) {
		genericoDao.atualizar(pergunta);
	}

	@Override
	public Pergunta pesquisarPorId(Integer id) {
		return genericoDao.pesquisarPorID(id);
	}

	@Override
	public List<Pergunta> listarPerguntas() {
		return genericoDao.listarTodos();
	}

}
