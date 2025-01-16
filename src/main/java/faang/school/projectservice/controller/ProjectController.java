package faang.school.projectservice.controller;

import faang.school.projectservice.dto.project.ProjectDto;
import faang.school.projectservice.model.ProjectStatus;
import faang.school.projectservice.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping("/create")
    public ProjectDto createProject(@RequestBody ProjectDto projectDto, @RequestParam Long ownerId) {
        return projectService.createProject(projectDto, ownerId);
    }

    @PostMapping("/update")
    public ProjectDto updateProject(@RequestBody Long projectId, @RequestParam ProjectDto projectDto) {
        return projectService.updateProject(projectId, projectDto);
    }

    @GetMapping("/all")
    public List<ProjectDto> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/search")
    public List<ProjectDto> searchProjectByNameAndStatus(@RequestBody String name, @RequestBody ProjectStatus status) {
        return projectService.searchProjectByNameAndStatus(name, status);
    }

    @GetMapping("/{projectId}")
    public ProjectDto getProjectById(@PathVariable Long projectId) {
        return projectService.getProjectById(projectId);
    }
}
