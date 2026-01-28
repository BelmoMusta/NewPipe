package org.schabi.newpipe.local.history;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import org.schabi.newpipe.R;
import org.schabi.newpipe.databinding.DialogEditTextBinding;
import org.schabi.newpipe.util.ThemeHelper;

public class OpenURLDialog extends DialogFragment implements DialogInterface.OnClickListener {

    private final OpenDialogAction action;
    private DialogEditTextBinding dialogBinding;
    public OpenURLDialog(final OpenDialogAction action) {
        this.action = action;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable final Bundle savedInstanceState) {
        dialogBinding = DialogEditTextBinding.inflate(getLayoutInflater());

        dialogBinding.getRoot().getContext().setTheme(ThemeHelper.getDialogTheme(requireContext()));
        dialogBinding.dialogEditText.setHint("URL : ");
        dialogBinding.dialogEditText.setInputType(InputType.TYPE_CLASS_TEXT);

        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(requireContext(),
                ThemeHelper.getDialogTheme(requireContext()))
                .setTitle("Open URL")
                .setView(dialogBinding.getRoot())
                .setCancelable(true)
                .setNeutralButton(R.string.enqueue, this)
                .setNegativeButton(R.string.cancel, this)
                .setPositiveButton(R.string.open, this);
        return dialogBuilder.create();
    }
    @Override
    public void onClick(DialogInterface dialog, int which) {
        final Editable editTextText = dialogBinding.dialogEditText.getText();
        if (editTextText != null && !editTextText.toString().trim().isBlank()) {
            final String url = editTextText.toString();
            switch (which){
                case DialogInterface.BUTTON_POSITIVE -> action.open(url);
                case DialogInterface.BUTTON_NEUTRAL -> action.enqueue(url);
            }
        }
    }
}
