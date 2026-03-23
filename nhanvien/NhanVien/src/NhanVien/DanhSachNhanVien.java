package NhanVien;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DanhSachNhanVien implements Serializable {
    private static final long serialVersionUID = 1L;

    private final List<NhanVien> ds;

    public DanhSachNhanVien() {
        ds = new ArrayList<>();
    }

    public boolean themNhanVien(NhanVien nv) {
        if (nv == null) {
            return false;
        }
        if (timTheoMa(nv.getMaNhanVien()) != null) {
            return false;
        }
        ds.add(nv);
        return true;
    }

    public NhanVien timTheoMa(String ma) {
        if (ma == null) {
            return null;
        }
        String maCanTim = ma.trim();
        for (NhanVien nv : ds) {
            if (nv.getMaNhanVien().equalsIgnoreCase(maCanTim)) {
                return nv;
            }
        }
        return null;
    }

    public boolean xoaTheoMa(String ma) {
        NhanVien nv = timTheoMa(ma);
        if (nv == null) {
            return false;
        }
        ds.remove(nv);
        return true;
    }

    public int size() {
        return ds.size();
    }

    public List<NhanVien> getTatCa() {
        return Collections.unmodifiableList(ds);
    }
}
