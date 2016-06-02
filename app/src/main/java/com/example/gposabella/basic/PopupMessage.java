package com.example.gposabella.basic;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class PopupMessage {
	public static void info(Context context, String text) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);
	
		alertDialogBuilder
		.setMessage(text)
		.setCancelable(true)
		.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {

			}
		  });/*
		.setNegativeButton("No",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
				// if this button is clicked, just close
				// the dialog box and do nothing
				dialog.cancel();
			}
		});*/

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();
}}
