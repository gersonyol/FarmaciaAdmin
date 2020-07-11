package com.example.ejemplowebservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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


public class EliminarActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    private EditText iTxtId;
    private TextView oTxtN, oTxtC, oTxtP, oTxtF;

    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;
    Eliminar objEliminar = new Eliminar();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);

        iTxtId = findViewById(R.id.txtIdE);
        oTxtN = findViewById(R.id.txtNombreE);
        oTxtC = findViewById(R.id.txtCantidadE);
        oTxtP = findViewById(R.id.txtPrecioE);
        oTxtF = findViewById(R.id.txtVencimientoE);

        requestQueue = Volley.newRequestQueue(this);

    }

    public void onclick(View view) {

        switch (view.getId()) {
            case R.id.btnBuscarE:
                this.buscarDato();
                break;
            case R.id.btnEliminarMed:

                    if (!iTxtId.getText().toString().isEmpty() && !oTxtN.getText().toString().isEmpty() && !oTxtC.getText().toString().isEmpty() &&
                            !oTxtP.getText().toString().isEmpty() && !oTxtF.getText().toString().isEmpty()) {

                        this.objEliminar.eliminar(getApplicationContext(), Integer.parseInt(iTxtId.getText().toString()));

                    } else {
                        Toast.makeText(this, "Debe cargar un registro", Toast.LENGTH_SHORT).show();
                    }

                this.limpiarTxt();
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
                Toast.makeText(this, "Error buscar: "+e, Toast.LENGTH_SHORT).show();
            }


        } else {
            Toast.makeText(this, "Id no ingresado", Toast.LENGTH_SHORT).show();
        }

    }

    private void limpiarTxt(){
        oTxtN.setText("");
        oTxtC.setText("");
        oTxtP.setText("");
        oTxtF.setText("");
    }


    @Override
    public void onResponse(JSONObject response) {

        //Toast.makeText(this, "Respuesta: "+response, Toast.LENGTH_SHORT).show();

        Medicamento medicamento = new Medicamento();

        JSONArray jsonArray = response.optJSONArray("tbl_medicamento");
        JSONObject jsonObject = null;

        if (!jsonArray.isNull(0)){

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

                Toast.makeText(this, "Registro almacenado correctamente", Toast.LENGTH_SHORT).show();

            }catch(Exception e){
                Toast.makeText(this, "Error: "+e, Toast.LENGTH_SHORT).show();
                System.out.println("............Error onResponse: "+e);
            }

        }else{

            Toast.makeText(this, "Id sin registro", Toast.LENGTH_SHORT).show();

        }


    }

        @Override
        public void onErrorResponse (VolleyError error){
            Toast.makeText(this, "Error respuesta: " + error, Toast.LENGTH_SHORT).show();
        }


    }
