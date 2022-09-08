/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Maverllous
 */
public class SanPham {
    int masp;
    String tensp;
    String loai;
    int soluong;
    float gia;
    float gianhap;
    String ngay;

    public int getMasp() {
        return masp;
    }

    public String getTensp() {
        return tensp;
    }

    public String getLoai() {
        return loai;
    }

    public int getSoluong() {
        return soluong;
    }

    public float getGia() {
        return gia;
    }

    public float getGianhap() {
        return gianhap;
    }

    public String getNgay() {
        return ngay;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }

    public void setGianhap(float gianhap) {
        this.gianhap = gianhap;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    
}
