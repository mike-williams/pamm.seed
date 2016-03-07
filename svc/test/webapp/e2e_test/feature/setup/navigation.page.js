"use strict";

module.exports = (function () {
    function Navigation() {
        this.projectLink = $('[data-ng-click="u.navigateToProjects()"]');
        this.addProjectLink = $('[data-ng-click="p.newRootProject()"]');
    }

    Navigation.prototype.toProject = function () {
        return this.projectLink.click();
    };

    Navigation.prototype.toAddProject = function () {
        return this.addProjectLink.click();
    };

    return Navigation;
})();
