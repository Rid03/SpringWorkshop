package faang.school.projectservice.controller;

import faang.school.projectservice.dto.project.ProjectDto;
import faang.school.projectservice.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping("/create")
    public ProjectDto createProject(@RequestParam ProjectDto projectDto, @RequestParam Long ownerId) {
        return projectService.createProject(projectDto, ownerId);
    }

    @PostMapping("/update")
    public ProjectDto updateProject(@RequestParam Long projectId, @RequestParam ProjectDto projectDto) {
        return projectService.updateProject(projectId, projectDto);
    }

    @GetMapping("/all")
    public List<ProjectDto> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/search")
    public List<ProjectDto> searchProjectByNameAndStatus(@RequestParam String name, @RequestParam String status) {
        return projectService.searchProjectByNameAndStatus(name, status);
    }

    @GetMapping("/id")
    public ProjectDto getProjectById(@RequestParam Long projectId) {
        return projectService.getProjectById(projectId);
    }
}
