package com.practica2.rest.Controller;

import com.practica2.rest.Model.Matricula;
import com.practica2.rest.Model.ModelResponse;
import com.practica2.rest.Service.MatriculaService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
public class GeneralController {

    @Autowired
    MatriculaService matriculaService;

    public GeneralController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }

//    @PostMapping(value = "/registroMatricula")
//    public Matricula registroMatricula(@RequestParam("matricula") String matricula, @RequestParam("entrada") Boolean entrada){
//        Matricula m = new Matricula(entrada,matricula);
//        this.matriculaService.createMatricula(m);
//        System.out.println("Se ha creado el registro: " + m);
//        return m;
//    }

    @PostMapping(value = "/registroMatricula")
    public ResponseEntity<ModelResponse<Matricula>> registroMatricula(@RequestBody Matricula m){
        this.matriculaService.createMatricula(m);
        System.out.println("Se ha creado el registro: " + m);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ModelResponse<>("Se ha creado el registro", m));
    }

    @GetMapping(value = "/coste/{matricula}")
    public ResponseEntity<ModelResponse<Matricula>> registroMatricula(@PathVariable("matricula") String matricula){
        List<Matricula> matriculaList = this.matriculaService.findMatricula(matricula);
        if (matriculaList.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.PRECONDITION_FAILED)
                    .body(new ModelResponse<>("Primero debe de haber ingresado algún resultado de la matrícula", new Matricula(false,matricula)));
        }
        return  this.matriculaService.calcularTarifa(matriculaList.get(matriculaList.size()-1));
    }

}
