package logistus.net.weatherapp.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import logistus.net.weatherapp.R;
import logistus.net.weatherapp.models.HourlyWeather;

public class HourAdapter extends RecyclerView.Adapter<HourAdapter.HourViewHolder> {
    private HourlyWeather[] mHourlyWeathers;
    private Context mContext;

    public HourAdapter(Context context, HourlyWeather[] hourlyWeathers){
        mContext = context;
        mHourlyWeathers = hourlyWeathers;
    }

    @Override
    public HourViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hourly_list_item, parent, false);
        return new HourViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HourViewHolder holder, int position) {
        holder.bindHour(mHourlyWeathers[position]);
    }

    @Override
    public int getItemCount() {
        return mHourlyWeathers.length;
    }

    public class HourViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTimeLabel;
        public TextView mSummaryLabel;
        public TextView mTemperatureLabel;
        public ImageView mIconImageView;

        public HourViewHolder(View itemView) {
            super(itemView);

            mTimeLabel = itemView.findViewById(R.id.timeLabel);
            mSummaryLabel = itemView.findViewById(R.id.summaryValue);
            mTemperatureLabel = itemView.findViewById(R.id.temperatureValue);
            mIconImageView = itemView.findViewById(R.id.iconImageView);

            itemView.setOnClickListener(this);
        }

        public void bindHour(HourlyWeather hourlyWeather) {
            mTimeLabel.setText(hourlyWeather.getHour());
            mSummaryLabel.setText(hourlyWeather.getSummary());
            mTemperatureLabel.setText(hourlyWeather.getTemperature()+"");
            mIconImageView.setImageResource(hourlyWeather.getIconId());
        }

        @Override
        public void onClick(View view) {
            String dayOfTheWeek = mTimeLabel.getText().toString();
            String conditions = mSummaryLabel.getText().toString();
            String highTemp = mTemperatureLabel.getText().toString();
            String message = String.format("On %s the high will be %s and it will be %s",
                    dayOfTheWeek, highTemp, conditions);
            Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
        }
    }
}
