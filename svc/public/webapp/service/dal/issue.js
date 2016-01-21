"use strict";

angular.module("pamm").service("issueDao", ["dal", "$log", function (dal, $log) {
    this.getProjectIssues = function (projectId) {
        return dal.http.GET("project/" + projectId + "/issue");
    };

    this.saveIssue = function (issueToSave) {
        return dal.http.POST("project/" + projectId + "/issue", issueToSave);
    };

    $log.info("dal:issueDao Instantiated");
}]);
