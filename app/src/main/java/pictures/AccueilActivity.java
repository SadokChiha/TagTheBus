package pictures;

import android.animation.AnimatorSet;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import sqlite.DatabaseHandler;
import sqlite.PictureStation;
import tn.abdessamed.yessine.tagthebus.R;

public class AccueilActivity extends AppCompatActivity {
    Context mContext;
    private Handler mUiHandler = new Handler();
    int id;
    private CustomAdapter customAdapter;
    RecyclerView recyclerView;

    //Json ****************************
    // private static String url = "http://miit.tn/OMPV/pub";
    //*********************************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler);
        //Integration de  menu
        mContext = this.getApplicationContext();
        Intent i = getIntent();
        id = Integer.parseInt(i.getStringExtra("id"));

        System.out.println("hedha  houwa  wine  ");


        //data base


//********************************************************************************** Fin d'integration
        remplissage();
    }

    //*****************************************************************************Animation menu
    private void createCustomAnimation() {
        AnimatorSet set = new AnimatorSet();

    }


    //****************************************Fin menu
    public void remplissage() {
        DatabaseHandler db = new DatabaseHandler(mContext);

        List<PictureStation> contacts = db.getAll();
        ArrayList<PublicationClass> mContentList = new ArrayList<>();

        for (PictureStation cn : contacts) {
            if (cn.getIdStation() == id) {

                String log = "Id: " + cn.getId() + " ,Name: " + cn.getNomStation() + " ,id station : " + cn.getIdStation() + " date" + cn.getDateCreation();
                // Writing Contacts to log
                Log.d("Name: ", log);
                System.out.println("ow " + log);
                PublicationClass publicationClass = new PublicationClass();

                publicationClass.setId("" + cn.getId());

                publicationClass.setNomPublication(cn.getNomStation());
                publicationClass.setDescription("ggg" + cn.getTitre());
                publicationClass.setEmplacement("qqqqq");
                publicationClass.setDate(cn.getDateCreation());
                publicationClass.setImgFood(cn.getNomStation());

                mContentList.add(publicationClass);
            }
        }
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        customAdapter = new CustomAdapter(AccueilActivity.this, mContentList);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(AccueilActivity.this));


    }


    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //builder.setTitle("Wait...");
        builder.setMessage("Do you really want to exit?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                moveTaskToBack(true);
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
