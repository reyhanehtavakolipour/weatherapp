package rtp.reyhanehtpour.weatherapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import rtp.reyhanehtpour.weatherapp.R;
import rtp.reyhanehtpour.weatherapp.data.model.City;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder2> {

    private List<City> cities;
    private CityCallback mClickListener;

    public CityAdapter(CityCallback mClickListener) {
        this.mClickListener = mClickListener;
        cities = new ArrayList<City>();
    }

    // inflates the row layout from xml when needed
    @Override
    public CityViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_item_rv, parent, false);
        return new CityViewHolder2(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(CityViewHolder2 holder, int position) {
        City city = cities.get(position);
        holder.cityTv.setText(city.getCityName());
        holder.countryTv.setText(city.getCountry());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return cities.size();
    }


    public void addItems(List<City> items){
        cities.clear();
        cities.addAll(items);
        notifyDataSetChanged();
    }


    // stores and recycles views as they are scrolled off screen
    public class CityViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView cityTv;
        TextView countryTv;

        CityViewHolder2(View itemView) {
            super(itemView);
            cityTv = itemView.findViewById(R.id.city_tv);
            countryTv = itemView.findViewById(R.id.country_tv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.cityClickListener(cities.get(getAdapterPosition()));
        }
    }

    // parent activity will implement this method to respond to click events
    public interface CityCallback {
        void cityClickListener(City city);
    }
}
