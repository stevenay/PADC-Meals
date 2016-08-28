package net.naylinaung.meal_ordering.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import net.naylinaung.meal_ordering.R;

/**
 * Created by Dell on 8/28/2016.
 */
public class ShowOptionsDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Are you on fire?")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

        // Create the AlertDialog object and return it
        AlertDialog dialog = builder.create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams wmlp = new WindowManager.LayoutParams();

        Window window = dialog.getWindow();
        wmlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wmlp.gravity = Gravity.BOTTOM | Gravity.CENTER;
        wmlp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        window.setAttributes(wmlp);
        return dialog;
    }
}