package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import model.NhanVien;
import model.PhongBan;

@SuppressWarnings("serial")
public class QuanLiNhanVienUI extends JFrame {
	JComboBox<PhongBan> cboPhongBan;
	JList<NhanVien> listNhanVien;
	JTextField txtMa, txtTen, txtNgayVaoLam, txtNamSinh;
	JButton btnLuu, btnXoa, btnThoat;

	ArrayList<PhongBan> dsPhongBan;
	ArrayList<NhanVien> dsNhanVien;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	PhongBan pbSelect = null;
	NhanVien nvSelect = null;

	public QuanLiNhanVienUI(String tieude) {
		this.setTitle(tieude);
		addControls();
		fakeData();
		addEvents();

	}

	public void addControls() {
		Container con = getContentPane();
		JPanel pnMain = new JPanel();
		pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
		con.add(pnMain);
		JPanel pnPhongBan = new JPanel();
		pnPhongBan.setLayout(new FlowLayout());
		pnMain.add(pnPhongBan);
		JLabel lblPhongBan = new JLabel("Chọn phòng ban");
		cboPhongBan = new JComboBox<PhongBan>();
		cboPhongBan.setPreferredSize(new Dimension(200, 25));
		pnPhongBan.add(lblPhongBan);
		pnPhongBan.add(cboPhongBan);

		JPanel pnDanhSachVaChiTiet = new JPanel();
		pnDanhSachVaChiTiet.setLayout(new GridLayout(1, 2));
		pnMain.add(pnDanhSachVaChiTiet);
		JPanel pnDanhSach = new JPanel();
		pnDanhSach.setLayout(new BorderLayout());

		Border borderDanhsach = BorderFactory.createLineBorder(Color.BLUE);
		TitledBorder titleBorderDanhSach = new TitledBorder(borderDanhsach, "Danh sách");
		titleBorderDanhSach.setTitleJustification(TitledBorder.CENTER);
		pnDanhSach.setBorder(titleBorderDanhSach);

		listNhanVien = new JList<NhanVien>();
		JScrollPane sc = new JScrollPane(listNhanVien, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		// Màn hình cần co dãn theo người dùng, use borderlayout
		pnDanhSach.add(sc, BorderLayout.CENTER);
		pnDanhSachVaChiTiet.add(pnDanhSach);

		JPanel pnChiTiet = new JPanel();
		pnChiTiet.setLayout(new BoxLayout(pnChiTiet, BoxLayout.Y_AXIS));
		Border borderChiTiet = BorderFactory.createLineBorder(Color.BLUE);
		TitledBorder titleBorderChiTiet = new TitledBorder(borderChiTiet, "Chi tiết");
		titleBorderChiTiet.setTitleJustification(TitledBorder.CENTER);
		pnChiTiet.setBorder(titleBorderChiTiet);
		pnDanhSachVaChiTiet.add(pnChiTiet);

		JPanel pnMa = new JPanel();
		pnMa.setLayout(new FlowLayout());
		JLabel lblMa = new JLabel("Mã: ");
		txtMa = new JTextField(15);
		pnMa.add(lblMa);
		pnMa.add(txtMa);
		pnChiTiet.add(pnMa);

		JPanel pnTen = new JPanel();
		pnTen.setLayout(new FlowLayout());
		JLabel lblTen = new JLabel("Tên: ");
		txtTen = new JTextField(15);
		pnTen.add(lblTen);
		pnTen.add(txtTen);
		pnChiTiet.add(pnTen);

		JPanel pnNgayVaoLam = new JPanel();
		pnNgayVaoLam.setLayout(new FlowLayout());
		JLabel lblNgayVaoLam = new JLabel("Ngày vào làm: ");
		txtNgayVaoLam = new JTextField(15);
		pnNgayVaoLam.add(lblNgayVaoLam);
		pnNgayVaoLam.add(txtNgayVaoLam);
		pnChiTiet.add(pnNgayVaoLam);

		JPanel pnNamSinh = new JPanel();
		pnNamSinh.setLayout(new FlowLayout());
		JLabel lblNamSinh = new JLabel("Năm sinh: ");
		txtNamSinh = new JTextField(15);
		pnNamSinh.add(lblNamSinh);
		pnNamSinh.add(txtNamSinh);
		pnChiTiet.add(pnNamSinh);

		JPanel pnButton = new JPanel();
		Border borderButton = BorderFactory.createLineBorder(Color.BLUE);
		TitledBorder titleBorderButton = new TitledBorder(borderButton, "Chọn chức năng");
		titleBorderButton.setTitleJustification(TitledBorder.CENTER);
		titleBorderButton.setTitleColor(Color.RED);
		pnButton.setBorder(titleBorderButton);

		pnButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btnLuu = new JButton("Lưu");
		btnXoa = new JButton("Xóa");
		btnThoat = new JButton("Thoát");
		pnButton.add(btnLuu);
		pnButton.add(btnXoa);
		pnButton.add(btnThoat);
		pnMain.add(pnButton);

		lblMa.setPreferredSize(lblNgayVaoLam.getPreferredSize());
		lblNamSinh.setPreferredSize(lblNgayVaoLam.getPreferredSize());
		lblTen.setPreferredSize(lblNgayVaoLam.getPreferredSize());
	}

	@SuppressWarnings("deprecation")
	public void fakeData() {
		dsPhongBan = new ArrayList<PhongBan>();
		PhongBan phtgv = new PhongBan();
		phtgv.setMaPhong("P01");
		phtgv.setTenPhong("Phòng hợp tác giảng viên");

		PhongBan pkd = new PhongBan();
		pkd.setMaPhong("P02");
		pkd.setTenPhong("Phòng kinh doanh");

		PhongBan pkt = new PhongBan();
		pkt.setMaPhong("P03");
		pkt.setTenPhong("Phòng kế toán");

		dsPhongBan.add(phtgv);
		dsPhongBan.add(pkd);
		dsPhongBan.add(pkt);

		phtgv.themNhanVien(new NhanVien("NV1", "Chưa biết", new Date(2016, 1, 1), new Date(1990, 1, 1)));
		phtgv.themNhanVien(new NhanVien("NV2", "Chưa biết", new Date(2016, 1, 1), new Date(1990, 1, 1)));
		pkt.themNhanVien(new NhanVien("NV3", "Chưa biết", new Date(2016, 1, 1), new Date(1990, 1, 1)));
		pkt.themNhanVien(new NhanVien("NV4", "Chưa biết", new Date(2016, 1, 1), new Date(1990, 1, 1)));
		pkd.themNhanVien(new NhanVien("NV5", "Chưa biết", new Date(2016, 1, 1), new Date(1990, 1, 1)));
		pkd.themNhanVien(new NhanVien("NV6", "Chưa biết", new Date(2016, 1, 1), new Date(1990, 1, 1)));

		for (PhongBan pb : dsPhongBan) {
			cboPhongBan.addItem(pb);
		}

	}

	public void addEvents() {
		cboPhongBan.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (cboPhongBan.getSelectedIndex() == -1)
					return;

				PhongBan pbSelect = (PhongBan) cboPhongBan.getSelectedItem();
				listNhanVien.setListData(pbSelect.getNhanViens());

			}
		});

		listNhanVien.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (listNhanVien.getSelectedIndex() == -1)
					return;
				nvSelect = listNhanVien.getSelectedValue();
				txtMa.setText(nvSelect.getMaNhanVien());
				txtTen.setText(nvSelect.getTenNhanVien());
				txtNgayVaoLam.setText(sdf.format(nvSelect.getNgayVaoLamViec()));
				txtNamSinh.setText(sdf.format(nvSelect.getNamSinh()));
			}
		});

		btnLuu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				xuLyLuu();

			}
		});

		btnXoa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				xuLyXoa();

			}
		});

		btnThoat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});
	}

	protected void xuLyXoa() {
		try {
			if (nvSelect != null) {
				pbSelect.getNhanViens().remove(nvSelect);
				nvSelect = null;
				listNhanVien.setListData(pbSelect.getNhanViens());

			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.toString());
		}
	}

	protected void xuLyLuu() {
		try {
			NhanVien nv = new NhanVien(txtMa.getText(), txtTen.getText(), sdf.parse(txtNgayVaoLam.getText()),
					sdf.parse(txtNamSinh.getText()));
			if (pbSelect != null) {
				pbSelect.themNhanVien(nv);
				listNhanVien.setListData(pbSelect.getNhanViens());
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.toString());
		}
	}

	public void showWindows() {
		this.setSize(600, 400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
