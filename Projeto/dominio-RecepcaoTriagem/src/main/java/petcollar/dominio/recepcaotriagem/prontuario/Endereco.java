package petcollar.dominio.recepcaotriagem.prontuario;

import java.util.Objects;

public final class Endereco {

    private final String logradouro;
    private final String cidade;
    private final String estado;
    private final String cep;

    public Endereco(String logradouro, String cidade, String estado, String cep) {
        if (logradouro == null || logradouro.isBlank())
            throw new IllegalArgumentException("Logradouro não pode ser vazio.");
        if (cidade == null || cidade.isBlank())
            throw new IllegalArgumentException("Cidade não pode ser vazia.");
        if (estado == null || estado.isBlank())
            throw new IllegalArgumentException("Estado não pode ser vazio.");
        if (cep == null || cep.isBlank())
            throw new IllegalArgumentException("CEP não pode ser vazio.");
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    public String getLogradouro() { return logradouro; }
    public String getCidade()     { return cidade; }
    public String getEstado()     { return estado; }
    public String getCep()        { return cep; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Endereco)) return false;
        Endereco outro = (Endereco) o;
        return Objects.equals(logradouro, outro.logradouro)
            && Objects.equals(cidade, outro.cidade)
            && Objects.equals(estado, outro.estado)
            && Objects.equals(cep, outro.cep);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logradouro, cidade, estado, cep);
    }
}
