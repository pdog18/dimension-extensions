package com.pdog.dimension.sample

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pdog.dimension.dp
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.decorView.post {


            text_content.run {
                setPadding(8.dp, 8.dp, 8.dp, 8.dp)

                val density = this.resources.displayMetrics.density

                val height = (window.decorView.height / density).roundToInt()
                val width = (window.decorView.width / density).roundToInt()

                text = "decorView : { h : ${height}dp, w : ${width}dp }"
            }

            text_center.run {
                height = 66.dp
                width = 180.dp
            }
        }
    }
}
