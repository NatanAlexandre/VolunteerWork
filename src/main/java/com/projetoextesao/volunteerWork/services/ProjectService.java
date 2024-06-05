package com.projetoextesao.volunteerWork.services;

import com.projetoextesao.volunteerWork.models.ProjectModel;
import com.projetoextesao.volunteerWork.repositories.ProjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectService {

    final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }

    @Transactional
    public ProjectModel saveProject(ProjectModel project){
        return this.projectRepository.save(project);
    }

    public List<ProjectModel> findAllProjects(){
        return this.projectRepository.findAll();
    }

    public Optional<ProjectModel> findProjectById(UUID id){
        return this.projectRepository.findById(id);
    }

    @Transactional
    public void deleteProject(ProjectModel project){
        this.projectRepository.delete(project);
    }

}
