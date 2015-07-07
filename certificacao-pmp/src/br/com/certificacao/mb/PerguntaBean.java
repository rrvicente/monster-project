package br.com.certificacao.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.certificacao.entidades.Pergunta;
import br.com.certificacao.entidades.Resposta;
import br.com.certificacao.entidades.dao.PerguntaDao;
import br.com.certificacao.entidades.dao.RespostaDao;
import br.com.certificacao.interceptadores.Transacional;

@Named
@SessionScoped
public class PerguntaBean implements Serializable {

	private static final long serialVersionUID = -1937862234813871553L;

	@Inject
	PerguntaDao perguntaDao;
	private Pergunta pergunta = new Pergunta();
	private List<Pergunta> perguntas;

	@Inject
	RespostaDao respostaDao;
	private Resposta resposta = new Resposta();
	private List<Resposta> respostas;

	@Transacional
	public void salvarPergunta() {
		if (pergunta.getId() == null)
			perguntaDao.inserir(pergunta);
		else
			perguntaDao.atualizar(pergunta);

		perguntas = perguntaDao.listarPerguntas();
	}

	@Transacional
	public void salvarResposta() {
		this.pergunta = perguntaDao.pesquisarPorId(pergunta.getId());

		if (resposta.getId() == null) {
			resposta.setPergunta(this.pergunta);
			pergunta.getRespostas().add(resposta);
			respostaDao.inserir(resposta);
		} else {
			respostaDao.atualizar(resposta);
		}

		salvarPergunta();

		this.resposta = new Resposta();
	}

	@Transacional
	public String novaPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
		this.respostas = new ArrayList<Resposta>();

		this.resposta = new Resposta();
		return "respostas";
	}

	@Transacional
	public String alterarPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
		this.respostas = this.pergunta.getRespostas();

		this.resposta = new Resposta();
		return "respostas";
	}

	@Transacional
	public void alterarResposta(Resposta resposta) {
		this.resposta = resposta;
		this.pergunta = resposta.getPergunta();
	}

	@Transacional
	public void removerPergunta(Pergunta pergunta) {
		perguntaDao.removerPorId(pergunta.getId());

		this.perguntas = perguntaDao.listarPerguntas();
	}

	@Transacional
	public void removerResposta(Resposta resposta) {

		for (Resposta res : pergunta.getRespostas()) {
			if (res.getId() == resposta.getId()) {

				pergunta.getRespostas().remove(res);
				perguntaDao.atualizar(pergunta);
				respostaDao.remover(resposta);

				break;
			}
		}

		this.resposta = new Resposta();
		this.pergunta = perguntaDao.pesquisarPorId(pergunta.getId());
		this.respostas = perguntaDao.pesquisarPorId(pergunta.getId()).getRespostas();
	}

	public Pergunta getPergunta() {
		return pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}

	public Resposta getResposta() {
		return resposta;
	}

	public void setResposta(Resposta resposta) {
		this.resposta = resposta;
	}

	@Transacional
	public List<Pergunta> getPerguntas() {
		if (perguntas == null) {
			perguntas = perguntaDao.listarPerguntas();
		}

		return perguntas;
	}

	public void setPerguntas(List<Pergunta> perguntas) {
		this.perguntas = perguntas;
	}

	@Transacional
	public List<Resposta> getRespostas() {
		if (pergunta == null)
			this.pergunta = new Pergunta();
		else if (pergunta.getId() != null)
			this.respostas = perguntaDao.pesquisarPorId(pergunta.getId()).getRespostas();
		return this.respostas;
	}

	public void setRespostas(List<Resposta> respostas) {
		this.respostas = respostas;
	}

	public void obterObjeto(ActionEvent e) {
		UIParameter component = (UIParameter) e.getComponent().findComponent("pergunta");
		if (component != null && component.getValue() != null)
			this.pergunta = (Pergunta) component.getValue();
		else {
			this.pergunta = new Pergunta();
		}
	}

}
