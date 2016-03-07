module.exports = function loginTest() {
    var LoginPage = require("../login/login.page.js");
    var testData = require("../login/login-data.json");

//var testData = require(base + "test-data/" + browser.params.profile + "/login-add-project-data.json");

    var login = new LoginPage;
    var test = this;

    this.setDefaultTimeout(60000);

    test.Given("I am on the login view at the start", function () {
        login.visitPage();

    });

    test.Given("I login with valid credentials", function (next) {
        login.fillInDetails(testData.userName, testData.password);
        login.login().then(function () {
            next();
        });
    });

    test.Then("I see the home page", function () {
        expect(login.currentURL()).to.eventually.equal(testData.homeURL);
    });
};
