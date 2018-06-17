package com.example.thong.model;

public class DonHang extends SanPham {
    String tenkh,diachi,sdt;
    int soluong;
    public DonHang(){

    }
    public DonHang(String MaSP,String TenSP,String gia,String tenkh,String diachi,String sdt,int soluong){
        this.masp=MaSP;
        this.tensp=TenSP;
        this.gia=gia;
        this.tenkh=tenkh;
        this.diachi=diachi;
        this.sdt=sdt;
        this.soluong=soluong;
    }

    @Override
    public String getMasp() {
       return this.masp;
    }

    @Override
    public void setMasp(String masp) {
        this.masp=masp;
    }

    @Override
    public String getTensp() {
        return this.tensp;
    }

    @Override
    public void setTensp(String tensp) {
        this.tensp=tensp;
    }

    @Override
    public String getGia() {
        return this.gia;
    }

    @Override
    public void setGia(String gia) {
        this.gia=gia;
    }

    public float tinhtien(){
        return (float)soluong*(float)Integer.parseInt(gia);
    }

    public String getTenkh() {
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
