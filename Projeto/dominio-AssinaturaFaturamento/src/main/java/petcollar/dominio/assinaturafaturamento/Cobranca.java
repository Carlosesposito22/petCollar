package petcollar.dominio.assinaturafaturamento;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Cobranca {

    private final CobrancaId id;
    private final AssinaturaId assinaturaId;
    private final Dinheiro valorOriginal;
    private final LocalDate vencimento;
    private StatusCobranca status;
    private final LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;

    public Cobranca(CobrancaId id, AssinaturaId assinaturaId,
                    Dinheiro valorOriginal, LocalDate vencimento) {
        if (id == null)
            throw new IllegalArgumentException("Id da cobrança não pode ser nulo.");
        if (assinaturaId == null)
            throw new IllegalArgumentException("AssinaturaId não pode ser nulo.");
        if (valorOriginal == null)
            throw new IllegalArgumentException("Valor original da cobrança não pode ser nulo.");
        if (vencimento == null)
            throw new IllegalArgumentException("Vencimento da cobrança não pode ser nulo.");
        this.id = id;
        this.assinaturaId = assinaturaId;
        this.valorOriginal = valorOriginal;
        this.vencimento = vencimento;
        this.status = StatusCobranca.ABERTA;
        this.criadoEm = LocalDateTime.now();
    }

    public Cobranca(CobrancaId id, AssinaturaId assinaturaId,
                    Dinheiro valorOriginal, LocalDate vencimento,
                    StatusCobranca status, LocalDateTime criadoEm,
                    LocalDateTime atualizadoEm) {
        this.id = id;
        this.assinaturaId = assinaturaId;
        this.valorOriginal = valorOriginal;
        this.vencimento = vencimento;
        this.status = status;
        this.criadoEm = criadoEm;
        this.atualizadoEm = atualizadoEm;
    }

    public void marcarEmAtraso() {
        if (this.status != StatusCobranca.ABERTA)
            throw new IllegalStateException(
                    "Só é possível marcar em atraso cobrança com status ABERTA.");
        this.status = StatusCobranca.EM_ATRASO;
        this.atualizadoEm = LocalDateTime.now();
    }

    public void registrarPagamento() {
        if (this.status == StatusCobranca.CANCELADA)
            throw new IllegalStateException(
                    "Não é possível registrar pagamento de cobrança cancelada.");
        if (this.status == StatusCobranca.PAGA)
            throw new IllegalStateException(
                    "Cobrança já foi paga.");
        this.status = StatusCobranca.PAGA;
        this.atualizadoEm = LocalDateTime.now();
    }

    public void cancelar() {
        if (this.status == StatusCobranca.PAGA)
            throw new IllegalStateException(
                    "Não é possível cancelar cobrança já paga.");
        this.status = StatusCobranca.CANCELADA;
        this.atualizadoEm = LocalDateTime.now();
    }

    public CobrancaId getId()               { return id; }
    public AssinaturaId getAssinaturaId()   { return assinaturaId; }
    public Dinheiro getValorOriginal()      { return valorOriginal; }
    public LocalDate getVencimento()        { return vencimento; }
    public StatusCobranca getStatus()       { return status; }
    public LocalDateTime getCriadoEm()      { return criadoEm; }
    public LocalDateTime getAtualizadoEm()  { return atualizadoEm; }
}
