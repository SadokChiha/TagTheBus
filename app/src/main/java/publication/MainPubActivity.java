package publication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pictures.MapActivity;
import pictures.PublicationClass;
import sqlite.DatabaseHandler;
import sqlite.PictureStation;
import tn.abdessamed.yessine.tagthebus.MainActivity;
import tn.abdessamed.yessine.tagthebus.R;
import uploads.CaptureActivity;

public class MainPubActivity extends AppCompatActivity {

    PictureStation station;
    ArrayList<DataPubModel> dataModels;
    ListView listView;
    private static CustomPubAdapter adapter;
    int id = 1;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_main);


        listView = (ListView) findViewById(R.id.list);
        mContext = this.getApplicationContext();
        dataModels = new ArrayList<>();

        Intent i = getIntent();
        id = Integer.parseInt(i.getStringExtra("id"));
        System.out.println("ani linna  taw taw  id  2" + id);
        remplissage();
        //   dataModels.add(new DataModel("Apple Pie","","",""));


        adapter = new CustomPubAdapter(dataModels, getApplicationContext());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                DataPubModel dataModel = dataModels.get(position);
                station = dataModel.getStation();


                Snackbar.make(view, station.getDateCreation() + "\n" + station.getTitre(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();


            }

        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_picture, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                //        Intent i = new Intent(MainPubActivity.this, CaptureActivity.class);
                //  i.putExtra("s",dataModel);
                // startActivity(i);
                Intent intent = new Intent(mContext, CaptureActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                int id = dataModel.getStation().getIdStation();
                intent.putExtra("s",""+id);
                mContext.startActivity(intent);
            //    Toast.makeText(this.getApplicationContext(), "add", Toast.LENGTH_LONG).show();
                return (true);

            case R.id.map:
                 intent = new Intent(mContext, MapActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                int id = dataModel.getStation().getIdStation();
                intent.putExtra("s",""+id);
                mContext.startActivity(intent);
                break;
            case R.id.back:

                Intent i= new Intent(MainPubActivity.this, MainActivity.class);
               mContext.startActivity(i);
        }

        return (super.onOptionsItemSelected(item));
    }
    @Override
    public void onBackPressed() {
        Toast.makeText(mContext,"no back ",Toast.LENGTH_LONG).show();
    }
    public void remplissage() {

        DatabaseHandler db = new DatabaseHandler(mContext);

        List<PictureStation> contacts = db.getAll();
        ArrayList<PublicationClass> mContentList = new ArrayList<>();
       // PublicationClass publicationClass = new PublicationClass();

        for (PictureStation cn : contacts) {
            if (cn.getIdStation() == id) {
                PictureStation ps = new PictureStation();
                String log = "Id: " + cn.getTitre() + " ,Name: " + cn.getNomStation() + " ,id station : " + cn.getIdStation() + " date" + cn.getDateCreation();
                // Writing Contacts to log
          //      Toast.makeText(this.getApplicationContext(), log, Toast.LENGTH_LONG).show();
                Log.d("Name: ", log);
          //      Toast.makeText(this.getApplicationContext(), "awww " + log, Toast.LENGTH_LONG).show();
                System.out.println("ow " + log);


                ps.setId(cn.getId());

                ps.setTitre(cn.getTitre());
                ps.setNomStation("" + cn.getNomStation());
                ps.setDateCreation("" + cn.getDateCreation());
                ps.setIdStation(cn.getIdStation());


                DataPubModel d = new DataPubModel(ps);
                dataModels.add(d);


            }
            adapter = new CustomPubAdapter(dataModels, MainPubActivity.this);

        }
    }
}
