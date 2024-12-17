package com.josemanuel.paf_agrohub_grupo01.datos;


import com.josemanuel.paf_agrohub_grupo01.dominio.ActualizarConsumidorRequest;
import com.josemanuel.paf_agrohub_grupo01.dominio.ActualizarConsumidorResponse;
import com.josemanuel.paf_agrohub_grupo01.dominio.AgricultorRequest;
import com.josemanuel.paf_agrohub_grupo01.dominio.AgricultorResponse;
import com.josemanuel.paf_agrohub_grupo01.dominio.CategoriaResponse;
import com.josemanuel.paf_agrohub_grupo01.dominio.ConsumidorRequest;
import com.josemanuel.paf_agrohub_grupo01.dominio.ConsumidorResponse;
import com.josemanuel.paf_agrohub_grupo01.dominio.LoginRequest;
import com.josemanuel.paf_agrohub_grupo01.dominio.LoginResponse;
import com.josemanuel.paf_agrohub_grupo01.dominio.ObtenerAgricultorResp;
import com.josemanuel.paf_agrohub_grupo01.dominio.RegistrarProductoReq;
import com.josemanuel.paf_agrohub_grupo01.dominio.RegistrarProductoResp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;


public interface ApiService {
    @POST("registrar_agricultor")
    Call<AgricultorResponse> registrarAgricultor(@Body AgricultorRequest agricultorRequest);

    @POST("registrar_consumidor")
    Call<ConsumidorResponse> registrarConsumidor(@Body ConsumidorRequest consumidorRequest);

    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @GET("obtenerCategorias")
    Call<List<CategoriaResponse>> obtenerCategorias();

    @GET("listarAgricultores")
    Call<List<ObtenerAgricultorResp>> obtenerAgricultor();

    @POST("/registrar_producto")
    Call<RegistrarProductoResp> registrarProducto(@Body RegistrarProductoReq request);

    @PUT("actualizarConsumidor")
    Call<ActualizarConsumidorResponse> actualizarConsumidor(@Body ActualizarConsumidorRequest actualizarConsumidorRequest);
}
