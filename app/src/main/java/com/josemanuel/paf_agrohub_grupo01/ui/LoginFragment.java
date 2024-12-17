package com.josemanuel.paf_agrohub_grupo01.ui;

import android.os.Bundle;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.josemanuel.paf_agrohub_grupo01.MainActivity;
import com.josemanuel.paf_agrohub_grupo01.R;
import com.josemanuel.paf_agrohub_grupo01.databinding.FragmentLoginBinding;
import com.josemanuel.paf_agrohub_grupo01.datos.ApiClient;
import com.josemanuel.paf_agrohub_grupo01.datos.ApiService;
import com.josemanuel.paf_agrohub_grupo01.dominio.LoginRequest;
import com.josemanuel.paf_agrohub_grupo01.dominio.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    public LoginFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        binding = FragmentLoginBinding.inflate(inflater,container,false);

        binding.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = binding.editTextUser.getText().toString();
                String password = binding.editTextPassword.getText().toString();

                // Validar que los campos no estén vacíos
                if (user.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getContext(), "Ingrese usuario y contraseña", Toast.LENGTH_SHORT).show();
                    return;
                }


                LoginRequest loginRequest = new LoginRequest(user, password);
                ApiService apiService = ApiClient.getClient().create(ApiService.class);


                Call<LoginResponse> call = apiService.login(loginRequest);
                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            LoginResponse loginResponse = response.body();
                            Bundle bundleAC = new Bundle();
                            if (loginResponse.isSuccess()) {
                                String rol = loginResponse.getRol();
                                if ("Agricultor".equals(rol)) {
                                    bundleAC.putString("rol",rol);
                                    ((MainActivity) requireActivity()).handleLoginRole(bundleAC);

                                    NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.action_loginFragment_to_agPerfilAgricultorFragment);
                                } else if ("Consumidor".equals(rol)) {
                                    bundleAC.putString("rol", rol);
                                    ((MainActivity) requireActivity()).handleLoginRole(bundleAC);
                                    NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.action_loginFragment_to_consumidorVistaFragment);
                                }
                            } else {
                                Toast.makeText(getContext(), loginResponse.getMensaje(), Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(getContext(), "Error en la respuesta del servidor", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(getContext(), "Error al conectar con el servidor", Toast.LENGTH_LONG).show();
                    }

                });
            }
        });
        binding.textViewCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.action_loginFragment_to_iniRegistroFragment);
            }
        });






        return binding.getRoot();
    }
}