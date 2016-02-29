package pamm.infrastructure.audit.model;

import pamm.infrastructure.audit.service.AuditService;

public class AuditInfo {

    private AuditService.LEVEL level;
    private String message;

    public AuditService.LEVEL getLevel() {
        return level;
    }

    public void setLevel(AuditService.LEVEL level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
