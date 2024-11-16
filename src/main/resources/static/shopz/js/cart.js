/* Cotroller for cart.html */
app.controller("cartCtrl", function($http,$scope){
	
	let cart_host = "http://localhost:8080/api/GioHang"
	
	$scope.items = []
	
	//Load tất cả giỏ hàng của người dùng được chỉ định
	$scope.load_all_by_maNd = function(){
		var url = `${cart_host}/hoang0207`
		$http.get(url).then(resp => {
			$scope.items = resp.data
			console.log("Success load GioHang by MaNd")
		}).catch(error => {
			console.log("Errro load GioHang by MaNd",error)
		})
	}
	
	//Cập nhật giỏ hàng
	$scope.update_gh = function(maGh,maNd,maSp,soLuong,nguoiDung,sanPham){
		var url = `${cart_host}`
		var gh = {maGioHang: maGh, maNd: maNd, maSp: maSp, soLuong: soLuong, nguoiDung: nguoiDung, sanPham: sanPham}
		$http.put(url,gh).then(resp => {
			var index = $scope.items.findIndex(item => item.maGioHang == maGh)
            $scope.items[index] = resp.data
           	//$scope.load_all_by_maNd()
			console.log("Success update GioHang",resp)
		}).catch(error => {
			console.log("Error update GioHang", error)
		})
		
	}
	
	//Tự động chạy khi vào giỏ hàng
	$scope.load_all_by_maNd()
})