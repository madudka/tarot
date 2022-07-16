package com.madudka.tarot.view

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.madudka.tarot.R

fun showDialogInfo(context: Context): AlertDialog =
        MaterialAlertDialogBuilder(context, R.style.Custom_AlertDialog)
            .setTitle(context.getString(R.string.title_limit))// context.getString(R.string.title_limit))
            .setMessage(context.getString(R.string.day_limit))
            .setIcon(R.drawable.ic_dialog_eye_triangle)
            .setPositiveButton(context.getString(R.string.ok)) { _,_ -> }
            .show()
