package com.example.ejemplowebservice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ejemplowebservice.conexion.Constantes;
import com.example.ejemplowebservice.modeloVO.Medicamento;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;

public class ModificarActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    private EditText iTxtId;
    private EditText oTxtN, oTxtC, oTxtP, oTxtF;

    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;

    public final Calendar c = Calendar.getInstance();

    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);

    Modificar objModificar = new Modificar();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        iTxtId = findViewById(R.id.txtIdM);
        oTxtN = findViewById(R.id.txtNombreM);
        oTxtC = findViewById(R.id.txtCantidadM);
        oTxtP = findViewById(R.id.txtPrecioM);
        oTxtF = findViewById(R.id.txtFechaM);
        oTxtF.setEnabled(false);

        requestQueue = Volley.newRequestQueue(this);

    }

    public void onclick(View view) {

        switch (view.getId()) {
            case R.id.btnBuscarM:
                this.buscarDato();
                break;
            case R.id.imgFechaM:
                this.fechaCalendario();
                break;
            case R.id.btnModificarMed:
                if (!iTxtId.getText().toString().isEmpty() && !oTxtN.getText().toString().isEmpty() && !oTxtC.getText().toString().isEmpty() &&
                        !oTxtP.getText().toString().isEmpty() && !oTxtF.getText().toString().isEmpty()) {

                    this.objModificar.modificar(getApplicationContext(), iTxtId.getText().toString(),
                            oTxtN.getText().toString(), oTxtC.getText().toString(),
                            oTxtP.getText().toString(), oTxtF.getText().toString());

                }else{
                    Toast.makeText(this, "Registro no ingresado", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    public void buscarDato() {

        String url;

        if(!iTxtId.getText().toString().isEmpty()) {

            try {
                url = Constantes.IP_SERVER +"php_sw/buscar_sw.php?id=" + iTxtId.getText().toString();
                jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
                requestQueue.add(jsonObjectRequest);

            } catch (Exception e) {
                Toast.makeText(this, "Error Desconocido", Toast.LENGTH_SHORT).show();
            }


        } else {
            Toast.makeText(this, "Id no ingresado", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onResponse(JSONObject response) {
        Medicamento medicamento = new Medicamento();
        JSONArray jsonArray = response.optJSONArray("tbl_medicamento");
        JSONObject jsonObject = null;

        if (!jsonArray.isNull(0)) {

            try {
                jsonObject = jsonArray.getJSONObject(0);

                medicamento.setNombremedicamento(jsonObject.optString("nombremedicamento"));
                medicamento.setCantidad(jsonObject.optInt("cantidad"));
                medicamento.setPrecio(jsonObject.optDouble("precio"));
                medicamento.setFechavencimiento(jsonObject.optString("fechavencimiento"));

                oTxtN.setText(medicamento.getNombremedicamento());
                oTxtC.setText(String.valueOf(medicamento.getCantidad()));
                oTxtP.setText(String.valueOf(medicamento.getPrecio()));
                oTxtF.setText(medicamento.getFechavencimiento());

                Toast.makeText(this, "Registro cargado correctamente", Toast.LENGTH_SHORT).show();


            } catch (Exception e) {
                Toast.makeText(this, "Error: " + e, Toast.LENGTH_SHORT).show();
                System.out.println("..............Response: " + e);
            }

        }else{
            Toast.makeText(this, "Id sin registro", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "Error referente a " + error, Toast.LENGTH_SHORT).show();
        System.err.println(".............ErrorResponse: " + error);
    }


    public void fechaCalendario() {
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                final int mesActual = month + 1;
                String diaFormateado = (dayOfMonth < 10) ? 0 + String.valueOf(dayOfMonth) : String.valueOf(dayOfMonth);
                String mesFormateado = (mesActual < 10) ? 0 + String.valueOf(mesActual) : String.valueOf(mesActual);
                String anio = String.valueOf(year);

                oTxtF.setText(mesFormateado + "/" + diaFormateado + "/" + anio);
            }
        }, anio, mes, dia);
        recogerFecha.show();
    }


}
