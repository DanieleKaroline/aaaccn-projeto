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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.persistence.FetchType;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity

@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Member implements Serializable {

    @Id
    @NotNull
    @Size(min = 10, max = 10)
    private Integer matricula;
    
    @NotNull
    @Size(min = 11, max = 11)
    private Integer cpf;
    
    @NotNull
    @Size(min = 1, max = 25, message = "Mensagem customizada de erro! O nome do usuário deve ter no máximo 25 caracteres.")
    @Pattern(regexp = "[^0-9]*", message = "O nome de usuário não pode conter digitos.")
    private String nome;

    @NotNull
    @NotEmpty
    @Email(message = "Não é um endereço de E-mail válido")
    private String email;
    
    @NotNull
    @NotEmpty
    private String datanasc;

    private String alecond;

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
    private Carteirinha carteirinha;
    
   
	@NotNull
    @Size(min = 10, max = 12)
    @Digits(fraction = 0, integer = 12)
    @Column(name = "telefone")
    private String phoneNumber;

	@ManyToMany(mappedBy = "membro", fetch = FetchType.EAGER)
    private List<Evento> evento = new ArrayList<Evento>();
	
    public List<Evento> getEvento() {
		return evento;
	}

	public void setEvento(List<Evento> evento) {
		this.evento = evento;
	}
	 public Carteirinha getCarteirinha() {
			return carteirinha;
		}

		public void setCarteirinha(Carteirinha carteirinha) {
			this.carteirinha = carteirinha;
		}


	public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }
    
    public Integer getCpf() {
        return cpf;
    }

    public void setCpf(Integer cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getDatanasc() {
		return datanasc;
	}

	public void setDatanasc(String datanasc) {
		this.datanasc = datanasc;
	}
    
	public String getAlecond() {
		return alecond;
	}

	public void setAlecond(String alecond) {
		this.alecond = alecond;
	}
	
    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
    
    public String getModalidade() {
        return email;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }
    
    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
