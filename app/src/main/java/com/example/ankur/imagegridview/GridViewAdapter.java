package com.example.ankur.imagegridview;
import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.squareup.picasso.Picasso;

public class GridViewAdapter extends ArrayAdapter<GridItem> {

    private Context mContext;
    private int layoutResourceId;
    private ArrayList<GridItem> mGridData = new ArrayList<GridItem>();

    public GridViewAdapter(Context mContext, int layoutResourceId, ArrayList<GridItem> mGridData) {
        super(mContext, layoutResourceId, mGridData);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.mGridData = mGridData;
    }


    /**
     * Updates grid data and refresh grid items.
     * @param mGridData
     */
    public void setGridData(ArrayList<GridItem> mGridData) {
        this.mGridData = mGridData;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;
        ViewHolder2 holder2 ;
        holder = new ViewHolder();
        holder2 = new ViewHolder2() ;

        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder.titleTextView = (TextView) row.findViewById(R.id.grid_item_title);
            holder2.imageView = (ImageView) row.findViewById(R.id.grid_item_image);
            holder.date_takenTextView = (TextView) row.findViewById(R.id.grid_item_dateTaken);
            holder.descriptionTextView = (TextView) row.findViewById(R.id.grid_item_description);
            holder.flip = (ViewFlipper) row.findViewById(R.id.my_view_flipper);
            holder2.imageView.setLayoutParams(new LinearLayout.LayoutParams(400,400));
            holder2.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            holder2.imageView.setPadding(8, 8, 8, 8);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
            holder2.imageView = (ImageView) row.findViewById(R.id.grid_item_image);
        }

        GridItem item = mGridData.get(position);
        holder.titleTextView.setText(Html.fromHtml(item.getTitle()));
        holder.descriptionTextView.setText(Html.fromHtml(item.getDescription()));
        holder.date_takenTextView.setText(Html.fromHtml(item.getDate_taken()));
        Picasso.with(mContext).load(item.getImage()).into(holder2.imageView);

        if(item.isflipped()){
            holder.flip.setDisplayedChild(1);
            item.setIsflipped(true);
        }
        else{
            holder.flip.setDisplayedChild(0);
            item.setIsflipped(false);
        }

        return row;
    }

    static class ViewHolder {
        ViewFlipper flip ;
        TextView titleTextView;
        TextView date_takenTextView;
        TextView descriptionTextView;
    }

    static class ViewHolder2{

        ImageView imageView;
    }
}