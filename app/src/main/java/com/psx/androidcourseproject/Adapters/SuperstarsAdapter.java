package com.psx.androidcourseproject.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.psx.androidcourseproject.R;
import com.psx.androidcourseproject.model.Superstar;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Pranav on 06-03-2017.
 */

public class SuperstarsAdapter extends RecyclerView.Adapter<SuperstarsAdapter.MyViewHolder>  {

    private Context context;
    private List<Superstar> superstars;

    public SuperstarsAdapter (Context context, List<Superstar> superstars){
        // constructor
        this.context = context;
        this.superstars = superstars;
    }

    @Override
    public int getItemViewType(int position) {
        return (position%2);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case 0:
                // even
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.superstar_layout_even,parent,false);
                MyViewHolder myViewHolder = new MyViewHolder(view);
                return myViewHolder;
            case 1:
                //odd
                View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.superstar_layout_odd,parent,false);
                MyViewHolder myViewHolder1 = new MyViewHolder(view1);
                return myViewHolder1;
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return superstars.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.description.setText(superstars.get(position).getDescription());
        holder.name.setText(superstars.get(position).getName());
        if (!superstars.get(position).isMale()){
            holder.profile_pic.setImageResource(R.drawable.ic_girl);
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView name;
        private TextView description;
        private ImageView profile_pic;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.superstar_name);
            description = (TextView) itemView.findViewById(R.id.superstar_championship);
            profile_pic = (ImageView) itemView.findViewById(R.id.superstar_pic);
        }
    }
}
