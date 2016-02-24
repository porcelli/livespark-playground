package demo.server.security;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.jboss.errai.bus.server.api.RpcContext;
import org.jboss.errai.security.shared.api.identity.User;
import org.jboss.errai.security.shared.api.identity.UserImpl;
import org.jboss.errai.security.shared.service.AuthenticationService;
import org.uberfire.rpc.SessionInfo;
import org.uberfire.rpc.impl.SessionInfoImpl;

@ApplicationScoped
public class SessionInfoFactory {

    @Inject
    private AuthenticationService authenticationService;

    @Produces
    @RequestScoped
    public User getIdentity() {
        try {
            return authenticationService.getUser();
        } catch ( final Exception ex ) {
            // There is a NPE while creating the form sources injecting the User. Added this to avoid the error.
            return new UserImpl( "system" );
        }
    }

    @Produces
    @RequestScoped
    @Default
    public static SessionInfo getSessionInfo( AuthenticationService authenticationService) {
        return new SessionInfoImpl( RpcContext.getQueueSession().getSessionId(), authenticationService.getUser() );
    }
}
