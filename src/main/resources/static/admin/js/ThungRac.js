app.controller('ThungRacCtrl', function($http, $scope) {
	
	$scope.items = []
	
	$scope.title = null
	
	$scope.default_image_src = "/api/file/SanPham/defaultProductPhoto.png"
	
	//Mở tab thùng rác của sản phẩm
	$scope.open_tab_sanPham = function(){
		$scope.items = []
		$scope.title="sản phẩm"
		$scope.load_all_sp_deleted()
	}
	
	//Mở tab thùng rác của người dùng
	$scope.open_tab_nguoiDung = function(){
		$scope.items = []
		$scope.title="người dùng"
		$scope.load_all_nd_deleted()
	}
	
	//Mở tab thùng rác của đơn hàng
	$scope.open_tab_donHang = function(){
		$scope.items = []
		$scope.title="đơn hàng"
		$scope.load_all_dh_deleted()
	}
	
	//Load tất cả những sản phẩm đã bị vô hiệu hóa
	$scope.load_all_sp_deleted = function(){
		var url = `${host}/SanPham/TrangThai/false`
		$http.get(url).then(resp => {
			$scope.items = resp.data
			console.log("Success load all deleted SanPham")
		}).catch(error => {
			console.log("Error load all deleted SanPham", error)
		})
	}
	
	//Load tất cả những người dùng đã bị vô hiệu hóa
	$scope.load_all_nd_deleted = function(){
		var url = `${host}/NguoiDung/TrangThai/false`
		$http.get(url).then(resp => {
			$scope.items = resp.data
			console.log("Success load all Nd deleted")
		}).catch(error => {
			console.log("Error laod all Nd deleted",error)
		})
	}
	
	//Load tất cả những đơn hàng đã bị vô hiệu hóa
	$scope.load_all_dh_deleted = function(){
		var url = `${host}/DonHang/IsActive/false`
		$http.get(url).then(resp => {
			$scope.items = resp.data
			console.log("Success load all Dh deleted")
		}).catch(error => {
			console.log("Error load all Dh deleted",error)
		})
	}
	
	//Khôi phục lại trạng thái của sản phẩm đã bị vô hiệu hóa thành đã kích hoạt
	$scope.restore_sp = function(maSp){
		var url = `${host}/SanPham/Restore/${maSp}`
		$http.put(url).then(resp => {
			index = $scope.items.findIndex(item => item.maSanPham == resp.data.maSanPham)
			$scope.items.splice(index,1)
			console.log(resp)
			swal("Thành công !","Khôi phục sản phẩm thành công","success")
		}).catch(error => {
			console.log("Error restore SanPham",error)
		})
	}
	
	//Khôi phục lại trạng thái của người dùng thành đã kích hoạt
	$scope.restore_nd = function(maNd){
		var url = `${host}/NguoiDung/Restore/${maNd}`
		$http.put(url).then(resp => {
			index = $scope.items.findIndex(item => item.maNguoiDung = resp.data.maNguoiDung)
			$scope.items.splice(index,1)
			swal("Thành công !","Khôi phục người dùng thành công","success")
		}).catch(error => {
			console.log("Error restore NguoiDung",error)
		})
	}
	
	//Khôi phục lại trạng thái của đơn hàng thành đã kích hoạt
	$scope.restore_dh = function(maDh){
		var url = `${host}/DonHang/Restore/${maDh}`
		$http.put(url).then(resp => {
			index = $scope.items.findIndex(item => item.maDonHang = resp.data.maDonHang)
			$scope.items.splice(index,1);
			swal("Thành công !","Khôi phục đơn hàng thành công","success")
		}).catch(error => {
			console.log("Error restore DonHang",error)
		})
	}
	
	//Xem chi tiết sản phẩm theo id
	$scope.watch_sp = function(maSp){
		var url = `${host}/SanPham/${maSp}`
		$http.get(url).then(resp => {
			$scope.form = resp.data
			$scope.tenChungLoai = resp.data.chungLoai.tenChungLoai
			$scope.tenNhaCungCap = resp.data.nhaCungCap.tenNhaCungCap
			$scope.tenThuocTinhDacBiet = resp.data.thuocTinhDacBiet.tenThuocTinhDacBiet
			console.log("Success load SanPham by id")
		}).catch(error => {
			console.log("Error load SanPham by id")
		})
	}
	
	//page start
	$scope.limit = 7
	$scope.start = 0
	//page end
	
	//Bắt sự kiện khi bấm vào các thẻ theo dõi đơn hàng hoặc thùng rác
	function attachOrderButtonEvents() {
		//Lấy tất cả các thẻ con của titleDonHangText
		const divElementDonHang = document.querySelectorAll('.titleDonHangText>div');
		//Thêm sự kiện click cho từng thẻ
		divElementDonHang.forEach(
			div => {
				div.addEventListener('click', function() {
					//Xóa class selected từ tất cả các thẻ
					divElementDonHang.forEach(el => el.classList.remove('selectedTitleDonHang'))
	
					//Thêm class selected cho thẻ được nhấn
					this.classList.add('selectedTitleDonHang')
				})
			}
		)
	}
	
	//Tự động chạy khi mở tab ThungRac
	$scope.open_tab_sanPham()
	attachOrderButtonEvents()
})