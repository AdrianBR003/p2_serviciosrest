package com.practica2.rest.Controller;

import com.practica2.rest.Model.Matricula;
import com.practica2.rest.Service.MatriculaService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @PostMapping(value = "/registroMatricula")
    public String registroMatricula(@RequestParam("matricula") String matricula, @RequestParam("entrada") Boolean entrada){
        Matricula m = new Matricula(entrada,matricula);
        this.matriculaService.createMatricula(m);
        System.out.println("Se ha creado el registro: " + m);
        return m.toString();
    }

    @PostMapping(value = "/coste/{matricula}")
    public String registroMatricula(@PathVariable("matricula") String matricula){
        List<Matricula> matriculaList = this.matriculaService.findMatricula(matricula);
        return  this.matriculaService.calcularTarifa(this.matriculaService.findMatricula(matricula).get(matriculaList.size()-1));
    }

}
