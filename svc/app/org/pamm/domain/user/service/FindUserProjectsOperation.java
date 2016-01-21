package org.pamm.domain.user.service;

import org.pamm.dal.user.UserRepository;
import org.pamm.domain.ServiceResult;
import org.pamm.domain.project.model.Project;
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
        return new ServiceResult(Json.toJson(projects));
    }
}
