package crain.henry.ilovezappos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BitstampService {

    @GET("transactions/btcusd")
    public Call<List<Transaction>> getTransactions();

    @GET("order_book/btcusd")
    public Call<OrderBookDict> getOrderBook();
}
