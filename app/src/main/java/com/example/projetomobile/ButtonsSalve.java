package com.example.projetomobile;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class ButtonsSalve  {
    public interface OnDismissListener {
        void onDismiss(boolean validacao);
    }

    public static void showAlertDialog(Context context, String title, String message, final OnDismissListener onDismissListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(onDismissListener != null) {
                    onDismissListener.onDismiss(true);
                }
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (onDismissListener != null) {
                    onDismissListener.onDismiss(false); // Retorno negativo
                }
                dialog.dismiss();
            }
        });

        final AlertDialog dialog = builder.create();

        if (onDismissListener != null) {
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
//                    onDismissListener.onDismiss();
                }
            });
        }

        dialog.show();
    }
}
