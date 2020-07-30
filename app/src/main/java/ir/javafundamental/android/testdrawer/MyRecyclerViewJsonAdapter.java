package ir.javafundamental.android.testdrawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyRecyclerViewJsonAdapter extends RecyclerView.Adapter<MyRecyclerViewJsonAdapter.ViewHolder> {

    private static final String LOG_TAG = MyRecyclerViewJsonAdapter.class.getSimpleName();

    private List<Vehicle> mData;
    private LayoutInflater mInflater;
    private Context myContext;

    // data is passed into the constructor
    MyRecyclerViewJsonAdapter(Context context, List<Vehicle> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.myContext = context;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerviewjson_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the Button in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Vehicle dto_Vehicle = mData.get(position);
        try {
            holder.lbl_bearing.setText(String.valueOf(dto_Vehicle.getBearing()));
            holder.lbl_lat.setText(String.valueOf(dto_Vehicle.getLat()));
            holder.lbl_lng.setText(String.valueOf(dto_Vehicle.getLng()));
            holder.lbl_type.setText(dto_Vehicle.getType());
            Glide.with(this.myContext).load(dto_Vehicle.getImageUrl()).into(holder.img_car);
        }
        catch (Exception ex){
            String xxx = ex.getMessage();
        }
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView lbl_type;
        TextView lbl_lat;
        TextView lbl_lng;
        TextView lbl_bearing;
        ImageView img_car;
        ViewHolder(View itemView) {
            super(itemView);
            lbl_type = (TextView)itemView.findViewById(R.id.lbl_type);
            lbl_lat = (TextView)itemView.findViewById(R.id.lbl_lat);
            lbl_lng = (TextView)itemView.findViewById(R.id.lbl_lng);
            lbl_bearing = (TextView)itemView.findViewById(R.id.lbl_bearing);
            img_car = (ImageView) itemView.findViewById(R.id.imgjson_url);
        }

    }

    // convenience method for getting data at click position
    Vehicle getItem(int id) {
        return mData.get(id);
    }

}
