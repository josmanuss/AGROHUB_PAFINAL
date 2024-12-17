package com.josemanuel.paf_agrohub_grupo01.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;



import java.util.ArrayList;
import java.util.List;

public class CarritoViewModel extends ViewModel {
    private final MutableLiveData<List<CliCarritoComprasFragment.Producto>> productos;

    public CarritoViewModel() {
        productos = new MutableLiveData<>(new ArrayList<>());
    }

    public MutableLiveData<List<CliCarritoComprasFragment.Producto>> getProductos() {
        return productos;
    }

    public void agregarProducto(CliCarritoComprasFragment.Producto producto) {
        List<CliCarritoComprasFragment.Producto> currentList = productos.getValue();
        if (currentList != null) {
            currentList.add(producto);
            productos.setValue(currentList);
        }
    }
}
