package petcollar.dominio.assinaturafaturamento;

public interface IPagamentoRepositorio {
    void save(Pagamento pagamento);
    Pagamento findById(PagamentoId id);
}
