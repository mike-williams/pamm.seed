module.exports = function loginTest() {
    var RegistrationPage = require("./registration.page.js");
    var testData = require("./registration-testdata.json");

//var testData = require(base + "test-data/" + browser.params.profile + "/login-testdata.json");
    console.log("+++++" + browser.params.testMode);


    var register = new RegistrationPage;
    var test = this;

    this.setDefaultTimeout(60000);

    this.Given(/^i am on the registration page$/, function (callback) {
       register.goToPage();
    });

    this.Given(/^i have entered valid registration information$/, function (callback) {
      register.fillFirstName(testData.firstName);
      register.fillLastName(testData.lastName);
      register.fillEmail(testData.email);
      register.fillConfirmEmail(testData.email);
      register.fillPassword(testData.password);
      register.fillConfirmPassword(testData.password);

    });

    this.Given(/^i have accepted the terms and conditions$/, function (callback) {
        register.clickTermsAndConditionsCheckBox();

    });

    this.When(/^i request account registration$/, function (callback) {
        register.register();
    });

    this.Then(/^i should receive a confirmation email$/, function (callback) {
        callback.pending();
    });

};
