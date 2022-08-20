package com.example.todolist.ui.detailScreen;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.todolist.R;

public class AddListItemDialog extends DialogFragment {
    private EditText etListItemDescription;
    private String listItemDescription;

    private AddListItemDialogListener listener;

    public static final String TAG = "AddListItemDialog";

    public interface AddListItemDialogListener {
        void onDialogPositiveClick(String listItemDescription);
        void onDialogNegativeClick();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_list_item, null);
        etListItemDescription = view.findViewById(R.id.dialog_add_list_item_input);

        builder.setView(view)
                .setPositiveButton(R.string.add_list_dialog_ok_button, this::onOkButtonClick)
                .setNegativeButton(R.string.cancel_dialog, this::onCancelDialogClick);

        return builder.create();
    }

    public void setListener(AddListItemDialogListener listener) {
        this.listener = listener;
    }

    private void onOkButtonClick(DialogInterface dialog, int which) {
        listItemDescription = etListItemDescription.getText().toString();

        if (!listItemDescription.isEmpty()) {
            listener.onDialogPositiveClick(listItemDescription);
        }
    }

    private void onCancelDialogClick(DialogInterface dialog, int which) {
        // TODO: save what user have typed
        listener.onDialogNegativeClick();
    }
}
