package com.josemanuel.paf_agrohub_grupo01.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
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
    private CliCarritoComprasFragment carrito;
    private int id_consumidor;

    public CliProductosFragment() {
        carrito = new CliCarritoComprasFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.id_consumidor = CliConsumidorVistaFragmentArgs.fromBundle(getArguments()).getIdConsumidor();
            Toast.makeText(getContext(), "ID_CONSUMIDOR: " + String.valueOf(id_consumidor), Toast.LENGTH_LONG).show();
        }

        obtenerProductos();
    }

    // Obtener productos de la API
    public void obtenerProductos() {

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<List<ObtenerProductoResp>> call = apiService.obtenerProductos();

        call.enqueue(new Callback<List<ObtenerProductoResp>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<List<ObtenerProductoResp>> call, Response<List<ObtenerProductoResp>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ObtenerProductoResp> productos = response.body();

                    binding.linearLayoutProductos.removeAllViews(); // Limpiar contenido previo

                    // Recorrer la lista de productos y agregar cada uno a la vista
                    for (ObtenerProductoResp obtenerProductoResp : productos) {
                        // Inflar el layout para cada producto usando ItemProductoBinding
                        View productoView = LayoutInflater.from(getContext()).inflate(R.layout.item_producto, binding.linearLayoutProductos, false);
                        ItemProductoBinding itemBinding = ItemProductoBinding.bind(productoView); // Usamos el ViewBinding para el item

                        // Establecer los valores dinámicos usando el itemBinding
                        itemBinding.tvNombreProducto.setText(obtenerProductoResp.getId_producto() + "-" + obtenerProductoResp.getNombre_producto());
                        itemBinding.tvDescripcionProducto.setText(obtenerProductoResp.getDescripcion());
                        itemBinding.tvPrecioProducto.setText("Precio Unitario: S/ " + obtenerProductoResp.getPrecio());

                        if (obtenerProductoResp.getCantidad_disponible() > 0) {
                            itemBinding.chipStock.setText("En Stock");
                            itemBinding.chipStock.setChipBackgroundColorResource(R.color.green);
                        } else {
                            itemBinding.chipStock.setText("Agotado");
                            itemBinding.chipStock.setChipBackgroundColorResource(R.color.red);
                        }

                        MaterialButton btnAgregarCarrito = itemBinding.btnAgregarCarrito;
                        btnAgregarCarrito.setOnClickListener(v -> {
                            Toast.makeText(getContext(), "Producto agregado al carrito: " + obtenerProductoResp.getNombre_producto(), Toast.LENGTH_SHORT).show();

                            // Obtener la cantidad ingresada en el EditText
                            int cantidad = Integer.parseInt(binding.etCantidadProducto.getText().toString().trim());
                            if (cantidad <= 0) {
                                Toast.makeText(getContext(), "Cantidad inválida", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            // Obtener el producto y agregarlo al carrito
                            int idProducto = obtenerProductoResp.getId_producto();
                            String nombre = obtenerProductoResp.getNombre_producto();
                            float precioProducto = (float) obtenerProductoResp.getPrecio();
                            float subtotal = cantidad * precioProducto;

                            carrito.getProductos().add(new CliCarritoComprasFragment.Producto(
                                    idProducto,
                                    nombre,
                                    cantidad,
                                    precioProducto,
                                    subtotal
                            ));
                        });

                        // Agregar la vista inflada al LinearLayout
                        binding.linearLayoutProductos.addView(productoView);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ObtenerProductoResp>> call, Throwable t) {
                // Manejar error
                Toast.makeText(getContext(), "Error al cargar productos", Toast.LENGTH_SHORT).show();
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
