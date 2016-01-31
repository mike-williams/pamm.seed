"use strict";

module.exports = (function () {
    function Navigation() {
        this.projectLink = $('[data-ng-click="u.navigateToProjects()"]');
    }

    Navigation.prototype.toProject = function () {
        return this.projectLink.click();
    };

    return Navigation;
})();
