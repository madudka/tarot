package com.madudka.tarot.utils

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.madudka.tarot.R

fun showDialogInfo(context: Context): AlertDialog =
        MaterialAlertDialogBuilder(context, R.style.Custom_AlertDialog)
            .setTitle(context.getString(R.string.title_limit))
            .setMessage(context.getString(R.string.day_limit))
            .setIcon(R.drawable.ic_dialog_eye_triangle)
            .setPositiveButton(context.getString(R.string.ok)) { _,_ -> }
            .show()

fun showSignificatorInfo(context: Context): AlertDialog {
    val view = LayoutInflater.from(context).inflate(R.layout.dialog_significator_info, null)

    return MaterialAlertDialogBuilder(context, R.style.Custom_AlertDialog)
        .setTitle(context.getString(R.string.significator))
        .setView(view)
        .setIcon(R.drawable.ic_dialog_eye_triangle)
        .setPositiveButton(context.getString(R.string.ok)) { _,_ -> }
        .show()
}

fun showInternetConnectionDialog(context: Context): AlertDialog =
    MaterialAlertDialogBuilder(context, R.style.Custom_AlertDialog)
        .setTitle(context.getString(R.string.connection))
        .setMessage(context.getString(R.string.connect))
        .setIcon(R.drawable.ic_dialog_eye_triangle)
        .setPositiveButton(context.getString(R.string.ok)) { _,_ -> }
        .show()

fun showCleanCacheDialog(context: Context): AlertDialog =
    MaterialAlertDialogBuilder(context, R.style.Custom_AlertDialog)
        .setTitle(context.getString(R.string.clean_cache))
        .setMessage(context.getString(R.string.clean_success))
        .setIcon(R.drawable.ic_dialog_eye_triangle)
        .setPositiveButton(context.getString(R.string.ok)) { _,_ -> }
        .show()