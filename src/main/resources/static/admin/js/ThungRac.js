app.controller('ThungRacCtrl', function($http, $scope) {
	
	$scope.items = []
	
	$scope.title = null
	
	$scope.default_image_src = "/api/file/SanPham/defaultProductPhoto.png"
	
	//Load tất cả những sản phẩm đã bị vô hiệu hóa
	$scope.load_all_sp_deleted = function(){
		var url = `${host}/SanPham/TrangThai/false`
		$http.get(url).then(resp => {
			$scope.title="sản phẩm"
			$scope.items = resp.data
			console.log("Success load all deleted SanPham")
		}).catch(error => {
			console.log("Error load all deleted SanPham", error)
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
	$scope.load_all_sp_deleted()
	attachOrderButtonEvents()
})