package crain.henry.ilovezappos.ui.main;

import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import crain.henry.ilovezappos.R;

public class OrderBook extends Fragment {

    private OrderBookViewModel viewModel;
    private RecyclerView bidsView;
    private RecyclerView asksView;

    public static OrderBook newInstance() {
        return new OrderBook();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_order_book, container, false);
        viewModel = ViewModelProviders.of(this).get(OrderBookViewModel.class);

        bidsView = root.findViewById(R.id.bids_view);
        asksView = root.findViewById(R.id.asks_view);
        bidsView.setLayoutManager(new LinearLayoutManager(getContext()));
        asksView.setLayoutManager(new LinearLayoutManager(getContext()));
        bidsView.setAdapter(new Adapter());
        asksView.setAdapter(new Adapter());

        viewModel.init(bidsView, asksView);
        viewModel.getOrderBook();

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public static class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

        private List<List<Float>> orders = new ArrayList<>();

        @Override
        @NonNull
        public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
            View view = LayoutInflater.from(parent.getContext())
                                      .inflate(R.layout.vh_order, parent, false);
            return new ViewHolder(view);
        }

        @SuppressLint("DefaultLocale")
        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.priceView.setText(String.format("%f", orders.get(position).get(0)));
            holder.amtView.setText(String.format("%f", orders.get(position).get(1)));
        }

        @Override
        public int getItemCount() {
            return orders.size();
        }

        public void setOrders(List<List<Float>> orders) {
            this.orders = orders;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public TextView priceView;
            public TextView amtView;

            public ViewHolder(View view) {
                super(view);
                this.priceView = view.findViewById(R.id.price_view);
                this.amtView = view.findViewById(R.id.amt_view);
            }
        }
    }

}
