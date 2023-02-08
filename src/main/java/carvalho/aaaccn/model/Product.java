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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity

@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Product implements Serializable {

    @Id
    @NotNull
    @Size(min = 10, max = 10)
    private Long matricula;
    
    @NotNull
    @Size(min = 11, max = 11)
    private Long cpf;

    @NotNull
    @Size(min = 1, max = 100)
    @Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
    private String nome;

    @NotNull
    @NotEmpty
    @Email
    private String email;
    
    @NotNull
    @NotEmpty
    private String endereco;
    
    @NotNull
    @NotEmpty
    private String curso;
    
    @NotNull
    @NotEmpty
    private String modalidade;

   
    @NotNull
    @Size(min = 10, max = 12)
    @Digits(fraction = 0, integer = 12)
    @Column(name = "telefone")
    private String phoneNumber;

    
}
