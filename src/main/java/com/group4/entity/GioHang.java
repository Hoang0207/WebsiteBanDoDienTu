package com.group4.entity;

import lombok.Data;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "gio_hang")
public class GioHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_gio_hang", nullable = false)
    private Integer maGioHang;

    @Column(name = "ma_nd", nullable = false, length = 50)
    private String maNd;

    @Column(name = "ma_sp", nullable = false, length = 20)
    private String maSp;

    @Column(name = "so_luong", nullable = false)
    private Integer soLuong;

    // Mối quan hệ với NguoiDung
    @ManyToOne
    @JoinColumn(name = "ma_nd", insertable = false, updatable = false)
    private NguoiDung nguoiDung;

    // Mối quan hệ với SanPham
    @ManyToOne
    @JoinColumn(name = "ma_sp", insertable = false, updatable = false)
    private SanPham sanPham;
}