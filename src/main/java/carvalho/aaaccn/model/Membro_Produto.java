/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package carvalho.aaaccn.model;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
//@Entity

public class Membro_Produto implements Serializable {

    @NotNull
    @NotEmpty
    private String dataped;
    
    @NotNull
    @NotEmpty
    private String datapag;

	private String tipo_pag;
    
    private String comprovante;
    
	@NotNull
    @NotEmpty
    private String endereco;
    
    @NotNull
    @NotEmpty
    private String curso;
    
    @NotNull
    @NotEmpty
    private String modalidade;
    
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Member member;
    
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Product produto;
   
    public String getDataped() {
		return dataped;
	}

	public void setDataped(String dataped) {
		this.dataped = dataped;
	}

	public String getDatapag() {
		return datapag;
	}

	public void setDatapag(String datapag) {
		this.datapag = datapag;
	}

	public String getTipo_pag() {
		return tipo_pag;
	}

	public void setTipo_pag(String tipo_pag) {
		this.tipo_pag = tipo_pag;
	}

	public String getComprovante() {
		return comprovante;
	}

	public void setComprovante(String comprovante) {
		this.comprovante = comprovante;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getModalidade() {
		return modalidade;
	}

	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Product getProduto() {
		return produto;
	}

	public void setProduto(Product produto) {
		this.produto = produto;
	}
}
