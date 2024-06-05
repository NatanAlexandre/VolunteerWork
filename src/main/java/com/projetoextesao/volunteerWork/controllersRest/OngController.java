package com.projetoextesao.volunteerWork.controllersRest;

import com.projetoextesao.volunteerWork.dtos.OngDto;
import com.projetoextesao.volunteerWork.models.OngModel;
import com.projetoextesao.volunteerWork.services.OngService;
import com.projetoextesao.volunteerWork.services.UserService;
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
@CrossOrigin(origins = "*", maxAge=3600)
@RequestMapping("/ongs")
public class OngController {

    final OngService ongService;
    final UserService userService;

    public OngController(OngService ongService, UserService userService){
        this.ongService = ongService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Object> saveOng(@RequestBody @Valid OngDto ongDTO){
        var ongModel = new OngModel();
        BeanUtils.copyProperties(ongDTO, ongModel);
        ongModel.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(ongService.save(ongModel));
    }

    @GetMapping
    public ResponseEntity<List<OngModel>> getAllOngs(){
        List<OngModel> ongModelList = ongService.findAllOngs();
        if (!ongModelList.isEmpty()){
            for (OngModel ong : ongModelList){
                UUID id = ong.getOngId();
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(ongModelList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneOng(@PathVariable(value = "id") UUID id){
        Optional<OngModel> ong = ongService.findOngById(id);
        if (!ong.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ong with ID: " + id + " Not found!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(ong.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateOng(@PathVariable(value = "id") UUID id,
                                            @RequestBody @Valid OngDto ongDTO){
        Optional<OngModel> ong = ongService.findOngById(id);
        if (!ong.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ong with ID: " + id + " Not found!");
        }
        var ongModel = new OngModel();
        BeanUtils.copyProperties(ongDTO, ongModel);
        ongModel.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        ongModel.setOngId(ong.get().getOngId());
        return ResponseEntity.status(HttpStatus.OK).body(ongService.save(ongModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOng(@PathVariable(value = "id") UUID id){
        Optional<OngModel> ong = ongService.findOngById(id);
        if (!ong.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ong with ID: " + id + " Not found!");
        }
        ongService.delete(ong.get());
        return ResponseEntity.status(HttpStatus.OK).body("ONG: " + ong.get().getOngName() +  " deleted successfully!");
    }

}
