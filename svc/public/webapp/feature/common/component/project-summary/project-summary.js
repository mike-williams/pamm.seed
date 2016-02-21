"use strict";

angular.module("pamm").directive("projectSummary", [function () {
    return {
        restrict: "E",
        replace: true,
        templateUrl: "feature/common/component/project-summary/project-summary.html",
        controller: "projectSummaryCtrl",
        controllerAs: 'vm',
        scope: true,
        bindToController : {
            project: "="
        }
    };
}]);
