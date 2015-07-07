package br.com.certificacao.entidades.dao;

import java.util.List;

import br.com.certificacao.entidades.Pergunta;

public interface PerguntaDao {

	void inserir(Pergunta pergunta);

	void remover(Pergunta pergunta);

	void removerPorId(Integer id);

	void atualizar(Pergunta pergunta);

	Pergunta pesquisarPorId(Integer id);

	List<Pergunta> listarPerguntas();
}
