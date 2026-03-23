package NhanVien;

import java.io.Serializable;
import java.util.Objects;

public class NhanVien implements Serializable {
    private static final long serialVersionUID = 1L;

    private String maNhanVien;
    private String ho;
    private String ten;
    private String phai;
    private int tuoi;
    private double luong;
    private String phongBan;

    public NhanVien(String maNhanVien, String ho, String ten, String phai, int tuoi, double luong, String phongBan) {
        this.maNhanVien = maNhanVien;
        this.ho = ho;
        this.ten = ten;
        this.phai = phai;
        this.tuoi = tuoi;
        this.luong = luong;
        this.phongBan = phongBan;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public String getHo() {
        return ho;
    }

    public String getTen() {
        return ten;
    }

    public String getPhai() {
        return phai;
    }

    public int getTuoi() {
        return tuoi;
    }

    public double getLuong() {
        return luong;
    }

    public String getPhongBan() {
        return phongBan;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof NhanVien)) return false;
        NhanVien other = (NhanVien) obj;
        return maNhanVien.equalsIgnoreCase(other.maNhanVien);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maNhanVien.toLowerCase());
    }
}
