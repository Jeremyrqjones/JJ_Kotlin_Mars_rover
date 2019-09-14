package com.realpage.jj_kotlin_mars_rover

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.SpinnerAdapter
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment



class SearchDialogFragment : DialogFragment() {

    private lateinit var roverSpinner: Spinner
    var roverSelection: String? = "spirit"

    // Use this instance of the interface to deliver action events
    internal lateinit var listener: NoticeDialogListener

    /* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    interface NoticeDialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = context as NoticeDialogListener
        } catch (e: ClassCastException) {
            // The activity doesn't implement the interface, throw exception
            throw ClassCastException((context.toString() +
                    " must implement NoticeDialogListener"))
        }
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        return super.onCreateDialog(savedInstanceState)
        return activity?.let {
                        // Build the dialog and set up the button click handlers

            val inflater = requireActivity().layoutInflater
            val rootView = inflater.inflate(R.layout.dialog_search, null)
            roverSpinner = rootView.findViewById(R.id.roverSpinner)
            val rovers = ArrayList<String>()
            rovers.add("Curiosity")
            rovers.add("Opportunity")
            rovers.add("Spirit")
            val spinnerAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, rovers)
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            roverSpinner.adapter = spinnerAdapter

            val builder = AlertDialog.Builder(it)
            builder.setView(rootView)

            builder.setTitle(R.string.search_dialog_message)
                .setPositiveButton(R.string.search,
                    DialogInterface.OnClickListener { dialog, id ->
                        // Send the positive button event back to the host activity
                        listener.onDialogPositiveClick(this)
                    })
                .setNegativeButton(R.string.cancel,
                    DialogInterface.OnClickListener { dialog, id ->
                        // Send the negative button event back to the host activity
                        listener.onDialogNegativeClick(this)
                    })

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")

    }





}