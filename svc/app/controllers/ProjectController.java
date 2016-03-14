package controllers;

import pamm.domain.ServiceResult;
import pamm.domain.project.model.Project;
import pamm.domain.project.service.ProjectService;
import pamm.domain.user.service.UserService;
import pamm.infrastructure.security.authentication.Principal;
import play.data.Form;
import play.data.validation.ValidationError;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Result;
import views.html.web.user.project.createProject;
import views.html.web.user.project.createProjectMember;
import views.html.web.user.project.project;
import views.html.web.user.project.projectMenu;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

public class ProjectController extends ResourceController {
    private final UserService userService;
    private final ProjectService projectService;

    @Inject
    public ProjectController(UserService userService, ProjectService projectService) {
        this.userService = userService;
        this.projectService = projectService;
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

    @Transactional
    public Result getCreateProject() {
        return ok(createProject.render(projectMenu.apply()));
    }

    @Transactional
    public Result postCreateProject() {
        final Form<Project> projectForm = Form.form(Project.class).bindFromRequest();

        if (projectForm.hasErrors()) {
            final Map<String, List<ValidationError>> errors = projectForm.errors();

            if (errors.get("title") != null) {
                flash("title.required", "error");
            }

            if (errors.get("projectCode") != null) {
                flash("projectCode.required", "error");
            }

            if (errors.get("summary") != null) {
                flash("summary.required", "error");
            }

            return badRequest(createProject.render(projectMenu.apply()));
        } else {
            final Project project = projectForm.get();
            projectService.create(Json.toJson(project));

            // TODO check status
            // set the CSS for home tab
            flash("tab.project", "active");
            return redirect(routes.ProjectController.index());
        }
    }

    @Transactional
    public Result getCreateProjectMember() {
        return ok(createProjectMember.render(projectMenu.apply()));
    }
}

