package petcollar.dominio.assinaturafaturamento.bdd;

import org.mockito.Mockito;
import petcollar.dominio.assinaturafaturamento.*;

import java.util.List;

public class ContextoCenario {

    // ── Mocks de repositório ────────────────────────────────────────────────
    public final IPlanoRepositorio planoRepositorio =
            Mockito.mock(IPlanoRepositorio.class);

    public final IAssinaturaRepositorio assinaturaRepositorio =
            Mockito.mock(IAssinaturaRepositorio.class);

    public final ICobrancaRepositorio cobrancaRepositorio =
            Mockito.mock(ICobrancaRepositorio.class);

    public final IPagamentoRepositorio pagamentoRepositorio =
            Mockito.mock(IPagamentoRepositorio.class);

    // ── Serviços de domínio (recebem os mocks via construtor) ───────────────
    public final ContratacaoPlanoService contratacaoPlanoService =
            new ContratacaoPlanoService(planoRepositorio, assinaturaRepositorio, cobrancaRepositorio);

    public final GestaoInadimplenciaService gestaoInadimplenciaService =
            new GestaoInadimplenciaService(assinaturaRepositorio, cobrancaRepositorio);

    public final GeracaoQRCodeService geracaoQRCodeService =
            new GeracaoQRCodeService(cobrancaRepositorio, pagamentoRepositorio);

    // ── Serviços de cálculo puro (sem repositório) ──────────────────────────
    public final CalculoJurosService calculoJurosService =
            new CalculoJurosService();

    // ── Estado do cenário (preenchido pelos passos) ─────────────────────────
    public Plano plano;
    public PlanoId planoId;

    public Assinatura assinatura;
    public AssinaturaId assinaturaId;

    public Cobranca cobranca;
    public CobrancaId cobrancaId;
    public List<Cobranca> cobrancasEmAtraso;

    public Pagamento pagamento;

    public Dinheiro valorCalculado;
    public double valorOriginalCobranca;

    public Exception excecaoCapturada;
}
