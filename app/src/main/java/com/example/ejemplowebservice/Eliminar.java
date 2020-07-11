package com.example.ejemplowebservice;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ejemplowebservice.conexion.Constantes;

import org.json.JSONObject;

public class Eliminar implements Response.Listener<JSONObject>, Response.ErrorListener {

    private JsonObjectRequest jsonObjectRequest;
    private RequestQueue requestQueue;
    private int idE;
    private Context context;

    public void eliminar(Context context, int idE){

        this.context = context;
        this.idE = idE;
        String url;

        try {

            url = Constantes.IP_SERVER +"php_sw/eliminar_sw.php?id=" + idE;
            requestQueue = Volley.newRequestQueue(context);
            jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
            requestQueue.add(jsonObjectRequest);

        }catch(Exception e){
            Toast.makeText(context, "Error: "+e, Toast.LENGTH_SHORT).show();
            System.out.println("................"+e);
        }

    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(context, "Registro eliminado correctamente", Toast.LENGTH_SHORT).show();
        System.out.println("............Registro eliminado "+ response);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(context, "Error Respuesta: "+error, Toast.LENGTH_SHORT).show();
        System.out.println(".................Error respuesta: "+error);
    }


}
