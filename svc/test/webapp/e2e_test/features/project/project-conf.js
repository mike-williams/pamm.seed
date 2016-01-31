exports.config = {
    seleniumAddress: 'http://localhost:4444/wd/hub',

    capabilities: {
        browserName: 'chrome'
    },

    // CHANGE this to test server
    baseUrl: "http://localhost:9000",

    params: {
        profile: "local",
        testMode: "single"
    },

    specs: [
        "../setup/setup-login.feature",
        "project.feature"
    ],

    onPrepare: function () {
        browser.driver.manage().window().setPosition(0, 0);
        browser.driver.manage().window().setSize(1280, 720);
        global.EC = protractor.ExpectedConditions;

        chai = require('chai');
        chaiAsPromised = require("chai-as-promised");
        chai.use(chaiAsPromised);

        expect = chai.expect;
        assert = chai.assert;
        should = chai.should;
    },

    // set to "custom" instead of cucumber.
    framework: 'custom',

    // path relative to the current config file
    frameworkPath: '../../../../../../node_modules/protractor-cucumber-framework',

    cucumberOpts: {
        //format: "summary",
        require: [
            '../setup/setup-login.step.js',
            'project.step.js'
        ]
    }
};
