package br.com.clinicaMed.entity;

import javax.persistence.*;

@Entity
public class Recepcionista {

    @Id
    @Column(name = "re_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "re_nome")
    private String nome;

    @Column(name = "re_cpf")
    private String cpf;

    @JoinColumn(name = "us_id")
    @OneToOne
    private Usuario usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
