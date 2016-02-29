package pamm.infrastructure.security.endpoint;

import com.google.inject.Inject;
import pamm.infrastructure.security.authentication.TokenStatus;
import play.libs.F;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import pamm.infrastructure.security.authentication.Authenticator;
import pamm.infrastructure.security.authentication.Principal;

public class ControllerSecuredAction extends Action.Simple {

    private final Authenticator authenticator;

    @Inject
    public ControllerSecuredAction(Authenticator authenticator) {
        this.authenticator = authenticator;
    }

    public F.Promise<Result> call(final Http.Context ctx) throws Throwable {
        final String token = ctx.session().get("authToken");

        if (token == null) {
            return F.Promise.pure(Results.unauthorized("No Credentials"));
        } else {
            final Principal principal = authenticator.validateToken(token);

            if (principal.getTokenStatus() == TokenStatus.INVALID) {
                return F.Promise.pure(Results.unauthorized("Invalid Credentials"));
            } else if (principal.getTokenStatus() == TokenStatus.EXPIRED) {
                return F.Promise.pure(Results.unauthorized("Token Expired"));
            } else {
                ctx.args.put(Principal.class.getName(), principal);
                return delegate.call(ctx);
            }
        }
    }
}
