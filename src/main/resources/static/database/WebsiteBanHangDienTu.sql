CREATE DATABASE WebsiteBanHangDienTu

GO

USE WebsiteBanHangDienTu

GO

CREATE TABLE nguoi_dung(
	ma_nguoi_dung VARCHAR(50) NOT NULL PRIMARY KEY,
	mat_khau VARCHAR(100) NOT NULL,
	ho_ten NVARCHAR (200) NOT NULL,
	gioi_tinh BIT NOT NULL,
	ngay_sinh DATE,
	so_dien_thoai VARCHAR(15),
	dia_chi NVARCHAR(400),
	email VARCHAR(50) NOT NULL UNIQUE,
	hinh_anh NVARCHAR(100),
	ngay_dang_ky DATE NOT NULL,
	trang_thai BIT NOT NULL
)

CREATE TABLE vai_tro(
	ma_vai_tro VARCHAR(10) PRIMARY KEY NOT NULL,
	ten_vai_tro VARCHAR(40) NOT NULL
)

CREATE TABLE phan_quyen(
	ma_phan_quyen INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	ma_nd VARCHAR(50) NOT NULL,
	ma_vt VARCHAR(10) NOT NULL,
	FOREIGN KEY (ma_nd) REFERENCES nguoi_dung(ma_nguoi_dung) ON DELETE CASCADE,
	FOREIGN KEY (ma_vt) REFERENCES vai_tro(ma_vai_tro)
)

CREATE TABLE chung_loai(
	ma_chung_loai VARCHAR(20) PRIMARY KEY NOT NULL,
	ten_chung_loai NVARCHAR(100) NOT NULL
)

CREATE TABLE nha_cung_cap(
	ma_nha_cung_cap VARCHAR(20) PRIMARY KEY NOT NULL,
	ten_nha_cung_cap NVARCHAR(100) NOT NULL
)

CREATE TABLE thuoc_tinh_dac_biet(
	ma_thuoc_tinh_dac_biet VARCHAR(20) PRIMARY KEY NOT NULL,
	ten_thuoc_tinh_dac_biet NVARCHAR(100) NOT NULL
) 

CREATE TABLE san_pham(
	ma_san_pham VARCHAR(20) NOT NULL PRIMARY KEY,
	ten_san_pham NVARCHAR(100) NOT NULL,
	so_luong INT NOT NULL,
	gia_tien FLOAT NOT NULL,
	mo_ta NVARCHAR(200),
	hinh_anh NVARCHAR(100),
	trang_thai BIT,
	ma_cl VARCHAR(20) NOT NULL,
	ma_ncc VARCHAR(20) NOT NULL,
	ma_ttdb VARCHAR(20) NOT NULL,
	FOREIGN KEY (ma_cl) REFERENCES chung_loai(ma_chung_loai),
	FOREIGN KEY (ma_ncc) REFERENCES nha_cung_cap(ma_nha_cung_cap),
	FOREIGN KEY (ma_ttdb) REFERENCES thuoc_tinh_dac_biet(ma_thuoc_tinh_dac_biet)
)

CREATE TABLE gio_hang(
	ma_gio_hang INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	ma_nd VARCHAR(50) NOT NULL,
	ma_sp VARCHAR(20) NOT NULL,
	so_luong INT NOT NULL,
	FOREIGN KEY (ma_nd) REFERENCES nguoi_dung(ma_nguoi_dung),
	FOREIGN KEY (ma_sp) REFERENCES san_pham(ma_san_pham)
)

CREATE TABLE don_hang(
	ma_don_hang INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	ngay_lap_don DATE NOT NULL,
	dia_chi_giao NVARCHAR(200) NOT NULL,
	trang_thai NVARCHAR(50) NOT NULL,
	ma_nd VARCHAR(50) NOT NULL,
	FOREIGN KEY (ma_nd) REFERENCES nguoi_dung(ma_nguoi_dung)
)

CREATE TABLE don_hang_chi_tiet(
	ma_don_hang_chi_tiet  INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	so_luong INT NOT NULL,
	gia_tien FLOAT NOT NULL,
	ma_sp VARCHAR(20) NOT NULL,
	ma_dh INT NOT NULL,
	FOREIGN KEY (ma_sp) REFERENCES san_pham(ma_san_pham),
	FOREIGN KEY (ma_dh) REFERENCES don_hang(ma_don_hang) ON DELETE CASCADE
)

/*Thêm dữ liệu vào các bảng*/
INSERT INTO nguoi_dung VALUES
	('hoang0207', '02072003', N'Nguyễn Văn Kim Hoàng', 1, '2003-07-02', '0849314345', N'293 Nam Kỳ Khởi Nghĩa, TP.HCM', 'hoangnvkps24867@fpt.edu.vn', null, '2024-10-3', 1),
	('hoa232', '1234567', N'Lưu Đức Hòa', 1, '2003-01-03', '0979324347', N'85 Phan Kế Bính, Đa Kao, Quận 1, Hồ Chí Minh', 'hoa2222@gmail.com', null, '2024-10-3', 1),
	('thanh222', '222333', N'Nguyễn Văn Thanh', 1, '2003-11-30', '0849314345', N'142 Nguyễn Văn Công, Phường 3, Gò Vấp, Hồ Chí Minh', 'thanh23232@gmail.com', null, '2024-10-3', 1),
	('long888', '19102004', N'Trần Chiến Long', 1, '2003-03-02', '0908730881', N'345 Tô Ký, Trung Mỹ Tây, Quận 12, Hồ Chí Minh', 'long888@gmail.com', null, '2024-10-3', 1),
	('linh775', '232323', N'Nguyễn Kiều Thảo Linh', 0, '2003-05-06', '0909748663', N'194 Hoàng Văn Thụ, Phường 9, Phú Nhuận, Hồ Chí Minh', 'Linh775@gmail.com', null, '2024-10-3', 1),
	('Thang443', '222333', N'Trần Ngọc Thắng', 1, '2003-04-23', '0907574883', N'141 Lê Thị Nho, Trung Mỹ Tây, Quận 12, Hồ Chí Minh', 'Thang443@gmail.com', null, '2024-10-3', 1),
    ('user001', 'password001', N'Nguyễn Anh Dũng', 1, '2000-08-10', '0908000001', N'10 Lý Thái Tổ, Quận 10, TP.HCM', 'user001@gmail.com', null, '2024-07-01', 1),
    ('user002', 'password002', N'Hoàng Thị Ngọc', 0, '2000-09-05', '0908000002', N'12 Phan Đình Phùng, Quận 1, TP.HCM', 'user002@gmail.com', null, '2024-07-01', 1),
    ('user003', 'password003', N'Phạm Minh Hòa', 1, '1999-12-15', '0908000003', N'23 Võ Thị Sáu, Bình Thạnh, TP.HCM', 'user003@gmail.com', null, '2024-07-01', 1),
    ('user004', 'password004', N'Lê Thị Hồng', 0, '2001-02-20', '0908000004', N'15 Trường Chinh, Tân Bình, TP.HCM', 'user004@gmail.com', null, '2024-07-01', 1),
    ('user005', 'password005', N'Võ Đình Nam', 1, '2002-05-22', '0908000005', N'9 Tân Sơn Nhì, Tân Phú, TP.HCM', 'user005@gmail.com', null, '2024-07-01', 1),
    ('user006', 'password006', N'Nguyễn Thanh Trúc', 0, '2001-03-10', '0908000006', N'18 Phan Văn Trị, Gò Vấp, TP.HCM', 'user006@gmail.com', null, '2024-08-01', 1),
    ('user007', 'password007', N'Trần Hữu Phúc', 1, '2003-07-02', '0908000007', N'20 Nguyễn Trãi, Quận 5, TP.HCM', 'user007@gmail.com', null, '2024-08-01', 1),
    ('user008', 'password008', N'Lê Mai Lan', 0, '1998-11-18', '0908000008', N'27 Nguyễn Thị Minh Khai, Quận 3, TP.HCM', 'user008@gmail.com', null, '2024-08-01', 1),
    ('user009', 'password009', N'Phạm Văn Hiếu', 1, '2001-10-30', '0908000009', N'7 Lê Quang Định, Bình Thạnh, TP.HCM', 'user009@gmail.com', null, '2024-08-01', 1),
    ('user010', 'password010', N'Trần Thị Mai', 0, '2000-06-25', '0908000010', N'5 Cách Mạng Tháng 8, Quận 3, TP.HCM', 'user010@gmail.com', null, '2024-08-01', 1),
    ('user011', 'password011', N'Trịnh Văn Lâm', 1, '2002-04-12', '0908000011', N'1 Đinh Tiên Hoàng, Bình Thạnh, TP.HCM', 'user011@gmail.com', null, '2024-09-01', 1),
    ('user012', 'password012', N'Vũ Thị Thanh', 0, '1997-07-17', '0908000012', N'3 Hai Bà Trưng, Quận 1, TP.HCM', 'user012@gmail.com', null, '2024-09-01', 1),
    ('user013', 'password013', N'Nguyễn Tấn Phát', 1, '1999-10-08', '0908000013', N'4 Lê Văn Sỹ, Quận Phú Nhuận, TP.HCM', 'user013@gmail.com', null, '2024-09-01', 1),
    ('user014', 'password014', N'Phạm Thanh Sơn', 1, '2001-01-15', '0908000014', N'17 Võ Văn Tần, Quận 3, TP.HCM', 'user014@gmail.com', null, '2024-09-01', 1),
    ('user015', 'password015', N'Lê Quang Dũng', 1, '1998-08-18', '0908000015', N'8 Trần Hưng Đạo, Quận 5, TP.HCM', 'user015@gmail.com', null, '2024-09-01', 1),
    ('user016', 'password016', N'Đặng Thị Hòa', 0, '1996-12-23', '0908000016', N'6 Nguyễn Văn Cừ, Quận 5, TP.HCM', 'user016@gmail.com', null, '2024-10-01', 1),
    ('user017', 'password017', N'Thái Thanh Bình', 1, '1997-09-11', '0908000017', N'14 Nguyễn Huệ, Quận 1, TP.HCM', 'user017@gmail.com', null, '2024-10-01', 1),
    ('user018', 'password018', N'Lê Phương Dung', 0, '2002-03-25', '0908000018', N'25 Trần Não, Quận 2, TP.HCM', 'user018@gmail.com', null, '2024-10-01', 1)


INSERT INTO vai_tro VALUES
	('CUST', 'Customers'),
	('DIRE', 'Directors'),
	('STAF', 'Staffs')

INSERT INTO phan_quyen VALUES
	('hoang0207','DIRE'),
	('hoang0207', 'STAF'),
	('hoa232', 'STAF'),
	('thanh222', 'CUST'),
	('long888', 'CUST'),
	('linh775', 'CUST'),
	('Thang443', 'CUST'),
	('user001', 'STAF'),
    ('user002', 'CUST'),
    ('user003', 'CUST'),
    ('user004', 'STAF'),
    ('user005', 'CUST'),
    ('user006', 'STAF'),
    ('user007', 'CUST'),
    ('user008', 'CUST'),
    ('user009', 'STAF'),
    ('user010', 'CUST'),
    ('user011', 'STAF'),
    ('user012', 'CUST'),
    ('user013', 'CUST'),
    ('user014', 'STAF'),
    ('user015', 'CUST'),
    ('user016', 'STAF'),
    ('user017', 'CUST'),
    ('user018', 'STAF')
	
INSERT INTO chung_loai VALUES
	('CL001', N'Máy tính xách tay'),
	('CL002', N'Bo mạch chủ'),
	('CL003', N'Card màn hình'),
	('CL004', N'Chip xử lý CPU'),
	('CL005', N'Màn hình máy tính'),
	('CL006', N'Bàn phím'),
	('CL007', N'Màn hình LED'),
	('CL008', N'Máy in'),
	('CL009', N'USB Flash Drive'),
	('CL010', N'Tai nghe')

INSERT INTO nha_cung_cap VALUES
	('NCC001', N'Asus'),
	('NCC002', N'Msi'),
	('NCC003', N'Lenovo'),
	('NCC004', N'Intel'),
	('NCC005', N'AMD'),
	('NCC006', N'Samsung'),
	('NCC007', N'HP'),
	('NCC008', N'Corsair'),
	('NCC009', N'Sony'),
	('NCC010', N'Toshiba')


INSERT INTO thuoc_tinh_dac_biet VALUES
	('TTDB001',N'Hàng nổi bật'),
	('TTDB002',N'Hàng mới')

INSERT INTO san_pham VALUES
	('SP001', N'Laptop Asus VivoBook 14X', 10, 23490000, N'Laptop Asus VivoBook 14X là một trong những chiếc laptop đáng chú ý trên thị trường hiện nay, đặc biệt dành cho những người dùng yêu thích thiết kế đẹp mắt và trải nghiệm hình ảnh sắc nét.', 'hinh1.png', 1, 'CL001', 'NCC001', 'TTDB001'),
	('SP002', N'Laptop ASUS ProArt',  5, 69490000, N'Laptop ASUS ProArt là lựa chọn hàng đầu cho những ai đang tìm kiếm một chiếc máy tính xách tay cao cấp và tiện lợi.', 'hinh2.png', 1, 'CL001', 'NCC001', 'TTDB002'),
	('SP003', N'Laptop MSI Modern 14', 7, 9990000, N'Một trong những siêu phẩm sở hữu thiết kế năng động cấu hình mạnh mẽ đến từ con chip gen 12 đáp ứng mọi nhu cầu công việc hằng ngày.', 'hinh3.png', 1, 'CL001', 'NCC002', 'TTDB002'),
	('SP004', N'Laptop Lenovo V15 G3 ABA', 15, 9990000, N'Lenovo V15 G3 ABA là chiếc laptop văn phòng chỉn chu từ thiết kế, hoàn thiện về cấu hình sẽ không làm các bạn học sinh - sinh viên thất vọng.', 'hinh4.png', 1, 'CL001', 'NCC003', 'TTDB001'),
	('SP005', N'Bo mạch chủ ASUS Strix B760', 10, 4990000, N'Bộ phận tản nhiệt trên mainboard ASUS ROG STRIX B760 được bố trí hợp lý và tối ưu hóa các khe VRM và M2 để đảm bảo hiệu suất.', 'hinh5.png', 1, 'CL002', 'NCC001', 'TTDB002'),
	('SP006', N'Mainboard Gigabyte B760M GAMING X DDR4', 12, 5199000, N'một chiêc main boar cực kì mạnh mẽ hỗ trợ cho con chip của đội xanh với 4 khe ram tối đa tới 128gb giúp bạn thoải mái trong việc lựa chọn', 'hinh6.png', 1, 'CL002', 'NCC001', 'TTDB002'),
	('SP007', N'Card màn hình Asus TUF RTX 4070 SUPER-O12G GAMING', 11, 21999000, N'Chiếc card đồ họa cực kì mạnh mẽ tới từ nhà NVDIA với hiệu năng vượt trội và giá thành lại không quá đắt như 4070 ti', 'hinh7.png', 1, 'CL003', 'NCC001', 'TTDB002'),
	('SP008', N'Card màn hình Asus DUAL RX 6600 8GB-V3', 13, 5499000, N'Một chiếc card quốc dân ở tầm giá rẻ đến từ đội đỏ sản phẩm này mang 1 hiệu năng ấn tượng so với tầm giá của nó', 'hinh8.png', 1, 'CL003', 'NCC001', 'TTDB002'),
	('SP009', N'CPU Intel Core i7-14700K', 18, 12999000, N'Chiếc CPU mạnh mẽ đến từ đội xanh với hiệu năng vượt trội đảm bảo mượt mà khi trải nghiệm', 'hinh9.png', 1, 'CL004', 'NCC004', 'TTDB001'),
    ('SP010', N'CPU AMD Ryzen 9 5900X', 15, 14499000, N'Chiếc CPU này có 1 hiệu năng vượt trội đảm bảo mang lại trải nghiệm làm việc và học tập tốt nhất', 'hinh10.png', 1, 'CL004', 'NCC005', 'TTDB001'),
	('SP011', N'Màn hình Samsung Odyssey G7', 10, 13990000, N'Màn hình cong Samsung Odyssey G7 với độ phân giải 2560x1440 và tần số quét 240Hz.', 'hinh11.png', 1, 'CL005', 'NCC006', 'TTDB001'),
	('SP012', N'Màn hình LG 27GN950-B', 8, 14990000, N'Màn hình 4K với độ chính xác màu sắc tuyệt vời, dành cho game thủ và nhà sáng tạo nội dung.', 'hinh12.png', 1, 'CL005', 'NCC007', 'TTDB001'),
	('SP013', N'Bàn phím cơ Logitech G Pro X', 15, 2990000, N'Bàn phím cơ Logitech G Pro X với công nghệ switch hot-swappable, cho phép thay đổi công tắc nhanh chóng.', 'hinh13.png', 1, 'CL006', 'NCC008', 'TTDB001'),
	('SP014', N'Bàn phím Corsair K95 RGB', 20, 5490000, N'Bàn phím cơ với đèn nền RGB và các phím chức năng macro, lý tưởng cho game thủ.', 'hinh14.png', 1, 'CL006', 'NCC008', 'TTDB001'),
	('SP015', N'Màn hình LED BenQ EL2870U', 5, 8990000, N'Màn hình LED 28 inch 4K HDR với độ sáng cao, rất phù hợp cho công việc đồ họa và giải trí.', 'hinh15.png', 1, 'CL007', 'NCC007', 'TTDB002'),
	('SP016', N'Máy in HP LaserJet Pro MFP M428fdw', 12, 10990000, N'Máy in đa chức năng HP với khả năng in, quét, sao chép và fax, phù hợp cho văn phòng nhỏ.', 'hinh16.png', 1, 'CL008', 'NCC009', 'TTDB002'),
	('SP017', N'USB Flash Drive Sandisk Ultra 128GB', 25, 599000, N'USB Flash Drive Sandisk Ultra với dung lượng 128GB, tốc độ truyền tải dữ liệu nhanh chóng.', 'hinh17.png', 1, 'CL009', 'NCC010', 'TTDB001'),
	('SP018', N'Tai nghe Sony WH-1000XM5', 30, 9999000, N'Tai nghe Sony WH-1000XM5 với công nghệ chống ồn tuyệt vời và chất lượng âm thanh xuất sắc.', 'hinh18.png', 1, 'CL010', 'NCC009', 'TTDB002'),
	('SP019', N'Màn hình ASUS TUF VG27AQ', 10, 8990000, N'Màn hình ASUS TUF với độ phân giải 2560x1440 và tần số quét 165Hz, lý tưởng cho game thủ.', 'hinh19.png', 1, 'CL005', 'NCC001', 'TTDB002'),
	('SP020', N'Màn hình Acer Predator XB273K', 7, 15990000, N'Màn hình 4K với công nghệ G-Sync, cung cấp trải nghiệm game mượt mà.', 'hinh20.png', 1, 'CL005', 'NCC007', 'TTDB002'),
	('SP021', N'Bàn phím Razer Huntsman Mini', 14, 3690000, N'Bàn phím cơ với switch quang học và kích thước 60%, tiện dụng cho game thủ.', 'hinh21.png', 1, 'CL006', 'NCC008', 'TTDB001'),
	('SP022', N'Máy in Canon PIXMA TS9120', 6, 5490000, N'Máy in đa chức năng với khả năng in ảnh chất lượng cao và hỗ trợ kết nối không dây.', 'hinh22.png', 1, 'CL008', 'NCC009', 'TTDB001'),
	('SP023', N'USB Flash Drive Kingston DataTraveler 64GB', 18, 399000, N'USB Kingston DataTraveler 64GB, dung lượng lớn, thích hợp lưu trữ và chia sẻ dữ liệu nhanh chóng.', 'hinh23.png', 1, 'CL009', 'NCC010', 'TTDB001'),
	('SP024', N'Tai nghe Logitech G Pro X Wireless', 12, 4990000, N'Tai nghe không dây với âm thanh 7.1 và chất lượng micro xuất sắc, lý tưởng cho game thủ chuyên nghiệp.', 'hinh24.png', 1, 'CL010', 'NCC008', 'TTDB002'),
	('SP025', N'Màn hình Dell U2723QE', 9, 13990000, N'Màn hình 27 inch 4K với độ chính xác màu sắc tuyệt vời, phù hợp cho các chuyên gia đồ họa.', 'hinh25.png', 1, 'CL005', 'NCC001', 'TTDB001'),
	('SP026', N'Màn hình LG 34WN80C-B', 11, 10990000, N'Màn hình cong 34 inch với độ phân giải 1440p, lý tưởng cho những người làm việc đa nhiệm.', 'hinh26.png', 1, 'CL005', 'NCC007', 'TTDB002'),
	('SP027', N'Bàn phím Logitech G915 TKL', 13, 4990000, N'Bàn phím cơ với công nghệ switch quang học và đèn nền RGB tuyệt đẹp.', 'hinh27.png', 1, 'CL006', 'NCC008', 'TTDB002'),
	('SP028', N'Máy in Epson EcoTank L3150', 8, 4490000, N'Máy in phun màu đa chức năng Epson với công nghệ EcoTank tiết kiệm mực và chi phí in ấn.', 'hinh28.png', 1, 'CL008', 'NCC009', 'TTDB001'),
	('SP029', N'USB Flash Drive Transcend JetFlash 520', 20, 199000, N'USB Flash Drive Transcend với thiết kế nhỏ gọn, tốc độ truyền tải nhanh chóng.', 'hinh29.png', 1, 'CL009', 'NCC010', 'TTDB001'),
	('SP030', N'Tai nghe SteelSeries Arctis 7', 10, 5990000, N'Tai nghe không dây với âm thanh vòm 7.1 và micro có khả năng giảm ồn, hoàn hảo cho game thủ.', 'hinh30.png', 1, 'CL010', 'NCC008', 'TTDB001'),
	('SP031', N'Màn hình AOC C27G1', 5, 6290000, N'Màn hình cong với độ phân giải 1920x1080, tần số quét 165Hz, lý tưởng cho game thủ.', 'hinh31.png', 1, 'CL005', 'NCC001', 'TTDB001'),
	('SP032', N'Màn hình MSI Optix MAG272C', 8, 7490000, N'Màn hình MSI Optix với độ phân giải 1920x1080, hỗ trợ AMD FreeSync và tần số quét 165Hz.', 'hinh32.png', 1, 'CL005', 'NCC002', 'TTDB002'),
	('SP033', N'Bàn phím Corsair K70 RGB MK.2', 18, 4790000, N'Bàn phím cơ với switch Cherry MX và đèn nền RGB, nổi bật với khả năng ghi nhớ các phím macro.', 'hinh33.png', 1, 'CL006', 'NCC008', 'TTDB001'),
	('SP034', N'Máy in Brother HL-L2375DW', 22, 3990000, N'Máy in laser đơn sắc hỗ trợ kết nối không dây, phù hợp cho văn phòng nhỏ.', 'hinh34.png', 1, 'CL008', 'NCC009', 'TTDB002'),
	('SP035', N'USB Flash Drive Patriot Supersonic Rage 128GB', 10, 799000, N'USB Flash Drive Patriot với tốc độ đọc lên đến 400MB/s, lý tưởng cho việc chuyển các tệp lớn nhanh chóng.', 'hinh35.png', 1, 'CL009', 'NCC010', 'TTDB002')

INSERT INTO gio_hang VALUES
	('thanh222', 'SP001', 1),
	('hoang0207', 'SP003', 1),
	('long888', 'SP006', 1),
	('Thang443', 'SP005', 1)


INSERT INTO don_hang VALUES
	('2024-6-3', N'293 Nam Kỳ Khởi Nghĩa, TP.HCM', N'Chờ xác nhận', 'hoang0207'),
	('2024-6-3', N'85 Phan Kế Bính, Đa Kao, Quận 1, Hồ Chí Minh', N'Đang xử lý', 'hoa232'),
	('2024-6-3', N'85 Phan Kế Bính, Đa Kao, Quận 1, Hồ Chí Minh', N'Đang vận chuyển', 'hoa232'),
	('2024-6-3', N'142 Nguyễn Văn Công, Phường 3, Gò Vấp, Hồ Chí Minh', N'Đã giao', 'thanh222'),
	('2024-7-3', N'142 Nguyễn Văn Công, Phường 3, Gò Vấp, Hồ Chí Minh', N'Đã giao', 'thanh222'),
	('2024-7-3', N'345 Tô Ký, Trung Mỹ Tây, Quận 12, Hồ Chí Minh', N'Đã giao', 'long888'),
	('2024-7-3', N'345 Tô Ký, Trung Mỹ Tây, Quận 12, Hồ Chí Minh', N'Đã giao', 'long888'),
	('2024-7-3', N'141 Lê Thị Nho, Trung Mỹ Tây, Quận 12, Hồ Chí Minh', N'Đã hủy', 'thang443'),
	('2024-8-3', N'194 Hoàng Văn Thụ, Phường 9, Phú Nhuận, Hồ Chí Minh', N'Đã giao', 'linh775'),
	('2024-8-5', N'345 Tô Ký, Trung Mỹ Tây, Quận 12, Hồ Chí Minh', N'Đã giao', 'long888'),
	('2024-8-8', N'142 Nguyễn Văn Công, Phường 3, Gò Vấp, Hồ Chí Minh', N'Đã hủy', 'thanh222'),
	('2024-9-3', N'85 Phan Kế Bính, Đa Kao, Quận 1, Hồ Chí Minh', N'Đang vận chuyển', 'hoa232'),
	('2024-9-3', N'293 Nam Kỳ Khởi Nghĩa, TP.HCM', N'Chờ xác nhận', 'hoang0207'),
	('2024-10-5', N'345 Tô Ký, Trung Mỹ Tây, Quận 12, Hồ Chí Minh', N'Đã giao', 'long888'),
	('2024-10-3', N'85 Phan Kế Bính, Đa Kao, Quận 1, Hồ Chí Minh', N'Đang vận chuyển', 'hoa232')

INSERT INTO don_hang_chi_tiet VALUES
	(1, 23490000, 'SP001', 1),
	(1, 4990000, 'SP005', 2),
	(2, 9990000, 'SP004', 3),
	(2, 9990000, 'SP004', 4),
	(3, 4990000, 'SP005', 5),
	(1, 23490000, 'SP001', 5),
	(1, 69490000, 'SP002', 6),
	(1, 9990000, 'SP003', 7),
	(2, 4990000, 'SP004', 8),
	(3, 16497000, 'SP006', 9),
	(2, 10998000, 'SP008', 10),
	(1, 9990000, 'SP004', 11),
	(1, 5499000, 'SP008', 12),
	(1, 69490000, 'SP002', 13),
	(3, 16497000, 'SP008', 14),
	(1, 12999000, 'SP009', 15)


SELECT 	
	MONTH(ngay_lap_don) AS thang,
	YEAR(ngay_lap_don) AS nam,
	SUM(so_luong*gia_tien) AS tong_doanh_thu
FROM don_hang 
	JOIN don_hang_chi_tiet ON don_hang.ma_don_hang = don_hang_chi_tiet.ma_dh
WHERE trang_thai LIKE N'%Đã giao%'
GROUP BY 
	MONTH(ngay_lap_don), 
	YEAR(ngay_lap_don)
ORDER BY nam, thang

SELECT * 
FROM san_pham sp 
JOIN chung_loai cl ON sp.ma_cl = cl.ma_chung_loai
