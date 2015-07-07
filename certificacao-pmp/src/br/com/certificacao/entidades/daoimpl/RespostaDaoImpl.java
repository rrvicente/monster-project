package br.com.certificacao.entidades.daoimpl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.certificacao.entidades.Resposta;
import br.com.certificacao.entidades.dao.GenericoDao;
import br.com.certificacao.entidades.dao.RespostaDao;

public class RespostaDaoImpl implements RespostaDao, Serializable {

	private static final long serialVersionUID = 1L;
	private GenericoDao<Resposta, Integer> genericoDao;

	@Inject
	RespostaDaoImpl(EntityManager entityManager) {
		genericoDao = new GenericoDaoImpl<Resposta, Integer>(Resposta.class, entityManager);
	}

	@Override
	public void inserir(Resposta resposta) {
		genericoDao.inserir(resposta);
	}

	@Override
	public void remover(Resposta resposta) {
		genericoDao.remover(resposta);
	}

	@Override
	public void removerPorId(Integer id) {
		genericoDao.removerPorId(id);
	}

	@Override
	public void atualizar(Resposta resposta) {
		genericoDao.atualizar(resposta);
	}

	@Override
	public Resposta pesquisarPorId(Integer id) {
		return genericoDao.pesquisarPorID(id);
	}

	@Override
	public List<Resposta> listarRespostas() {
		return genericoDao.listarTodos();
	}

}
