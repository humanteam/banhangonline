package com.example.thong;

public class APIs {
    public static String server = "http://192.168.2.14";
    public static String api_home =server+"/api/sanpham";//api home==>trả về tất cả các sp
    public static String api_mypham=server+"/api/sanpham/1";//trả về danh sách mỹ phẩm
    public static String api_toc=server+"/api/sanpham/2";//Trả về danh sách sản phẩm tóc
    public static String api_thietbi=server+"/api/sanpham/3";//trả về danh sách thiết bị
    public static String database_name="banhangonline.sqlite";//Ten databbase
    public static String api_send_mail=server+"/WebServices/AuthenCation.asmx/send_mail";//Send mail
    public static String admod_id="ca-app-pub-5317078946898434~3053253574";
    public static String banner_id="ca-app-pub-5317078946898434/5697945822";
}
