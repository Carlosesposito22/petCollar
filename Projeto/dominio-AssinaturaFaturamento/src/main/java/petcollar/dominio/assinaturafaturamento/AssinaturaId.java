package petcollar.dominio.assinaturafaturamento;

import java.util.Objects;
import java.util.UUID;

public class AssinaturaId {

    private final String valor;

    private AssinaturaId(String valor) {
        this.valor = valor;
    }

    public static AssinaturaId gerar() {
        return new AssinaturaId(UUID.randomUUID().toString());
    }

    public static AssinaturaId de(String valor) {
        if (valor == null || valor.isBlank())
            throw new IllegalArgumentException("AssinaturaId não pode ser vazio.");
        return new AssinaturaId(valor);
    }

    public String getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AssinaturaId)) return false;
        return Objects.equals(valor, ((AssinaturaId) o).valor);
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
