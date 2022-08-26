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
import com.example.todolist.databinding.DialogAddListItemBinding;

public class AddListItemDialog extends DialogFragment {
    private EditText etListItemDescription;

    private AddListItemOkButtonDialogListener okButtonListener;

    public static final String TAG = "AddListItemDialog";

    public interface AddListItemOkButtonDialogListener {
        void onDialogPositiveClick(String listItemDescription);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());

        DialogAddListItemBinding binding = DialogAddListItemBinding.inflate(LayoutInflater.from(getContext()));
        View rootView = binding.getRoot();

        etListItemDescription = binding.dialogAddListItemInput;

        builder.setView(rootView)
                .setPositiveButton(R.string.add_list_dialog_ok_button, this::onOkButtonClick)
                .setNegativeButton(R.string.cancel_dialog, (dialog, which) -> {});

        return builder.create();
    }

    public void setOkButtonListener(AddListItemOkButtonDialogListener okButtonListener) {
        this.okButtonListener = okButtonListener;
    }

    private void onOkButtonClick(DialogInterface dialog, int which) {
        String listItemDescription = etListItemDescription.getText().toString();

        if (!listItemDescription.isEmpty()) {
            okButtonListener.onDialogPositiveClick(listItemDescription);
        }
    }
}
