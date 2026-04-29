package petcollar.dominio.assinaturafaturamento;

import java.time.LocalDate;

public class ContratacaoPlanoService {

    private final IPlanoRepositorio planoRepositorio;
    private final IAssinaturaRepositorio assinaturaRepositorio;
    private final ICobrancaRepositorio cobrancaRepositorio;

    public ContratacaoPlanoService(IPlanoRepositorio planoRepositorio,
                                   IAssinaturaRepositorio assinaturaRepositorio,
                                   ICobrancaRepositorio cobrancaRepositorio) {
        this.planoRepositorio = planoRepositorio;
        this.assinaturaRepositorio = assinaturaRepositorio;
        this.cobrancaRepositorio = cobrancaRepositorio;
    }

    public Assinatura contratar(PlanoId planoId, String tutorId) {
        Plano plano = planoRepositorio.findById(planoId);
        if (plano == null)
            throw new IllegalArgumentException("Plano não encontrado com o id informado.");
        if (!plano.isDisponivel())
            throw new IllegalArgumentException("Plano não está disponível para contratação.");

        AssinaturaId assinaturaId = AssinaturaId.gerar();
        Assinatura assinatura = new Assinatura(assinaturaId, tutorId, planoId);
        assinaturaRepositorio.save(assinatura);

        Cobranca primeiraCobranca = new Cobranca(
                CobrancaId.gerar(),
                assinaturaId,
                plano.getMensalidade(),
                LocalDate.now().plusDays(7)
        );
        cobrancaRepositorio.save(primeiraCobranca);

        return assinatura;
    }

    public Assinatura confirmarPrimeiroPagamento(AssinaturaId assinaturaId) {
        Assinatura assinatura = assinaturaRepositorio.findById(assinaturaId);
        if (assinatura == null)
            throw new IllegalArgumentException("Assinatura não encontrada com o id informado.");
        assinatura.ativar();
        assinaturaRepositorio.save(assinatura);
        return assinatura;
    }
}
