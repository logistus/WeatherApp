package logistus.net.weatherapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HourlyWeather implements Parcelable {
    private long mTime;
    private String mSummary;
    private double mTemperature;
    private String mIcon;
    private String mTimezone;

    public HourlyWeather() {}

    public long getTime() {
        return mTime;
    }

    public void setTime(long time) {
        mTime = time;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    public int getTemperature() {
        return (int) Math.round(mTemperature);
    }

    public void setTemperature(double temperature) {
        mTemperature = temperature;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public String getTimezone() {
        return mTimezone;
    }

    public void setTimezone(String timezone) {
        mTimezone = timezone;
    }

    public String getHour() {
        SimpleDateFormat formatter = new SimpleDateFormat("h a");
        return formatter.format(new Date(mTime * 1000));
    }

    public int getIconId() {
        return Forecast.getIconId(mIcon);
    }

    public static HourlyWeather[] getHourlyDetails(String jsonData) throws JSONException {
        JSONObject forecast = new JSONObject(jsonData);
        String timezone = forecast.getString("timezone");
        JSONObject hourly = forecast.getJSONObject("hourly");
        JSONArray data = hourly.getJSONArray("data");

        HourlyWeather[] hourlyWeathers = new HourlyWeather[data.length()];
        for (int i = 0; i < data.length(); i++) {
            JSONObject jsonHour = data.getJSONObject(i);
            HourlyWeather hourlyWeather = new HourlyWeather();
            hourlyWeather.setSummary(jsonHour.getString("summary"));
            hourlyWeather.setTemperature(jsonHour.getDouble("temperature"));
            hourlyWeather.setIcon(jsonHour.getString("icon"));
            hourlyWeather.setTime(jsonHour.getLong("time"));
            hourlyWeather.setTimezone(timezone);

            hourlyWeathers[i] = hourlyWeather;
        }
        return hourlyWeathers;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(mTime);
        parcel.writeString(mSummary);
        parcel.writeDouble(mTemperature);
        parcel.writeString(mIcon);
        parcel.writeString(mTimezone);
    }

    private HourlyWeather(Parcel in) {
        mTime = in.readLong();
        mSummary = in.readString();
        mTemperature = in.readDouble();
        mIcon = in.readString();
        mTimezone = in.readString();
    }

    public static final Creator<HourlyWeather> CREATOR = new Creator<HourlyWeather>() {
        @Override
        public HourlyWeather createFromParcel(Parcel parcel) {
            return new HourlyWeather(parcel);
        }

        @Override
        public HourlyWeather[] newArray(int i) {
            return new HourlyWeather[i];
        }
    };
}
