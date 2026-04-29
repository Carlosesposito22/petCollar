package petcollar.dominio.assinaturafaturamento;

import java.util.List;

public class GestaoInadimplenciaService {

    private static final int LIMITE_INADIMPLENCIA = 1;
    private static final int LIMITE_SUSPENSAO = 3;

    private final IAssinaturaRepositorio assinaturaRepositorio;
    private final ICobrancaRepositorio cobrancaRepositorio;

    public GestaoInadimplenciaService(IAssinaturaRepositorio assinaturaRepositorio,
                                      ICobrancaRepositorio cobrancaRepositorio) {
        this.assinaturaRepositorio = assinaturaRepositorio;
        this.cobrancaRepositorio = cobrancaRepositorio;
    }

    public Assinatura avaliarStatusAssinatura(AssinaturaId assinaturaId) {
        Assinatura assinatura = assinaturaRepositorio.findById(assinaturaId);
        if (assinatura == null)
            throw new IllegalArgumentException("Assinatura não encontrada com o id informado.");

        int mesesEmAtraso = assinatura.getMensalidadesConsecutivasEmAtraso();

        if (mesesEmAtraso >= LIMITE_SUSPENSAO) {
            assinatura.suspender();
        } else if (mesesEmAtraso >= LIMITE_INADIMPLENCIA) {
            assinatura.marcarInadimplente();
        }

        assinaturaRepositorio.save(assinatura);
        return assinatura;
    }

    public Assinatura registrarQuitacao(AssinaturaId assinaturaId) {
        Assinatura assinatura = assinaturaRepositorio.findById(assinaturaId);
        if (assinatura == null)
            throw new IllegalArgumentException("Assinatura não encontrada com o id informado.");

        List<Cobranca> cobrancasEmAtraso = cobrancaRepositorio
                .findByAssinaturaIdEStatus(assinaturaId, StatusCobranca.EM_ATRASO);

        for (Cobranca cobranca : cobrancasEmAtraso) {
            cobranca.registrarPagamento();
            cobrancaRepositorio.save(cobranca);
        }

        assinatura.regularizar();
        assinaturaRepositorio.save(assinatura);
        return assinatura;
    }
}
