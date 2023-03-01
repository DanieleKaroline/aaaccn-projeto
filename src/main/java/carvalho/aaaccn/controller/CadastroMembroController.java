package carvalho.aaaccn.controller;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import carvalho.aaaccn.model.Member;
import carvalho.aaaccn.dao.MemberDao;

public class CadastroMembroController implements Serializable {
	//Anotação que marca atributo para ser gerenciado pelo CDI
		//O CDI criará uma instância do objeto automaticamente quando necessário
		@Inject
		private FacesContext facesContext;
		
		//atributos que não podem ser serializáveis (normalmente dependências externas) devem ser marcados como transient 
		//(eles são novamente criados a cada nova requisição independente do escopo da classe)
		@Inject
	    transient private Pbkdf2PasswordHash passwordHash;
		
		@Inject
	    private MemberDao MemberDao;
		
		private Member member;
		
		private List<Member> listaMember;
		
		private List<SelectItem> permissoes;
		
		private List<Integer> permissoesSelecionadas;
		
		//Anotação que força execução do método após o construtor da classe ser executado
	    @PostConstruct
	    public void init() {
	    	//Verifica se usuário está autenticado e possui a permissão adequada
	    	if (!this.facesContext.getExternalContext().isUserInRole("ADMINISTRADOR")) {
	    		try {
					this.facesContext.getExternalContext().redirect("login-error.xhtml");
				} catch (IOException e) {e.printStackTrace();}
	    	}
	    	//Inicializa elementos importantes
	    	this.permissoesSelecionadas = new ArrayList<Integer>();
	    	this.listaMember = memberDao.listarTodos();
	    	//O elemento de checkbox da tela usa uma lista do tipo SelectItem
	    	this.permissoes = new ArrayList<SelectItem>();
	    	//É necessário mapear a lista de permissões manualmente para o tipo SelectItem
	    	for (TipoPermissao p: this.tipoPermissaoDAO.listarTodos()) {
	    		//O primeiro elemento é a chave (oculta) e o segundo a descrição que aparecerá para o usuário em tela
	    		SelectItem i = new SelectItem(p.getPermissao().id, p.getPermissao().name());		
	    		this.permissoes.add(i);
	    	}
	    }
		
	    //Chamado pelo botão novo
		public void novoCadastro() {
	        this.setMember(new Member());
	    }
		
		//Chamado ao salvar cadastro de usuário (novo ou edição)
		public void salvar() {
			//Chama método de verificação se usuário é válido (regras negociais)
	        if (memberValido()) {
	        	//Limpa lista de permissões de usuário (é mais simples limpar e adicionar todas novamente depois)
	    		this.member.getPermissoes().clear();
	    		//Adiciona todas as permissões selecionadas em tela
	    		for (Integer id: this.permissoesSelecionadas) {
	    			TipoPermissao permissao = tipoPermissaoDAO.encontrarPermissao(id);
	    			//Chama método que adiciona o usuário para a permissão e vice-versa (necessário em relacionamento ManyToMany)
	    			permissao.addMember(this.member);	
	    		}
	        	try {
	        		//Aplica Hash na senha
	        		this.member.setSenha(this.passwordHash.generate(this.member.getSenha().toCharArray()));
			        //Verifica se é um novo cadastro ou é a alteração de um cadastro
	        		if (this.member.getId() == null) {
			        	this.memberDAO.salvar(this.member);
			        	this.facesContext.addMessage(null, new FacesMessage("Usuário Criado"));
			        } else {
			        	this.memberDAO.atualizar(this.member);
			        	this.facesContext.addMessage(null, new FacesMessage("Usuário Atualizado"));
			        }
	        		//Após salvar usuário é necessário recarregar lista que popula tabela com os novos dados
			        this.listaMember = memberDAO.listarTodos();
			        //Atualiza e executa elementos Javascript na tela assincronamente
				    PrimeFaces.current().executeScript("PF('memberDialog').hide()");
				    PrimeFaces.current().ajax().update("form:messages", "form:dt-members");
		        } catch (Exception e) {
		            String errorMessage = getMensagemErro(e);
		            this.facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null));
		        }
	        }
		}	
		
		//Realiza validações adicionais (não relizadas no modelo) e/ou complexas/interdependentes
		private boolean memberValido() {
			if (this.permissoesSelecionadas.isEmpty()) {
				this.facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Selecione ao menos uma permissão para o novo usuário.", null));
				return false;
			}			
			if (this.member.getId() == null && !this.memberDAO.ehMemberUnico(this.member.getMember())) {
				this.facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Este nome de usuário já está em uso.", null));
				return false;
			}
			return true;
		}
		
		//Chamado pelo botão remover da tabela
		public void remover() {
			try {
				this.MemberDao.excluir(this.member);
				//Após excluir usuário é necessário recarregar lista que popula tabela com os novos dados
				this.listaMember = MemberDao.listarTodos();
		        //Limpa seleção de usuário
				this.member = null;
		        this.facesContext.addMessage(null, new FacesMessage("Usuário Removido"));
		        PrimeFaces.current().ajax().update("form:messages", "form:dt-members");
	        } catch (Exception e) {
	            String errorMessage = getMensagemErro(e);
	            this.facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, null));
	        }
		}
		
		//Chamado pelo botão alterar da tabela
		public void alterar() {
			this.permissoesSelecionadas.clear();
			for (TipoPermissao p: this.member.getPermissoes())
				this.permissoesSelecionadas.add(p.getId());
			this.member.setSenha("");
		}
		
		//Captura mensagem de erro das validações do Hibernate
		private String getMensagemErro(Exception e) {
	        String erro = "Falha no sistema!. Contacte o administrador do sistema.";
	        if (e == null) 
	            return erro;
	        Throwable t = e;
	        while (t != null) {
	            erro = t.getLocalizedMessage();
	            t = t.getCause();
	        }
	        return erro;
	    }
		
		//GETs e SETs
		//GETs são necessários para elementos visíveis em tela
		//SETs são necessários para elementos que serão editados em tela
		public Member getMember() {
			return member;
		}

		public void setMember(Member member) {
			this.member = member;
		}

		public List<Member> getListaMembers() {
			return listaMember;
		}

		public void setListaMember(List<Member> listaMember) {
			this.listaMember = listaMember;
		}

		public List<SelectItem> getPermissoes() {
			return permissoes;
		}

		public void setPermissoes(List<SelectItem> permissoes) {
			this.permissoes = permissoes;
		}

		public List<Integer> getPermissoesSelecionadas() {
			return permissoesSelecionadas;
		}

		public void setPermissoesSelecionadas(List<Integer> permissoesSelecionadas) {
			this.permissoesSelecionadas = permissoesSelecionadas;
		}
	
}