package net.atos.pamm.domain.model.user;

import net.atos.pamm.domain.model.Repository;
import net.atos.pamm.domain.model.project.Project;

import java.util.List;

public interface UserRepository extends Repository<User, Integer> {
    /**
     *
     * @param email
     * @return
     */
    User findUserByEmail(String email);

    /**
     *
     * @param userId
     * @return
     */
    List<Project> findProjectsForUser(Integer userId);

    /**
     *
     * @param user
     */
    void activate(User user);

}
