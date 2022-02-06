package com.example.disneyproject.ui.homedetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.disneyproject.databinding.HomeDetailLayoutBinding
import timber.log.Timber


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class HomeDetailFragment : Fragment() {
    val args: HomeDetailFragmentArgs by navArgs()
    private var _binding: HomeDetailLayoutBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    /**
     * On create view
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = HomeDetailLayoutBinding.inflate(inflater, container, false)
        return binding.root

    }

    /**
     * On view created
     *
     * @param view
     * @param savedInstanceState
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         val imageUrl = args.imageUrl
         val descriptionText = args.desriptionText
         val titleString = args.titleString
         val readOnlineLink = args.readOnlineLink

        Glide.with(binding.detailsImageView.context)
            .load(imageUrl)
            .into(binding.detailsImageView)

        binding.titleTv.text = titleString
        binding.descriptionTv.text = descriptionText

        binding.readNowBtn.setOnClickListener {
            openUrl(readOnlineLink!!)
        }


    }

    /**
     * Open url
     *
     * @param url
     */
    private fun openUrl(url:String){
        val uri = Uri.parse(url) // missing 'http://' will cause crashed

        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    /**
     * On destroy view
     *
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}