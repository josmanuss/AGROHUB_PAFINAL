package com.josemanuel.paf_agrohub_grupo01.ui;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.josemanuel.paf_agrohub_grupo01.R;
import com.josemanuel.paf_agrohub_grupo01.databinding.FragmentIniRegistroBinding;
import com.josemanuel.paf_agrohub_grupo01.dominio.AgricultorRequest;
import com.josemanuel.paf_agrohub_grupo01.dominio.AgricultorResponse;
import com.josemanuel.paf_agrohub_grupo01.dominio.ConsumidorRequest;

import com.josemanuel.paf_agrohub_grupo01.datos.ApiClient;
import com.josemanuel.paf_agrohub_grupo01.datos.ApiService;
import com.josemanuel.paf_agrohub_grupo01.dominio.ConsumidorResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IniRegistroFragment extends Fragment {

    private FragmentIniRegistroBinding binding;
    private ApiService apiService;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentIniRegistroBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupSpinner();

        binding.buttonEnviar.setOnClickListener(v -> validateAndRegister());
    }

    private void setupSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getContext(), R.array.user_types, android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerUserType.setAdapter(adapter);
    }

    private void validateAndRegister() {
        String nombre = binding.etNombre.getText().toString().trim();
        String direccion = binding.etDireccion.getText().toString().trim();
        String email = binding.etEmail.getText().toString().trim();
        String telefono = binding.etTelefono.getText().toString().trim();
        String usuario = binding.etNombreUsuario.getText().toString().trim();
        String password = binding.etContrasenia.getText().toString().trim();
        String preferencias = binding.etPreferencias.getVisibility() == View.VISIBLE ? binding.etPreferencias.getText().toString().trim() : "";
        String userType = binding.spinnerUserType.getSelectedItem().toString();

        if (isFieldEmpty(nombre, "Nombre") || isFieldEmpty(direccion, "Dirección") ||
                isFieldEmpty(email, "Correo Electrónico") || isFieldEmpty(password, "Contraseña")) {
            return;
        }

        if (userType.equals("Agricultor")) {
            AgricultorRequest agricultor = new AgricultorRequest(nombre, email, direccion, telefono, usuario, password, "Certificado por AGROHUB");
            registerAgricultor(agricultor);
        } else if (userType.equals("Consumidor")) {
            if (isFieldEmpty(preferencias, "Preferencias")) return;
            ConsumidorRequest consumidor = new ConsumidorRequest(nombre, email, direccion, telefono, usuario, password, preferencias);
            registerConsumidor(consumidor);
        } else {
            Toast.makeText(getContext(), "Seleccione un tipo de usuario válido", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isFieldEmpty(String fieldValue, String fieldName) {
        if (TextUtils.isEmpty(fieldValue)) {
            Toast.makeText(getContext(), "El campo " + fieldName + " no puede estar vacío", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    private void registerAgricultor(AgricultorRequest agricultor) {
        Call<AgricultorResponse> call = ApiClient.getClient().create(ApiService.class).registrarAgricultor(agricultor);
        call.enqueue(new Callback<AgricultorResponse>() {
            @Override
            public void onResponse(@NonNull Call<AgricultorResponse> call, @NonNull Response<AgricultorResponse> response) {
                handleApiResponse(response, "Registro de Agricultor exitoso");
            }

            @Override
            public void onFailure(@NonNull Call<AgricultorResponse> call, @NonNull Throwable t) {
                handleApiFailure(t);
            }
        });
    }

    private void registerConsumidor(ConsumidorRequest consumidor) {
            Call<ConsumidorResponse> call = ApiClient.getClient().create(ApiService.class).registrarConsumidor(consumidor);
        call.enqueue(new Callback<ConsumidorResponse>() {
            @Override
            public void onResponse(@NonNull Call<ConsumidorResponse> call, @NonNull Response<ConsumidorResponse> response) {
                handleApiResponse(response, "Registro de Consumidor exitoso");
            }

            @Override
            public void onFailure(@NonNull Call<ConsumidorResponse> call, @NonNull Throwable t) {
                handleApiFailure(t);
            }
        });
    }

    private <T> void handleApiResponse(@NonNull Response<T> response, String successMessage) {
        if (response.isSuccessful()) {
            Toast.makeText(getContext(), successMessage, Toast.LENGTH_SHORT).show();
            navigateToLogin();
        } else {
            try {
                String errorBody = response.errorBody() != null ? response.errorBody().string() : "Error desconocido";
                Log.e("Registro Error", "Código: " + response.code() + ", Mensaje: " + errorBody);
                Toast.makeText(getContext(), "Error: " + errorBody, Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Log.e("Registro Error", "Error al procesar respuesta", e);
                Toast.makeText(getContext(), "Error en el registro", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void handleApiFailure(Throwable t) {
        Log.e("Registro Error", "Fallo en la conexión", t);
        Toast.makeText(getContext(), "Error de conexión: " + t.getMessage(), Toast.LENGTH_LONG).show();
    }

    private void navigateToLogin() {
        new Handler().postDelayed(() -> NavHostFragment.findNavController(this)
                .navigate(R.id.action_iniRegistroFragment_to_loginFragment), 2000);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
