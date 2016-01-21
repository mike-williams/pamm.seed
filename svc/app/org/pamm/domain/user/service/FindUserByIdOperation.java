package org.pamm.domain.user.service;

import org.pamm.dal.user.UserRepository;
import org.pamm.domain.ServiceResult;
import org.pamm.domain.user.model.User;
import play.Logger;
import play.libs.Json;

import javax.inject.Inject;

public class FindUserByIdOperation {
    private static final Logger.ALogger LOG = Logger.of(FindUserByIdOperation.class);

    private final UserRepository repository;

    @Inject
    public FindUserByIdOperation(UserRepository repository) {
        this.repository = repository;
    }

    public ServiceResult execute(final Integer id) {
        final User user = repository.get(id);
        return new ServiceResult(Json.toJson(user));
    }
}
