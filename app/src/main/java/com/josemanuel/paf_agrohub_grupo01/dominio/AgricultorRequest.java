package com.josemanuel.paf_agrohub_grupo01.dominio;

public class AgricultorRequest {
    private String nombre;
    private String email;
    private String direccion;
    private String telefono;
    private String username;//a este le falta el _
    private String password;
    private String certificacion;


    public AgricultorRequest(String nombre, String email, String direccion, String telefono, String username, String password, String certificacion) {
        this.nombre = nombre;
        this.email = email;
        this.direccion = direccion;
        this.telefono = telefono;
        this.username = username;
        this.password = password;
        this.certificacion = certificacion;
    }

    public String getNombre_usuario() {
        return username;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.username = nombre_usuario;
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
        return password;
    }

    public void setContrasenia(String contrasenia) {
        this.password = contrasenia;
    }

    public String getCertificacion() {
        return certificacion;
    }

    public void setCertificacion(String certificacion) {
        this.certificacion = certificacion;
    }
}
