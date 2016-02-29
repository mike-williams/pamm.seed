package pamm.infrastructure.module;

import com.google.inject.AbstractModule;
import pamm.infrastructure.security.authentication.Authenticator;
import pamm.infrastructure.security.authentication.UserBearerDbAuthenticator;
import pamm.infrastructure.security.cipher.BCryptCipher;
import pamm.infrastructure.security.cipher.Cipher;

public class AuthenticationModule extends AbstractModule {
    protected void configure() {

        bind(Authenticator.class).to(UserBearerDbAuthenticator.class);
        bind(Cipher.class).to(BCryptCipher.class);
    }
}
