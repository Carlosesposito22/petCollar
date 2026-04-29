package petcollar.dominio.assinaturafaturamento;

import java.time.LocalDateTime;

public class Assinatura {

    private final AssinaturaId id;
    private final String tutorId;
    private final PlanoId planoId;
    private StatusAssinatura status;
    private int mensalidadesConsecutivasEmAtraso;
    private final LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;

    public Assinatura(AssinaturaId id, String tutorId, PlanoId planoId) {
        if (id == null)
            throw new IllegalArgumentException("Id da assinatura não pode ser nulo.");
        if (tutorId == null || tutorId.isBlank())
            throw new IllegalArgumentException("TutorId não pode ser vazio.");
        if (planoId == null)
            throw new IllegalArgumentException("PlanoId não pode ser nulo.");
        this.id = id;
        this.tutorId = tutorId;
        this.planoId = planoId;
        this.status = StatusAssinatura.PENDENTE;
        this.mensalidadesConsecutivasEmAtraso = 0;
        this.criadoEm = LocalDateTime.now();
    }

    public Assinatura(AssinaturaId id, String tutorId, PlanoId planoId,
                      StatusAssinatura status, int mensalidadesConsecutivasEmAtraso,
                      LocalDateTime criadoEm, LocalDateTime atualizadoEm) {
        this.id = id;
        this.tutorId = tutorId;
        this.planoId = planoId;
        this.status = status;
        this.mensalidadesConsecutivasEmAtraso = mensalidadesConsecutivasEmAtraso;
        this.criadoEm = criadoEm;
        this.atualizadoEm = atualizadoEm;
    }

    public void ativar() {
        if (this.status != StatusAssinatura.PENDENTE)
            throw new IllegalStateException(
                    "Só é possível ativar assinatura com status PENDENTE.");
        this.status = StatusAssinatura.ATIVA;
        this.atualizadoEm = LocalDateTime.now();
    }

    public void marcarInadimplente() {
        if (this.status == StatusAssinatura.CANCELADA)
            throw new IllegalStateException(
                    "Não é possível alterar assinatura cancelada.");
        this.status = StatusAssinatura.INADIMPLENTE;
        this.atualizadoEm = LocalDateTime.now();
    }

    public void suspender() {
        if (this.status == StatusAssinatura.CANCELADA)
            throw new IllegalStateException(
                    "Não é possível alterar assinatura cancelada.");
        this.status = StatusAssinatura.SUSPENSA;
        this.atualizadoEm = LocalDateTime.now();
    }

    public void regularizar() {
        if (this.status != StatusAssinatura.INADIMPLENTE
                && this.status != StatusAssinatura.SUSPENSA)
            throw new IllegalStateException(
                    "Só é possível regularizar assinatura INADIMPLENTE ou SUSPENSA.");
        this.mensalidadesConsecutivasEmAtraso = 0;
        this.status = StatusAssinatura.ATIVA;
        this.atualizadoEm = LocalDateTime.now();
    }

    public void incrementarAtraso() {
        if (this.status == StatusAssinatura.CANCELADA)
            throw new IllegalStateException(
                    "Não é possível registrar atraso em assinatura cancelada.");
        this.mensalidadesConsecutivasEmAtraso++;
        this.atualizadoEm = LocalDateTime.now();
    }

    public AssinaturaId getId()                        { return id; }
    public String getTutorId()                         { return tutorId; }
    public PlanoId getPlanoId()                        { return planoId; }
    public StatusAssinatura getStatus()                { return status; }
    public int getMensalidadesConsecutivasEmAtraso()   { return mensalidadesConsecutivasEmAtraso; }
    public LocalDateTime getCriadoEm()                 { return criadoEm; }
    public LocalDateTime getAtualizadoEm()             { return atualizadoEm; }
}
