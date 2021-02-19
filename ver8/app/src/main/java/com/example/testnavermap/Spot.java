package com.example.testnavermap;

import java.io.Serializable;

public class Spot implements Serializable {

    private static final long serialVersionUID = 1L;

    public String id;
    public String name;
    public String address;
    public String image;
    public String naverurl;
    public String greenurl;

    public int picto_A;
    public int picto_B;
    public int picto_C;
    public int picto_D;
    public int picto_E;
    public int picto_F;
    public int picto_G;
    public int picto_H;
    public int picto_I;
    public int picto_J;
    public int picto_K;

    public Spot(String id, String name, String address, String image, String naverurl, String greenurl,
                int picto_A, int picto_B, int picto_C, int picto_D, int picto_E, int picto_F, int picto_G, int picto_H, int picto_I, int picto_J, int picto_K) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.image = image;
        this.naverurl = naverurl;
        this.greenurl = greenurl;
        this.picto_A = picto_A;
        this.picto_B = picto_B;
        this.picto_C = picto_C;
        this.picto_D = picto_D;
        this.picto_E = picto_E;
        this.picto_F = picto_F;
        this.picto_G = picto_G;
        this.picto_H = picto_H;
        this.picto_I = picto_I;
        this.picto_J = picto_J;
        this.picto_K = picto_K;
    }
}
