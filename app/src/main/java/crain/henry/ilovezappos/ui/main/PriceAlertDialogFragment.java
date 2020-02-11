package crain.henry.ilovezappos.ui.main;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import crain.henry.ilovezappos.R;

public class PriceAlertDialogFragment extends DialogFragment {

    @SuppressLint("InflateParams")
    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialog_title)
               .setView(inflater.inflate(R.layout.fragment_price_alert_dialog, null, false))
               .setPositiveButton(R.string.dialog_btn_set, (dialog, which) -> {
                   // TODO
                   dialog.cancel();
               })
               .setNegativeButton(R.string.dialog_btn_cancel, (dialog, which) -> {
                   // TODO
                   dialog.cancel();
               });
        return builder.create();
    }
}
