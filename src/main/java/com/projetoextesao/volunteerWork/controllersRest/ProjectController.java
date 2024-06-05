package com.projetoextesao.volunteerWork.controllersRest;

import com.projetoextesao.volunteerWork.dtos.ProjectDto;
import com.projetoextesao.volunteerWork.models.ProjectModel;
import com.projetoextesao.volunteerWork.services.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/projects")
public class ProjectController {

    final ProjectService projectService;

    public ProjectController(ProjectService projectService){
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<Object> saveProject(@RequestBody @Valid ProjectDto projectDto){
        var projectModel = new ProjectModel();
        BeanUtils.copyProperties(projectDto, projectModel);
        projectModel.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(this.projectService.saveProject(projectModel));
    }

    @GetMapping
    public ResponseEntity<List<ProjectModel>> getAllProjects(){
        List<ProjectModel> projects = this.projectService.findAllProjects();
        if (!projects.isEmpty()){
            for (ProjectModel project : projects){
                UUID id = project.getProjectId();
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(projects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneProject(@PathVariable(value = "id") UUID id){
        Optional<ProjectModel> project = this.projectService.findProjectById(id);
        if (!project.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project with ID: " + id + " not found!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(project.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProject(@PathVariable(value = "id") UUID id,
                                                @RequestBody @Valid ProjectDto projectDto){
        Optional<ProjectModel> project = this.projectService.findProjectById(id);
        if (!project.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project with ID: " + id + " not found!");
        }
        var projectModel = new ProjectModel();
        projectModel.setProjectId(project.get().getProjectId());
        projectModel.setCreatedAt(project.get().getCreatedAt());
        BeanUtils.copyProperties(projectDto, projectModel);
        return ResponseEntity.status(HttpStatus.OK).body(this.projectService.saveProject(projectModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProject(@PathVariable(value = "id") UUID id){
        Optional<ProjectModel> project = this.projectService.findProjectById(id);
        if (!project.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project with ID: " + id + " not found!");
        }
        this.projectService.deleteProject(project.get());
        return ResponseEntity.status(HttpStatus.OK).body("Project: " + project.get().getProjectName() + " deleted successfully!");
    }

}
