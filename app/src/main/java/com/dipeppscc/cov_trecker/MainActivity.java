package com.dipeppscc.cov_trecker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.dipeppscc.cov_trecker.api.ApiUtilities;
import com.dipeppscc.cov_trecker.api.countrydata;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView totalconfirm,totalactive,totalrecovered, totaldeaths,totaltests;
    private TextView todayconfirm,todayrecovered,todaydeaths;
    private List<countrydata> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();

        init();

        ApiUtilities.getapiinterface().getcountrydata()
                .enqueue(new Callback<List<countrydata>>() {
                    @Override
                    public void onResponse(Call<List<countrydata>> call, Response<List<countrydata>> response) {
                        list.addAll(response.body());

                        for(int i=0;i<list.size();i++)
                        {
                            if(list.get(i).getCountry().equals("India")){
                                int confirm = Integer.parseInt(list.get(i).getCases());
                                int active = Integer.parseInt(list.get(i).getActive());
                                int recovered = Integer.parseInt(list.get(i).getRecovered());
                                int death = Integer.parseInt(list.get(i).getDeaths());

                                totalactive.setText(NumberFormat.getInstance().format(active));
                                totalconfirm.setText(NumberFormat.getInstance().format(confirm));
                                totalrecovered.setText(NumberFormat.getInstance().format(recovered));
                                totaldeaths.setText(NumberFormat.getInstance().format(death));

                                todaydeaths.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTodayDeaths())));
                                todayconfirm.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTodayCases())));
                                todayrecovered.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTodayRecovered())));
                                totaltests.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTests())));

                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<List<countrydata>> call, Throwable t) {
                        Toast.makeText(MainActivity.this,"Error :" +t.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });

    }
    private void init()
    {
        totalconfirm = findViewById(R.id.totalconfirm);
        totalactive= findViewById(R.id.totalactive);
        totalrecovered = findViewById(R.id.totalrecovered);
        totaldeaths = findViewById(R.id.totaldeaths);
        todayconfirm = findViewById(R.id.todayconfirm);
        totaltests = findViewById(R.id.totaltests);
        todayrecovered = findViewById(R.id.todayrecovered);
        todaydeaths = findViewById(R.id.todaydeaths);



    }

}