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
public class NhanVien {
    int manv;
    String tennv;
    String gioitinh;
    String diachi;
    String sdt;
    String cmnd;
    int macv;
    float luong;
    String calam;

    public int getManv() {
        return manv;
    }

    public String getTennv() {
        return tennv;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public String getDiachi() {
        return diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public String getCmnd() {
        return cmnd;
    }

    public int getMacv() {
        return macv;
    }

    public float getLuong() {
        return luong;
    }
    
    public String getCalam(){
        return calam;
    }

    public void setManv(int manv) {
        this.manv = manv;
    }

    public void setTennv(String tennv) {
        this.tennv = tennv;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public void setMacv(int macv) {
        this.macv = macv;
    }

    public void setLuong(float luong) {
        this.luong = luong;
    }

    public void setCalam(String calam){
        this.calam = calam;
    }
}
