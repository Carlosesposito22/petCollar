package petcollar.dominio.recepcaotriagem.prontuario;

import java.util.List;

public interface IResultadoBuscaRepositorio {
    void save(ResultadoBusca resultado);
    ResultadoBusca findById(ResultadoBuscaId id);
    List<ResultadoBusca> findUltimosPorCpf(CPF cpf, int limite);
}
