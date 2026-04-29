package petcollar.dominio.assinaturafaturamento;

public class CalculoJurosService {

    private static final double TAXA_JUROS_MENSAL = 0.02;

    public Dinheiro calcularValorAtualizado(Dinheiro valorOriginal, int mesesAtraso) {
        if (valorOriginal == null)
            throw new IllegalArgumentException("Valor original não pode ser nulo.");
        if (mesesAtraso < 0)
            throw new IllegalArgumentException("Quantidade de meses em atraso não pode ser negativa.");
        double valorAtualizado = valorOriginal.getValor()
                * (1 + TAXA_JUROS_MENSAL * mesesAtraso);
        return new Dinheiro(valorAtualizado, valorOriginal.getMoeda());
    }
}
