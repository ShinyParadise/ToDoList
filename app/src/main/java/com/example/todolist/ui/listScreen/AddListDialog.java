package com.example.todolist.ui.listScreen;

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

public class AddListDialog extends DialogFragment {
    private EditText etListName, etListDescription;
    private String listName, listDescription;

    private AddListDialogListener listener;

    public static final String TAG = "AddListDialog";

    public interface AddListDialogListener {
        void onDialogPositiveClick(String listName, String listDescription);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_list, null);
        etListName = view.findViewById(R.id.dialog_add_list_input_name);
        etListDescription = view.findViewById(R.id.dialog_add_list_input_description);

        builder.setView(view).setPositiveButton(R.string.add_list_dialog_ok_button, this::onOkButtonClick);

        return builder.create();
    }

    public void setListener(AddListDialogListener listener) {
        this.listener = listener;
    }

    private void onOkButtonClick(DialogInterface dialog, int which) {
        listName = etListName.getText().toString();
        listDescription = etListDescription.getText().toString();

        if (!listName.isEmpty() && !listDescription.isEmpty()) {
            listener.onDialogPositiveClick(listName, listDescription);
        }
    }
}
