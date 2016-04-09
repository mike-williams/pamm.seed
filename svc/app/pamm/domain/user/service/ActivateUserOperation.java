package pamm.domain.user.service;

import pamm.dal.user.UserRepository;
import pamm.domain.ServiceResult;
import pamm.domain.user.model.User;
import pamm.infrastructure.security.authentication.Principal;
import pamm.infrastructure.security.authentication.TokenStatus;
import pamm.infrastructure.security.authentication.UserAuthenticator;

import javax.inject.Inject;

public class ActivateUserOperation {

    private final UserRepository userRepository;
    private final UserAuthenticator authenticator;

    @Inject
    public ActivateUserOperation(UserRepository userRepository,
                                 UserAuthenticator authenticator) {
        this.userRepository = userRepository;
        this.authenticator = authenticator;
    }

    public ServiceResult execute(final String activationCode) {
        final Principal principal = authenticator.validateToken(activationCode);

        if (principal.getTokenStatus() == TokenStatus.VALID) {
            final Integer userId = new Integer(principal.getSubject());
            final User user = userRepository.get(userId);

            if (user == null) {
                return new ServiceResult(ServiceResult.Status.OP_ERROR, "User not found");
            } else if (user.getActivationDate() != null) {
                return new ServiceResult("User is already activated");
            } else {
                userRepository.activate(user);
                return (new ServiceResult(ServiceResult.Status.SUCCESS));
            }
        } else {
            return new ServiceResult(ServiceResult.Status.OP_ERROR, "Invalid Activation code");
        }
    }
}
