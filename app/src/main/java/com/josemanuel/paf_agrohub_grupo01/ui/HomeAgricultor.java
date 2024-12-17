package com.josemanuel.paf_agrohub_grupo01.ui;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.josemanuel.paf_agrohub_grupo01.databinding.FragmentHomeAgricultorBinding;


public class HomeAgricultor extends Fragment {

    private FragmentHomeAgricultorBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        binding = FragmentHomeAgricultorBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }





}
