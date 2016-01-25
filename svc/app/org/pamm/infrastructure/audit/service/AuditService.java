package org.pamm.infrastructure.audit.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.pamm.domain.ServiceResult;
import org.pamm.infrastructure.audit.model.AuditInfo;
import play.libs.Json;


public class AuditService {

    public enum LEVEL {UNRECOVERABLE, SEVERE, ERROR, WARNING, AUDIT, INFO, CONFIG}

    public void log(LEVEL level, String message) {
        System.out.println("[" + level.name() + "] " + message);
    }

    public ServiceResult log(JsonNode jsonRequest) {

        final AuditInfo auditInfo = Json.fromJson(jsonRequest, AuditInfo.class);

        log(auditInfo.getLevel(), auditInfo.getMessage());

        return new ServiceResult(ServiceResult.Status.SUCCESS);
    }
}
