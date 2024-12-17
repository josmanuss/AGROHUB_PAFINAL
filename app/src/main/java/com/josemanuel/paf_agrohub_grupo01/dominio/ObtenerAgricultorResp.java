package com.josemanuel.paf_agrohub_grupo01.dominio;

public class ObtenerAgricultorResp
{
    private int id_agricultor;
    private int id_persona;
    private String nombre_persona;
    private String email;
    private String direccion;
    private String telefono;
    private String nombre_usuario;
    private String contrasenia;

    public int getId_agricultor() {
        return id_agricultor;
    }

    public void setId_agricultor(int id_agricultor) {
        this.id_agricultor = id_agricultor;
    }

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public String getNombre_persona() {
        return nombre_persona;
    }

    public void setNombre_persona(String nombre_persona) {
        this.nombre_persona = nombre_persona;
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
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
