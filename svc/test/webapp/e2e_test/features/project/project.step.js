module.exports = function projectTest() {
    var NavigationPage = require("../" + "setup/navigation.page.js");
    var nav = new NavigationPage;

    var ProjectListPage = require("./page/project-list-page.js");
    var projectList = new ProjectListPage;

    var test = this;

    test.setDefaultTimeout(60000);

    test.Given("I am on the project page", function (next) {
        nav.toProject().then(function() {
            next();
        });
    });

    test.Given("I am not a member of any projects", function() {
    });

    test.Then("I should see a no projects message", function (next) {
        projectList.noProjectMessage.isDisplayed().then(function(isDisplayed) {
            expect(isDisplayed).to.be.true;
            next();
        });
    });

};
