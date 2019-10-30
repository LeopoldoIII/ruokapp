package com.ruokapp.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ruokapp.R;
import com.ruokapp.core.RecipeRef;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {

    private Context context;
    private static LayoutInflater inflater;
    private ArrayList<RecipeRef> data;

    public Adapter(Context context, ArrayList<RecipeRef> data){
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        //Not necessary
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        //Not necessary
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final View view =  inflater.inflate(R.layout.item_food, null);
        ImageView foodImage = (ImageView) view.findViewById(R.id.image_food);
        TextView titleFood = (TextView) view.findViewById(R.id.title_food);
        ImageView iconDifficult = (ImageView) view.findViewById(R.id.icon_difficult);
        ImageView iconTimer = (ImageView) view.findViewById(R.id.icon_timer);
        TextView timerText = (TextView) view.findViewById(R.id.text_timer);

        titleFood.setText(data.get(position).getTitle());
        timerText.setText(data.get(position).getPreparationMinutes());
        foodImage.setImageResource(R.drawable.referencia);
        iconDifficult.setImageResource(R.drawable.difficult);
        iconTimer.setImageResource(R.drawable.timer);

        return view;
    }
}
