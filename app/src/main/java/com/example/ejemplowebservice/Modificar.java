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

public class Modificar implements Response.Listener<JSONObject>, Response.ErrorListener{

    private Context context;

    private String id, cantidad;
    private String precio;
    private String nombre, fecha;

    private JsonObjectRequest jsonObjectRequest;
    private RequestQueue requestQueue;

    public void modificar(Context context, String id, String nombre, String cantidad, String precio, String fecha ){

        this.context = context;
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.fecha = fecha;

        String url;

        try {

            url = Constantes.IP_SERVER+"php_sw/modificar_sw.php?nombre_medicamento=" +nombre+
                    "&cantidad="+cantidad + "&precio="+precio + "&fecha_vencimiento="+fecha + "&id="+id;
            requestQueue = Volley.newRequestQueue(context);
            jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
            requestQueue.add(jsonObjectRequest);


        } catch (Exception e) {
            Toast.makeText(context, "Error: "+e, Toast.LENGTH_SHORT).show();
            System.out.println(".....................ErrorModificar"+e);
        }

    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(context, "Actualizado correctamente", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(context, "Error al actualizar"+error, Toast.LENGTH_SHORT).show();
        System.err.println("..............ErrorResponse: "+error);
    }


}
