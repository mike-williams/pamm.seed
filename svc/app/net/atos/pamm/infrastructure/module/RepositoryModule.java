package net.atos.pamm.infrastructure.module;

import com.google.inject.AbstractModule;
import net.atos.pamm.dal.jpa.project.ProjectJpaRepository;
import net.atos.pamm.dal.jpa.user.UserJpaRepository;
import net.atos.pamm.domain.project.ProjectRepository;
import net.atos.pamm.domain.user.UserRepository;

public class RepositoryModule extends AbstractModule {
    protected void configure() {

        bind(ProjectRepository.class).to(ProjectJpaRepository.class);
        bind(UserRepository.class).to(UserJpaRepository.class);
    }
}
