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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.persistence.JoinColumn;
import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity

public class Blog implements Serializable {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer id_blog;

	@NotEmpty
	 @NotNull
	 private String titulo;
	 
	 @NotEmpty
	 @NotNull
	 private String subtitulo;
	 
	 
	 @NotEmpty
	 @NotNull
	 private String imagem;
	 
	 @JoinTable(
		      name = "publicacao",
		      joinColumns = @JoinColumn(name = "id_blog"),
		      inverseJoinColumns = @JoinColumn(name = "id_adm")
	)
	 @NotEmpty
	 @NotNull
	 @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	 private List<Administrador> adm = new ArrayList<Administrador>();
	 
	 
	 
	 @NotEmpty
	 @NotNull
	 private String texto;

	 public Integer getId_blog() {
		return id_blog;
	}

	public void setId_blog(Integer id_blog) {
		this.id_blog = id_blog;
	}

	public List<Administrador> getAdm() {
		return adm;
	}

	public void setAdm(List<Administrador> adm) {
		this.adm = adm;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSubtitulo() {
		return subtitulo;
	}

	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}


	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	 
	
	  
}
