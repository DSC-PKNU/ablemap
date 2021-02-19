package com.example.testnavermap

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_nature_info.*

class Nature_info : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nature_info)

        textOneSpan()
        textTwoSpan()
        textThreeSpan()
        textFourSpan()
        textFiveSpan()
    }
    private fun textOneSpan() {
        val spannableString = SpannableString("구포 무장애 숲길\n" +
                "화장실 : 0\n" +
                "주차장 : 0\n" +
                "휠체어 : 0\n" +
                "이동 : 0(경사로 있음)\n")
        val fColor1 = ForegroundColorSpan(Color.BLUE)

        spannableString.setSpan(fColor1,0,9, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        tv_nt1.text = spannableString

    }
    private fun textTwoSpan() {
        val spannableString = SpannableString("부산 시민 공원\n" +
                "화장실 : 0\n" +
                "주차장 : 0\n" +
                "휠체어 : 0\n" +
                "이동 : 0\n")
        val fColor1 = ForegroundColorSpan(Color.BLUE)

        spannableString.setSpan(fColor1, 0, 8, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        tv_nt2.text = spannableString
    }

    private fun textThreeSpan() {
        val spannableString = SpannableString("을숙도\n" +
                "화장실 : 0\n" +
                "주차장 : 0\n" +
                "휠체어 : 0\n" +
                "이동 : 0(경사로 있음)\n")
        val fColor1 = ForegroundColorSpan(Color.BLUE)

        spannableString.setSpan(fColor1, 0, 3, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        tv_nt3.text = spannableString
    }
    private fun textFourSpan() {
        val spannableString = SpannableString("아미산 전망대\n" +
                "화장실 : 0\n" +
                "주차장 : 0\n" +
                "휠체어 : 0\n" +
                "이동 : 0(경사로 있음)\n")
        val fColor1 = ForegroundColorSpan(Color.BLUE)

        spannableString.setSpan(fColor1, 0, 7, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        tv_nt4.text = spannableString
    }
    private fun textFiveSpan() {
        val spannableString = SpannableString("다대포 해수욕장\n" +
                "화장실 : 0\n" +
                "주차장 : 0\n" +
                "휠체어 : 0\n" +
                "이동 : 0(경사로 있음)\n")
        val fColor1 = ForegroundColorSpan(Color.BLUE)

        spannableString.setSpan(fColor1, 0, 8, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        tv_nt5.text = spannableString
    }
}