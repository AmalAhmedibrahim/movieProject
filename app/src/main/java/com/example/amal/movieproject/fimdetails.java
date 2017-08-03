package com.example.amal.movieproject;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amal.movieproject.DatabaseClass.Helper;
import com.example.amal.movieproject.adapter.Adapter;
import com.example.amal.movieproject.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.duration;
import static android.R.id.list;
import static com.example.amal.movieproject.DatabaseClass.Contractor.table1.TABLE_NAME;

public class fimdetails extends AppCompatActivity {
    Movie movie;
    String title;
    String description;
    String URL;
    TextView Title;
    TextView Description;
    ImageView imagee ;
    Button AddButton;
    List<Movie> favoritelist;
    Helper helper;
    Cursor c;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fimdetails);
        imagee = (ImageView)findViewById(R.id.image);
        Description = (TextView)findViewById(R.id.description);
        Title = (TextView)findViewById(R.id.MOVIE_Title);
        AddButton= (Button)findViewById(R.id.Add);
        movie = (Movie) getIntent().getSerializableExtra("movieClicked");
        favoritelist= new ArrayList<>();

        helper= new Helper(this);
       c= helper.select(movie);
        if(c.getCount()!=0)
        {
            AddButton.setText("Unfavorite");
        }



        description=movie.getOverView();
        Description.setText(description);

        title=movie.getName();
        Title.setText(title);

        URL=movie.getPosterPath();
        Picasso.with(getApplicationContext()).load(URL).into(imagee);







    }

    public void AddToList(View v)
    {


        c=helper.select(movie);
        if(c.getCount()==0)
        {
            helper.insert(movie);
            AddButton.setText("Unfavorite");
            Toast.makeText(this, "Movie Added success", Toast.LENGTH_SHORT).show();
        }

        else
        {
            helper.RemoveMovie(movie);
            AddButton.setText("add to Favorite");
            Toast.makeText(this, getString(R.string.mm), Toast.LENGTH_SHORT).show();
        }




    }
}
