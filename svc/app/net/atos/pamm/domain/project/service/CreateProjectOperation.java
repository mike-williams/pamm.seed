package net.atos.pamm.domain.project.service;

import com.fasterxml.jackson.databind.JsonNode;
import io.jsonwebtoken.Claims;
import net.atos.pamm.domain.ServiceResult;
import net.atos.pamm.domain.project.ProjectRepository;
import net.atos.pamm.domain.project.model.Project;
import net.atos.pamm.domain.project.model.ProjectMember;
import net.atos.pamm.domain.user.UserRepository;
import net.atos.pamm.domain.user.model.User;
import net.atos.pamm.infrastructure.mail.EmailService;
import net.atos.pamm.infrastructure.security.authentication.Principal;
import play.Logger;
import play.libs.Json;
import play.mvc.Http;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class CreateProjectOperation {
    private static final Logger.ALogger LOG = Logger.of(CreateProjectOperation.class);

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    @Inject
    public CreateProjectOperation(ProjectRepository projectRepository,
                                  UserRepository userRepository,
                                  EmailService emailService) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    public ServiceResult execute(JsonNode jsonRequest) {
        final Principal principal = (Principal) Http.Context.current().args.get(Principal.class.getName());
        final Project project = new Project();

        project.setTitle(jsonRequest.findPath("title").textValue());
        project.setProjectCode(jsonRequest.findPath("projectCode").textValue());
        project.setClient(jsonRequest.findPath("client").textValue());
        project.setSummary(jsonRequest.findPath("summary").textValue());
        project.setStatus(jsonRequest.findPath("status").textValue());

        final List<ProjectMember> members = new ArrayList<>();
        for (JsonNode jsonMember : jsonRequest.findValue("members")) {
            final ProjectMember member = new ProjectMember();
            member.setEmail(jsonMember.findPath("email").textValue());
            member.setRole(ProjectMember.Role.MEMBER);

            final User user = userRepository.findUserByEmail(jsonMember.findPath("email").textValue());
            if (user != null) {
                member.setForename(user.getForename());
                member.setSurname(user.getSurname());
            }
            members.add(member);
        }
        project.setMembers(members);

        final Claims claims = principal.getClaims();
        final ProjectMember ownerMember = new ProjectMember();

        ownerMember.setUserId(new Integer((String) claims.get("id")));
        ownerMember.setForename((String) claims.get("forename"));
        ownerMember.setSurname((String) claims.get("surname"));
        ownerMember.setEmail((String) claims.get("email"));
        ownerMember.setRole(ProjectMember.Role.OWNER);

        project.setOwner(ownerMember);

        final Project savedProject = projectRepository.set(project);

        // TODO email project members

        return new ServiceResult(Json.toJson(savedProject));
    }
}
