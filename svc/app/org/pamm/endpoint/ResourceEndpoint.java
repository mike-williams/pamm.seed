package org.pamm.endpoint;

import org.pamm.domain.ServiceResult;
import org.pamm.infrastructure.security.authentication.Principal;
import org.pamm.infrastructure.security.endpoint.SecuredAction;
import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;

@With(SecuredAction.class)
public abstract class ResourceEndpoint extends Controller {
    private static final Logger.ALogger LOG = Logger.of(ResourceEndpoint.class);

    /**
     * Helper method for returning the appropriate HTTP response base on the service result.
     *
     * @param serviceResult
     * @return http response
     */
    protected Result response(final ServiceResult serviceResult) {
        switch (serviceResult.getStatus()) {
            case SUCCESS: {
                return ok(serviceResult.getResult());
            }
            case UNAUTHORIZED: {
                return unauthorized();
            }
            default: {
                return internalServerError();
            }
        }
    }

    protected Principal getUserPrincipal() {
        return (Principal) ctx().args.get(Principal.class.getName());
    }
}
