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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity

@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Administrador implements Serializable {


	@Id
    @NotNull
    @Size(min = 10, max = 10)
    private Integer id;
    

    @NotNull
	@Size(min = 1, max = 25, message = "Mensagem customizada de erro! O nome do usuário deve ter no máximo 25 caracteres.")
	@Pattern(regexp = "[^0-9]*", message = "O nome de usuário não pode conter digitos.")
	private String nome;
    
    @NotNull
    @NotEmpty
    private String funcao;
    
    @NotNull
    @NotEmpty
    private String senha;
    
    @NotNull
    @NotEmpty
    @Email(message = "Não é um endereço de E-mail válido")
    private String email;
    
    
    @ManyToMany(mappedBy = "adm", fetch = FetchType.EAGER)
    private List<Blog> blog = new ArrayList<Blog>();
    
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

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
    
}
