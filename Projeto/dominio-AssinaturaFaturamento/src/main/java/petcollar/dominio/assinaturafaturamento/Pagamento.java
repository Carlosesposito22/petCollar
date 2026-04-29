package petcollar.dominio.assinaturafaturamento;

import java.time.LocalDateTime;
import java.util.List;

public class Pagamento {

    private final PagamentoId id;
    private final AssinaturaId assinaturaId;
    private final List<CobrancaId> cobrancaIds;
    private final Dinheiro valor;
    private final QRCodePix qrCode;
    private StatusPagamento status;
    private final LocalDateTime geradoEm;
    private final LocalDateTime expiraEm;

    public Pagamento(PagamentoId id, AssinaturaId assinaturaId,
                     List<CobrancaId> cobrancaIds, Dinheiro valor, QRCodePix qrCode) {
        if (id == null)
            throw new IllegalArgumentException("Id do pagamento não pode ser nulo.");
        if (assinaturaId == null)
            throw new IllegalArgumentException("AssinaturaId não pode ser nulo.");
        if (cobrancaIds == null || cobrancaIds.isEmpty())
            throw new IllegalArgumentException("Pagamento deve referenciar ao menos uma cobrança.");
        if (valor == null)
            throw new IllegalArgumentException("Valor do pagamento não pode ser nulo.");
        if (qrCode == null)
            throw new IllegalArgumentException("QR Code Pix não pode ser nulo.");
        this.id = id;
        this.assinaturaId = assinaturaId;
        this.cobrancaIds = List.copyOf(cobrancaIds);
        this.valor = valor;
        this.qrCode = qrCode;
        this.status = StatusPagamento.AGUARDANDO;
        this.geradoEm = LocalDateTime.now();
        this.expiraEm = this.geradoEm.plusHours(24);
    }

    public Pagamento(PagamentoId id, AssinaturaId assinaturaId,
                     List<CobrancaId> cobrancaIds, Dinheiro valor, QRCodePix qrCode,
                     StatusPagamento status, LocalDateTime geradoEm, LocalDateTime expiraEm) {
        this.id = id;
        this.assinaturaId = assinaturaId;
        this.cobrancaIds = List.copyOf(cobrancaIds);
        this.valor = valor;
        this.qrCode = qrCode;
        this.status = status;
        this.geradoEm = geradoEm;
        this.expiraEm = expiraEm;
    }

    public void confirmar() {
        if (this.status != StatusPagamento.AGUARDANDO)
            throw new IllegalStateException(
                    "Só é possível confirmar pagamento com status AGUARDANDO.");
        this.status = StatusPagamento.CONFIRMADO;
    }

    public void expirar() {
        if (this.status != StatusPagamento.AGUARDANDO)
            throw new IllegalStateException(
                    "Só é possível expirar pagamento com status AGUARDANDO.");
        this.status = StatusPagamento.EXPIRADO;
    }

    public void cancelar() {
        if (this.status == StatusPagamento.CONFIRMADO)
            throw new IllegalStateException(
                    "Não é possível cancelar pagamento já confirmado.");
        this.status = StatusPagamento.CANCELADO;
    }

    public boolean estaExpirado() {
        return LocalDateTime.now().isAfter(this.expiraEm);
    }

    public PagamentoId getId()                  { return id; }
    public AssinaturaId getAssinaturaId()       { return assinaturaId; }
    public List<CobrancaId> getCobrancaIds()    { return cobrancaIds; }
    public Dinheiro getValor()                  { return valor; }
    public QRCodePix getQrCode()                { return qrCode; }
    public StatusPagamento getStatus()          { return status; }
    public LocalDateTime getGeradoEm()          { return geradoEm; }
    public LocalDateTime getExpiraEm()          { return expiraEm; }
}
