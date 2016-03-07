require("../setup/conf.js");

config.cucumberOpts = {
    //format: "summary",
    require: [
        '../setup/setup-login.step.js',
        'add-project.step.js'
    ]
};

config.specs = [
    "../setup/setup-login.feature",
    "project.feature"
];


exports.config = config;