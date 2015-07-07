package br.com.certificacao.entidades.dao;

import java.util.List;

import br.com.certificacao.entidades.Resposta;

public interface RespostaDao {

	void inserir(Resposta resposta);

	void remover(Resposta resposta);

	void removerPorId(Integer id);

	void atualizar(Resposta resposta);

	Resposta pesquisarPorId(Integer id);

	List<Resposta> listarRespostas();
}
