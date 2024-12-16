package com.josemanuel.paf_agrohub_grupo01.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import com.josemanuel.paf_agrohub_grupo01.databinding.FragmentCliConsumidorVistaBinding;

public class CliConsumidorVistaFragment extends Fragment {

    // Binding para acceder a los elementos del layout
    private FragmentCliConsumidorVistaBinding binding;

    public CliConsumidorVistaFragment() {
        // Constructor vacío requerido
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Si hay argumentos que se pasan al fragmento, puedes obtenerlos aquí
        if (getArguments() != null) {
            // Aquí puedes acceder a los argumentos si los tienes
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Se infla el layout usando el ViewBinding
        binding = FragmentCliConsumidorVistaBinding.inflate(inflater, container, false);

        // Aquí puedes configurar elementos de la vista como el texto, botones, etc.
        // Ejemplo: binding.textView.setText("Texto de ejemplo");

        // Devuelve la vista raíz del fragmento
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Es recomendable liberar la referencia al binding para evitar posibles fugas de memoria
        binding = null;
    }
}
