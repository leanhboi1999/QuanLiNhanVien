package model;

import java.io.Serializable;
import java.util.ArrayList;

public class PhongBan implements Serializable{
	private String maPhong;
	private String tenPhong;
	private ArrayList<NhanVien> nhanViens;
	
	public void themNhanVien(NhanVien nv) {
		this.nhanViens.add(nv);
		nv.setPhong(this);
	}
	
	public PhongBan() {
		super();
		this.nhanViens= new ArrayList<NhanVien>();
	}
	public String getMaPhong() {
		return maPhong;
	}
	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}
	public String getTenPhong() {
		return tenPhong;
	}
	public void setTenPhong(String tenPhong) {
		this.tenPhong = tenPhong;
	}
	public ArrayList<NhanVien> getNhanViens() {
		return nhanViens;
	}
	public void setNhanViens(ArrayList<NhanVien> nhanViens) {
		this.nhanViens = nhanViens;
	}
	
}
