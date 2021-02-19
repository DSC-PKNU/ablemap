package com.example.testnavermap

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import kotlinx.android.synthetic.main.activity_nampo_info.*

class Nampo_info : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nampo_info)

        textOneSpan()
        textTwoSpan()
        textThreeSpan()
        textFourSpan()
        textFiveSpan()


    }

    private fun textOneSpan() {
        val spannableString = SpannableString("용두산 공원(부산타워)\t자가용 필요\t\n" +
                "화장실 : 0\n" +
                "주차장 : 0\n" +
                "휠체어 : 0\n" +
                "이동 : 0(경사로 있음)\n")
        val fColor1 = ForegroundColorSpan(Color.BLUE)
        val fColor2 = ForegroundColorSpan(Color.RED)

        spannableString.setSpan(fColor1,0,11, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(fColor2, 13, 19, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        tv_n1.text = spannableString

    }
    private fun textTwoSpan() {
        val spannableString = SpannableString("자갈치 시장\n" +
                "화장실 : 0\n" +
                "주차장 : 0\n" +
                "휠체어 : 0\n" +
                "이동 : 0\n")
        val fColor1 = ForegroundColorSpan(Color.BLUE)

        spannableString.setSpan(fColor1, 0, 6, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        tv_n2.text = spannableString
    }

    private fun textThreeSpan() {
        val spannableString = SpannableString("BIFF 광장\n" +
                "화장실 : X\n" +
                "주차장 : 0\n" +
                "휠체어 : 0\n" +
                "이동 : 0\n")
        val fColor1 = ForegroundColorSpan(Color.BLUE)

        spannableString.setSpan(fColor1, 0, 7, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        tv_n3.text = spannableString

    }
    private fun textFourSpan() {
        val spannableString = SpannableString("부평 깡통시장(국제시장)\n" +
                "화장실 : X\n" +
                "주차장 : 0\n" +
                "휠체어 : 0\n" +
                "이동 : 0\n")

        val fColor1 = ForegroundColorSpan(Color.BLUE)

        spannableString.setSpan(fColor1, 0, 13, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        tv_n4.text = spannableString
    }
    private fun textFiveSpan() {
        val spannableString = SpannableString("보수동 책방골목\n" +
                "화장실 : X\n" +
                "주차장 : 0\n" +
                "휠체어 : 0\n" +
                "이동 : △(경사로가 조금 높음)\n\n")

        val fColor1 = ForegroundColorSpan(Color.BLUE)

        spannableString.setSpan(fColor1,0,8, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        tv_n5.text = spannableString
    }
}