package petcollar.dominio.assinaturafaturamento;

import java.util.List;
import java.util.UUID;

public class GeracaoQRCodeService {

    private final ICobrancaRepositorio cobrancaRepositorio;
    private final IPagamentoRepositorio pagamentoRepositorio;

    public GeracaoQRCodeService(ICobrancaRepositorio cobrancaRepositorio,
                                IPagamentoRepositorio pagamentoRepositorio) {
        this.cobrancaRepositorio = cobrancaRepositorio;
        this.pagamentoRepositorio = pagamentoRepositorio;
    }

    public Pagamento gerarQRCodeParaCobranca(CobrancaId cobrancaId) {
        Cobranca cobranca = cobrancaRepositorio.findById(cobrancaId);
        if (cobranca == null)
            throw new IllegalArgumentException("Cobrança não encontrada com o id informado.");

        QRCodePix qrCode = gerarQRCode(cobranca.getValorOriginal());
        Pagamento pagamento = new Pagamento(
                PagamentoId.gerar(),
                cobranca.getAssinaturaId(),
                List.of(cobrancaId),
                cobranca.getValorOriginal(),
                qrCode
        );
        pagamentoRepositorio.save(pagamento);
        return pagamento;
    }

    public Pagamento gerarQRCodeConsolidado(AssinaturaId assinaturaId,
                                            List<Cobranca> cobrancasEmAtraso) {
        if (cobrancasEmAtraso == null || cobrancasEmAtraso.isEmpty())
            throw new IllegalArgumentException(
                    "É necessário ao menos uma cobrança em atraso para gerar QR Code consolidado.");

        double totalDevido = cobrancasEmAtraso.stream()
                .mapToDouble(c -> c.getValorOriginal().getValor())
                .sum();
        Dinheiro valorTotal = new Dinheiro(totalDevido,
                cobrancasEmAtraso.get(0).getValorOriginal().getMoeda());

        List<CobrancaId> ids = cobrancasEmAtraso.stream()
                .map(Cobranca::getId)
                .toList();

        QRCodePix qrCode = gerarQRCode(valorTotal);
        Pagamento pagamento = new Pagamento(
                PagamentoId.gerar(),
                assinaturaId,
                ids,
                valorTotal,
                qrCode
        );
        pagamentoRepositorio.save(pagamento);
        return pagamento;
    }

    private QRCodePix gerarQRCode(Dinheiro valor) {
        String txid = UUID.randomUUID().toString().replace("-", "").substring(0, 26);
        String codigoCopiaECola = "00020126330014BR.GOV.BCB.PIX"
                + txid
                + "5204000053039865802BR"
                + String.format("%.2f", valor.getValor())
                + "6304";
        return new QRCodePix(codigoCopiaECola, txid);
    }
}
