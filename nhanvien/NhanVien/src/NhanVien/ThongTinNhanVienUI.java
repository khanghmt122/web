package NhanVien;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class ThongTinNhanVienUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final String FILE_DATA = "nhanvien.dat";

    private final JTextField txtMaNV = new JTextField(14);
    private final JTextField txtHo = new JTextField(14);
    private final JTextField txtTen = new JTextField(14);
    private final JTextField txtTuoi = new JTextField(14);
    private final JTextField txtLuong = new JTextField(14);
    private final JTextField txtTim = new JTextField(12);

    private final JComboBox<String> cboPhai = new JComboBox<>(new String[] { "Nam", "Nu" });
    private final JComboBox<String> cboPhongBan = new JComboBox<>(new String[] {
            "1. Phong to chuc",
            "2. Phong ky thuat",
            "3. Phong nhan su"
    });

    private final DefaultTableModel model = new DefaultTableModel(
            new String[] { "Ma NV", "Ho", "Ten", "Phai", "Tuoi", "Tien luong", "Phong ban" }, 0) {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private final JTable table = new JTable(model);
    private DanhSachNhanVien ds = new DanhSachNhanVien();

    public ThongTinNhanVienUI() {
        setTitle("THONG TIN NHAN VIEN");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(8, 8));

        taoTieuDe();
        taoVungNhapLieu();
        taoBang();
        taoNutChucNang();
        ganSuKien();
        taiDuLieuDaLuu();
    }

    private void taoTieuDe() {
        JPanel pnTop = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblTitle = new JLabel("THONG TIN NHAN VIEN");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        pnTop.add(lblTitle);
        add(pnTop, BorderLayout.NORTH);
    }

    private void taoVungNhapLieu() {
        JPanel pnNhap = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 6, 4, 6);
        gbc.anchor = GridBagConstraints.WEST;

        int row = 0;
        gbc.gridx = 0;
        gbc.gridy = row;
        pnNhap.add(new JLabel("Ma nhan vien:"), gbc);
        gbc.gridx = 1;
        pnNhap.add(txtMaNV, gbc);

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        pnNhap.add(new JLabel("Ho:"), gbc);
        gbc.gridx = 1;
        pnNhap.add(txtHo, gbc);

        gbc.gridx = 2;
        pnNhap.add(new JLabel("Ten nhan vien:"), gbc);
        gbc.gridx = 3;
        pnNhap.add(txtTen, gbc);

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        pnNhap.add(new JLabel("Tuoi:"), gbc);
        gbc.gridx = 1;
        pnNhap.add(txtTuoi, gbc);

        gbc.gridx = 2;
        pnNhap.add(new JLabel("Phai:"), gbc);
        gbc.gridx = 3;
        pnNhap.add(cboPhai, gbc);

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        pnNhap.add(new JLabel("Tien luong:"), gbc);
        gbc.gridx = 1;
        pnNhap.add(txtLuong, gbc);

        gbc.gridx = 2;
        pnNhap.add(new JLabel("Phong ban:"), gbc);
        gbc.gridx = 3;
        pnNhap.add(cboPhongBan, gbc);

        add(pnNhap, BorderLayout.WEST);
    }

    private void taoBang() {
        table.getColumnModel().getColumn(3).setCellEditor(
                new DefaultCellEditor(new JComboBox<>(new String[] { "Nam", "Nu" })));
        JScrollPane scroll = new JScrollPane(table);
        add(scroll, BorderLayout.CENTER);
    }

    private void taoNutChucNang() {
        JPanel pnBottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnBottom.add(new JLabel("Nhap ma so can tim:"));
        pnBottom.add(txtTim);

        JButton btnTim = new JButton("Tim");
        JButton btnThem = new JButton("Them");
        JButton btnXoaTrang = new JButton("Xoa trang");
        JButton btnXoa = new JButton("Xoa");
        JButton btnLuu = new JButton("Luu");

        btnTim.setActionCommand("tim");
        btnThem.setActionCommand("them");
        btnXoaTrang.setActionCommand("xoatrang");
        btnXoa.setActionCommand("xoa");
        btnLuu.setActionCommand("luu");

        btnTim.addActionListener(e -> timNhanVienTheoMa());
        btnThem.addActionListener(e -> themNhanVien());
        btnXoaTrang.addActionListener(e -> xoaTrangForm());
        btnXoa.addActionListener(e -> xoaNhanVienDangChon());
        btnLuu.addActionListener(e -> luuFile());

        pnBottom.add(btnTim);
        pnBottom.add(Box.createHorizontalStrut(30));
        pnBottom.add(btnThem);
        pnBottom.add(btnXoaTrang);
        pnBottom.add(btnXoa);
        pnBottom.add(btnLuu);
        add(pnBottom, BorderLayout.SOUTH);
    }

    private void ganSuKien() {
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() >= 0) {
                int row = table.getSelectedRow();
                txtMaNV.setText(String.valueOf(model.getValueAt(row, 0)));
                txtHo.setText(String.valueOf(model.getValueAt(row, 1)));
                txtTen.setText(String.valueOf(model.getValueAt(row, 2)));
                cboPhai.setSelectedItem(String.valueOf(model.getValueAt(row, 3)));
                txtTuoi.setText(String.valueOf(model.getValueAt(row, 4)));
                txtLuong.setText(String.valueOf(model.getValueAt(row, 5)));
                cboPhongBan.setSelectedItem(String.valueOf(model.getValueAt(row, 6)));
            }
        });
    }

    private void themNhanVien() {
        String ma = txtMaNV.getText().trim();
        String ho = txtHo.getText().trim();
        String ten = txtTen.getText().trim();
        String phai = String.valueOf(cboPhai.getSelectedItem());
        String phongBan = String.valueOf(cboPhongBan.getSelectedItem());
        String tuoiText = txtTuoi.getText().trim();
        String luongText = txtLuong.getText().trim();

        if (ma.isEmpty()) {
            showMessage("Ma nhan vien khong duoc rong.");
            txtMaNV.requestFocus();
            return;
        }
        if (ds.timTheoMa(ma) != null) {
            showMessage("Ma nhan vien bi trung.");
            txtMaNV.requestFocus();
            return;
        }
        if (ho.isEmpty() || ten.isEmpty()) {
            showMessage("Ho va ten khong duoc rong.");
            return;
        }

        int tuoi;
        try {
            tuoi = Integer.parseInt(tuoiText);
        } catch (NumberFormatException ex) {
            showMessage("Tuoi phai la so nguyen.");
            return;
        }
        if (tuoi < 18 || tuoi > 60) {
            showMessage("Tuoi phai trong khoang 18 den 60.");
            return;
        }

        double luong;
        try {
            luong = Double.parseDouble(luongText);
        } catch (NumberFormatException ex) {
            showMessage("Luong phai la so.");
            return;
        }
        if (luong <= 0) {
            showMessage("Luong phai lon hon 0.");
            return;
        }

        NhanVien nv = new NhanVien(ma, ho, ten, phai, tuoi, luong, phongBan);
        if (ds.themNhanVien(nv)) {
            model.addRow(new Object[] { ma, ho, ten, phai, tuoi, luong, phongBan });
            xoaTrangForm();
        } else {
            showMessage("Khong the them nhan vien.");
        }
    }

    private void xoaNhanVienDangChon() {
        int row = table.getSelectedRow();
        if (row < 0) {
            showMessage("Ban chua chon dong nao de xoa.");
            return;
        }
        String ma = String.valueOf(model.getValueAt(row, 0));
        ds.xoaTheoMa(ma);
        model.removeRow(row);
        xoaTrangForm();
    }

    private void timNhanVienTheoMa() {
        String maCanTim = txtTim.getText().trim();
        if (maCanTim.isEmpty()) {
            showMessage("Nhap ma can tim.");
            return;
        }
        for (int i = 0; i < model.getRowCount(); i++) {
            String ma = String.valueOf(model.getValueAt(i, 0));
            if (ma.equalsIgnoreCase(maCanTim)) {
                table.setRowSelectionInterval(i, i);
                table.scrollRectToVisible(table.getCellRect(i, 0, true));
                return;
            }
        }
        showMessage("Khong tim thay ma: " + maCanTim);
    }

    private void xoaTrangForm() {
        txtMaNV.setText("");
        txtHo.setText("");
        txtTen.setText("");
        txtTuoi.setText("");
        txtLuong.setText("");
        cboPhai.setSelectedIndex(0);
        cboPhongBan.setSelectedIndex(0);
        txtMaNV.requestFocus();
    }

    private void luuFile() {
        LuuTru luuTru = new LuuTru();
        try {
            luuTru.luuFile(ds, FILE_DATA);
            showMessage("Da luu " + ds.size() + " nhan vien vao file " + FILE_DATA);
        } catch (Exception ex) {
            showMessage("Loi luu file: " + ex.getMessage());
        }
    }

    private void taiDuLieuDaLuu() {
        LuuTru luuTru = new LuuTru();
        try {
            Object data = luuTru.docFile(FILE_DATA);
            if (data instanceof DanhSachNhanVien) {
                ds = (DanhSachNhanVien) data;
                napDanhSachLenBang();
            }
        } catch (Exception ex) {
            // File chua co thi bo qua.
        }
    }

    private void napDanhSachLenBang() {
        model.setRowCount(0);
        for (NhanVien nv : ds.getTatCa()) {
            model.addRow(new Object[] {
                    nv.getMaNhanVien(),
                    nv.getHo(),
                    nv.getTen(),
                    nv.getPhai(),
                    nv.getTuoi(),
                    nv.getLuong(),
                    nv.getPhongBan()
            });
        }
    }

    private void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ThongTinNhanVienUI().setVisible(true));
    }
}
