"use strict";

angular.module("pamm").service("issueRepository", ["$q", "$log", "dal", "$rootScope", "contextEvent", "userContext", "issueDao",
    function ($q, $log, dal, $rootScope, contextEvent, userContext, issueDao) {
        var issueCache = [];

        (function init() {
            $rootScope.$on(contextEvent.CLEAR_CONTEXT, function clearContext() {
                issueCache = [];
                $log.info("issueRepository: context cleared");
            })
        })();

        this.getProjectIssues = function (projectId) {
            var deferred = $q.defer();

            if (issueCache[projectId] == undefined) {
                issueDao.getProjectIssues(projectId).then(function (results) {
                    issueCache[projectId] = results;
                    deferred.resolve(results);
                }, function (error) {
                    deferred.reject(error);
                });
            } else {
                deferred.resolve(issueCache);
            }
            return deferred.promise;
        };

        this.saveProject = function (issueToSave) {
            var deferred = $q.defer();

            issueDao.saveProject(issueToSave).then(function (savedIssue) {
                issueCache.push(savedIssue);
                deferred.resolve(savedIssue);
            }, function (error) {
                deferred.reject(error);
            });
            return deferred.promise;
        };
    }]);

