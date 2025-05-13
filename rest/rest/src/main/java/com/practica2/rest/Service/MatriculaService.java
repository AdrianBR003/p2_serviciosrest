package com.practica2.rest.Service;

import com.practica2.rest.Model.Matricula;
import com.practica2.rest.Model.ModelResponse;
import com.practica2.rest.Repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.List;

@Service
public class MatriculaService{

    @Autowired
    MatriculaRepository matriculaRepository;

    public MatriculaService(MatriculaRepository matriculaRepository) {
        this.matriculaRepository = matriculaRepository;
    }

    public void initMatricula(){
        if(matriculaRepository.findAll().isEmpty()){
            Matricula m1 = new Matricula(false, "Prueba00");
        }
    }

    public List<Matricula> findMatricula(String matricula){
       return matriculaRepository.findListBymatricula(matricula).orElse(null);
    }

    public Matricula createMatricula(Matricula m){
        return this.matriculaRepository.save(m);
    }

    public Matricula modifyMatricula(Matricula m){
        return this.matriculaRepository.save(m);
    }

    public ResponseEntity<ModelResponse<Matricula>> calcularTarifa(Matricula m){
        System.out.println("Matricula calcular tarifa= " + m);
        if(!m.isEntrada()) { // Entrada es false, por lo que ha salido, si no, no tendría hora de salida
            List<Matricula> matriculaList = this.matriculaRepository.findRecordsByMatricula(m.getMatricula());
            if(matriculaList.isEmpty()){
                return ResponseEntity
                        .status(HttpStatus.PRECONDITION_FAILED)
                        .body(new ModelResponse<>("Primero debe de haber ingresado algun resultado de la matricula", m));
            }
            Timestamp t2 = matriculaList.get(matriculaList.size()-1).getHora();
            Timestamp t1 = matriculaList.get(matriculaList.size() - 2).getHora(); // Cogemos la primera hora (entrada)
            long totalSegundos = (t2.getTime()-t1.getTime()) / 1000;

            // Calcular horas, minutos y segundos
            long horas = totalSegundos / 3600;
            long minutos = (totalSegundos % 3600) / 60;
            long segundos = totalSegundos % 60;
            String tiempo = "Horas: " + horas + ", Minutos: " + minutos + ", Segundos: " + segundos;

            // Aplicar Coste

            double cuota = 0.0013; // 5€ la hora (3600 s)
            double coste = (totalSegundos)*cuota;
            if(coste<0) coste = coste*-1;
            BigDecimal bd = new BigDecimal(coste);
            bd = bd.setScale(3, RoundingMode.HALF_UP);
            double costeRedondeado = bd.doubleValue();
            String mensaje =  "Coste = " + costeRedondeado + " € | Tiempo= " + tiempo;
//            ModelResponse<Matricula> body = new ModelResponse<>(mensaje,m);
//            ResponseEntity<ModelResponse<Matricula>> res = new ResponseEntity<>(body,HttpStatus.OK);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ModelResponse<>(mensaje,m));
        }else{
            String mensaje =  "Error - La matricula aun está en el parking";
            return ResponseEntity
                    .status(HttpStatus.PRECONDITION_FAILED)
                    .body(new ModelResponse<>(mensaje,m));
        }
    }

}
