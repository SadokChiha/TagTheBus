package publication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import pictures.MapActivity;
import sqlite.DatabaseHandler;
import sqlite.PictureStation;
import tn.abdessamed.yessine.tagthebus.MainActivity;
import tn.abdessamed.yessine.tagthebus.R;


public class CustomPubAdapter extends ArrayAdapter<DataPubModel> implements View.OnClickListener {

    private ArrayList<DataPubModel> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        TextView txtType;
        TextView txtVersion;
        ImageView delete;
        ImageView image;
        ImageView visualise;
    }

    public CustomPubAdapter(ArrayList<DataPubModel> data, Context context) {
        super(context, R.layout.row_picture_item, data);
        this.dataSet = data;
        this.mContext = context;

    }

    @Override
    public void onClick(final View v) {

        int position = (Integer) v.getTag();
        Object object = getItem(position);
  final      DataPubModel dataModel = (DataPubModel) object;

        switch (v.getId()) {

            case R.id.item_delete:
                //    Intent intent = new Intent(mContext, CaptureActivity.class);
                //  System.out.println("sadok "+dataModel.getStation().getId());
                //int id = dataModel.getStation().getId();
                //intent.putExtra("s",""+id);
                //mContext.startActivity(intent);
                Snackbar snackbar = Snackbar
                        .make(v, "Confirmez vous la suppression ? ", Snackbar.LENGTH_LONG)
                        .setAction("Supprimer", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                PictureStation ps = dataModel.getStation();
                                DatabaseHandler db = new DatabaseHandler(mContext);

                                db.deleteContact(ps);
                                notifyDataSetChanged();

                                Intent intent = new Intent(mContext, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                int id = dataModel.getStation().getId();
                                intent.putExtra("id", "" + id);
                                Toast.makeText(mContext, "Image Supprimée ", Toast.LENGTH_LONG).show();

                                mContext.startActivity(intent);
                            }
                        });

                snackbar.show();
                //  Toast.makeText(mContext,"ao",Toast.LENGTH_LONG).show();



                break;
            case R.id.visualise:

                Toast.makeText(mContext, "visualise", Toast.LENGTH_LONG).show();
                 Intent i = new Intent(mContext,PictureZoom.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    i.putExtra("url",""+dataModel.getStation().getNomStation());
               i.putExtra("titre",""+dataModel.getStation().getTitre());
              mContext.startActivity(i);
            case R.id.back:
              i= new Intent(mContext, MainActivity.class);
                mContext.startActivity(i);

        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        DataPubModel dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_picture_item, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.namee);

            viewHolder.delete = (ImageView) convertView.findViewById(R.id.item_delete);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.item_image);
            viewHolder.visualise = (ImageView) convertView.findViewById(R.id.visualise);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.txtName.setText("Titre : " + dataModel.getStation().getTitre()
                + "   à   " + dataModel.getStation().getDateCreation());
        Glide.with(mContext).load("" + dataModel.getStation().getNomStation())
                .placeholder(R.drawable.image_food_erreur)
                .error(R.drawable.image_food_erreur)
                .into(viewHolder.image);

        viewHolder.delete.setOnClickListener(this);
        viewHolder.delete.setTag(position);

        viewHolder.visualise.setOnClickListener(this);
        viewHolder.visualise.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}