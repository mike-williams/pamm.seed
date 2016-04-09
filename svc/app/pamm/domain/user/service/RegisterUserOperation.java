package pamm.domain.user.service;

import com.fasterxml.jackson.databind.JsonNode;
import pamm.dal.user.UserRepository;
import pamm.domain.ServiceResult;
import pamm.domain.user.model.User;
import pamm.infrastructure.mail.EmailService;
import pamm.infrastructure.security.authentication.UserAuthenticator;
import pamm.infrastructure.security.cipher.BCryptCipher;
import pamm.infrastructure.util.RequestUtil;
import play.Logger;
import play.libs.Json;

import javax.inject.Inject;

public class RegisterUserOperation {
    private static final Logger.ALogger LOG = Logger.of(RegisterUserOperation.class);

    private final UserRepository repository;
    private final BCryptCipher cipher;
    private final EmailService emailService;
    private final RequestUtil requestUtil;
    private final UserAuthenticator authenticator;


    @Inject
    public RegisterUserOperation(UserRepository repository,
                                 BCryptCipher cipher,
                                 UserAuthenticator authenticator,
                                 EmailService emailService,
                                 RequestUtil requestUtil) {
        this.repository = repository;
        this.cipher = cipher;
        this.emailService = emailService;
        this.requestUtil = requestUtil;
        this.authenticator = authenticator;
    }

    public ServiceResult execute(JsonNode jsonRequest) {
        if (repository.findUserByEmail(jsonRequest.findPath("email").textValue()) != null) {
            return new ServiceResult(ServiceResult.Status.OP_ERROR);
        } else {
            final User user = Json.fromJson(jsonRequest, User.class);
            user.setRole(User.Role.USER);

            user.setPassword(cipher.hash(jsonRequest.findPath("password").textValue()));
            repository.set(user);

            final String token = authenticator.generateJwtTokenForUser(user);

            emailService.sendEmail(user.getEmail(), "Welcome to Pamm. Please activate your account",
                    views.html.mailtemplates.userActivation.render(user, requestUtil.getBaseUrl(), token).toString());

            return new ServiceResult(jsonRequest);
        }
    }
}
