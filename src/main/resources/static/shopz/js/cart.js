/* Cotroller for cart.html */
app.controller("cartCtrl", function($http, $scope) {

	let cart_host = "http://localhost:8080/api/GioHang"

	$scope.user = null

	$scope.items = []

	//Lấy thông tin người dùng định nghĩa cho $scope.user
	$scope.get_user = function() {
		var url = "http://localhost:8080/api/NguoiDung/Session"
		$http.get(url).then(resp => {
			$scope.user = resp.data
			console.log("Success get user in session")
			$scope.load_all_by_maNd()
		}).catch(error => {
			console.log("Error get user in session", error)
		})
	}

	//Load tất cả giỏ hàng của người dùng được chỉ định
	$scope.load_all_by_maNd = function() {
		var url = cart_host + "/" + $scope.user.maNguoiDung
		$http.get(url).then(resp => {
			$scope.items = resp.data
			console.log("Success load GioHang by MaNd")
		}).catch(error => {
			console.log("Errro load GioHang by MaNd", error)
		})
	}

	//Cập nhật giỏ hàng
	$scope.update_gh = function(maGh, maNd, maSp, soLuong, nguoiDung, sanPham) {
		var url = `${cart_host}`
		var gh = { maGioHang: maGh, maNd: maNd, maSp: maSp, soLuong: soLuong, nguoiDung: nguoiDung, sanPham: sanPham }
		$http.put(url, gh).then(resp => {
			var index = $scope.items.findIndex(item => item.maGioHang == maGh)
			$scope.items[index] = resp.data
			//$scope.load_all_by_maNd()
			console.log("Success update GioHang")
		}).catch(error => {
			console.log("Error update GioHang", error)
		})

	}

	//Xóa giỏ hàng theo id
	$scope.delete = function(maGh) {
		swal({
			//title: "Bạn có chắc chắn không ?",
			text: "Bạn có chắc muốn xóa sản phẩm này khỏi giỏ hàng ?",
			icon: "warning",
			buttons: true,
			dangerMode: true,
		}).then((willDelete) => {
			if (willDelete) {
				var url = `${cart_host}/${maGh}`
				$http.delete(url).then(() => {
					var index = $scope.items.findIndex(item => item.maGioHang == maGh)
					$scope.items.splice(index, 1)
					swal("Xóa thành công", { icon: "success", });
				}).catch(error => {
					console.log("Error delete GioHang", error)
				})
			} else {
				swal("Không thực hiện thao tác xóa")
			}
		})
	}

	//Tự động chạy khi vào giỏ hàng
	$scope.get_user()

})