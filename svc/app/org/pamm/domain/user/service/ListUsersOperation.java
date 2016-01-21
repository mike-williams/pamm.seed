package org.pamm.domain.user.service;

import org.pamm.dal.user.UserRepository;
import org.pamm.domain.ServiceResult;
import org.pamm.domain.user.model.User;
import play.Logger;
import play.libs.Json;

import javax.inject.Inject;
import java.util.List;

public class ListUsersOperation {
    private static final Logger.ALogger LOG = Logger.of(ListUsersOperation.class);

    private final UserRepository repository;

    @Inject
    public ListUsersOperation(UserRepository repository) {
        this.repository = repository;
    }

    public ServiceResult execute() {
        final List<User> users = repository.getAll();
        return new ServiceResult(Json.toJson(users));
    }
}
