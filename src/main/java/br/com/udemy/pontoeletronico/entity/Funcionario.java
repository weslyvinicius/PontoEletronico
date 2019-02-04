package br.com.udemy.pontoeletronico.entity;

import br.com.udemy.pontoeletronico.Enum.PerfilEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ToString
@EqualsAndHashCode(callSuper = false)
@Entity
public class Funcionario extends AbstractEntity{

    @Getter @Setter
    @Column(name = "nome", nullable = false)
    private String nome;

    @Getter @Setter
    @Column(name = "email", nullable = false)
    private String email;

    @Getter @Setter
    @Column(name = "senha", nullable = false)
    private String senha;

    @Getter @Setter
    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Setter
    @Column(name = "valor_hora")
    private BigDecimal valorHora;

    @Setter
    @Column(name = "qtd_horas_trabalho_dia")
    private Float qtdHorasTrabalhoDia;

    @Setter
    @Column(name = "qtd_horas_almoco")
    private Float qtdHorasAlmoco;

    @Getter @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "perfil", nullable = false)
    private PerfilEnum perfil;

    @Getter @Setter
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Getter @Setter
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_atualizacao", nullable = false)
    private LocalDateTime dataAtualizacao;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    private Empresa empresa;

    @Getter @Setter
    @OneToMany(mappedBy = "funcionario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Lancamento> lancamentos;

    public Optional<BigDecimal> getValorHora() {
        return Optional.ofNullable(valorHora);
    }

    public Optional<Float> getQtdHorasTrabalhoDia() {
        return Optional.ofNullable(qtdHorasTrabalhoDia);
    }

    public Optional<Float> getQtdHorasAlmoco() {
        return Optional.ofNullable(qtdHorasAlmoco);
    }


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
