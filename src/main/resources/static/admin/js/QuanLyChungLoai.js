app.controller('QuanLyChungloaiCtrl', function($http, $scope) {

	$scope.form = {}
	$scope.items = []
	$scope.update = false

	//reset lại form
	$scope.reset = function() {
		$scope.form = {}
		$scope.update = false
	}

	//load lên toàn bộ bảng chủng loại
	$scope.load_all = function() {
		var url = `${host}/ChungLoai`
		$http.get(url).then(resp => {
			$scope.items = resp.data;
			$scope.init_page()
			console.log("Get all ChungLoai  success")
		}).catch(error => {
			console.log("error get all ChungLoai", error)
		})
	}

	//Hiện chi tiết chủng loại theo id
	$scope.load_by_id = function(maCl) {
		var url = `${host}/ChungLoai/${maCl}`
		$http.get(url).then(resp => {
			$scope.form = resp.data
			$scope.update = true
			console.log("Success edit ChungLoai by id", resp)
		}).catch(error => {
			console.log("Error edit ChungLoai by id", error)
		})
	}

	//Thêm chủng loại mới
	$scope.create = function() {
		var url = `${host}/ChungLoai`
		$http.post(url, $scope.form).then(resp => {
			$scope.load_all()
			$scope.update = true
			swal({
				title: "Thêm thành công !",
				text: "Bạn đã thêm thành công chủng loại mới",
				icon: "success",
				button: "Đóng",
			});
			console.log("Success create ChungLoai", resp)
		}).catch(error => {
			if(error.status == 400){
				swal ("Thất bại !","Chủng loại không được thêm thành công do mã chủng loại đã tồn tại","error")
			}
			console.log("Error create ChugnLoai", error)
		})
	}

	//Cập nhật chủng loại
	$scope.update_cl = function(macl) {
		var url = `${host}/ChungLoai/${macl}`
		$http.put(url, $scope.form).then(resp => {
			$scope.load_all()
			swal({
				title: "Cập nhật thành công !",
				text: "Bạn đã cập nhật thành công chủng loại",
				icon: "success",
				button: "Đóng",
			});
			console.log("Success update ChungLoai", resp)
		}).catch(error => {
			console.log("Error update ChungLoai", error)
		})
	}

	//Xóa chủng loại
	$scope.delete = function(macl) {
		swal({
			title: "Bạn chắc chắn không ?",
			text: "Khi đã xóa thì bạn không thể khôi phục lại được",
			icon: "warning",
			buttons: true,
			dangerMode: true,
		})
			.then((willDelete) => {
				if (willDelete) {
					var url = `${host}/ChungLoai/${macl}`
					$http.delete(url).then(resp => {
						$scope.load_all()
						console.log("Success delete ChungLoai")
						swal("Xóa thành công", {icon: "success",})
					}).catch(error => {
						console.log("Error delete ChungLoai", error)
					})
				} else {
					swal("Không thực hiện thao tác xóa")
				}
			})
	}
	
	//Phân trang start
	$scope.page = 1
	$scope.limit = 9
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

	//Tự động chạy đầu tiên khi mở tab Quản lý chủng loại
	$scope.load_all()

})