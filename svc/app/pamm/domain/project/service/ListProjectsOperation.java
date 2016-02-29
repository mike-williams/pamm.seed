package pamm.domain.project.service;

import pamm.dal.project.ProjectRepository;
import pamm.domain.ServiceResult;
import pamm.domain.project.model.Project;
import play.Logger;
import play.libs.Json;

import javax.inject.Inject;
import java.util.List;

public class ListProjectsOperation {
    private static final Logger.ALogger LOG = Logger.of(ListProjectsOperation.class);

    private final ProjectRepository repository;

    @Inject
    public ListProjectsOperation(ProjectRepository repository) {
        this.repository = repository;
    }

    public ServiceResult execute() {
        final List<Project> projects = repository.getAll();
        return new ServiceResult(Json.toJson(projects));
    }
}
