package com.josemanuel.paf_agrohub_grupo01;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
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

    private Bundle bundleLogin;

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
        NavigationView navigationView = binding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.cliConsumidorVistaFragment,
                R.id.clienteProductosFragment,
                R.id.cliRealizarPedidoFragment,
                R.id.perfilConsumidor,
                R.id.agPerfilAgricultorFragment,
                R.id.perfilConsumidor,
                R.id.agSoporteAgricultorFragment,
                R.id.agNuevoProductoFragment,
                R.id.agEditarProductoFragment)
                .setOpenableLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (hiddenElementsFragmentIds.contains(destination.getId())) {

                binding.appBarMain.toolbar.setVisibility(View.GONE);
                binding.appBarMain.fab.setVisibility(View.GONE);
                drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            }
            else {
                binding.appBarMain.toolbar.setVisibility(View.VISIBLE);
                binding.appBarMain.fab.setVisibility(View.VISIBLE);
                drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        if (navController.getCurrentDestination() != null &&
                hiddenElementsFragmentIds.contains(navController.getCurrentDestination().getId())) {
            return false;
        }



        if ( handleLoginRole( bundleLogin )){
            getMenuInflater().inflate(R.menu.nav_menu_agricultor, menu);
        }
        else{
            getMenuInflater().inflate(R.menu.nav_menu_consumidor, menu);
        }
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public boolean handleLoginRole(Bundle bundle) {
        boolean resultado = false;

        if (bundle != null) {
            this.bundleLogin = bundle;
            String rol = this.bundleLogin.getString("rol");
            resultado = "Agricultor".equals(rol);
        }

        return resultado;
    }


}
