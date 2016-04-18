require("./conf.js");

config.cucumberOpts = {
    //format: "summary",
    require: [
        "../login/login.step.js"
    ]
};

config.specs = [
    "../login/login.feature"
];

exports.config = config;

