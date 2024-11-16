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
	
	//Tự động chạy khi vào giỏ hàng
	$scope.load_all_by_maNd()
})