package br.com.certificacao.entidades.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.certificacao.entidades.dao.GenericoDao;

public class GenericoDaoImpl<T, K> implements GenericoDao<T, K> {

	private Class<T> classeDaEntidade;
	private EntityManager entityManager;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GenericoDaoImpl(Class classeDaEntidade, EntityManager entityManger) {
		this.classeDaEntidade = classeDaEntidade;
		this.entityManager = entityManger;
	}

	@Override
	public void inserir(T entidade) {
		entityManager.persist(entidade);
	}

	@Override
	public void inserirTodos(List<T> entidades) {
		for (T t : entidades) {
			entityManager.persist(t);
		}
	}

	@Override
	public void remover(T entidade) {
		entityManager.remove(entityManager.merge(entidade));
	}

	@Override
	public void removerPorId(K id) {
		T entity = entityManager.find(classeDaEntidade, id);
		entityManager.remove(entity);
	}

	@Override
	public void atualizar(T entidade) {
		entityManager.merge(entidade);
	}

	@Override
	public T pesquisarPorID(K id) {
		return entityManager.find(classeDaEntidade, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> listarTodos() {
		return entityManager.createQuery("select t from " + classeDaEntidade.getSimpleName() + " t").getResultList();
	}

}
