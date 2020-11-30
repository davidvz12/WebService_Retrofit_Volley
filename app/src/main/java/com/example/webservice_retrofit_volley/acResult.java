package com.example.webservice_retrofit_volley;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.webservice_retrofit_volley.Interface.JSonNumbersapi;
import com.example.webservice_retrofit_volley.Model.Posts;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class acResult extends AppCompatActivity {

    RequestQueue requestQueue;
    private static final String url = "http://numbersapi.com/";
    TextView lblTitle;

    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac_result);
        bundle = this.getIntent().getExtras();
        TextView txtMessage = (TextView)findViewById(R.id.lblMessage);
        lblTitle = (TextView)findViewById(R.id.lblTile2);
        if (bundle.getInt("option") == 0)
            retrofit(txtMessage);
        else {
            requestQueue = Volley.newRequestQueue(this);
            volley(txtMessage);
        }
    }

    private void retrofit(TextView message)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JSonNumbersapi jsonnumbersapi = retrofit.create(JSonNumbersapi.class);
        Call<Posts> call = jsonnumbersapi.getPosts(bundle.getInt("number"));

        call.enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {
                if(!response.isSuccessful()){
                    message.setText("Code: "+ response.code());
                    return;
                }
                Posts post = response.body();
                String content = "";
                content +="NUMBER : "+ post.getNumber() + "\n\n";
                content +="TRIVIA : "+ post.getText() + "\n";
                message.append(content);
                lblTitle.setText("NUMBERSAPI - (Retrofit)");
            }

            @Override
            public void onFailure(Call<Posts> call, Throwable t) {
                message.setText(t.getMessage());
            }
        });
    }
    private void volley(TextView message)
    {
        StringRequest request = new StringRequest(Request.Method.GET
                , url + bundle.getInt("number") + "?json", new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject json = null;
                try {
                    json = new JSONObject(response);
                    String content = "";
                    content +="NUMBER : "+ json.getString("number") + "\n\n";
                    content +="TRIVIA : "+ json.getString("text") + "\n";
                    message.append(content);
                    lblTitle.setText("NUMBERSAPI - (Volley)");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                message.setText(error.getMessage());
            }
        }
        );
        requestQueue.add(request);
    }

}