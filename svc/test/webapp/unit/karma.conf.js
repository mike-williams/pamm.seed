module.exports = function (config) {
    config.set({
        frameworks: ['jasmine'],

        basePath: '../../../',

        preprocessors: {
            "public/feature/**/*.js": ['coverage'],
            "public/service/**/*.js": ['coverage'],
            "public/constant/**/*.js": ['coverage'],
            "public/directive/**/*.js": ['coverage'],
            "public/*.js": ['coverage']
        },

        files: [
            "public/lib/lodash/lodash.min.js",
            "public/lib/jquery/jquery.min.js",
            "public/lib/angular/angular.min.js",
            "public/lib/angular/angular-messages.min.js",
            "public/lib/angular/angular-animate.min.js",
            "public/lib/angular-ui/angular-ui-router.min.js",
            "public/lib/angular-ui/ui-bootstrap-tpls.min.js",
            "public/lib/bootstrap/js/bootstrap.min.js",
            "public/lib/bootstrap3-dialog/js/bootstrap-dialog.min.js",
            "public/lib/angular-google-chart/ng-google-chart.min.js",

            "test/webapp/unit/app.js",

            <!-- feature being tested -->
            "public/service/repository/dal.js",

            {pattern: 'src/test/webapp/service/**/*.spec.js'}

        ],
        // list of files to exclude
        exclude: [],

        // web server port
        port: 9876,

        // enable / disable colors in the output (reporters and logs)
        colors: true,

        // possible values: config.LOG_DISABLE || config.LOG_ERROR || config.LOG_WARN || config.LOG_INFO || config.LOG_DEBUG
        logLevel: config.LOG_INFO,

        // enable / disable watching file and executing tests whenever any file changes
        autoWatch: true,

        // start these browsers when tests are launched
        browsers: ['Chrome'],

        // test results reporter to use
        reporters: ['progress', 'coverage'],

        coverageReporter: {
            type: 'html',
            dir: 'test/webapp/unit/reports/karma/coverage/'
        },

        // Continuous Integration mode
        // if true, Karma captures browsers, runs the tests and exits
        singleRun: true
    });
};
