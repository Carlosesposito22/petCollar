package petcollar.dominio.recepcaotriagem.prontuario;

import java.util.List;

public interface ITutorRepositorio {
    void save(Tutor tutor);
    Tutor findByCpf(CPF cpf);
    boolean existsByCpf(CPF cpf);
    List<Tutor> findTodos();
}
