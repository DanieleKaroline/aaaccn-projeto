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
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity

public class Evento implements Serializable {

	@Id
    //Gerada automaticamente pelo banco
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    
	@NotNull
	@Size(min = 1, max = 25, message = "Mensagem customizada de erro! O nome do usuário deve ter no máximo 25 caracteres.")	 
	@Pattern(regexp = "[^0-9]*", message = "O nome de usuário não pode conter digitos.")
	private String nome;
	
	private String descricao;
	
	private String tipo;
	
	private String modalidade;
	
	private Double valor;
	
	private String data;
	
	private String local;
	
	@OneToOne
	@NotNull
    private Administrador id_adm;

	@JoinTable(
		      name = "Inscricao_Evento",
		      joinColumns = @JoinColumn(name = "id_evento"),
		      inverseJoinColumns = @JoinColumn(name = "matricula_membro")
		)
	 
	 @NotEmpty
	 @NotNull
	 @ManyToMany(cascade = CascadeType.MERGE)
	 private List<Member> membro = new ArrayList<Member>();
	
	public void setMembro(List<Member> membro) {
		this.membro = membro;
	}

	public void setId_adm(Administrador id_adm) {
		this.id_adm = id_adm;
	}

	 
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getModalidade() {
		return modalidade;
	}

	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}
	
	
}
