package controllers;

import pamm.infrastructure.security.authentication.Principal;
import pamm.infrastructure.security.endpoint.RestSecuredAction;
import play.Logger;
import play.mvc.Controller;
import play.mvc.With;

@With(RestSecuredAction.class)
public abstract class ResourceController extends Controller {
    private static final Logger.ALogger LOG = Logger.of(ResourceController.class);

    protected Principal getUserPrincipal() {
        return (Principal) ctx().args.get(Principal.class.getName());
    }
}
