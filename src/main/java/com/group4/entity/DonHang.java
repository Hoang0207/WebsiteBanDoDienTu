package com.group4.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "don_hang")
public class DonHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_don_hang", nullable = false)
    private Integer maDonHang;

    @Temporal(TemporalType.DATE)
    @Column(name = "ngay_lap_don", nullable = false)
    private Date ngayLapDon;

    @Column(name = "dia_chi_giao", nullable = false, length = 200)
    private String diaChiGiao;

    @Column(name = "trang_thai", nullable = false, length = 50)
    private String trangThai;

    @Column(name = "ma_nd", nullable = false, length = 50)
    private String maNd;

    // Mối quan hệ với NguoiDung
    @ManyToOne
    @JoinColumn(name = "ma_nd", insertable = false, updatable = false)
    private NguoiDung nguoiDung;

    // Mối quan hệ với DonHangChiTiet
    @OneToMany(mappedBy = "donHang", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DonHangChiTiet> donHangChiTiets;
}
