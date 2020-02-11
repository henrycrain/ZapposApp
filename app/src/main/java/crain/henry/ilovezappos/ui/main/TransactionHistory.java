package crain.henry.ilovezappos.ui.main;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;

import crain.henry.ilovezappos.R;

public class TransactionHistory extends Fragment {

    private TransactionHistoryViewModel viewModel;
    private LineChart chart;

    public static TransactionHistory newInstance() {
        return new TransactionHistory();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_transaction_history, container, false);
        viewModel = ViewModelProviders.of(this).get(TransactionHistoryViewModel.class);

        chart = root.findViewById(R.id.chart);
        viewModel.init(chart);

        viewModel.getTransactions();

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
