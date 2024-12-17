package com.josemanuel.paf_agrohub_grupo01.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.josemanuel.paf_agrohub_grupo01.databinding.FragmentHomeClienteBinding;

public class HomeClienteFragment extends Fragment {

    private FragmentHomeClienteBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeClienteBinding.inflate(inflater, container, false);



        binding.botonVerMas.setOnClickListener(v -> {

        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
