package com.josemanuel.paf_agrohub_grupo01.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.josemanuel.paf_agrohub_grupo01.R;
import com.josemanuel.paf_agrohub_grupo01.databinding.FragmentCliProductosBinding;
import com.josemanuel.paf_agrohub_grupo01.databinding.ItemProductoBinding;
import com.josemanuel.paf_agrohub_grupo01.datos.ApiClient;
import com.josemanuel.paf_agrohub_grupo01.datos.ApiService;
import com.josemanuel.paf_agrohub_grupo01.dominio.ObtenerProductoResp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class CliProductosFragment extends Fragment {

    private FragmentCliProductosBinding binding;

    public CliProductosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        obtenerProductos();
    }

    // Obtener productos de la API
    public void obtenerProductos() {

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<List<ObtenerProductoResp>> call = apiService.obtenerProductos();

        call.enqueue(new Callback<List<ObtenerProductoResp>>() {
            @Override
            public void onResponse(Call<List<ObtenerProductoResp>> call, Response<List<ObtenerProductoResp>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ObtenerProductoResp> productos = response.body();

                    binding.linearLayoutProductos.removeAllViews(); // Limpiar contenido previo

                    // Recorrer la lista de productos y agregar cada uno a la vista
                    for (ObtenerProductoResp producto : productos) {
                        // Inflar el layout para cada producto usando ItemProductoBinding
                        View productoView = LayoutInflater.from(getContext()).inflate(R.layout.item_producto, binding.linearLayoutProductos, false);
                        ItemProductoBinding itemBinding = ItemProductoBinding.bind(productoView); // Usamos el ViewBinding para el item

                        // Establecer los valores dinámicos usando el itemBinding
                        itemBinding.tvNombreProducto.setText(producto.getNombre_producto());
                        itemBinding.tvDescripcionProducto.setText(producto.getDescripcion());
                        itemBinding.tvPrecioProducto.setText("Precio Unitario: S/ " + producto.getPrecio());

                        if (producto.getCantidad_disponible() > 0) {
                            itemBinding.chipStock.setText("En Stock");
                            itemBinding.chipStock.setChipBackgroundColorResource(R.color.green);
                        } else {
                            itemBinding.chipStock.setText("Agotado");
                            itemBinding.chipStock.setChipBackgroundColorResource(R.color.red);
                        }

                        // Agregar la vista inflada al LinearLayout
                        binding.linearLayoutProductos.addView(productoView);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ObtenerProductoResp>> call, Throwable t) {
                // Manejar error
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCliProductosBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
