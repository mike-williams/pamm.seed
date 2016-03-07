module.exports = function addProjectStep() {
    var NavigationPage = require("../setup/navigation.page.js");
    var nav = new NavigationPage;

    var ProjectAddPage = require("./page/project-add.page.js");
    var projectForm = new ProjectAddPage;

    var SetupServiceCaller = require("../" + "setup/setup-service-caller.js");
    var db = new SetupServiceCaller();

    db.update("delete.sql");

    var test = this;
    var testData = require("./add-project-data.json");


    test.setDefaultTimeout(60000);

    test.Given("I am on the add project form", function (next) {
        nav.toAddProject().then(function () {
            next();
        });
    });

    test.Then("I should be able add a new project", function (next) {
        projectForm.addTitle(testData.title);
        projectForm.addSummary(testData.summary);
        projectForm.addProjectCode(testData.projectCode);
        projectForm.saveProject().then(function () {
            db.query("check.sql").then(
                function success(response) {
                    expect(response).to.equal("PASSED");
                    next();
                }, function error(error) {
                    console.error("[ERROR] problem executing check script: " + error);
                    next();
                }
            );
        });
    });
};
