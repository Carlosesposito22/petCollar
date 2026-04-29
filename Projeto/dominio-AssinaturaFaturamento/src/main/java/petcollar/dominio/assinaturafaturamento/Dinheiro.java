package petcollar.dominio.assinaturafaturamento;

import java.util.Objects;

public final class Dinheiro {

    private final double valor;
    private final String moeda;

    public Dinheiro(double valor, String moeda) {
        if (valor < 0)
            throw new IllegalArgumentException("Valor monetário não pode ser negativo.");
        if (moeda == null || moeda.isBlank())
            throw new IllegalArgumentException("Moeda não pode ser vazia.");
        this.valor = valor;
        this.moeda = moeda;
    }

    public static Dinheiro emReais(double valor) {
        return new Dinheiro(valor, "BRL");
    }

    public double getValor() {
        return valor;
    }

    public String getMoeda() {
        return moeda;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dinheiro)) return false;
        Dinheiro other = (Dinheiro) o;
        return Double.compare(other.valor, valor) == 0
                && Objects.equals(moeda, other.moeda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor, moeda);
    }

    @Override
    public String toString() {
        return valor + " " + moeda;
    }
}
