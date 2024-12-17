package com.josemanuel.paf_agrohub_grupo01.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import com.josemanuel.paf_agrohub_grupo01.R;

public class AgPerfilAgricultorFragment extends Fragment {



    public AgPerfilAgricultorFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_ag_perfil_agricultor, container, false);
    }
}