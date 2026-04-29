package petcollar.dominio.recepcaotriagem.prontuario;

import java.util.List;

public interface IDiagnosticoRepositorio {
    List<String> findInfectantesUltimos(int dias, PacienteId pacienteId);
}
