package petcollar.dominio.assinaturafaturamento;

import java.util.List;

public interface ICobrancaRepositorio {
    void save(Cobranca cobranca);
    Cobranca findById(CobrancaId id);
    List<Cobranca> findByAssinaturaId(AssinaturaId assinaturaId);
    List<Cobranca> findByAssinaturaIdEStatus(AssinaturaId assinaturaId, StatusCobranca status);
}
