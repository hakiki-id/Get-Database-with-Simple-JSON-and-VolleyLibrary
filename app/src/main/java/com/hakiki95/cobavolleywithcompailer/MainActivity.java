package com.hakiki95.cobavolleywithcompailer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button btnConnect;
    TextView tvShow;

    RequestQueue requestData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvShow = (TextView) findViewById(R.id.tvshow);

        btnConnect= (Button) findViewById(R.id.btnConnect);

        requestData = Volley.newRequestQueue(this);

        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonObjectRequest jsonReques = new JsonObjectRequest(Request.Method.GET, "http://192.168.49.2/makanan/getdatamakanan.php",
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                try {
                                    JSONArray getArray = response.getJSONArray("makanan");

                                    for (int i=0; i<getArray.length(); i++) {
                                        JSONObject getObjek = getArray.getJSONObject(i);

                                        String namamakanan = getObjek.getString("nama_makanan");
                                        String hargamakanan = getObjek.getString("harga");

                                        tvShow.append(namamakanan + " Harga : " + hargamakanan + "\n");

                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        },

                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                Log.e("VOLLEY", "ERROR");
                            }
                        }

                );
                requestData.add(jsonReques);
            }
        });
    }
}
