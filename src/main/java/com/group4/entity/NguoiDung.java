package com.group4.entity;

import lombok.*;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "nguoi_dung")
public class NguoiDung {

    @Id
    @Column(name = "ma_nguoi_dung", nullable = false, length = 50)
    private String maNguoiDung;

    @Column(name = "mat_khau", nullable = false, length = 50)
    private String matKhau;

    @Column(name = "ho_ten", nullable = false, length = 100)
    private String hoTen;

    @Column(name = "gioi_tinh", nullable = false)
    private Boolean gioiTinh;

    @Temporal(TemporalType.DATE)
    @Column(name = "ngay_sinh")
    private Date ngaySinh;

    @Column(name = "so_dien_thoai", length = 15)
    private String soDienThoai;

    @Column(name = "dia_chi", length = 200)
    private String diaChi;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "hinh_anh", length = 100)
    private String hinhAnh;

    @Temporal(TemporalType.DATE)
    @Column(name = "ngay_dang_ky", nullable = false)
    private Date ngayDangKy;

    @Column(name = "trang_thai", nullable = false)
    private Boolean trangThai=true;
    
    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    // Mối quan hệ với GioHang
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "nguoiDung", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<GioHang> gioHangs;

    // Mối quan hệ với DonHang
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "nguoiDung", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<DonHang> donHangs;
    
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "nguoiDung", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<PhanQuyen> phanQuyens;
  
    /* Old main
    @Id
    @Column(name = "ma_nguoi_dung", nullable = false, length = 50)
    private String maNguoiDung;

    @Column(name = "mat_khau", nullable = false, columnDefinition = "TEXT")
    private String matKhau;

    @Column(name = "ho_ten", nullable = false, length = 100)
    private String hoTen;

    @Column(name = "gioi_tinh", nullable = false)
    private Boolean gioiTinh;

    @Temporal(TemporalType.DATE)
    @Column(name = "ngay_sinh")
    private Date ngaySinh;

    @Column(name = "so_dien_thoai", length = 15)
    private String soDienThoai;

    @Column(name = "dia_chi", length = 200)
    private String diaChi;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "hinh_anh", length = 100)
    private String hinhAnh;

    @Temporal(TemporalType.DATE)
    @Column(name = "ngay_dang_ky", nullable = false)
    private Date ngayDangKy;

    @Column(name = "trang_thai", nullable = false)
    private Boolean trangThai=true;
    
    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    // Mối quan hệ với GioHang
    @JsonIgnore
    @OneToMany(mappedBy = "nguoiDung", cascade = CascadeType.ALL)
    private Set<GioHang> gioHangs;

    // Mối quan hệ với DonHang
    @JsonIgnore
    @OneToMany(mappedBy = "nguoiDung", cascade = CascadeType.ALL)
    private Set<DonHang> donHangs;
    
    @JsonIgnore
    @OneToMany(mappedBy = "nguoiDung", cascade = CascadeType.ALL)
    private Set<PhanQuyen> phanQuyens;
    */
}