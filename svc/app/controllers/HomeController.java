package controllers;

import play.db.jpa.Transactional;
import play.mvc.Result;
import views.html.web.user.home.home;
import views.html.web.user.home.homeMenu;

public class HomeController extends ResourceController {

    @Transactional
    public Result index() {
        return ok(home.render(homeMenu.apply()));
    }
}
