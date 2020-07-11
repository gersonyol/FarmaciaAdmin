package com.example.ejemplowebservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class MostrarActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{

    private ListView lista;

    private ArrayList <String> listaDatos;
    private ArrayList <Medicamento>  listaMedicamento;

    private String idM, nombre, cantidad, precio, fecha;

    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);

        lista = findViewById(R.id.listaMostrar);
        requestQueue = Volley.newRequestQueue(this);

        this.consultaCompletaSW();

    }

    public void consultaCompletaSW(){
        String url;
        url = Constantes.IP_SERVER +"php_sw/mostrar_sw.php";
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        requestQueue.add(jsonObjectRequest);

    }

    @Override
    public void onResponse(JSONObject response) {

        Medicamento medicamento = null;
        JSONArray jsonArray = response.optJSONArray("tbl_medicamento");
        listaMedicamento = new ArrayList<>();

        try {

            for (int i = 0; i < jsonArray.length(); i++){
                medicamento = new Medicamento();

                JSONObject jsonObject = null;
                jsonObject = jsonArray.getJSONObject(i);
                medicamento.setId(jsonObject.optInt("id"));
                medicamento.setNombremedicamento(jsonObject.optString("nombremedicamento"));
                medicamento.setCantidad(jsonObject.optInt("cantidad"));
                medicamento.setPrecio(jsonObject.optDouble("precio"));
                medicamento.setFechavencimiento(jsonObject.optString("fechavencimiento"));

                listaMedicamento.add(medicamento);

            }

            listaDatos = new ArrayList<>();
            for (int i = 0; i < listaMedicamento.size(); i++){
                listaDatos.add(listaMedicamento.get(i).getNombremedicamento());
            }
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaDatos);
            lista.setAdapter(adapter);

            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    idM = String.valueOf(listaMedicamento.get(position).getId());
                    nombre = listaMedicamento.get(position).getNombremedicamento();
                    cantidad = String.valueOf(listaMedicamento.get(position).getCantidad());
                    precio = String.valueOf(listaMedicamento.get(position).getPrecio());
                    fecha = listaMedicamento.get(position).getFechavencimiento();

                    Intent intent = new Intent(getApplicationContext(), MostrarListaActivity.class);
                    intent.putExtra("idM", idM);
                    intent.putExtra("nombre", nombre);
                    intent.putExtra("cantidad", cantidad);
                    intent.putExtra("precio", precio);
                    intent.putExtra("fecha", fecha);

                    startActivity(intent);

                }
            });

        }catch(Exception e){
            Toast.makeText(this, "Error Desconocido"+e, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "Error "+error, Toast.LENGTH_SHORT).show();
    }




}
