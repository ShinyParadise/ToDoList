package com.example.todolist.ui.listscreen;

import android.app.Dialog;
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

    private ListViewModel listViewModel;

    AddListDialogListener listener;

    public static final String TAG = "AddListDialog";

    public interface AddListDialogListener {
        void onDialogPositiveClick(DialogFragment dialog);
        void onDialogNegativeClick(DialogFragment dialog);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        listViewModel = new ListViewModel(requireActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_list, null);
        etListName = view.findViewById(R.id.input_list_name);
        etListDescription = view.findViewById(R.id.input_list_description);

        builder.setView(view)
                .setPositiveButton(R.string.addListDialogOkButton, (dialog, which) -> {
                    listName = etListName.getText().toString();
                    listDescription = etListDescription.getText().toString();

                    if (!listName.isEmpty() && !listDescription.isEmpty()) {
                        listViewModel.insertNewList(listName, listDescription);
                        listener.onDialogPositiveClick(AddListDialog.this);
                    }
                } )
                .setNegativeButton(R.string.cancelDialog, (dialog, which) -> {
                    // TODO: save what user have typed
                    listener.onDialogNegativeClick(AddListDialog.this);
                });

        return builder.create();
    }
}
