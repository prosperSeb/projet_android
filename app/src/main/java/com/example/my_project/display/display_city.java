package com.example.my_project.display;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my_project.R;
import com.example.my_project.meteo.Main;
import com.example.my_project.meteo.Meteo;
import com.example.my_project.meteo.Weather;
import com.example.my_project.test_retrofit.API;
import com.example.my_project.ui.dashboard.DashboardFragment;
import com.squareup.picasso.Picasso;

import java.util.TimeZone;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class display_city extends AppCompatActivity {


    private TextView Name;
    private TextView country;
    private TextView temp;
    private TextView pressure;
    private TextView humi;
    private TextView sea;
    private TextView ground;
    private TextView sunrise;
    private TextView wind;
    private TextView sunset;

    private ImageView icon_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_city);

        Intent intent = getIntent();
        String message = intent.getStringExtra(DashboardFragment.EXTRA_MESSAGE);
        Name = findViewById(R.id.Name_home);
        country =findViewById(R.id.Country_home);
        temp = findViewById(R.id.temperature_hom);
        pressure = findViewById(R.id.pressure_home);
        humi = findViewById(R.id.humidity_home);
        sea=findViewById(R.id.sea_level_home);
        ground = findViewById(R.id.ground_level_home);
        sunrise=findViewById(R.id.Sunrise_home);
        sunset=findViewById(R.id.sunset_home);
        wind=findViewById(R.id.Wind_home);
        icon_image = findViewById(R.id.icon_home);

        if(message.contains(","))
        {
            String[] coord =message.split(",");
            getMeteoCoord(Double.parseDouble(coord[1]),Double.parseDouble(coord[0]));
        }
        else
        {
            getMeteo(message);
        }

    }

    private void getMeteo(String city) {
        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(display_city.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please Wait");
        progressDialog.show();
        API.getClient().getMeteo(city,"16c47e600889840bba122e2ef1091021",new Callback<Meteo>() {
            @Override
            public void success(Meteo meteo, Response response) {
                // in this method we will get the response from API
                progressDialog.dismiss(); //dismiss progress dialog
                if (meteo == null) {
                    Name.setText("null");
                } else {



                    long unixTime = meteo.getSys().getSunrise();
                    unixTime = 1000*unixTime;
                    java.util.Date GMTTime=new java.util.Date(unixTime);
                    long unixTime2 = meteo.getSys().getSunset();
                    unixTime2 = 1000*unixTime2;
                    java.util.Date GMTTime2=new java.util.Date(unixTime2);

                    //GMTTime.setTime(GMTTime.getTime() + meteo.getTimezone()*1000);


                    Main main = meteo.getMain();
                    String weather = meteo.getWeather().get(0).getDescription();
                    Weather test_meteo = meteo.getWeather().get(0);
                    String url ="http://openweathermap.org/img/wn/"+test_meteo.getIcon()+"@2x.png";

                    Name.setText("Ville : "+ meteo.getName());
                    country.setText("id pays : "+meteo.getSys().getCountry());
                    humi.setText("Humidity :"+ main.getHumidity().toString());
                    temp.setText("temperature : "+ main.getTemp().toString());
                    pressure.setText("pressure : " + main.getPressure().toString());
                    wind.setText("wind : "+ meteo.getWind().getSpeed().toString());
                    sunrise.setText("Sunrise : "+GMTTime.toString());
                    sunset.setText("Sunset : "+GMTTime2.toString());

                    test_image(url);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(display_city.this, error.toString(), Toast.LENGTH_LONG).show();
                Name.setText("error");
                progressDialog.dismiss(); //dismiss progress dialog

            }
        });
    }

    private void getMeteoCoord(double lat,double lon) {
        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(display_city.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please Wait");
        progressDialog.show();
        API.getClient().getMeteogeo(lat,lon,"16c47e600889840bba122e2ef1091021",new Callback<Meteo>() {
            @Override
            public void success(Meteo meteo, Response response) {
                // in this method we will get the response from API
                progressDialog.dismiss(); //dismiss progress dialog
                if (meteo == null) {
                    Name.setText("null");
                } else {



                    long unixTime = meteo.getSys().getSunrise();
                    unixTime = 1000*unixTime;
                    java.util.Date GMTTime=new java.util.Date(unixTime);
                    long unixTime2 = meteo.getSys().getSunset();
                    unixTime2 = 1000*unixTime2;
                    java.util.Date GMTTime2=new java.util.Date(unixTime2);

                    //GMTTime.setTime(GMTTime.getTime() + meteo.getTimezone()*1000);


                    Main main = meteo.getMain();
                    String weather = meteo.getWeather().get(0).getDescription();
                    Weather test_meteo = meteo.getWeather().get(0);
                    String url ="http://openweathermap.org/img/wn/"+test_meteo.getIcon()+"@2x.png";

                    Name.setText("Ville : "+ meteo.getName());
                    country.setText("id pays : "+meteo.getSys().getCountry());
                    humi.setText("Humidity :"+ main.getHumidity().toString());
                    temp.setText("temperature : "+ main.getTemp().toString());
                    pressure.setText("pressure : " + main.getPressure().toString());
                    wind.setText("wind : "+ meteo.getWind().getSpeed().toString());
                    sunrise.setText("Sunrise : "+GMTTime.toString());
                    sunset.setText("Sunset : "+GMTTime2.toString());

                    test_image(url);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(display_city.this, error.toString(), Toast.LENGTH_LONG).show();
                Name.setText("error");
                progressDialog.dismiss(); //dismiss progress dialog

            }
        });
    }

    private void test_image(String url_image){

        Picasso.get().load(url_image)
                .resize(250,250)
                .into(icon_image);
    }
}
