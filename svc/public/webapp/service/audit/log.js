angular.module("pamm").decorator("$log", ["$delegate", function ($delegate) {

    var log = $delegate.log;
    var info = $delegate.info;
    var debug = $delegate.debug;
    var warn = $delegate.warn;
    var error = $delegate.error;

    var audit = function(level, message) {
        // TODO send to server
    };

    $delegate.log = function (message) {
        log("[LOG] " + message);
        audit("LOG", message);
    };

    $delegate.info = function (message) {
        info("[DEBUG] " + message);
        audit("DEBUG", message);
    };

    $delegate.warn = function (message) {
        warn("[DEBUG] " + message);
        audit("DEBUG", message);
    };

    $delegate.error = function (message) {
        error("[DEBUG] " + message);
        audit("DEBUG", message);
    };

    $delegate.debug = function (message) {

        debug("[DEBUG] " + message);
        audit("DEBUG", message);
    };

    $delegate.debug("Audit: added logging");
    return $delegate;
}]);