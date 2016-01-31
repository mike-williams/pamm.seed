"use strict";

module.exports = (function () {
    function ProjectListPage() {
        this.noProjectMessage = $('[data-ng-show="p.projects.length==0 && !p.waiting"]');
    }

    return ProjectListPage;
})();
