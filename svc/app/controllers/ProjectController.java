package controllers;

import pamm.domain.ServiceResult;
import pamm.domain.project.model.Project;
import pamm.domain.user.service.UserService;
import pamm.infrastructure.security.authentication.Principal;
import play.db.jpa.Transactional;
import play.mvc.Result;
import views.html.web.user.project.project;
import views.html.web.user.project.projectMenu;

import javax.inject.Inject;
import java.util.List;

public class ProjectController extends ResourceController {
    private final UserService userService;

    @Inject
    public ProjectController(UserService userService) {
        this.userService = userService;
    }

    @Transactional
    public Result index() {
        final Principal userPrincipal = getUserPrincipal();
        final Integer userId = new Integer(userPrincipal.getSubject());
        final ServiceResult serviceResult = userService.findProjects(userId);
        final List<Project> projects = (List<Project>) serviceResult.getRawResult();

        // TODO check status
        // set the CSS for home tab
        flash("tab.project", "active");
        return ok(project.render(projects, projectMenu.apply()));
    }
}

