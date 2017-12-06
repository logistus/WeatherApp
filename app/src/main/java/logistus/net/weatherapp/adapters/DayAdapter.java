package logistus.net.weatherapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import logistus.net.weatherapp.R;
import logistus.net.weatherapp.models.DailyWeather;

public class DayAdapter extends BaseAdapter {
    private Context mContext;
    private DailyWeather[] mDays;

    public DayAdapter(Context context, DailyWeather[] days) {
        mContext = context;
        mDays = days;
    }

    @Override
    public int getCount() {
        return mDays.length;
    }

    @Override
    public Object getItem(int i) {
        return mDays[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.daily_list_item, null);
            holder = new ViewHolder();
            holder.iconImageView = view.findViewById(R.id.iconImageView);
            holder.temperatureLabel = view.findViewById(R.id.temperatureValue);
            holder.dayLabel = view.findViewById(R.id.daynameValue);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        DailyWeather dailyWeather = mDays[i];
        holder.iconImageView.setImageResource(dailyWeather.getIconId());
        holder.temperatureLabel.setText(dailyWeather.getTemperatureMax()+"");
        if (i == 0) {
            holder.dayLabel.setText("Today");
        } else {
            holder.dayLabel.setText(dailyWeather.getDayOfTheWeek());
        }

        return view;
    }

    private static class ViewHolder {
        ImageView iconImageView;
        TextView temperatureLabel;
        TextView dayLabel;
    }
}
