package pamm.domain.project.service;

import pamm.dal.project.ProjectRepository;
import pamm.domain.ServiceResult;
import pamm.domain.project.model.Project;
import play.Logger;
import play.libs.Json;

import javax.inject.Inject;

public class FindProjectOperation {
    private static final Logger.ALogger LOG = Logger.of(FindProjectOperation.class);

    private final ProjectRepository repository;

    @Inject
    public FindProjectOperation(ProjectRepository repository) {
        this.repository = repository;
    }

    public ServiceResult execute(final Integer id) {
        final Project project = repository.get(id);
        return new ServiceResult(Json.toJson(project));
    }
}
