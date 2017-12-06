package logistus.net.weatherapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import logistus.net.weatherapp.adapters.DayAdapter;
import logistus.net.weatherapp.models.DailyWeather;

public class DailyForecastActivity extends AppCompatActivity {
    private DailyWeather[] mDailyWeathers;

    @BindView(android.R.id.list) ListView mListView;
    @BindView(android.R.id.empty) TextView emptyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_forecast);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Parcelable[] parcelables = intent.getParcelableArrayExtra(MainActivity.DAILY_FORECAST);
        mDailyWeathers = Arrays.copyOf(parcelables, parcelables.length, DailyWeather[].class);

        DayAdapter adapter = new DayAdapter(this, mDailyWeathers);
        mListView.setAdapter(adapter);
        mListView.setEmptyView(emptyTextView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String dayOfTheWeek = mDailyWeathers[i].getDayOfTheWeek();
                String conditions = mDailyWeathers[i].getSummary();
                String highTemp = mDailyWeathers[i].getTemperatureMax()+"";
                String message = String.format("On %s the high will be %s and it will be %s",
                        dayOfTheWeek, highTemp, conditions);
                Toast.makeText(DailyForecastActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }
}
