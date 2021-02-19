package com.example.testnavermap

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_museum_info.*

class Museum_info : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_museum_info)

        textOneSpan()
        textTwoSpan()
        textThreeSpan()
    }
    private fun textOneSpan() {
        val spannableString = SpannableString("국립해양박물관\n" +
                "화장실 : 0\n" +
                "주차장 : 0\n" +
                "휠체어 : 0\n" +
                "이동 : 0(경사로 있음)\n")
        val fColor1 = ForegroundColorSpan(Color.BLUE)

        spannableString.setSpan(fColor1,0,7, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        tv_m1.text = spannableString

    }
    private fun textTwoSpan() {
        val spannableString = SpannableString("태종대\n" +
                "화장실 : 0\n" +
                "주차장 : 0\n" +
                "휠체어 : 0\n" +
                "이동 : 0(경사로 있음)\n")
        val fColor1 = ForegroundColorSpan(Color.BLUE)

        spannableString.setSpan(fColor1, 0, 3, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        tv_m2.text = spannableString
    }

    private fun textThreeSpan() {
        val spannableString = SpannableString("부산영화체험박물관\n" +
                "화장실 : 0\n" +
                "주차장 : 0\n" +
                "휠체어 : 0\n" +
                "이동 : 0\n")
        val fColor1 = ForegroundColorSpan(Color.BLUE)

        spannableString.setSpan(fColor1, 0, 9, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        tv_m3.text = spannableString

    }
}