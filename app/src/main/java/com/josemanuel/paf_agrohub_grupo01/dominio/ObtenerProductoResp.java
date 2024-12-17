package com.josemanuel.paf_agrohub_grupo01.dominio;

public class ObtenerProductoResp {
    private int id_producto;
    private String imagen;
    private String nombre_producto;
    private String descripcion;
    private double precio;
    private int cantidad_disponible;
    private int id_agricultor;
    private String fecha_publicacion;
    private int idCategoria;

    public ObtenerProductoResp() {
    }

    public ObtenerProductoResp(int id_producto, String imagen, String nombre_producto, String descripcion, double precio, int cantidad_disponible, int id_agricultor, String fecha_publicacion, int idCategoria) {
        this.id_producto = id_producto;
        this.imagen = imagen;
        this.nombre_producto = nombre_producto;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad_disponible = cantidad_disponible;
        this.id_agricultor = id_agricultor;
        this.fecha_publicacion = fecha_publicacion;
        this.idCategoria = idCategoria;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
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

    public String getFecha_publicacion() {
        return fecha_publicacion;
    }

    public void setFecha_publicacion(String fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
}
