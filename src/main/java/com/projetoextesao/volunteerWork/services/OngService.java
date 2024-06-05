package com.projetoextesao.volunteerWork.services;

import com.projetoextesao.volunteerWork.models.OngModel;
import com.projetoextesao.volunteerWork.repositories.OngRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OngService {
    final OngRepository ongRepository;

    public OngService(OngRepository ongRepository){
        this.ongRepository = ongRepository;
    }

    @Transactional
    public OngModel save(OngModel ong){
        return this.ongRepository.save(ong);
    }

    public Optional<OngModel> findOngById(UUID id){
        return this.ongRepository.findById(id);
    }

    public List<OngModel> findAllOngs(){
        return this.ongRepository.findAll();
    }

    @Transactional
    public void delete(OngModel ong){
        this.ongRepository.delete(ong);
    }

}
