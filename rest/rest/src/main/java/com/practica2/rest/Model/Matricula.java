package com.practica2.rest.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.springframework.lang.NonNull;

import java.sql.Timestamp;

@Entity
@Table(name = "matricula")
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private boolean entrada;
    @NonNull
    private String matricula;
    @NonNull
    @CreationTimestamp(source = SourceType.DB)
    private Timestamp hora;

    // El constructor vacio es necesario para que SpringBoot serialice correctamente
    public Matricula() {
    }

    public Matricula(boolean entrada, @NonNull String matricula) {
        this.entrada = entrada;
        this.matricula = matricula;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isEntrada() {
        return entrada;
    }

    public void setEntrada(boolean entrada) {
        this.entrada = entrada;
    }

    @NonNull
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(@NonNull String matricula) {
        this.matricula = matricula;
    }

    @NonNull
    public Timestamp getHora() {
        return hora;
    }

    public void setHora(@NonNull Timestamp hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "Matricula{" +
                "id=" + id +
                ", entrada=" + entrada +
                ", matricula='" + matricula + '\'' +
                ", hora=" + hora +
                '}';
    }
}
