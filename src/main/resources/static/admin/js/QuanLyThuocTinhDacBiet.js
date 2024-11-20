app.controller('QuanLyThuocTinhDacBietCtrl',function($http, $scope){
		
	$scope.items = []
	
	$scope.update = false
	
	//Hàm reset
	$scope.reset = function(){
		$scope.form = {}
		$scope.update = false
	}
	
	//load tất cả thuộc tính đặc biệt
	$scope.load_all = function(){
		var url = `${host}/ThuocTinhDacBiet`
		$http.get(url).then(resp => {
			$scope.items = resp.data
			$scope.init_page()
			console.log("Success load all TTDB")
		}).catch(error =>{
			console.log("Erorr load all TTDB", error)
		})
	}
	
	//load thuộc tính đặc biệt theo id
	$scope.load_by_id = function(maTtdb){
		var url = `${host}/ThuocTinhDacBiet/${maTtdb}`
		$http.get(url).then(resp => {
			$scope.form = resp.data
			$scope.update = true
		}).catch(error => {
			console.log("Error load TTDB by id", error)
		})
	}
	
	//Thêm thuộc tính đặc biệt
	$scope.create = function(){
		var url = `${host}/ThuocTinhDacBiet`
		$http.post(url,$scope.form).then(resp => {
			$scope.update = true
			$scope.load_all()
			swal("Thành công !","Bạn đã thêm thành công thuộc tính đặc biệt", "success")
		}).catch(error => {
			console.log("Error create TTDB", error)
		})
	}
	
	//Cập nhật thuộc tính đặc biệt
	$scope.update_TTDB = function(maTTDB){
		var url = `${host}/ThuocTinhDacBiet/${maTTDB}`
		$http.put(url,$scope.form).then(resp => {
			$scope.load_all()
			swal("Thành công !","Bạn đã cập nhật thành công thuộc tính đặc biệt","success")
		}).catch(error => {
			console.log("Error update TTDB",error)
		})
	}
	
	//Xóa thuộc tính đặc biệt
	$scope.delete = function(maTTDB){
		swal({
			title: "Bạn chắc chắn không ?",
			text: "Khi đã xóa thì bạn không thể khôi phục lại được",
			icon: "warning",
			buttons: true,
			dangerMode: true,
		}).then((willDelete) => {
			if(willDelete){
				var url = `${host}/ThuocTinhDacBiet/${maTTDB}`
				$http.delete(url).then(resp => {
					$scope.load_all()
					swal("Xóa thành công",{icon: "success"})
				}).catch(error => {
					console.log("Error delete TTDB",error)
				})
			}else{
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
	
	//Tự động chạy đầu tiên khi chọn tab
	$scope.load_all()
	
})