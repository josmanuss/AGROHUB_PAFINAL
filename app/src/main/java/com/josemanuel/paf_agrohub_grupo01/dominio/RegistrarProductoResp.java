package com.josemanuel.paf_agrohub_grupo01.dominio;

public class RegistrarProductoResp {

    private String message;
    private ProductoData data;
    private String status;

    // Clase interna ProductoData
    public static class ProductoData {
        private String nombre_producto;
        private String descripcion;
        private float precio;
        private int cantidad_disponible;
        private int id_agricultor;
        private int id_categoria;

        // Constructor
        public ProductoData(String nombre_producto, String descripcion, float precio,
                            int cantidad_disponible, int id_agricultor, int id_categoria) {
            this.nombre_producto = nombre_producto;
            this.descripcion = descripcion;
            this.precio = precio;
            this.cantidad_disponible = cantidad_disponible;
            this.id_agricultor = id_agricultor;
            this.id_categoria = id_categoria;
        }

        // Getters y Setters
        public String getNombre_producto() {
            return nombre_producto;
        }

        public void setNombre_producto(String nombre_producto) {
            this.nombre_producto = nombre_producto;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public float getPrecio() {
            return precio;
        }

        public void setPrecio(float precio) {
            this.precio = precio;
        }

        public int getCantidad_disponible() {
            return cantidad_disponible;
        }

        public void setCantidad_disponible(int cantidad_disponible) {
            this.cantidad_disponible = cantidad_disponible;
        }

        public int getId_agricultor() {
            return id_agricultor;
        }

        public void setId_agricultor(int id_agricultor) {
            this.id_agricultor = id_agricultor;
        }

        public int getId_categoria() {
            return id_categoria;
        }

        public void setId_categoria(int id_categoria) {
            this.id_categoria = id_categoria;
        }
    }

    // Getters y Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ProductoData getData() {
        return data;
    }

    public void setData(ProductoData data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
