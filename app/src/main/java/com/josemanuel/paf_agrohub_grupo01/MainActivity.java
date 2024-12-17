package com.josemanuel.paf_agrohub_grupo01;

import android.os.Bundle;
//import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.josemanuel.paf_agrohub_grupo01.databinding.ActivityMainBinding;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    private Bundle bundleLogin; // Para almacenar el rol del usuario
    private NavigationView navigationView;

    private final Set<Integer> hiddenElementsFragmentIds = new HashSet<Integer>() {{
        add(R.id.loginFragment);
        add(R.id.preCargaFragment);
        add(R.id.iniRegistroFragment);
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        navigationView = binding.navView; // Referencia al NavigationView

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.cliConsumidorVistaFragment,
                R.id.clienteProductosFragment,
                R.id.cliRealizarPedidoFragment,
                R.id.perfilConsumidor,
                R.id.agPerfilAgricultorFragment,
                R.id.perfilConsumidor,
                R.id.agSoporteAgricultorFragment,
                R.id.agNuevoProductoFragment,
                R.id.agEditarProductoFragment,
                R.id.HomeAgricultorFragment,
                R.id.cliCarritoComprasFragment,
                R.id.cliComprasFragment)
                .setOpenableLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (hiddenElementsFragmentIds.contains(destination.getId())) {
                binding.appBarMain.toolbar.setVisibility(View.GONE);
                //binding.appBarMain.fab.setVisibility(View.GONE);
                drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            } else {
                binding.appBarMain.toolbar.setVisibility(View.VISIBLE);
                //binding.appBarMain.fab.setVisibility(View.VISIBLE);
                drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.loginFragment) {
                    logout(navController); // Cerrar sesión
                } else {
                    // Delega al controlador de navegación por defecto
                    boolean handled = NavigationUI.onNavDestinationSelected(item, navController);
                    if (handled) {
                        binding.drawerLayout.closeDrawers(); // Cierra el Drawer después de navegar
                    }
                }
                return true;
            }
        });



        // Actualizar menú según el rol
        updateMenuAccordingToRole();
    }
    private void updateMenuAccordingToRole() {
        String rol = "consumidor";
        if (bundleLogin != null && bundleLogin.containsKey("rol")) {
            rol = bundleLogin.getString("rol");
        }

        if ("Agricultor".equals(rol)) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.nav_menu_agricultor);
        } else {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.nav_menu_consumidor);
        }
    }
    public boolean handleLoginRole(Bundle bundle) {
        this.bundleLogin = bundle; // Guarda el Bundle
        updateMenuAccordingToRole(); // Actualiza el menú según el rol
        return "Agricultor".equals(bundle.getString("rol"));
    }

    private void logout(NavController navController) {
        // Limpiar cualquier dato almacenado (bundleLogin, sesiones, etc.)
        bundleLogin = null;

        // Navegar al LoginFragment
        navController.navigate(R.id.loginFragment);

        // Cerrar el DrawerLayout si está abierto
        binding.drawerLayout.closeDrawers();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
