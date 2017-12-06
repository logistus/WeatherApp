package logistus.net.weatherapp.dialog_fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;

import logistus.net.weatherapp.R;

public class ConnectionErrorDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context context = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setMessage(R.string.error_message)
                .setPositiveButton(R.string.error_positive_button, null);
        AlertDialog dialog = builder.create();
        return dialog;
    }
}
