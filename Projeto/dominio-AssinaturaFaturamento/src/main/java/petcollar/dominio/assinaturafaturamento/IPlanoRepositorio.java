package petcollar.dominio.assinaturafaturamento;

public interface IPlanoRepositorio {
    void save(Plano plano);
    Plano findById(PlanoId id);
}
