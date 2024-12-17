package com.josemanuel.paf_agrohub_grupo01.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.josemanuel.paf_agrohub_grupo01.R;
import com.josemanuel.paf_agrohub_grupo01.databinding.FragmentCliCarritoComprasBinding;

import java.util.ArrayList;
import java.util.List;

public class CliCarritoComprasFragment extends Fragment {

    private FragmentCliCarritoComprasBinding binding;
    private List<Producto> productos = new ArrayList<>();

    public CliCarritoComprasFragment() {
        // Constructor vacío
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Crear una lista de productos por defecto
        productos = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCliCarritoComprasBinding.inflate(inflater, container, false);

        // Llenar la vista con la lista de productos
        mostrarProductos();

        // Manejo de eventos para modificar la cantidad
        binding.btnPedidoRevision.setOnClickListener(view -> {
            // Acción para el botón Pedido en revisión
            Toast.makeText(getContext(), "Pedido en revisión", Toast.LENGTH_SHORT).show();
        });

        return binding.getRoot();
    }

    private void mostrarProductos() {
        if (productos.isEmpty()) {
            Toast.makeText(getContext(), "El carrito está vacío", Toast.LENGTH_SHORT).show();
            return;
        }

        binding.productosCarrito.removeAllViews(); // Limpiar el contenedor antes de agregar productos nuevos

        for (int i = 0; i < productos.size(); i++) {
            Producto producto = productos.get(i);

            // Inflar una nueva vista de producto desde el archivo de layout
            View productoView = getLayoutInflater().inflate(R.layout.item_carrito, null);

            // Obtener referencias a los elementos de la vista
            TextView productName = productoView.findViewById(R.id.productName);
            TextView productQuantity = productoView.findViewById(R.id.productQuantity);
            TextView productSubTotal = productoView.findViewById(R.id.productSubTotal);
            MaterialButton btnSumar = productoView.findViewById(R.id.btnSumar);
            MaterialButton btnRestar = productoView.findViewById(R.id.btnRestar);

            // Llenar los datos de producto en la vista
            productName.setText(producto.getNombre());
            productQuantity.setText(String.valueOf(producto.getCantidad()));
            productSubTotal.setText(String.format("$ %.2f", producto.getSubtotal()));

            // Asignar listeners para los botones de sumar y restar
            btnSumar.setOnClickListener(v -> {
                producto.setCantidad(producto.getCantidad() + 1);
                producto.setSubtotal(producto.getCantidad() * producto.getPrecio());
                actualizarProducto(productoView, producto);
            });

            btnRestar.setOnClickListener(v -> {
                if (producto.getCantidad() > 1) {
                    producto.setCantidad(producto.getCantidad() - 1);
                    producto.setSubtotal(producto.getCantidad() * producto.getPrecio());
                    actualizarProducto(productoView, producto);
                }
            });

            // Agregar la vista del producto al contenedor
            binding.productosCarrito.addView(productoView);
        }
    }

    private void actualizarProducto(View productoView, Producto producto) {
        // Actualizar la cantidad y subtotal de un producto
        ((TextView) productoView.findViewById(R.id.productQuantity)).setText(String.valueOf(producto.getCantidad()));
        ((TextView) productoView.findViewById(R.id.productSubTotal)).setText(String.format("$ %.2f", producto.getSubtotal()));
        actualizarTotal();
    }

    private void actualizarTotal() {
        float total = 0;
        for (Producto producto : productos) {
            total += producto.getSubtotal();
        }
        binding.totalAmount.setText("Total: S/ " + total);
    }

    // Clase Producto para representar los productos en el carrito
    public static class Producto {
        private int id;
        private String nombre;
        private int cantidad;
        private float precio;
        private float subtotal;

        public Producto(int id, String nombre, int cantidad, float precio, float subtotal) {
            this.id = id;
            this.nombre = nombre;
            this.cantidad = cantidad;
            this.precio = precio;
            this.subtotal = subtotal;
        }

        public int getId() {
            return id;
        }

        public String getNombre() {
            return nombre;
        }

        public int getCantidad() {
            return cantidad;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }

        public float getPrecio() {
            return precio;
        }

        public float getSubtotal() {
            return subtotal;
        }

        public void setSubtotal(float subtotal) {
            this.subtotal = subtotal;
        }
    }
}
