package com.group4.entity;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter @Setter
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
    
    @Column(name = "is_active", nullable = false)
    private Boolean isActive=true;

    // Mối quan hệ với NguoiDung
    @ManyToOne
    @JoinColumn(name = "ma_nd", insertable = false, updatable = false)
    private NguoiDung nguoiDung;

    // Mối quan hệ với DonHangChiTiet
    @JsonIgnore
    @OneToMany(mappedBy = "donHang", cascade = CascadeType.ALL)
    private Set<DonHangChiTiet> donHangChiTiets;
}
