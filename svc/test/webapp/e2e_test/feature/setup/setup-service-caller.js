"use strict";

module.exports = (function () {
    var request = require('request-promise');

    function SetupServiceCaller() {
    }

    SetupServiceCaller.prototype.update = function (name) {
        var pathUrl = 'http://localhost:9001/update/' + name;

        // This returns a promise.  Use success call back to check database
        return request(pathUrl);
    };

    SetupServiceCaller.prototype.query = function (name) {
        var pathUrl = 'http://localhost:9001/query/' + name;

        // This returns a promise.  Use success call back to check database
        return request(pathUrl);
    };

    return SetupServiceCaller;
})();
