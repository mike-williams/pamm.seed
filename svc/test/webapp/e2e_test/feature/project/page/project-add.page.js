"use strict";

module.exports = (function () {
    function ProjectAddPage() {
        this.title = element(by.model("project.title"));
        this.projectCode = element(by.model("project.projectCode"));
        this.summary = element(by.model("project.summary"));
        this.save = element(by.css('[type="submit"]'));
    }

    ProjectAddPage.prototype.addTitle = function (title) {
        return this.title.sendKeys(title);
    };

    ProjectAddPage.prototype.addSummary = function (summary) {
        return this.summary.sendKeys(summary);
    };

    ProjectAddPage.prototype.addProjectCode = function (projectCode) {
        return this.projectCode.sendKeys(projectCode);
    };

    ProjectAddPage.prototype.saveProject = function () {
        return this.save.click();
    };

    ProjectAddPage.prototype.closeModal = function (buttonId) {
        var closeButton = element(by.id(buttonId));
        browser.wait(EC.visibilityOf(closeButton), 1000);
        return closeButton.click();
    };

    return ProjectAddPage;
})();
