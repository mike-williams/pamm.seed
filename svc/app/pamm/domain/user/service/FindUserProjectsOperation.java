package pamm.domain.user.service;

import pamm.dal.user.UserRepository;
import pamm.domain.ServiceResult;
import pamm.domain.project.model.Project;
import play.Logger;
import play.libs.Json;

import javax.inject.Inject;
import java.util.List;

public class FindUserProjectsOperation {
    private static final Logger.ALogger LOG = Logger.of(FindUserProjectsOperation.class);

    private final UserRepository repository;

    @Inject
    public FindUserProjectsOperation(UserRepository repository) {
        this.repository = repository;
    }

    public ServiceResult execute(final Integer userId) {
        final List<Project> projects = repository.findProjectsForUser(userId);
        final ServiceResult serviceResult = new ServiceResult(Json.toJson(projects));
        serviceResult.setRawResult(projects);
        return serviceResult;
    }
}
