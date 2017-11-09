package uploads;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import publication.MainPubActivity;
import sqlite.DatabaseHandler;
import sqlite.PictureStation;
import tn.abdessamed.yessine.tagthebus.R;

public class UploadActivity extends Activity {
    // LogCat tag
    private static final String TAG = CaptureActivity.class.getSimpleName();

    private ProgressBar progressBar;
    private String filePath = null;
    private TextView txtPercentage;
    private ImageView imgPreview;
    private VideoView vidPreview;
    private Button btnUpload;
    String nomImage = "";
    private EditText nom;
    private EditText desc;
    private EditText place;
    private TextView textView2;
    private TextView Description;
    private TextView Emplacement;
    private TextView Note;
    String max = "0";
    private EditText note;
    long totalSize = 0;
    Context mContext;
    String url = "";
    //	String id="";
    Button buttoncrop;
    private Vibrator vib;
    //Station station;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
      //  buttoncrop = (Button) findViewById(R.id.edit);
        txtPercentage = (TextView) findViewById(R.id.txtPercentage);
        btnUpload = (Button) findViewById(R.id.btnUpload);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        imgPreview = (ImageView) findViewById(R.id.imgPreview);
        vidPreview = (VideoView) findViewById(R.id.videoPreview);
        //	nom=(EditText)findViewById(R.id.nom);
        desc = (EditText) findViewById(R.id.desc);
        //	place=(EditText)findViewById(R.id.place);
        //	note=(EditText)findViewById(R.id.note);
        // Changing action bar background color
        mContext = this.getApplicationContext();
        vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

     //   textView2 = (TextView) findViewById(R.id.textView2);
        Description = (TextView) findViewById(R.id.textView);
     //   Emplacement = (TextView) findViewById(R.id.textView4);
       // Note = (TextView) findViewById(R.id.textView5);

        // Receiving the data from previous activity
        Intent i = getIntent();
        //	station = (Station)getIntent().getSerializableExtra("s");
        id = Integer.parseInt(getIntent().getStringExtra("s"));
        Toast.makeText(this.getApplicationContext(), "id recu upload " + id, Toast.LENGTH_SHORT).show();
        // image or video path that is captured in previous activity
        filePath = i.getStringExtra("filePath");
        Toast.makeText(this.getApplicationContext(), "path " + filePath.toString(), Toast.LENGTH_LONG).show();

        // boolean flag to identify the media type, image or video
        boolean isImage = i.getBooleanExtra("isImage", true);

        if (filePath != null) {
            // Displaying the image or video on the screen
            previewMedia(isImage);
        } else {
            Toast.makeText(getApplicationContext(),
                    "Sorry, file path is missing!", Toast.LENGTH_LONG).show();
        }


        desc.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (desc.getText().length() < 2) {
                    desc.setError("svp ajouter une description");
                    /*desc.requestFocus();*/
                    desc.setFocusable(true);
                }

            }
        });



        btnUpload.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // uploading the file to server


                String dateAjout = (new SimpleDateFormat("yyyy/MM/dd   HH:mm").format(Calendar.getInstance().getTime()));

                System.out.println("aw lahné");
                //	System.out.println("yaaw "+station.getStreet_name());
                DatabaseHandler db = new DatabaseHandler(mContext);
                PictureStation p = new PictureStation(id, filePath.toString(), desc.getText().toString(), dateAjout);
                db.addPicture(p);
                List<PictureStation> contacts = db.getAll();

                for (PictureStation cn : contacts) {
                    //	String log = "Id: "+cn.getId()+" ,Name: " + cn.getNomStation() + " ,id station : " + cn.getIdStation()+" date"+cn.getDateCreation()+ "titre" +cn.getTitre();
                    // Writing Contacts to log
                    //		Log.d("Name: ", log);
                    //		System.out.println("ow "+log);
                }
                //	int  id = station.getId();
                System.out.println("aw l id = " + id);
                Intent i = new Intent(UploadActivity.this, MainPubActivity.class);
                i.putExtra("id", "" + id);
                startActivity(i);


            }


        });





    }

    /**
     * Displaying captured image/video on the screen
     */
    private void previewMedia(boolean isImage) {
        // Checking whether captured media is image or video
        if (isImage) {
            imgPreview.setVisibility(View.VISIBLE);
            vidPreview.setVisibility(View.GONE);
            // bimatp factory
            BitmapFactory.Options options = new BitmapFactory.Options();

            // down sizing image as it throws OutOfMemory Exception for larger
            // images
            options.inSampleSize = 8;

            final Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);

            imgPreview.setImageBitmap(bitmap);
            System.out.println("aw deux");
        } else {
            imgPreview.setVisibility(View.GONE);
            vidPreview.setVisibility(View.VISIBLE);
            vidPreview.setVideoPath(filePath);
            // start playing
            vidPreview.start();
        }
    }

    /**
     * Uploading the file to server
     * */
}