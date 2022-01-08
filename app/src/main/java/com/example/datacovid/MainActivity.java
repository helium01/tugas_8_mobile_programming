package com.example.datacovid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView positif,dirawat,sembuh,meninggal,lastUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        positif = findViewById(R.id.totpositif);
        dirawat = findViewById(R.id.totdirawat);
        sembuh = findViewById(R.id.totsembuh);
        meninggal= findViewById(R.id.totmeninggal);
        lastUpdate= findViewById(R.id.last);
        tampilData();

    }

    private void tampilData() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://apicovid19indonesia-v2.vercel.app/api/indonesia";
        JSONObject jsonObject = new JSONObject();
        final String requestBody = jsonObject.toString();

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jo = new JSONObject(response.toString());
                    String positif = jo.getString("positif");
                    String dirawat = jo.getString("dirawat");
                    String sembuh = jo.getString("sembuh");
                    String meninggal= jo.getString("meninggal");
                    String lastUpdate = jo.getString("lastUpdate");


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"Data Gagal Dimuat ",Toast.LENGTH_SHORT).show();
            }
        }
        );
        queue.add(stringRequest);
    }
}