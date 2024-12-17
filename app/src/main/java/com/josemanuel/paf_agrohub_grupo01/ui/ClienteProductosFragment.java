package com.josemanuel.paf_agrohub_grupo01.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;

import com.josemanuel.paf_agrohub_grupo01.R;
import com.josemanuel.paf_agrohub_grupo01.databinding.FragmentClienteProductosBinding;
import com.josemanuel.paf_agrohub_grupo01.datos.ApiClient;
import com.josemanuel.paf_agrohub_grupo01.datos.ApiService;
import com.josemanuel.paf_agrohub_grupo01.dominio.CategoriaResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClienteProductosFragment extends Fragment {

    private FragmentClienteProductosBinding binding;  // Definir binding

    // Se elimina la variable 'contenedorCategorias' porque ahora lo obtenemos directamente desde el binding
    // private LinearLayout contenedorCategorias;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el layout utilizando ViewBinding
        binding = FragmentClienteProductosBinding.inflate(inflater, container, false);

        // Ya no necesitas llamar a findViewById(), simplemente usas el binding
        // contenedorCategorias = this.binding.contenedorCategoriasssssss; (esto ya no es necesario)

        cargarCategorias(); // Llamar al método para cargar las categorías
        return binding.getRoot(); // Retornar la vista raíz del binding
    }

    private void cargarCategorias() {
        // Llamada a la API para obtener las categorías
        ApiClient.getClient().create(ApiService.class).obtenerCategorias().enqueue(new Callback<List<CategoriaResponse>>() {
            @Override
            public void onResponse(Call<List<CategoriaResponse>> call, Response<List<CategoriaResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Si la respuesta es exitosa, añadir las categorías
                    for (CategoriaResponse categoria : response.body()) {
                        agregarLayoutCategoria(categoria);
                    }
                } else {
                    // Mostrar un mensaje si no se pudieron cargar las categorías
                    Toast.makeText(getContext(), "No se pudieron cargar las categorías.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<CategoriaResponse>> call, Throwable t) {
                // Mostrar un mensaje si ocurre un error en la llamada a la API
                Toast.makeText(getContext(), "Error al cargar categorías: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void agregarLayoutCategoria(CategoriaResponse categoria) {
        if (getContext() == null) return;

        // Creamos el CardView para mostrar cada categoría
        CardView cardView = new CardView(getContext());
        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        cardParams.setMargins(0, 16, 0, 16);  // Margen superior e inferior
        cardView.setLayoutParams(cardParams);
        cardView.setRadius(8);  // Radio de las esquinas del CardView
        cardView.setCardElevation(4);  // Sombra

        // Creamos el layout para la categoría (contenedor de título, imagen y precio)
        LinearLayout layoutCategoria = new LinearLayout(getContext());
        layoutCategoria.setOrientation(LinearLayout.VERTICAL);
        layoutCategoria.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        layoutCategoria.setPadding(16, 16, 16, 16);  // Relleno del layout

        // Título de la categoría
        TextView tituloCategoria = new TextView(getContext());
        tituloCategoria.setText("Categoría: " + categoria.getNombreCategoria());
        tituloCategoria.setTextSize(18);
        tituloCategoria.setPadding(0, 0, 0, 16);  // Relleno inferior
        layoutCategoria.addView(tituloCategoria);  // Agregar al layout

        // Imagen de producto (aquí podrías cargar imágenes de una URL si tienes un campo de URL en la respuesta de la API)
        ImageView imagenProducto = new ImageView(getContext());
        imagenProducto.setImageResource(R.drawable.arandanos);  // Imagen por defecto (cambiar si tienes una URL de imagen)
        imagenProducto.setAdjustViewBounds(true);
        imagenProducto.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        layoutCategoria.addView(imagenProducto);  // Agregar la imagen

        // Precio del producto
        TextView textoPrecio = new TextView(getContext());
        textoPrecio.setText("Precio por kilo: $50");  // Precio por defecto (esto lo puedes cambiar para que sea dinámico)
        textoPrecio.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textoPrecio.setPadding(0, 16, 0, 0);  // Relleno superior
        layoutCategoria.addView(textoPrecio);  // Agregar el precio

        // Añadimos el layout de la categoría al CardView, y luego el CardView al contenedor
        cardView.addView(layoutCategoria);
        binding.contenedorCategoriasssssss.addView(cardView);  // Usamos el binding para acceder al contenedor
    }

    // Es importante asegurarse de destruir el binding correctamente cuando el fragmento es destruido
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;  // Liberamos la referencia al binding para evitar fugas de memoria
    }
}
