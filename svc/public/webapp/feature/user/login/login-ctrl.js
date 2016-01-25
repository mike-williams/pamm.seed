"use strict";

angular.module("pamm").controller("userLoginCtrl", ["$state", "$log", "userContext", "httpStatus",
    function ($state, $log, userContext, httpStatus) {
        var vm = this;

        (function init() {
            vm.hasAuthenticationError = false;
        })();

        vm.register = function () {
            $state.go("user.register");
        };

        vm.login = function (loginForm) {
            if (loginForm.$valid) {
                var waitingDialog = $$dialog.waiting("Please wait - Logging In");

                vm.hasAuthenticationError = false;

                userContext.login(this.credentials.username, this.credentials.password).then(
                    function success() {
                        waitingDialog.close();
                        $state.go("user.home");
                    },
                    function error(errorResponse) {
                        waitingDialog.close();
                        if (errorResponse.status == httpStatus.UNAUTHORIZED) {
                            vm.hasAuthenticationError = true;
                        } else {
                            $$dialog.error("Service temporary unavailable.  Please try again later");
                        }
                    }
                )
            }
        };
    }]);
