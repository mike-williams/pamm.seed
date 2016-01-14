"use strict";

angular.module("pamm").controller("userRegisterCtrl", ["$state", "$stateParams", "$log", "userDao",
    function ($state, $stateParams, $log, userDao) {
        var vm = this;

        (function init() {
            vm.registerError = false;
            vm.$$dataType = $$dataType;

            //Prepare the scope to store user inputs
            if ($stateParams.data == null) {
                vm.details = {};
            }
            else {
                vm.details = $stateParams.data;
            }
        })();

        vm.register = function (form) {
            if (form.$valid) {
                vm.registerError = false;

                var waitingDialog = $$dialog.waiting("Please wait - Registration in progress");
                var successfulRegistration = function () {
                    waitingDialog.close();
                    $$dialog.success("Registration is successful. A validation link has been sent to your email",
                        function () {
                            $state.go("user.login");
                        })
                };

                var failedRegistration = function (error) {
                    vm.registerError = true;
                    waitingDialog.close();

                    // TODO check error code and display appropriate message
                    $$dialog.error("Registration is unsuccessful");
                };

                userDao.register(vm.details).then(successfulRegistration, failedRegistration);
            }
        };

        vm.cancel = function () {
            $state.go("user.login");
        };

        vm.showTermsOfUse = function () {
            $state.go("user.termsofuse", {registration: vm.registration});
        };

        $log.debug("userRegisterCtrl: instantiated");
    }]);
