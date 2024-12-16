package com.josemanuel.paf_agrohub_grupo01.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.josemanuel.paf_agrohub_grupo01.R;
import com.josemanuel.paf_agrohub_grupo01.databinding.FragmentPreCargaBinding;


public class PreCargaFragment extends Fragment {


    private FragmentPreCargaBinding binding;

    public PreCargaFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentPreCargaBinding.inflate(inflater, container, false);


        binding.buttonContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(PreCargaFragment.this).navigate(R.id.action_preCargaFragment_to_loginFragment);
            }
        });



        return binding.getRoot();

    }
}