package com.practica2.rest.Model;

public class ModelResponse<T> {
    private String mensaje;
    private T datos;

    public ModelResponse() {
    }

    public ModelResponse(String mensaje, T datos){
        this.mensaje = mensaje;
        this.datos = datos;
    }

    @Override
    public String toString() {
        return "ModelResponse{" +
                "mensaje='" + mensaje + '\'' +
                ", datos=" + datos +
                '}';
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public T getDatos() {
        return datos;
    }

    public void setDatos(T datos) {
        this.datos = datos;
    }
}
