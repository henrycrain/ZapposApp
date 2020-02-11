package crain.henry.ilovezappos.ui.main;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import crain.henry.ilovezappos.BitstampService;
import crain.henry.ilovezappos.R;
import crain.henry.ilovezappos.Transaction;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TransactionHistoryViewModel extends ViewModel {

    private BitstampService service;
    private LineChart chart;

    public void init(LineChart chart) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.bitstamp.net/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(BitstampService.class);
        this.chart = chart;
    }

    public void getTransactions() {
        Call<List<Transaction>> call = service.getTransactions();
        call.enqueue(new Callback<List<Transaction>>() {
            @Override
            public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {
                List<Transaction> transactions = response.body();
                List<Entry> entries = new ArrayList<>();

                for (Transaction trans: transactions) {
                    entries.add(new Entry(trans.date, trans.price));
                }
                LineDataSet dataSet = new LineDataSet(entries, "Price");
                LineData data = new LineData(dataSet);
                chart.setData(data);
                chart.invalidate();
            }

            @Override
            public void onFailure(Call<List<Transaction>> call, Throwable t) {
                t.printStackTrace();  // TODO fix this
            }
        });
    }
}
