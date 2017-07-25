package com.example.amal.movieproject;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.service.voice.VoiceInteractionSession;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.amal.movieproject.DatabaseClass.Helper;
import com.example.amal.movieproject.adapter.Adapter;
import com.example.amal.movieproject.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;


public class MainActivity extends AppCompatActivity  {

    Adapter adapter;
    String sector="http://image.tmdb.org/t/p/w185/";
    List<Movie> myMovies;
    RecyclerView display;
    RequestQueue requestQueue;

    List<Movie> favoritelist;



    Helper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue= Volley.newRequestQueue(this);
        myMovies=new ArrayList<>();
        display=(RecyclerView)findViewById(R.id.myview);
        display.setLayoutManager(new GridLayoutManager(this,2));
        adapter=new Adapter(MainActivity.this,myMovies);
        display.setAdapter(adapter);
        String url="https://api.themoviedb.org/3/discover/movie?api_key=69f8d44407d7b73a4103add4c76fccb6";
        getMovieData(url);

        favoritelist= new ArrayList<>();
        helper= new Helper(this);




    }

    void getMovieData(String url) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray fileinput = response.getJSONArray("results");
                    for (int i = 0; i < fileinput.length(); i++) {
                        JSONObject temp = fileinput.getJSONObject(i);
                        Movie movie = new Movie();
                        movie.setName(temp.getString("title"));
                        movie.setPosterPath(sector + temp.getString("poster_path"));
                        movie.setOverView(temp.getString("overview"));
                        myMovies.add(movie);
                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch (item.getItemId())
        {
            case R.id.FavoriteList:
                ChangeList();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }
    public void ChangeList()
    {

        //Cursor c ;
        //c=helper.select();
       // c.moveToFirst();
        //for(int i=0;i<c.getCount();i++)
       // {
          //  Movie movieee = new Movie();
           // movieee.setName(c.getString(0));
           // movieee.setPosterPath(c.getString(1));
           // movieee.setOverView(c.getString(2));

            favoritelist=helper.getAllContacts();
            //c.moveToNext();
      //  }
        adapter.notifyDataSetChanged();
        adapter=new Adapter(MainActivity.this,favoritelist);
        display.setAdapter(adapter);







    }
}

