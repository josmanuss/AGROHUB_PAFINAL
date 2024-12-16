package com.josemanuel.paf_agrohub_grupo01.dominio;

public class LoginResponse {
    private boolean success;
    private String mensaje;
    private int id_usuario;
    private String rol;
    private Persona persona;

    public static class Persona {
        private int id_persona;
        private String nombre;
        private String email;
        private String direccion;
        private String telefono;
        private String fecha_registro;

        public int getId_persona() {
            return id_persona;
        }

        public void setId_persona(int id_persona) {
            this.id_persona = id_persona;
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

        public String getFecha_registro() {
            return fecha_registro;
        }

        public void setFecha_registro(String fecha_registro) {
            this.fecha_registro = fecha_registro;
        }
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
