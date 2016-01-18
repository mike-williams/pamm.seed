/**
 * Common page for T&C.  This page relies on the calling state to provide the calling state name so that it can use
 * it to navigate back.
 *
 * @param $stateParams.from Name of the state.  This is used for navigating back
 */

angular.module("pamm").controller("termsOfUseCtrl", ["$state", "$stateParams", "$log", "legalRefData",
    function ($state, $stateParams, $log, legalRefData) {
        var vm = this;

        (function init() {
            vm.termsAndConditionsHeading = legalRefData.TERMS_OF_USE.heading;
            vm.termsAndConditionsBody = legalRefData.TERMS_OF_USE.body;
        })();

        vm.back = function () {
            $state.go($state.current.data.baseState, {credentials: $stateParams.credentials});
        }
    }]);
