package pamm.domain.user.service;

import pamm.dal.user.UserRepository;
import pamm.domain.ServiceResult;
import pamm.domain.user.model.User;
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
