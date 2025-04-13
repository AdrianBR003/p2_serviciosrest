package com.practica2.rest.Repository;

import com.practica2.rest.Model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {
    Optional<List<Matricula>> findListBymatricula(String matricula);
    ArrayList<Matricula> findRecordsByMatricula(String matricula);


}
