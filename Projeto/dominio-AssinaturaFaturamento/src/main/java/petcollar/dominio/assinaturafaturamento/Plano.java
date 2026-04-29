package petcollar.dominio.assinaturafaturamento;

import java.time.LocalDateTime;

public class Plano {

    private final PlanoId id;
    private final String nome;
    private final Dinheiro mensalidade;
    private boolean disponivel;
    private final LocalDateTime criadoEm;

    public Plano(PlanoId id, String nome, Dinheiro mensalidade) {
        if (id == null)
            throw new IllegalArgumentException("Id do plano não pode ser nulo.");
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome do plano não pode ser vazio.");
        if (mensalidade == null)
            throw new IllegalArgumentException("Mensalidade do plano não pode ser nula.");
        this.id = id;
        this.nome = nome;
        this.mensalidade = mensalidade;
        this.disponivel = true;
        this.criadoEm = LocalDateTime.now();
    }

    public Plano(PlanoId id, String nome, Dinheiro mensalidade,
                 boolean disponivel, LocalDateTime criadoEm) {
        this.id = id;
        this.nome = nome;
        this.mensalidade = mensalidade;
        this.disponivel = disponivel;
        this.criadoEm = criadoEm;
    }

    public void desativar() {
        if (!this.disponivel)
            throw new IllegalStateException("Plano já está desativado.");
        this.disponivel = false;
    }

    public void ativar() {
        if (this.disponivel)
            throw new IllegalStateException("Plano já está ativo.");
        this.disponivel = true;
    }

    public PlanoId getId()          { return id; }
    public String getNome()         { return nome; }
    public Dinheiro getMensalidade(){ return mensalidade; }
    public boolean isDisponivel()   { return disponivel; }
    public LocalDateTime getCriadoEm() { return criadoEm; }
}
