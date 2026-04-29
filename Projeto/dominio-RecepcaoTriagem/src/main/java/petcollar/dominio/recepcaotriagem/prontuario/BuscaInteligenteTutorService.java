package petcollar.dominio.recepcaotriagem.prontuario;

public class BuscaInteligenteTutorService {

    private final ITutorRepositorio tutorRepositorio;
    private final IResultadoBuscaRepositorio resultadoBuscaRepositorio;

    public BuscaInteligenteTutorService(ITutorRepositorio tutorRepositorio,
                                        IResultadoBuscaRepositorio resultadoBuscaRepositorio) {
        this.tutorRepositorio = tutorRepositorio;
        this.resultadoBuscaRepositorio = resultadoBuscaRepositorio;
    }

    /**
     * Busca um tutor pelo CPF. Cria e persiste um ResultadoBusca associando o tutor se encontrado.
     * RN-001, RN-002
     */
    public ResultadoBusca buscarPorCpf(CPF cpf) {
        if (cpf == null)
            throw new IllegalArgumentException("CPF não pode ser nulo para realizar a busca.");

        Tutor tutor = tutorRepositorio.findByCpf(cpf);

        ResultadoBuscaId resultadoId = ResultadoBuscaId.gerar();
        ResultadoBusca resultado = new ResultadoBusca(resultadoId, cpf);

        if (tutor != null) {
            resultado.associarTutor(tutor);
        }

        resultadoBuscaRepositorio.save(resultado);
        return resultado;
    }

    /**
     * Cadastra um novo tutor sem perder o contexto da busca anterior.
     * Cria o tutor, persiste e atualiza um novo ResultadoBusca com o tutor associado.
     * RN-002
     */
    public ResultadoBusca cadastrarNovoTutor(CPF cpf, String nome, String telefone, String email) {
        if (cpf == null)
            throw new IllegalArgumentException("CPF não pode ser nulo para cadastrar tutor.");
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome não pode ser vazio para cadastrar tutor.");

        TutorId tutorId = TutorId.gerar();
        Tutor tutor = new Tutor(tutorId, nome, cpf, telefone, email);
        tutorRepositorio.save(tutor);

        ResultadoBuscaId resultadoId = ResultadoBuscaId.gerar();
        ResultadoBusca resultado = new ResultadoBusca(resultadoId, cpf);
        resultado.associarTutor(tutor);
        resultadoBuscaRepositorio.save(resultado);

        return resultado;
    }
}
