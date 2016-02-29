package pamm.domain.user.service;

import pamm.dal.user.UserRepository;
import pamm.domain.user.model.User;
import pamm.domain.ServiceResult;

import javax.inject.Inject;

public class ActivateUserOperation {

    private final UserRepository userRepository;

    @Inject
    public ActivateUserOperation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ServiceResult execute(final Integer userId, final String activationCode) {
        final User user = userRepository.get(userId);

        if (user == null) {
            return new ServiceResult(ServiceResult.Status.OP_ERROR, "User not found");
        } else if (user.isActivated()) {
            return new ServiceResult("User is already activated");
        } else if (user.getActivationKey().equals(activationCode)) {
            userRepository.activate(user);
            return (new ServiceResult(ServiceResult.Status.SUCCESS));
        } else {
            return new ServiceResult(ServiceResult.Status.OP_ERROR, "Activation code fo not match");
        }
    }
}
