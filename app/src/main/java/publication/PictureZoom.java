package publication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import pictures.PublicationClass;
import sqlite.DatabaseHandler;
import sqlite.PictureStation;
import tn.abdessamed.yessine.tagthebus.R;
import uk.co.senab.photoview.PhotoViewAttacher;
import uploads.CaptureActivity;

public class PictureZoom extends AppCompatActivity {

    ImageView imageView;
    String titre;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zoom_activity);
         Intent  i = getIntent();
         titre= i.getStringExtra("titre");
         url=""+i.getStringExtra("url");
        Toast.makeText(getApplicationContext(),"url "+url,Toast.LENGTH_LONG).show();
        imageView = (ImageView)findViewById(R.id.imageView);
        Glide.with(this.getApplicationContext()).load(""+url)
                .placeholder(R.drawable.image_food_erreur)
                .error(R.drawable.image_food_erreur)
                .into(imageView);
        PhotoViewAttacher photoView = new PhotoViewAttacher(imageView);
        photoView.update();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_share, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                Uri bmpUri = Uri.fromFile(new File(url));
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("image/*");
                shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
                startActivity(Intent.createChooser(shareIntent, "Share Image"));
              //  getApplicationContext().startActivity(new_intent);

        }
        return (super.onOptionsItemSelected(item));
    }
}
