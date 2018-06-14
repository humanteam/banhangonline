package com.example.thong.model;

public class GioHang extends SanPham {
    String soluong,thanhtien,trangthai;

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }

    public String getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(String thanhtien) {
        this.thanhtien = thanhtien;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
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
    public String getAnh() {
        return this.anh;
    }

    @Override
    public void setAnh(String anh) {
        this.anh=anh;
    }

    @Override
    public String getChitiet() {
        return this.chitiet;
    }

    @Override
    public void setChitiet(String chitiet) {
        this.chitiet=chitiet;
    }

    @Override
    public String getMatheloai() {
        return this.matheloai;
    }

    @Override
    public void setMatheloai(String matheloai) {
        this.matheloai=matheloai;
    }

    @Override
    public String getGia() {
        return this.gia;
    }

    @Override
    public void setGia(String gia) {
        this.gia=gia;
    }

    public GioHang(String masp, String tensp, String anh, String chitiet, String matheloai, String dongia, String soluong, String thanhtien, String trangthai){
        this.masp=masp;
        this.tensp=tensp;
        this.anh=anh;
        this.chitiet=chitiet;
        this.matheloai=matheloai;
        this.gia=dongia;
        this.soluong=soluong;
        this.thanhtien=thanhtien;
        this.trangthai=trangthai;
    }
}
