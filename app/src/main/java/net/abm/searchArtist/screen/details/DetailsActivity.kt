package net.abm.searchArtist.screen.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*
import net.abm.searchArtist.R

class DetailsActivity : AppCompatActivity(){

    companion object {
        const val EXTRA_TITLE = "EXTRA_TITLE"
        const val EXTRA_SUB_TITLE = "EXTRA_SUB_TITLE"
        const val EXTRA_LINK = "EXTRA_LINK"

    }

    private var title = ""
    private var subTitle = ""
    private var link = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail)


        getInfo()

        initUI()
    }


    private fun getInfo() {
        if (intent != null){
            title = intent.getStringExtra(EXTRA_TITLE)
            subTitle = intent.getStringExtra(EXTRA_SUB_TITLE)
            link = intent.getStringExtra(EXTRA_LINK)



            if (!link.isNullOrBlank()){
                detailsUrl.visibility = View.VISIBLE
                detailsUrl.setOnClickListener {
                    launchUrl(link)
                }
            }
        }
    }



    private fun initUI() {
        detailsTitle.text = title
        detailsSub.text = subTitle

    }


    private fun launchUrl(link: String?) {

        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(link)
        startActivity(intent)

    }
}