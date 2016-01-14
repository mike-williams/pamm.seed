package net.atos.pamm.domain.service.project;

import net.atos.pamm.domain.model.project.Project;
import net.atos.pamm.domain.model.project.ProjectRepository;
import net.atos.pamm.domain.service.ServiceResult;
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

    public ServiceResult execute(Integer id) {
        final Project project = repository.get(id);
        return new ServiceResult(Json.toJson(project));
    }
}
