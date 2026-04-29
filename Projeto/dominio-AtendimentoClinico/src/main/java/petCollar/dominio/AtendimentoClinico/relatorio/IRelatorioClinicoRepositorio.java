package petCollar.dominio.AtendimentoClinico.relatorio;

import java.util.List;

public interface IRelatorioClinicoRepositorio {
    void save(RelatorioClinico relatorio);
    RelatorioClinico findById(RelatorioClinicoId id);
    List<RelatorioClinico> findByAtendimentoId(AtendimentoId atendimentoId);
    List<RelatorioClinico> findByPacienteId(PacienteId pacienteId);
    boolean existsByAtendimentoId(AtendimentoId atendimentoId);
}
