package com.example.thong.model;

public class SanPham {
   String masp,tensp,anh,chitiet,matheloai,gia;

    public SanPham(String masp, String tensp, String anh, String chitiet, String matheloai, String gia) {
        this.masp = masp;
        this.tensp = tensp;
        this.anh = anh;
        this.chitiet = chitiet;
        this.matheloai = matheloai;
        this.gia = gia;
    }

    public SanPham() {
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getChitiet() {
        return chitiet;
    }

    public void setChitiet(String chitiet) {
        this.chitiet = chitiet;
    }

    public String getMatheloai() {
        return matheloai;
    }

    public void setMatheloai(String matheloai) {
        this.matheloai = matheloai;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }
}
