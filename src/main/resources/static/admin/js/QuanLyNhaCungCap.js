app.controller('QuanLyNhaCungCapCtrl',function($http, $scope){
		
	$scope.items = []
	
	$scope.update = false
	
	//Chức năng reset
	$scope.reset = function(){
		$scope.update = false
		$scope.form = {}
	}
	
	//load tất cả nhà cung cấp
	$scope.load_all = function(){
		var url = `${host}/NhaCungCap`
		$http.get(url).then(resp =>{
			$scope.items = resp.data
			$scope.init_page()
			console.log("Success load all NhaCungCap")
		}).catch(error =>{
			console.log("Erorr load all NhaCungCap",error)
		})
	}
	
	//load nhà cung cấp theo id
	$scope.load_by_id = function(maNcc){
		var url = `${host}/NhaCungCap/${maNcc}`
		$http.get(url).then(resp => {
			$scope.form = resp.data
			$scope.update = true
		}).catch(error => {
			console.log("Error load NhaCungCap by id", error)
		})
	}
	
	//Thêm nhà cung cấp 
	$scope.create = function(){
		var url = `${host}/NhaCungCap`
		$http.post(url,$scope.form).then(resp => {
			$scope.load_all()
			$scope.update = true
			swal("Thành công !","Thêm nhà cung cấp mới thành công", "success")
		}).catch(error => {
			if(error.status == 400){
					swal ("Thất bại !","Nhà cung cấp không được thêm thành công do mã nhà cung cấp đã tồn tại","error")
				}
			console.log("Error create NhaCungCap", error)
		})
	}
	
		
	//Cập nhật nhà cung cấp
	$scope.update_ncc = function(maNcc){
		var url = `${host}/NhaCungCap/${maNcc}`
		$http.put(url,$scope.form).then(resp => {
			$scope.load_all()
			swal("Thành công !","Cập nhật nhà cung cấp thành công","success")
		}).catch(error => {
			console.log("Error update NhaCungCap", error)
		})
	}
	
	//Xóa nhà cung cấp
	$scope.delete = function(maNcc){
		swal({
			title: "Bạn chắc chắn không ?",
			text: "Khi đã xóa thì bạn không thể khôi phục lại được",
			icon: "warning",
			buttons: true,
			dangerMode: true,
		}).then((willDelete) => {
			if(willDelete){
				var url = `${host}/NhaCungCap/${maNcc}`
				$http.delete(url).then(resp => {
					$scope.load_all()
					swal("Xóa thành công",{icon: "success"})
				}).then(error => {
					console.log("Error delete NhaCungCap",error)
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
	
	//Tự động chạy đầu tiên khi mở tab 
	$scope.load_all()
})