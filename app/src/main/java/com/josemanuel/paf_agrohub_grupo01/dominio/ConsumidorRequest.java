package com.josemanuel.paf_agrohub_grupo01.dominio;

public class ConsumidorRequest {
    private String nombre;
    private String email;
    private String direccion;
    private String telefono;
    private String username;
    private String password;
    private String preferencias;

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

    public String getNombre_usuario() {
        return username;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.username = nombre_usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(String preferencias) {
        this.preferencias = preferencias;
    }

    public ConsumidorRequest(String nombre, String email, String direccion, String telefono, String username, String password, String preferencias) {
        this.nombre = nombre;
        this.email = email;
        this.direccion = direccion;
        this.telefono = telefono;
        this.username = username;
        this.password = password;
        this.preferencias = preferencias;
    }
}
