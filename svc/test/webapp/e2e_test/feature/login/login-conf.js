require("../setup/conf.js");

config.cucumberOpts = {
    //format: "summary",
    require: [
        "login.step.js"
    ]
};

config.specs = [
    "login.feature"
];

exports.config = config;

