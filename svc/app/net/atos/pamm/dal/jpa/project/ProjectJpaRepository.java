package net.atos.pamm.dal.jpa.project;

import play.Logger;
import net.atos.pamm.dal.jpa.EntityManagerProvider;
import net.atos.pamm.domain.model.project.Project;
import net.atos.pamm.domain.model.project.ProjectMember;
import net.atos.pamm.domain.model.project.ProjectRepository;

import javax.inject.Inject;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProjectJpaRepository implements ProjectRepository {
    private static final Logger.ALogger LOG = Logger.of(ProjectJpaRepository.class);

    private final EntityManagerProvider emProvider;
    private final ProjectMapper projectMapper;

    @Inject
    public ProjectJpaRepository(EntityManagerProvider emProvider,
                                ProjectMapper projectMapper) {
        this.emProvider = emProvider;
        this.projectMapper = projectMapper;
    }

    @Override
    public Project set(Project project) {
        final ProjectEntity newProjectEntity = projectMapper.projectToEntity(project);

        emProvider.getEntityManager().persist(newProjectEntity);
        project.setId((Integer) emProvider.getEntityManager().getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(newProjectEntity));
        newProjectEntity.setMembers(projectMapper.projectUsersToEntityList(project));
        emProvider.getEntityManager().persist(newProjectEntity);

        return project;
    }

    @Override
    public Project get(Integer projectId) {
        final Query query = emProvider.getEntityManager().createNamedQuery(ProjectEntity.FIND_BY_ID);
        query.setParameter(ProjectEntity.PROJECT_ID_PARAM, projectId);
        return projectMapper.projectToBusinessObject((ProjectEntity) query.getSingleResult());
    }

    @Override
    public List<Project> getAll() {
        final List<ProjectEntity> projectEntities = emProvider.getEntityManager().createNamedQuery(ProjectEntity.FIND_ALL).getResultList();
        return projectMapper.projectsToBusinessObjectList(projectEntities);
    }

    @Override
    public void update(Project project) {
        final Query query = emProvider.getEntityManager().createNamedQuery(ProjectEntity.FIND_BY_ID);
        query.setParameter(ProjectEntity.PROJECT_ID_PARAM, project.getId());
        final ProjectEntity projectEntityToUpdate = (ProjectEntity) query.getSingleResult();

        projectEntityToUpdate.setTitle(project.getTitle());
        projectEntityToUpdate.setProjectCode(project.getProjectCode());
        projectEntityToUpdate.setClient(project.getClient());
        projectEntityToUpdate.setSummary(project.getSummary());
        projectEntityToUpdate.setStatus(project.getStatus());

        final List<ProjectUserEntity> updatedMembersList = new ArrayList<>();
        for (ProjectMember projectMember : project.getMembers()) {
            if (projectMember.getStatus() == ProjectMember.SessionStatus.NEW) {
                updatedMembersList.add(projectMapper.projectUserToEntity(projectMember, project));
            } else if (projectMember.getStatus() == ProjectMember.SessionStatus.REMOVED) {
                for (Iterator<ProjectUserEntity> it = projectEntityToUpdate.getMembers().iterator(); it.hasNext(); ) {
                    final ProjectUserEntity projectUserEntityToCheck = it.next();
                    if (projectUserEntityToCheck.getId().getUserEmail().equals(projectMember.getEmail())) {
                        it.remove();
                        emProvider.getEntityManager().remove(projectUserEntityToCheck);
                        break;
                    }
                }
            }
        }
        projectEntityToUpdate.setMembers(updatedMembersList);
        emProvider.getEntityManager().merge(projectEntityToUpdate);
    }

    @Override
    public void remove(Project project) {
        final ProjectEntity projectToRemove = emProvider.getEntityManager().getReference(ProjectEntity.class, project.getId());
        emProvider.getEntityManager().remove(projectToRemove);
    }
}
