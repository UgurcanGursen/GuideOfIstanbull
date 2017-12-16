package com.ugurcangursen.guideofistanbul;

/**
 * Created by 291 on 15.12.2017.
 */

public class Guide {

    String baslik,aciklama,adres,karegori;
    byte [] image;

    public Guide(String baslik, String aciklama, String adres, byte[] image) {
        this.baslik = baslik;
        this.aciklama = aciklama;
        this.adres = adres;
        this.image = image;
    }

    public Guide (){

    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getKaregori() {
        return karegori;
    }

    public void setKaregori(String karegori) {
        this.karegori = karegori;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
