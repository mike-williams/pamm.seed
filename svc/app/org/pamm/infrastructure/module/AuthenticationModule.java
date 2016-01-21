package org.pamm.infrastructure.module;

import com.google.inject.AbstractModule;
import org.pamm.infrastructure.security.authentication.Authenticator;
import org.pamm.infrastructure.security.authentication.UserBearerDbAuthenticator;
import org.pamm.infrastructure.security.cipher.BCryptCipher;
import org.pamm.infrastructure.security.cipher.Cipher;

public class AuthenticationModule extends AbstractModule {
    protected void configure() {

        bind(Authenticator.class).to(UserBearerDbAuthenticator.class);
        bind(Cipher.class).to(BCryptCipher.class);
    }
}
