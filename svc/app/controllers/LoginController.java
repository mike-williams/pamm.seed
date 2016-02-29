package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import pamm.domain.user.model.Credentials;
import pamm.domain.user.service.UserService;
import play.data.Form;
import play.data.validation.ValidationError;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.web.user.home.home;
import views.html.web.user.home.homeMenu;
import views.html.web.user.login.login;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

public class LoginController extends Controller {

    private final UserService userService;

    @Inject
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    public Result index() {
        return ok(login.render());
    }

    @Transactional
    public Result login() {
        final Form<Credentials> loginForm = Form.form(Credentials.class).
                bindFromRequest();

        if (loginForm.hasErrors()) {
            final Map<String, List<ValidationError>> errors = loginForm.errors();

            if (errors.get("password") != null) {
                flash("password.required", "error");
            }

            if (errors.get("username") != null) {
                for (ValidationError error : errors.get("username")) {
                    switch (error.message()) {
                        case "error.email":
                            flash("username.email", "error");
                            break;
                        case "error.required":
                            flash("username.required", "error");
                            break;
                    }
                }
            }
            return badRequest(login.render());
        } else {
            final Credentials credentials = loginForm.get();
            final JsonNode userPrincipal = userService.authenticate(credentials.getUsername(), credentials.getPassword()).getResult();

            if (userPrincipal == null) {
                flash("authentication.error", "error");
                return unauthorized(login.render());
            }

            // set the CSS for home tab
            flash("tab.home", "active");
            session("authToken", userPrincipal.get("authToken").asText());
            return ok(home.render(homeMenu.apply()));
        }
    }
}
