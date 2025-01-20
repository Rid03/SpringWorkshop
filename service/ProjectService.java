package faang.school.projectservice.service;

import faang.school.projectservice.dto.project.ProjectDto;
import faang.school.projectservice.exception.DataValidationException;
import faang.school.projectservice.mapper.ProjectMapper;
import faang.school.projectservice.model.Project;
import faang.school.projectservice.model.ProjectStatus;
import faang.school.projectservice.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final ProjectFilterService projectFilterService;

    @Transactional
    public ProjectDto createProject(ProjectDto projectDto, Long ownerId) {
        if (projectRepository.existsByOwnerIdAndName(ownerId, projectDto.getName())) {
            throw new DataValidationException("Project already exists");
        }
        Project project = projectMapper.toEntity(projectDto);
        project.setOwnerId(ownerId);
        project.setStatus(ProjectStatus.CREATED);
        Project savedProject = projectRepository.save(project);
        return projectMapper.toDto(savedProject);
    }

    @Transactional
    public ProjectDto updateProject(Long projectId, ProjectDto projectDto) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new DataValidationException("Project " + projectId + " not found"));
        try {
            project.setStatus(ProjectStatus.valueOf(String.valueOf(projectDto.getStatus())));
        } catch (IllegalArgumentException e) {
            throw new DataValidationException("Invalid status value");
        }
        project.setDescription(projectDto.getDescription());
        project.setUpdatedAt(LocalDateTime.now());
        Project updateProject = projectRepository.save(project);
        return projectMapper.toDto(updateProject);
    }

    @Transactional
    public List<ProjectDto> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projectMapper.toDtoList(projects);
    }

    @Transactional(readOnly = true)
    public List<ProjectDto> searchProjectByNameAndStatus(String name, String status) {
        List<Project> projects = projectRepository.findByNameAndStatus(name, status);
        return projectMapper.toDtoList(projects);
    }

    @Transactional(readOnly = true)
    public ProjectDto getProjectById(Long projectId) {
        Optional<Project> project = projectRepository.findById(projectId);
        return projectMapper.toDto(project.orElse(null));
    }

    private void validateProject(String name, String status) {
        if (name == null || name.isEmpty() || status == null || status.isEmpty()) {
            throw new DataValidationException("Project name or status is empty");
        }
    }
}
//TODO: сделать приватным доступ к некоторым проектам., использовать фильтры
