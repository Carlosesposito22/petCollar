package petcollar.dominio.assinaturafaturamento;

import java.util.List;

public interface IAssinaturaRepositorio {
    void save(Assinatura assinatura);
    Assinatura findById(AssinaturaId id);
    List<Assinatura> findByTutorId(String tutorId);
    List<Assinatura> findByStatus(StatusAssinatura status);
}
