package com.josemanuel.paf_agrohub_grupo01.dominio;

public class AgricultorRequest {
    private String nombre;
    private String email;
    private String direccion;
    private String telefono;
    private String nombre_usuario;//a este le falta el _
    private String contrasenia;
    private String certificacion;


    public AgricultorRequest(String nombre, String email, String direccion, String telefono, String nombre_usuario, String contrasenia, String certificacion) {
        this.nombre = nombre;
        this.email = email;
        this.direccion = direccion;
        this.telefono = telefono;
        this.nombre_usuario = nombre_usuario;
        this.contrasenia = contrasenia;
        this.certificacion = certificacion;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getCertificacion() {
        return certificacion;
    }

    public void setCertificacion(String certificacion) {
        this.certificacion = certificacion;
    }
}
