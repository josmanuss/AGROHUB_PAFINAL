package com.josemanuel.paf_agrohub_grupo01.datos;


import com.josemanuel.paf_agrohub_grupo01.dominio.AgricultorRequest;
import com.josemanuel.paf_agrohub_grupo01.dominio.AgricultorResponse;
import com.josemanuel.paf_agrohub_grupo01.dominio.ConsumidorRequest;
import com.josemanuel.paf_agrohub_grupo01.dominio.ConsumidorResponse;
import com.josemanuel.paf_agrohub_grupo01.dominio.LoginRequest;
import com.josemanuel.paf_agrohub_grupo01.dominio.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface ApiService {
    @POST("registrar_agricultor")
    Call<AgricultorResponse> registrarAgricultor(@Body AgricultorRequest agricultorRequest);

    @POST("registrar_consumidor")
    Call<ConsumidorResponse> registrarConsumidor(@Body ConsumidorRequest consumidorRequest);

    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

}
