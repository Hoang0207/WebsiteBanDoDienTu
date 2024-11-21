package com.group4.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "san_pham")
public class SanPham {

    @Id
    @Column(name = "ma_san_pham", nullable = false, length = 20)
    private String maSanPham;

    @Column(name = "ten_san_pham", nullable = false, length = 100)
    private String tenSanPham;

    @Column(name = "so_luong", nullable = false)
    private Integer soLuong;

    @Column(name = "gia_tien", nullable = false)
    private Float giaTien;

    @Column(name = "mo_ta", length = 200)
    private String moTa;

    @Column(name = "hinh_anh", length = 100)
    private String hinhAnh;

    @Column(name = "trang_thai")
    private Boolean trangThai=true;

    @Column(name = "ma_cl", nullable = false, length = 20)
    private String maCl;

    @Column(name = "ma_ncc", nullable = false, length = 20)
    private String maNcc;

    @Column(name = "ma_ttdb", nullable = false, length = 20)
    private String maTtdb;

    // Mối quan hệ với ChungLoai
    @ManyToOne
    @JoinColumn(name = "ma_cl", insertable = false, updatable = false)
    private ChungLoai chungLoai;

    // Mối quan hệ với NhaCungCap
    @ManyToOne
    @JoinColumn(name = "ma_ncc", insertable = false, updatable = false)
    private NhaCungCap nhaCungCap;

    // Mối quan hệ với ThuocTinhDacBiet
    @ManyToOne
    @JoinColumn(name = "ma_ttdb", insertable = false, updatable = false)
    private ThuocTinhDacBiet thuocTinhDacBiet;

    // Mối quan hệ với GioHang
    @JsonIgnore
    @OneToMany(mappedBy = "sanPham", cascade = CascadeType.ALL)
    private Set<GioHang> gioHangs;

    // Mối quan hệ với DonHangChiTiet
    @JsonIgnore
    @OneToMany(mappedBy = "sanPham", cascade = CascadeType.ALL)
    private Set<DonHangChiTiet> donHangChiTiets;
    
    
}
