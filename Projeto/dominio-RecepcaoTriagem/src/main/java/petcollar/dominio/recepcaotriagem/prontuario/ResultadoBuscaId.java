package petcollar.dominio.recepcaotriagem.prontuario;

import java.util.Objects;
import java.util.UUID;

public class ResultadoBuscaId {

    private final String valor;

    private ResultadoBuscaId(String valor) {
        this.valor = valor;
    }

    public static ResultadoBuscaId gerar() {
        return new ResultadoBuscaId(UUID.randomUUID().toString());
    }

    public static ResultadoBuscaId de(String valor) {
        if (valor == null || valor.isBlank())
            throw new IllegalArgumentException("ResultadoBuscaId não pode ser vazio.");
        return new ResultadoBuscaId(valor);
    }

    public String getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResultadoBuscaId)) return false;
        return Objects.equals(valor, ((ResultadoBuscaId) o).valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }

    @Override
    public String toString() {
        return valor;
    }
}
