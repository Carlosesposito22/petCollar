package petcollar.dominio.assinaturafaturamento;

import java.util.Objects;

public final class QRCodePix {

    private final String codigoCopiaECola;
    private final String txid;

    public QRCodePix(String codigoCopiaECola, String txid) {
        if (codigoCopiaECola == null || codigoCopiaECola.isBlank())
            throw new IllegalArgumentException("Código Copia e Cola do QR Code Pix não pode ser vazio.");
        if (txid == null || txid.isBlank())
            throw new IllegalArgumentException("TXID do QR Code Pix não pode ser vazio.");
        this.codigoCopiaECola = codigoCopiaECola;
        this.txid = txid;
    }

    public String getCodigoCopiaECola() {
        return codigoCopiaECola;
    }

    public String getTxid() {
        return txid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QRCodePix)) return false;
        QRCodePix other = (QRCodePix) o;
        return Objects.equals(codigoCopiaECola, other.codigoCopiaECola)
                && Objects.equals(txid, other.txid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoCopiaECola, txid);
    }
}
