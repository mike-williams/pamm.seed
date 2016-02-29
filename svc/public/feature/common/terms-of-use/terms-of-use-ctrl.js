angular.module("pamm").controller("termsOfUseCtrl", ["$state", "$log", "userContext", "legalRefData",
    function ($state, $log, userContext, legalRefData) {
        var vm = this;

        (function init() {
            vm.termsAndConditionsHeading = legalRefData.TERMS_OF_USE.heading;
            vm.termsAndConditionsBody = legalRefData.TERMS_OF_USE.body;
        })();

        vm.back = function () {
            userContext.returnToLastState();
        }
    }]);
