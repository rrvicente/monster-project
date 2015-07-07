package br.com.certificacao.mb;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
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
@ConversationScoped
public class RespostaBean implements Serializable {

	private static final long serialVersionUID = -3126304703800341119L;

	@Inject
	RespostaDao respostaDao;
	private Resposta resposta = new Resposta();
	private List<Resposta> respostas;

	@Inject
	PerguntaDao perguntaDao;

	private Pergunta pergunta = new Pergunta();

	// @PostConstruct
	// public void init() {
	//
	// Object str = FacesContext.getCurrentInstance().getExternalContext().getFlash().get("pergunta");
	// if (str != null)
	// this.pergunta = (Pergunta) str;
	// }

	public void obterObjeto(ActionEvent e) {
		UIParameter component = (UIParameter) e.getComponent().findComponent("pergunta");
		this.pergunta = (Pergunta) component.getValue();
	}

	@Transacional
	public void salvar() {

		if (pergunta.getId() == null)
			perguntaDao.inserir(pergunta);
		else
			perguntaDao.atualizar(pergunta);

		respostas = perguntaDao.pesquisarPorId(pergunta.getId()).getRespostas();
	}

	@Transacional
	public void adicionarResposta() {
		resposta.setPergunta(this.pergunta);

		if (resposta.getId() == null) {
			respostaDao.inserir(resposta);
		} else {
			respostaDao.atualizar(resposta);
		}

		resposta = new Resposta();
		resposta.setPergunta(pergunta);
	}

	@Transacional
	public void alterarResposta(Resposta resposta) {
		this.resposta = resposta;
		this.pergunta = resposta.getPergunta();
	}

	@Transacional
	public void removerResposta(Resposta resposta) {
		pergunta.getRespostas().remove(resposta);

		respostaDao.remover(resposta);
		respostas = respostaDao.listarRespostas();
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

	public List<Resposta> getRespostas() {
		if (pergunta != null)
			this.respostas = perguntaDao.pesquisarPorId(pergunta.getId()).getRespostas();
		return this.respostas;
	}

	public void setRespostas(List<Resposta> respostas) {
		this.respostas = respostas;
	}

}
