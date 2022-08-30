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
import com.example.todolist.databinding.DialogAddListBinding;

public class AddListDialog extends DialogFragment {
    private EditText etListName, etListDescription;

    private AddListDialogOkButtonListener okButtonListener;

    public static final String TAG = "AddListDialog";

    public interface AddListDialogOkButtonListener {
        void onDialogPositiveClick(String listName, String listDescription);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());

        DialogAddListBinding binding = DialogAddListBinding.inflate(LayoutInflater.from(getContext()));
        View rootView = binding.getRoot();

        etListName = binding.dialogAddListInputName;
        etListDescription = binding.dialogAddListInputDescription;

        builder.setView(rootView)
                .setPositiveButton(R.string.add_list_dialog_ok_button, this::onOkButtonClick)
                .setNegativeButton(R.string.cancel_dialog, (dialog, which) -> {});

        return builder.create();
    }

    public void setOkButtonListener(AddListDialogOkButtonListener okButtonListener) {
        this.okButtonListener = okButtonListener;
    }

    private void onOkButtonClick(DialogInterface dialogInterface, int which) {
        String listName = etListName.getText().toString();
        String listDescription = etListDescription.getText().toString();

        if (!listName.isEmpty() && !listDescription.isEmpty()) {
            okButtonListener.onDialogPositiveClick(listName, listDescription);
        }
    }
}
