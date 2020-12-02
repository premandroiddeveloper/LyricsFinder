package com.example.lyricsfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
EditText ed1artist,ed2song;
Button b1;
TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1artist=(EditText)findViewById(R.id.editTextTextPersonName);
        ed2song=(EditText)findViewById(R.id.editTextTextPersonName2);
        b1=(Button)findViewById(R.id.button);
        t1=(TextView)findViewById(R.id.textView);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String url="https://api.lyrics.ovh/v1/"+ed1artist.getText().toString()+"/"+ed2song.getText().toString();
                url.replace(" ","20%");
                final RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
                JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    public void onResponse(JSONObject response) {
                        try {
                            t1.setText(response.getString("lyrics"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });
                requestQueue.add(request);
            }
        });
    }
}