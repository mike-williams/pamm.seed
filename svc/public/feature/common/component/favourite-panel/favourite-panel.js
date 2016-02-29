"use strict";

angular.module("pamm").directive("favouritePanel", function () {
    return {
        restrict: "E",
        replace: true,
        templateUrl: "feature/common/component/favourite-panel/favourite-panel.html"
    };
});
