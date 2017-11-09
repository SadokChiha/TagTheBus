package pictures;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tn.abdessamed.yessine.tagthebus.R;

/**
 * Created by Sadok on 07/04/17.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ItemViewHolder> {


    private List<PublicationClass> mDataList = Collections.emptyList();;
    private LayoutInflater mLayoutInflater;
    private Context context;
    PublicationClass current;


    public CustomAdapter(Context context, ArrayList<PublicationClass> dataList) {
        mDataList = dataList;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context=context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mLayoutInflater.inflate(R.layout.activity_accueil, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holders, final int position) {
        final ItemViewHolder holder= (ItemViewHolder) holders;
        PublicationClass dataModel = mDataList.get(position);
        String dateEtEmp = "Le "+dataModel.getDate() +" à " + dataModel.getEmplacement();
        holder.mDateEtEmplacement.setText(dateEtEmp);
        holder.mDescription.setText(dataModel.getDescription());
        Glide.with(context).load(dataModel.getImgFood())
                .placeholder(R.drawable.image_food_erreur)
                .error(R.drawable.image_food_erreur)
                .into(holder.mImageFood);


    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView mImage;
        TextView mNomUser;
        TextView mDateEtEmplacement;
        TextView mDescription;
        ImageView mImageFood;
        ImageView mImageUp;
        ImageView mImageDown;



        public ItemViewHolder(View itemView) {
            super(itemView);

            mImage = (ImageView) itemView.findViewById(R.id.image);
            mNomUser = (TextView) itemView.findViewById(R.id.nomUser);
            mDateEtEmplacement = (TextView) itemView.findViewById(R.id.dateETemplacement);
            mDescription = (TextView) itemView.findViewById(R.id.description);
            mImageFood = (ImageView) itemView.findViewById(R.id.imageFood);


        }

    }
}
