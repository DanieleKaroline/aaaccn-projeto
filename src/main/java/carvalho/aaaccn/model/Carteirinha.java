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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity


public class Carteirinha implements Serializable {

    @Id
    @NotNull
    @Size(min = 10, max = 10)
    private Integer id_matricula;
    
    @NotEmpty
    @NotNull
    private String validade;
    
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Member membro;

	public Integer getId_matricula() {
		return id_matricula;
	}

	public void setId_matricula(Integer id_matricula) {
		this.id_matricula = id_matricula;
	}

	public String getValidade() {
		return validade;
	}

	public void setValidade(String validade) {
		this.validade = validade;
	}

	public Member getMembro() {
		return membro;
	}

	public void setMembro(Member membro) {
		this.membro = membro;
	}
    
    
    
}
