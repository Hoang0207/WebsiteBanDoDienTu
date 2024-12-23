app.controller('QuanLyNguoiDungCtrl', function($http, $scope) {

	$scope.items = []

	$scope.update = false

	$scope.file = null

	$scope.temp_image_data = null

	$scope.roles = []

	$scope.authority = []

	//Ham reset
	$scope.reset = function() {
		$scope.temp_image_data = null
		$scope.file = null
		$scope.update = false
		$scope.authority = []
		$scope.form = { ngayDangKy: new Date(), gioiTinh: true }
		$scope.load_all_role()
	}

	//Gán dữ liệu cho roles
	$scope.load_all_role = function() {
		var url = `${host}/VaiTro`
		$http.get(url).then(resp => {
			$scope.roles = resp.data
			console.log("Success get all roles")
		}).catch(error => {
			console.log("Error get all roles", error)
		})
	}

	//Bắt sự kiện khi nhấp vào check box vai trò
	$scope.role_edit = function(maNd, role) {
		var author = $scope.authority_of(maNd, role)
		if (author) { // Có sẵn quyền => xóa quyền
			var url = `${host}/PhanQuyen/${author.maPhanQuyen}`
			$http.delete(url).then(() => {
				swal("Xóa quyền thành công", { icon: "success", })
				$scope.get_authority_by_maNd(maNd)
			}).catch(error => {
				console.log("Error delete author", error)
			})
		} else { //Chưa có quyền => cấp quyền
			$http.post(`${host}/PhanQuyen`, { maNd: maNd, maVt: role.maVaiTro }).then(resp => {
				swal("Cấp quyền thành công", { icon: "success", })
				$scope.get_authority_by_maNd(maNd)
			}).catch(error => {
				console.log("Error create authority", error)
			})
		}
	}

	//Tìm kiếm phân quyền theo mã người dùng
	$scope.get_authority_by_maNd = function(maNd) {
		var url = `${host}/PhanQuyen/${maNd}`
		$http.get(url).then(resp => {
			$scope.authority = resp.data
			console.log("Succes get PhanQuyen by MaNd")
		}).catch(error => {
			cosole.log("Error get PhanQuyen by MaNd", error)
		})
	}

	//Kiểm tra người dùng có vai trò này ko để check vào checkbox
	$scope.authority_of = function(maNd, role) {
		if ($scope.authority) {
			return $scope.authority.find(ur => ur.nguoiDung.maNguoiDung == maNd && ur.vaiTro.maVaiTro == role.maVaiTro)
		}
	}

	//load tat ca nguoi dung
	$scope.load_all = function() {
		var url = `${host}/NguoiDung/TrangThai/true`
		$http.get(url).then(resp => {
			$scope.items = resp.data
			$scope.init_page()
			console.log("Success load all NguoiDung")
		}).catch(error => {
			console.log("Error load all NguoiDung", error)
		})
	}

	//load nguoi dung theo id
	$scope.load_by_id = function(maNd) {
		var url = `${host}/NguoiDung/${maNd}`
		$http.get(url).then(resp => {
			$scope.reset()
			$scope.form = resp.data
			$scope.get_authority_by_maNd(maNd)
			$scope.form.ngayDangKy = new Date(resp.data.ngayDangKy) //Vì input có type là date nên phải truyền vào 1 Date
			$scope.form.ngaySinh = new Date(resp.data.ngaySinh)
			$scope.update = true
		}).catch(error => {
			console.log("Error load NguoiDung by id", error)
		})
	}

	//Bắt sự kiện khi nhấp vào hình
	$scope.image_click = function() {
		document.getElementById("fileHinh").click()
	}

	//Bắt sự kiện để hình ảnh thay đổi theo được chọn
	document.getElementById('fileHinh').addEventListener('change', function(event) {
		var input = event.target;
		if (input.files && input.files[0]) {
			$scope.file = input.files[0];
			var fileName = $scope.file.name; // Tên file bao gồm cả đuôi, ví dụ: "example.png"
			console.log("File name choose:", fileName);
			// Đọc file để hiển thị ảnh base64
			var reader = new FileReader()
			reader.onload = function(e) {
				$scope.$apply(function() {
					$scope.temp_image_data = e.target.result; // Cập nhật hiển thị tạm thời
				})
			}
			reader.readAsDataURL(input.files[0]);
		}
	})

	//Hàm lưu ảnh về
	$scope.save_image = function() {
		// Nếu thành công, gọi resolve() hoặc reject() nếu có lỗi
		return new Promise((resolve, reject) => {
			//Kiểm tra xem có chọn hình không (Không tính hình hiển thị sẵn)
			if ($scope.file && $scope.temp_image_data) {
				//Tạo ra param có tên là "file" và dữ liệu là $scope.file
				var formData = new FormData()
				formData.append("file", $scope.file)
				//Gửi file lên server qua api
				var url = `${host}/file/NguoiDung`
				$http.post(url, formData, {
					transformRequest: angular.identity, // Tắt tuần tự hóa mặc định
					headers: { 'Content-Type': undefined } // Để trình duyệt tự đặt Content-Type
				}).then(resp => {
					$scope.form.hinhAnh = $scope.file.name
					console.log("Success upload image file")
					resolve()
				}).catch(error => {
					console.log("Error upload image file", error)
					reject()
				})
			} else {
				resolve()
			}
		})
	}

	//Hàm xóa ảnh
	$scope.delete_image = function(image_name) {
		var url = `${host}/file/NguoiDung/${image_name}`
		$http.delete(url).then(resp => {
			console.log("Success delete NguoiDung Image", image_name)
		}).catch(error => {
			console.log("Error delete NguoiDung Image", error)
		})
	}

	//Thêm người dùng mới
	$scope.create = function() {
		$scope.save_image().then(() => {
			var url = `${host}/NguoiDung`
			$http.post(url, $scope.form).then(resp => {
				$scope.load_all()
				var email = $scope.form.email
				$scope.form.maNguoiDung = email.substring(0,email.indexOf("@"))
				$scope.update = true
				swal("Thành công !", "Thêm người dùng thành công", "success")
			}).catch(error => {
				if(error.status == 400){
					swal ("Thất bại !","Người dùng không được thêm thành công do email đã tồn tại","error")
				}
				console.log("Error create NguoiDung", error)
			})
		})
	}

	//Cập nhật người dùng
	$scope.update_nd = function(maNd, old_image) {
		var url = `${host}/NguoiDung/${maNd}`
		$scope.save_image().then(() => {
			$http.put(url, $scope.form).then(resp => {
				if (old_image && old_image != resp.data.hinhAnh) {
					$scope.delete_image(old_image)
				}
				$scope.load_all()
				swal("Thành công !", "Cập nhật người dùng thành công", "success")
			}).catch(error => {
				console.log("Error update NguoiDung", error)
			})
		})
	}

	//Xóa người dùng
	$scope.delete = function(maNd) {
		swal({
			text: "Bạn có muốn xóa người dùng này không ?",
			icon: "warning",
			buttons: true,
			dangerMode: true,
		}).then((willDelete) => {
			if (willDelete) {
				var url = `${host}/NguoiDung/${maNd}`
				$http.delete(url).then(resp => {
					$scope.load_all()
					swal("Xóa thành công !","Bạn vẫn có thể khôi phục lại trong thùng rác nếu muốn","success")
				}).catch(error => {
					console.log("Error delete NguoiDung", error)
				})
			} else {
				swal("Không thực hiện thao tác xóa")
			}
		})
	}
	
	//Phân trang start
	$scope.page = 1
	$scope.limit = 7
	$scope.start = ($scope.page-1) * $scope.limit
	//$scope.total_page = Math.ceil($scope.items.length / $scope.limit)
	//$scope.number_of_page = Array.from(Array($scope.total_page).keys())
	$scope.init_page = function(){
		$scope.total_page = Math.ceil($scope.items.length / $scope.limit)
		$scope.number_of_page = Array.from(Array($scope.total_page).keys())
		$scope.page = 1
		$scope.start = ($scope.page-1) * $scope.limit
	}
	$scope.change_page = function(i){
		$scope.page = i
		$scope.start = ($scope.page-1)*$scope.limit
	}
	//Phân trang end


	//Tự động chạy khi chọn tab người dùng
	$scope.load_all()
	$scope.load_all_role()

})