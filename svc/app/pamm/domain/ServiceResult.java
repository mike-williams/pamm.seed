package pamm.domain;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;

public class ServiceResult {

    public enum Status {SUCCESS, UNAUTHORIZED, OP_ERROR, SYS_ERROR, NOT_IMPLEMENTED}

    private final Status status;
    private final JsonNode result;
    private Object rawResult;

    public ServiceResult(JsonNode result) {
        this.result = result;
        this.status = Status.SUCCESS;
    }

    public ServiceResult(String message) {
        final ObjectNode messageNode = Json.newObject();
        messageNode.put("message", message);

        this.result = messageNode;
        this.status = Status.SUCCESS;
    }

    public ServiceResult(Status status, JsonNode result) {
        this.result = result;
        this.status = status;
    }

    public ServiceResult(Status status, String message) {
        final ObjectNode messageNode = Json.newObject();
        messageNode.put("message", message);
        result = messageNode;

        this.status = status;
    }

    public ServiceResult(Status status) {
        this.result = null;
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public JsonNode getResult() {
        return result;
    }

    public Object getRawResult() {
        return rawResult;
    }

    public void setRawResult(Object rawResult) {
        this.rawResult = rawResult;
    }
}
