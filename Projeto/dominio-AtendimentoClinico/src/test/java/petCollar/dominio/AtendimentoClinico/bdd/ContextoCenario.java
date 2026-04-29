package petCollar.dominio.AtendimentoClinico.bdd;

import org.mockito.Mockito;
import petCollar.dominio.AtendimentoClinico.relatorio.*;
import petCollar.dominio.AtendimentoClinico.nutricao.*;

import java.util.List;

public class ContextoCenario {

    // ── Mocks de repositório ──────────────────────────────────────────────────
    public final IRelatorioClinicoRepositorio repositorioRelatorio =
            Mockito.mock(IRelatorioClinicoRepositorio.class);

    // ── Serviços de domínio (recebem mocks via construtor) ────────────────────
    public final GeracaoEvolucaoComparativaService servicoEvolucao =
            new GeracaoEvolucaoComparativaService(repositorioRelatorio);

    public final AssinaturaDigitalService servicoAssinatura =
            new AssinaturaDigitalService(repositorioRelatorio);

    // ── Estado do cenário (preenchido pelos passos) ───────────────────────────
    public RelatorioClinico relatorio;
    public RelatorioClinicoId relatorioId;
    public PacienteId pacienteId;
    public EvolucaoComparativa evolucaoGerada;

    // ════════════════════════════════════════════════════════════════════════════════════════════
    // Mocks de Repositório
    // ════════════════════════════════════════════════════════════════════════════════════════════
    public final IPlanoNutricionalRepositorio repositorioPlanoNutricional =
            Mockito.mock(IPlanoNutricionalRepositorio.class);

    // ════════════════════════════════════════════════════════════════════════════════════════════
    // Serviços de Domínio (puros, instanciados diretamente)
    // ════════════════════════════════════════════════════════════════════════════════════════════
    public final CalculoNEMService calculoNEMService =
            new CalculoNEMService();

    public final AnaliseComparativaPesoService analiseComparativaPesoService =
            new AnaliseComparativaPesoService();

    public final GeracaoCronogramaTransicaoService geracaoCronogramaTransicaoService =
            new GeracaoCronogramaTransicaoService();

    // ════════════════════════════════════════════════════════════════════════════════════════════
    // Estado do Cenário (preenchido pelos passos Cucumber)
    // ════════════════════════════════════════════════════════════════════════════════════════════
    public PlanoNutricional plano;
    public PlanoNutricionalId planoId;
    public PesoIdeal pesoIdeal;
    public NivelAtividade nivelAtividadeAtual;
    public Double nemResultante;
    public Double gramasDiariasResultante;
    public AlertaManejoCritico alertaResultante;
    public CronogramaTransicao cronogramaResultante;
    public Exception excecaoCapturada;
}


