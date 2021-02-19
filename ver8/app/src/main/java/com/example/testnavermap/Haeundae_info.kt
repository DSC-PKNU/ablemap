package com.example.testnavermap

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import kotlinx.android.synthetic.main.activity_haeundae_info.*

class Haeundae_info : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_haeundae_info)

        texthOneSpan()
        texthTwoSpan()
        texthThreeSpan()
        texthFourSpan()
        texthFiveSpan()

    }


    private fun texthOneSpan() {
        val spannableString = SpannableString("해운대 해수욕장\n" +
                "화장실 : 0\n" +
                "주차장 : 0\n" +
                "휠체어 : 0\n" +
                "이동 : 0\n")
        val fColor1 = ForegroundColorSpan(Color.BLUE)

        spannableString.setSpan(fColor1,0,8, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        tv_h1.text = spannableString

    }
    private fun texthTwoSpan() {
        val spannableString = SpannableString("영화의 전당\n" +
                "화장실 : 0\n" +
                "주차장 : 0\n" +
                "휠체어 : 0\n" +
                "이동 : 0\n ")
        val fColor1 = ForegroundColorSpan(Color.BLUE)

        spannableString.setSpan(fColor1, 0, 6, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        tv_h2.text = spannableString
    }

    private fun texthThreeSpan() {
        val spannableString = SpannableString("미포, 청사포 \n" +
                "화장실 : X\n" +
                "주차장 : 0\n" +
                "휠체어 : 0\n" +
                "이동 : △(경사로가 조금 높음) \n")
        val fColor1 = ForegroundColorSpan(Color.BLUE)

        spannableString.setSpan(fColor1, 0, 7, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        tv_h3.text = spannableString

    }
    private fun texthFourSpan() {
        val spannableString = SpannableString("더베이 101\n" +
                "화장실 : 0\n" +
                "주차장 : 0\n" +
                "휠체어 : 0\n" +
                "이동 : 0\n\n")

        val fColor1 = ForegroundColorSpan(Color.BLUE)

        spannableString.setSpan(fColor1, 0, 7, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        tv_h4.text = spannableString
    }
    private fun texthFiveSpan() {
        val spannableString = SpannableString("광안리 해수욕장\n" +
                "화장실 : 0\n" +
                "주차장 : 0\n" +
                "휠체어 : 0\n" +
                "이동 : 0\n")

        val fColor1 = ForegroundColorSpan(Color.BLUE)

        spannableString.setSpan(fColor1,0,8, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        tv_h5.text = spannableString
    }


}