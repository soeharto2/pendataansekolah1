package model;

public class Siswa {

    private String nis;
    private String nama;
    private String kelas;
    private String bulan;
    private String status;

    public Siswa() {
    }

    public Siswa(String nis, String nama, String kelas, String bulan, String status) {
    this.nis = nis;
    this.nama = nama;
    this.kelas = kelas;
    this.bulan = bulan;
    this.status = status;
}

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getBulan() {
    return bulan;
}

public void setBulan(String bulan) {
    this.bulan = bulan;
}

public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}
}