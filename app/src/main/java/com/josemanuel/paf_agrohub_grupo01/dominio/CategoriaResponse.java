package com.josemanuel.paf_agrohub_grupo01.dominio;


public class CategoriaResponse {
    private int idCategoria;
    private String nombreCategoria;

    public CategoriaResponse() {
    }

    public CategoriaResponse(int idCategoria, String nombreCategoria) {
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }
}
