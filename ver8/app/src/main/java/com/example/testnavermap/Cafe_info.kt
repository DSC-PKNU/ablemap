package com.example.testnavermap

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_cafe_info.*

class Cafe_info : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cafe_info)

        textOneSpan()
        textTwoSpan()
        textThreeSpan()
    }
    private fun textOneSpan() {
        val spannableString = SpannableString("웨이브온 커피(Waveon Coffee)\n" +
                "화장실 : △(남자화장실이 2층에 있음)\n" +
                "주차장 : 0\n" +
                "휠체어 : 0 \n" +
                "이동 : 0\n")
        val fColor1 = ForegroundColorSpan(Color.BLUE)

        spannableString.setSpan(fColor1,0,22, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        tv_c0.text = spannableString

    }
    private fun textTwoSpan() {
        val spannableString = SpannableString("우즈베이커리(woo’z bakery)\n" +
                "화장실 : 0\n" +
                "주차장 : △(장애인 주차장은 따로 없음)\n" +
                "휠체어 : 0\n" +
                "이동 : 0\n")
        val fColor1 = ForegroundColorSpan(Color.BLUE)

        spannableString.setSpan(fColor1, 0, 20, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        tv_c1.text = spannableString
    }

    private fun textThreeSpan() {
        val spannableString = SpannableString("로와 맨션(Lowa Mansion)\n" +
                "화장실 : 0\n" +
                "주차장 : △(장애인 주차장은 따로 없음)\n" +
                "휠체어 : 0\n" +
                "이동 : 0\n")
        val fColor1 = ForegroundColorSpan(Color.BLUE)

        spannableString.setSpan(fColor1, 0, 19, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        tv_c2.text = spannableString
    }

}

