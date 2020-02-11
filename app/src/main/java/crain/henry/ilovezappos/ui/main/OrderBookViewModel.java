package crain.henry.ilovezappos.ui.main;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import crain.henry.ilovezappos.BitstampService;
import crain.henry.ilovezappos.OrderBookDict;
import crain.henry.ilovezappos.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrderBookViewModel extends ViewModel {

    private BitstampService service;
    private RecyclerView bidsView;
    private RecyclerView asksView;

    public void init(RecyclerView bidsView, RecyclerView asksView) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.bitstamp.net/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(BitstampService.class);
        this.bidsView = bidsView;
        this.asksView = asksView;
    }

    public void getOrderBook() {
        Call<OrderBookDict> call = service.getOrderBook();
        call.enqueue(new Callback<OrderBookDict>() {
            @Override
            public void onResponse(Call<OrderBookDict> call, Response<OrderBookDict> response) {
                OrderBookDict dict = response.body();
                List<List<Float>> bids = dict.bids;
                List<List<Float>> asks = dict.asks;

                OrderBook.Adapter bidsAdapter = (OrderBook.Adapter) bidsView.getAdapter();
                OrderBook.Adapter asksAdapter = (OrderBook.Adapter) asksView.getAdapter();

                bidsAdapter.setOrders(bids);
                asksAdapter.setOrders(asks);
                bidsAdapter.notifyDataSetChanged();
                asksAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<OrderBookDict> call, Throwable t) {
                t.printStackTrace();  // TODO fix this
            }
        });
    }
}
