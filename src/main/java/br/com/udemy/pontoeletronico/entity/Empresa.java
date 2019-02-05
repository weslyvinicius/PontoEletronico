package br.com.udemy.pontoeletronico.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Empresa extends AbstractEntity {

    @Column(name = "razao_social", nullable = false)
    private String razaoSocial;

    @Column(name = "cnpj", nullable = false)
    private String cnpj;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao", nullable = false)
    private LocalDateTime dataAtualizacao;

    @OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Funcionario> funcionarios;

    @PreUpdate
    public void preUpdate() {
        dataAtualizacao = LocalDateTime.now();
    }

    @PrePersist
    public void prePersist() {
        final LocalDateTime atual = LocalDateTime.now();
        dataCriacao = atual;
        dataAtualizacao = atual;
    }

}


