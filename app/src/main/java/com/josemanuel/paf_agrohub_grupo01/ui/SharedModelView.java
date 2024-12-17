package com.josemanuel.paf_agrohub_grupo01.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedModelView extends ViewModel {

    private MutableLiveData<Boolean> isRol= new MutableLiveData<>(); // True para Agricultor, False para Consumidor



    public void setIsRol(boolean isRol) {
        this.isRol.setValue(isRol);
    }


    public LiveData<Boolean> getIsRol() {
        return isRol;
    }
}

