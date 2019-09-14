package com.realpage.jj_kotlin_mars_rover

import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.AdapterView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.realpage.jj_kotlin_mars_rover.adapters.ImageListAdapter
import com.realpage.jj_kotlin_mars_rover.data.Photo
import com.realpage.jj_kotlin_mars_rover.viewmodels.CuriosityViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class ImageGridViewFragment : Fragment(){

    private  var curiosityViewModel: CuriosityViewModel = CuriosityViewModel()
    private var photoList: MutableList<Photo> = arrayListOf()
    private lateinit var photoRecyclerView: RecyclerView
    private lateinit var imageListAdapter: ImageListAdapter
    private lateinit var messageText: TextView
    private lateinit var roverSpinner: Spinner
    private lateinit var cameraSpinner: Spinner
    private lateinit var cameraHashMap: HashMap<String, String>
    private var selectedRover: String? = null
    private lateinit var solEditTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.menuSearch -> {
                showSearchDialog()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        photoList.clear()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.gridview_main, container, false)

        loadCameraHashMap()
        messageText = rootView.findViewById(R.id.defaultMessage)
        curiosityViewModel = ViewModelProviders.of(this).get(curiosityViewModel::class.java)
        curiosityViewModel.fetchCuriosityPhotos()
        curiosityViewModel.curiosityPhotosLiveData.observe(this, Observer { it ->

            if(!it.isNullOrEmpty()){
                photoList.clear()
                photoList.addAll(it)
                messageText.visibility = View.GONE
                val sizeWidth = (this.resources.displayMetrics.widthPixels / 2) - 16
                photoRecyclerView = rootView.findViewById(R.id.photosRecyclerView)
                photoRecyclerView.layoutManager = GridLayoutManager(context, 2)

                imageListAdapter = ImageListAdapter(photoList, context, sizeWidth)
                photoRecyclerView.adapter = imageListAdapter
                imageListAdapter.notifyDataSetChanged()

            }else{
                //NO DATA RETURNED
                messageText.visibility = View.VISIBLE
                messageText.text = "No images found"
                photoList.clear()
                imageListAdapter.notifyDataSetChanged()
//                curiosityViewModel.cancelAllRequests()
            }

        })


        return rootView
    }

    fun showSearchDialog(){
       if(context != null) {

           val inflater = requireActivity().layoutInflater
           val rootView = inflater.inflate(R.layout.dialog_search, null)
           roverSpinner = rootView.findViewById(R.id.roverSpinner)
           setupRoverSpinner()
           solEditTextView = rootView.findViewById(R.id.solEditText)
//           cameraSpinner = rootView.findViewById(R.id.cameraSpinner)
//           setupCameraSpinner()


           val builder = AlertDialog.Builder(context!!)
           builder.setView(rootView)

           builder.setTitle(R.string.search_dialog_message)
               .setPositiveButton(R.string.search,
                   DialogInterface.OnClickListener { dialog, id ->
                       // Send the positive button event back to the host activity
                       getImageSearch()

                   })
               .setNegativeButton(R.string.cancel,
                   DialogInterface.OnClickListener { dialog, id ->
                       dialog.dismiss()

                   })

           builder.create()
           builder.show()
       }

    }

    private fun setupRoverSpinner(){
        val rovers = ArrayList<String>()
        rovers.add("Curiosity")
        rovers.add("Opportunity")
        rovers.add("Spirit")
        val spinnerAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, rovers)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        roverSpinner.adapter = spinnerAdapter
        roverSpinner.onItemSelectedListener = roverItemSelected
    }

    private fun setupCameraSpinner(){
        val cameras = ArrayList<String>()

        for(value in cameraHashMap.values){
            cameras.add(value)
        }

        val spinnerAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, cameras)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        cameraSpinner.adapter = spinnerAdapter

    }

    private val  roverItemSelected = object:AdapterView.OnItemSelectedListener{
        override fun onItemSelected(parent:AdapterView<*>, view: View, position: Int, id: Long){
            selectedRover = parent.getItemAtPosition(position).toString()

        }

        override fun onNothingSelected(parent: AdapterView<*>){
            // Another interface callback
        }

    }

    private fun loadCameraHashMap(){
        cameraHashMap = HashMap<String, String>()
        cameraHashMap.put("FHAZ", "Front Hazard Avoidance Camera") // -- C, O, S
        cameraHashMap.put("RHAZ", "Rear Hazard Avoidance Camera")  //-- C, O, S
        cameraHashMap.put("MAST", "Mast Camera")  //-- C
        cameraHashMap.put("CHEMCAM", "Chemistry and Camera Complex") //-- C
        cameraHashMap.put("MAHLI", "Mars Hand Lens Imager") //-- C
        cameraHashMap.put("MARDI", "Mars Descent Imager") //-- C
        cameraHashMap.put("NAVCAM", "Navigation Camera") //-- C, O, S
        cameraHashMap.put("PANCAM", "Panoramic Camera") //-- O, S
        cameraHashMap.put("MINITES", "Miniature Thermal Emission Spectrometer (Mini-TES)") //-- O, S
    }


    private fun getImageSearch() {
        var selectedSol = 1
        var solText = solEditTextView.text.toString()
        if (!solText.isNullOrEmpty()) {
            selectedSol = solText.toInt()
        }

        if (selectedRover != null) {
            curiosityViewModel.fetchImagesByRover(selectedRover!!, selectedSol)
        }
    }

    //    NOTES:
    //        rovers = curiosity, opportunity, spirit
//        cameras:
//        FHAZ	Front Hazard Avoidance Camera -- C, O, S
//        RHAZ	Rear Hazard Avoidance Camera -- C, O, S
//        MAST	Mast Camera -- C
//        CHEMCAM	Chemistry and Camera Complex -- C
//        MAHLI	Mars Hand Lens Imager -- C
//        MARDI	Mars Descent Imager -- C
//        NAVCAM	Navigation Camera -- C, O, S
//        PANCAM	Panoramic Camera -- O, S
//        MINITES	Miniature Thermal Emission Spectrometer (Mini-TES) -- O, S

//        earth_date	YYYY-MM-DD

//        sol	int



}