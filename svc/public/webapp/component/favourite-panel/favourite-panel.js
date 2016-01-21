"use strict";

angular.module("pamm").directive("favpanel", function () {
    return {
        restrict: "E",
        replace: true,
        templateUrl: "component/favourite-panel/favourite-panel.html"
    };
});
