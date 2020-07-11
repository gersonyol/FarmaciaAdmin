package com.example.ejemplowebservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MostrarListaActivity extends AppCompatActivity {

    TextView oTxtId, oTxtNombre, oTxtCantidad, oTxtPrecio, oTxtFecha;

    private String idM, nombre, cantidad, precio, fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_lista);

        oTxtId = findViewById(R.id.txtIdV);
        oTxtNombre = findViewById(R.id.txtNombreV);
        oTxtCantidad = findViewById(R.id.txtCantidadV);
        oTxtPrecio = findViewById(R.id.txtPrecioV);
        oTxtFecha = findViewById(R.id.txtFechaV);

        this.obtenerDatos();

        oTxtId.setText(idM);
        oTxtNombre.setText(nombre);
        oTxtCantidad.setText(cantidad);
        oTxtPrecio.setText(precio);
        oTxtFecha.setText(fecha);

    }

    private void obtenerDatos() {
        Bundle bundle = getIntent().getExtras();
        idM = bundle.getString("idM");
        nombre = bundle.getString("nombre");
        cantidad = bundle.getString("cantidad");
        precio = bundle.getString("precio");
        fecha = bundle.getString("fecha");

    }


}
