package com.expedientesclinicos.dto.paciente;

import com.expedientesclinicos.dto.common.ModificadorRequest;

public class SesionRevertRequest {
    private Long historyId;
    private ModificadorRequest solicitante;

    public Long getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Long historyId) {
        this.historyId = historyId;
    }

    public ModificadorRequest getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(ModificadorRequest solicitante) {
        this.solicitante = solicitante;
    }
}
