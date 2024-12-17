package com.josemanuel.paf_agrohub_grupo01.dominio;

public class AgricultorResponse {
    private String message;
    private String status;
    private AgricultorData data;

    // Getters y setters

    public static class AgricultorData {
        private String nombre;
        private String email;
        private String direccion;
        private String telefono;
        private String username;
        private String certificacion;

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

        public String getCertificacion() {
            return certificacion;
        }

        public void setCertificacion(String certificacion) {
            this.certificacion = certificacion;
        }
    }

    public AgricultorData getData() {
        return data;
    }

    public void setData(AgricultorData data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
