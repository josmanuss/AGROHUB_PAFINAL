package com.josemanuel.paf_agrohub_grupo01.ui;

import com.josemanuel.paf_agrohub_grupo01.R;
import com.josemanuel.paf_agrohub_grupo01.datos.ApiClient;
import com.josemanuel.paf_agrohub_grupo01.datos.ApiService;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.josemanuel.paf_agrohub_grupo01.databinding.FragmentAgNuevoProductoBinding;
import com.josemanuel.paf_agrohub_grupo01.dominio.CategoriaResponse;
import com.josemanuel.paf_agrohub_grupo01.dominio.ObtenerAgricultorResp;
import com.josemanuel.paf_agrohub_grupo01.dominio.RegistrarProductoReq;
import com.josemanuel.paf_agrohub_grupo01.dominio.RegistrarProductoResp;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Callback;

public class AgNuevoProductoFragment extends Fragment {

    private FragmentAgNuevoProductoBinding binding;
    private List<CategoriaResponse> listaCategorias = new ArrayList<>();



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cargarCategoriasEnSpinner();
    }

    private void cargarCategoriasEnSpinner() {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<List<CategoriaResponse>> categorias = apiService.obtenerCategorias();
        categorias.enqueue(new Callback<List<CategoriaResponse>>() {
            @Override
            public void onResponse(Call<List<CategoriaResponse>> call, Response<List<CategoriaResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listaCategorias = response.body();
                    //
                    List<String> nombresCategorias = new ArrayList<>();
                    for (CategoriaResponse categoria : listaCategorias) {
                        nombresCategorias.add(categoria.getIdCategoria()+" - "+categoria.getNombreCategoria());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(
                            getContext(),
                            android.R.layout.simple_spinner_item,
                            nombresCategorias
                    );
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.category.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<CategoriaResponse>> call, Throwable t) {
                // Manejo de errores (puedes mostrar un mensaje al usuario si falla)
            }
        });
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentAgNuevoProductoBinding.inflate(inflater, container, false);
        binding.AgregarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nombre = binding.productName.getText().toString().trim();
                String descripcion = binding.description.getText().toString().trim();
                float precio = Float.parseFloat(binding.price.getText().toString().trim());
                int cantidad = Integer.parseInt(binding.quantity.getText().toString().trim());

                int id_categoria = Integer.parseInt(
                        binding.category.getItemAtPosition(binding.category.getSelectedItemPosition()).toString().split(" - ")[0]
                );

                RegistrarProductoReq productoRequest = new RegistrarProductoReq(
                        "imagen_url",
                        nombre,
                        descripcion,
                        precio,
                        cantidad,
                        0,
                        id_categoria
                );

                ApiService apiService = ApiClient.getClient().create(ApiService.class);
                Call<RegistrarProductoResp> call = apiService.registrarProducto(productoRequest);
                call.enqueue(new Callback<RegistrarProductoResp>() {
                    @Override
                    public void onResponse(@NonNull Call<RegistrarProductoResp> call, @NonNull Response<RegistrarProductoResp> response) {
                        if (response.isSuccessful()) {
                            RegistrarProductoResp resp = response.body();
                            Toast.makeText(getContext(), Objects.requireNonNull(resp).getMessage(),Toast.LENGTH_LONG).show();
                            NavHostFragment.findNavController(AgNuevoProductoFragment.this).navigate(
                                    R.id.action_agNuevoProductoFragment_to_HomeAgricultorFragment
                            );
                        }
                        else {
                            Toast.makeText(getContext(), response.message(),Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<RegistrarProductoResp> call, Throwable t) {
                        // Maneja el error
                    }
                });
            }
        });




        return binding.getRoot();
    }
}