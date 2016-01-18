package net.atos.pamm.domain.project.service;

import com.fasterxml.jackson.databind.JsonNode;
import net.atos.pamm.domain.ServiceResult;
import net.atos.pamm.domain.SessionStatus;
import net.atos.pamm.domain.project.ProjectRepository;
import net.atos.pamm.domain.project.model.Project;
import net.atos.pamm.domain.project.model.ProjectMember;
import net.atos.pamm.infrastructure.mail.EmailService;
import play.Logger;
import play.libs.Json;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class UpdateProjectOperation {
    private static final Logger.ALogger LOG = Logger.of(UpdateProjectOperation.class);

    private final ProjectRepository projectRepository;
    private final EmailService emailService;


    @Inject
    public UpdateProjectOperation(ProjectRepository projectRepository,
                                  EmailService emailService) {
        this.projectRepository = projectRepository;
        this.emailService = emailService;
    }

    public ServiceResult execute(JsonNode jsonRequest) {
        final Project project = new Project();

        project.setId(jsonRequest.findPath("id").asInt());
        project.setTitle(jsonRequest.findPath("title").textValue());
        project.setProjectCode(jsonRequest.findPath("projectCode").textValue());
        project.setClient(jsonRequest.findPath("client").textValue());
        project.setSummary(jsonRequest.findPath("summary").textValue());
        project.setStatus(jsonRequest.findPath("status").textValue());

        final List<ProjectMember> members = new ArrayList<>();
        for (JsonNode jsonMember : jsonRequest.findValue("members")) {
            final ProjectMember member = new ProjectMember();

            member.setEmail(jsonMember.findPath("email").textValue());
            member.setForename(jsonMember.findPath("forename").textValue());
            member.setSurname(jsonMember.findPath("surname").textValue());

            if (jsonMember.findPath("role").textValue() == null) {
                member.setRole(ProjectMember.Role.MEMBER);
            } else {
                member.setRole(ProjectMember.Role.valueOf(jsonMember.findPath("role").textValue()));
            }

            if (jsonMember.findPath("sessionStatus").textValue() != null) {
                member.setSessionStatus(SessionStatus.valueOf(jsonMember.findPath("sessionStatus").textValue()));
            }

            members.add(member);
        }
        project.setMembers(members);
        final Project updatedProject = projectRepository.update(project);

        // TODO email new project members


        return new ServiceResult(Json.toJson(updatedProject));
    }
}
