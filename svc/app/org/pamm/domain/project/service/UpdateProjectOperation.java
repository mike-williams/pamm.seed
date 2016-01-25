package org.pamm.domain.project.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.pamm.dal.project.ProjectRepository;
import org.pamm.domain.ServiceResult;
import org.pamm.domain.project.model.Project;
import org.pamm.infrastructure.mail.EmailService;
import play.Logger;
import play.libs.Json;

import javax.inject.Inject;

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

    public ServiceResult execute(final JsonNode jsonRequest) {
        final Project project = Json.fromJson(jsonRequest, Project.class);
        final Project updatedProject = projectRepository.update(project);

        // TODO email new project members


        return new ServiceResult(Json.toJson(updatedProject));
    }
}
