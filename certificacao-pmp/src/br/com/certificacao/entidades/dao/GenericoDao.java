package br.com.certificacao.entidades.dao;

import java.util.List;

public interface GenericoDao<T, K> {

	void inserir(T entidade);

	void inserirTodos(List<T> entidades);

	void remover(T entidade);

	void removerPorId(K id);

	void atualizar(T entidade);

	T pesquisarPorID(K id);

	List<T> listarTodos();

}