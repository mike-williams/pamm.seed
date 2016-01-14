package net.atos.pamm.dal.jpa.project;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.atos.pamm.domain.model.RepositoryObjectFactory;
import net.atos.pamm.domain.model.project.ProjectMember;
import net.atos.pamm.domain.model.project.Project;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class ProjectMapper {

    private final RepositoryObjectFactory repositoryObjectFactory;

    @Inject
    public ProjectMapper(RepositoryObjectFactory repositoryObjectFactory) {
        this.repositoryObjectFactory = repositoryObjectFactory;
    }

    public ProjectEntity projectToEntity(Project project) {
        return repositoryObjectFactory.createEntity(project, ProjectEntity.class);
    }

    public ProjectUserEntity projectUserToEntity(ProjectMember projectMember, Project project) {
        final ProjectUserEntity projectUserEntity = new ProjectUserEntity();
        final ProjectUserId projectUserId = new ProjectUserId();

        projectUserId.setProjectId(project.getId());
        projectUserId.setUserEmail(projectMember.getEmail());

        projectUserEntity.setId(projectUserId);
        projectUserEntity.setUserId(projectMember.getUserId());
        projectUserEntity.setForename(projectMember.getForename());
        projectUserEntity.setSurname(projectMember.getSurname());
        projectUserEntity.setRole(projectMember.getRole().name());

        return projectUserEntity;
    }

    public List<ProjectUserEntity> projectUsersToEntityList(Project project) {
        final List<ProjectUserEntity> projectUserEntities = new ArrayList<>();
        for (ProjectMember projectMember : project.getMembers()) {
            projectUserEntities.add(projectUserToEntity(projectMember, project));
        }
        return projectUserEntities;
    }

    public ProjectMember projectUserToBusinessObject(ProjectUserEntity userEntity) {
        final ProjectMember member = new ProjectMember();
        member.setProjectId(userEntity.getId().projectId);
        member.setUserId(userEntity.getUserId());
        member.setEmail(userEntity.getId().getUserEmail());
        member.setForename(userEntity.getForename());
        member.setSurname(userEntity.getSurname());
        member.setRole(ProjectMember.Role.valueOf(userEntity.getRole()));

        return member;
    }

    public List<Project> projectsToBusinessObjectList(List<ProjectEntity> projectEntities) {
        final List<Project> projects = new ArrayList<>();

        for (ProjectEntity projectEntity : projectEntities) {
            final Project project = repositoryObjectFactory.createBusinessObject(projectEntity, Project.class);
            final List<ProjectMember> members = new ArrayList<>();
            for (ProjectUserEntity userEntity : projectEntity.getMembers()) {
                members.add(projectUserToBusinessObject(userEntity));
            }
            project.setMembers(members);
            projects.add(project);
        }
        return projects;
    }

    public Project projectToBusinessObject(ProjectEntity projectEntity) {
        final Project project = repositoryObjectFactory.createBusinessObject(projectEntity, Project.class);
        final List<ProjectMember> members = new ArrayList<>();
        for (ProjectUserEntity userEntity : projectEntity.getMembers()) {
            members.add(projectUserToBusinessObject(userEntity));
        }
        project.setMembers(members);
        return project;
    }
}
