package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import dao.SiswaDAO;
import model.Siswa;
import java.util.List;
import javax.swing.JOptionPane;

public class MainFrame extends JFrame {

    // ========= FORM =========
    private JTextField txtNis;
    private JTextField txtNama;
    private JTextField txtKelas;

    private JComboBox<String> cmbBulan;
    private JComboBox<String> cmbStatus;

    private JButton btnSimpan;
    private JButton btnEdit;
    private JButton btnHapus;
    private JButton btnClear;

    // ========= TABLE =========
    private JTable table;
    private DefaultTableModel model;

    private JTextField txtCari;

    public MainFrame() {

    setTitle("Aplikasi Pendataan Siswa");
    setSize(950,550);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    initComponents();

    loadTable();

    btnSimpan.addActionListener(e -> simpanData());
    btnEdit.addActionListener(e -> editData());
    btnHapus.addActionListener(e -> hapusData());

    table.getSelectionModel().addListSelectionListener(e -> {
        if (!e.getValueIsAdjusting()) {
            isiForm();
        }
    });

    setVisible(true);
}
    
private void editData() {

    if (txtNis.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this,
                "Pilih data yang akan diedit!");
        return;
    }

    Siswa siswa = new Siswa();

    siswa.setNis(txtNis.getText().trim());
    siswa.setNama(txtNama.getText().trim());
    siswa.setKelas(txtKelas.getText().trim());
    siswa.setBulan(cmbBulan.getSelectedItem().toString());
    siswa.setStatus(cmbStatus.getSelectedItem().toString());

    SiswaDAO dao = new SiswaDAO();

    if (dao.update(siswa)) {

        JOptionPane.showMessageDialog(this,
                "Data berhasil diubah.");

        clearForm();
        loadTable();

    } else {

        JOptionPane.showMessageDialog(this,
                "Data gagal diubah.");

    }

}

private void hapusData() {

    if (txtNis.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this,
                "Pilih data yang akan dihapus!");
        return;
    }

    int pilih = JOptionPane.showConfirmDialog(
            this,
            "Yakin ingin menghapus data ini?",
            "Konfirmasi",
            JOptionPane.YES_NO_OPTION);

    if (pilih == JOptionPane.YES_OPTION) {

        SiswaDAO dao = new SiswaDAO();

        if (dao.hapus(txtNis.getText().trim())) {

            JOptionPane.showMessageDialog(this,
                    "Data berhasil dihapus.");

            clearForm();
            loadTable();

        } else {

            JOptionPane.showMessageDialog(this,
                    "Data gagal dihapus.");

        }

    }

}

private void isiForm() {

    int row = table.getSelectedRow();

    if (row == -1) {
        return;
    }

    txtNis.setText(model.getValueAt(row, 0).toString());
    txtNama.setText(model.getValueAt(row, 1).toString());
    txtKelas.setText(model.getValueAt(row, 2).toString());

    cmbBulan.setSelectedItem(model.getValueAt(row, 3).toString());
    cmbStatus.setSelectedItem(model.getValueAt(row, 4).toString());
}
    private void initComponents(){

        setLayout(new BorderLayout(10,10));

        //============================
        // PANEL KIRI
        //============================

        JPanel leftPanel = new JPanel(new GridBagLayout());

        leftPanel.setBorder(
                BorderFactory.createTitledBorder("Data Siswa")
        );

        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(5,5,5,5);
        c.fill = GridBagConstraints.HORIZONTAL;

        txtNis = new JTextField(15);
        txtNama = new JTextField(15);
        txtKelas = new JTextField(15);


        cmbBulan = new JComboBox<>();
cmbStatus = new JComboBox<>();

cmbBulan.addItem("Januari");
cmbBulan.addItem("Februari");
cmbBulan.addItem("Maret");
cmbBulan.addItem("April");
cmbBulan.addItem("Mei");
cmbBulan.addItem("Juni");
cmbBulan.addItem("Juli");
cmbBulan.addItem("Agustus");
cmbBulan.addItem("September");
cmbBulan.addItem("Oktober");
cmbBulan.addItem("November");
cmbBulan.addItem("Desember");

cmbStatus.addItem("Lunas");
cmbStatus.addItem("Belum Lunas");

        btnSimpan = new JButton("Simpan");
        btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus");
        btnClear = new JButton("Clear");

        // NIS
        c.gridx=0;
        c.gridy=0;
        leftPanel.add(new JLabel("NIS"),c);

        c.gridx=1;
        leftPanel.add(txtNis,c);

        // Nama
        c.gridx=0;
        c.gridy=1;
        leftPanel.add(new JLabel("Nama"),c);

        c.gridx=1;
        leftPanel.add(txtNama,c);

        // Kelas
        c.gridx=0;
        c.gridy=2;
        leftPanel.add(new JLabel("Kelas"),c);

        c.gridx=1;
        leftPanel.add(txtKelas,c);

        // Bulan
c.gridx = 0;
c.gridy = 3;
leftPanel.add(new JLabel("Bulan"), c);

c.gridx = 1;
leftPanel.add(cmbBulan, c);

// Status
c.gridx = 0;
c.gridy = 4;
leftPanel.add(new JLabel("Status"), c);

c.gridx = 1;
leftPanel.add(cmbStatus, c);


        // Tombol
        c.gridx = 0;
c.gridy = 5;
c.gridwidth = 2;

leftPanel.add(btnSimpan,c);

c.gridy++;
leftPanel.add(btnEdit,c);

c.gridy++;
leftPanel.add(btnHapus,c);

c.gridy++;
leftPanel.add(btnClear,c);

        //============================
        // PANEL KANAN
        //============================

        JPanel rightPanel = new JPanel(new BorderLayout(5,5));

        rightPanel.setBorder(
                BorderFactory.createTitledBorder("Daftar Data Siswa")
        );

        txtCari = new JTextField();

        rightPanel.add(txtCari,BorderLayout.NORTH);

        model = new DefaultTableModel();

        model.addColumn("NIS");
        model.addColumn("Nama");
        model.addColumn("Kelas");
        model.addColumn("Bulan");
model.addColumn("Status");

        table = new JTable(model);

        JScrollPane scroll = new JScrollPane(table);

        rightPanel.add(scroll,BorderLayout.CENTER);

        add(leftPanel,BorderLayout.WEST);
        add(rightPanel,BorderLayout.CENTER);

    }



private void simpanData() {

    if (txtNis.getText().trim().isEmpty()
            || txtNama.getText().trim().isEmpty()
            || txtKelas.getText().trim().isEmpty()) {

        JOptionPane.showMessageDialog(this,
                "Semua data wajib diisi!");

        return;
    }

    Siswa siswa = new Siswa();

    siswa.setNis(txtNis.getText().trim());
    siswa.setNama(txtNama.getText().trim());
    siswa.setKelas(txtKelas.getText().trim());
    siswa.setBulan(cmbBulan.getSelectedItem().toString());
siswa.setStatus(cmbStatus.getSelectedItem().toString());

    SiswaDAO dao = new SiswaDAO();

    if (dao.simpan(siswa)) {

        JOptionPane.showMessageDialog(this,
                "Data berhasil disimpan.");

        clearForm();

        loadTable();

    } else {

        JOptionPane.showMessageDialog(this,
                "Data gagal disimpan.");

    }

}

private void clearForm() {

    txtNis.setText("");
    txtNama.setText("");
    txtKelas.setText("");

    cmbBulan.setSelectedIndex(0);
    cmbStatus.setSelectedIndex(0);

    txtNis.requestFocus();

}

private void loadTable() {

    model.setRowCount(0);

    SiswaDAO dao = new SiswaDAO();

    List<Siswa> list = dao.getAll();

    for (Siswa s : list) {

        model.addRow(new Object[]{
    s.getNis(),
    s.getNama(),
    s.getKelas(),
    s.getBulan(),
    s.getStatus()
});

    }

}
}
