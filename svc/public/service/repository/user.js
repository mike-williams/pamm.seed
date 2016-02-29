"use strict";

angular.module("pamm").service("userRepository", ["dal", "$log",
    function (dal, $log) {
        this.register = function (newUser) {
            return dal.http.POST("register/user", newUser);
        };

        $log.info("dal:userRepository Instantiated");
    }]);
