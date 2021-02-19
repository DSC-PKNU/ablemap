package com.example.testnavermap;

import android.content.Intent;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DetailView extends AppCompatActivity {

    private TextView name;
    private TextView address;
    private ImageView imageView;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView imageView5;
    private ImageView imageView6;
    private ImageView imageView7;
    private ImageView imageView8;
    private ImageView imageView9;
    private ImageView imageView10;
    private ImageView imageView11;
    private ImageView imageView12;
    private TextView naver;
    private TextView green;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_info);

        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        
        Intent intent=getIntent();
        Spot spot = (Spot) intent.getSerializableExtra("spot");

        name=(TextView) findViewById(R.id.name);
        address=(TextView)findViewById(R.id.address);
        imageView=(ImageView)findViewById(R.id.imageView);
        imageView2=(ImageView)findViewById(R.id.imageView2);
        imageView3=(ImageView)findViewById(R.id.imageView3);
        imageView4=(ImageView)findViewById(R.id.imageView4);
        imageView5=(ImageView)findViewById(R.id.imageView5);
        imageView6=(ImageView)findViewById(R.id.imageView6);
        imageView7=(ImageView)findViewById(R.id.imageView7);
        imageView8=(ImageView)findViewById(R.id.imageView8);
        imageView9=(ImageView)findViewById(R.id.imageView9);
        imageView10=(ImageView)findViewById(R.id.imageView10);
        imageView11=(ImageView)findViewById(R.id.imageView11);
        imageView12=(ImageView)findViewById(R.id.imageView12);
        naver=(TextView)findViewById(R.id.naver);
        green=(TextView)findViewById(R.id.green);

        name.setText(spot.name);
        address.setText(spot.address);

        imageView.setImageResource(getResources().getIdentifier(spot.image,"drawable",getPackageName()));

        Linkify.TransformFilter linktest = new Linkify.TransformFilter() {
            @Override
            public String transformUrl(Matcher match, String url) {
                return spot.naverurl;
            }
        };
        Pattern pattern = Pattern.compile("네이버지도");
        Linkify.addLinks(naver, pattern, "", null, linktest);

        if(spot.greenurl.equals(""))
            green.setText("");
        else{
            linktest = new Linkify.TransformFilter() {
                @Override
                public String transformUrl(Matcher match, String url) {
                    return spot.greenurl;
                }
            };
            pattern = Pattern.compile("기아자동차 초록여행");
            Linkify.addLinks(green, pattern, "", null, linktest);
        }

        if(spot.picto_A==1)
            imageView2.setImageResource(R.drawable.picto_a_on);
        else imageView2.setImageResource(R.drawable.picto_a_off);
        if(spot.picto_B==1)
            imageView3.setImageResource(R.drawable.picto_b_on);
        else imageView3.setImageResource(R.drawable.picto_b_off);
        if(spot.picto_C==1)
            imageView4.setImageResource(R.drawable.picto_c_on);
        else imageView4.setImageResource(R.drawable.picto_c_off);
        if(spot.picto_D==1)
            imageView5.setImageResource(R.drawable.picto_d_on);
        else imageView5.setImageResource(R.drawable.picto_d_off);
        if(spot.picto_E==1)
            imageView6.setImageResource(R.drawable.picto_e_on);
        else imageView6.setImageResource(R.drawable.picto_e_off);
        if(spot.picto_F==1)
            imageView7.setImageResource(R.drawable.picto_f_on);
        else imageView7.setImageResource(R.drawable.picto_f_off);
        if(spot.picto_G==1)
            imageView8.setImageResource(R.drawable.picto_g_on);
        else imageView8.setImageResource(R.drawable.picto_g_off);
        if(spot.picto_H==1)
            imageView9.setImageResource(R.drawable.picto_h_on);
        else imageView9.setImageResource(R.drawable.picto_h_off);
        if(spot.picto_I==1)
            imageView10.setImageResource(R.drawable.picto_i_on);
        else imageView10.setImageResource(R.drawable.picto_i_off);
        if(spot.picto_J==1)
            imageView11.setImageResource(R.drawable.picto_j_on);
        else imageView11.setImageResource(R.drawable.picto_j_off);
        if(spot.picto_K==1)
            imageView12.setImageResource(R.drawable.picto_k_on);
        else imageView12.setImageResource(R.drawable.picto_k_off);


    }

}
