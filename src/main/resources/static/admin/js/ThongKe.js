app.controller('thongKeCtrl', function($http, $scope) {
	$scope.quantity_user = null
	$scope.quantity_product = null
	$scope.quantity_bill = null
	$scope.present_chart = null

	//Đếm số lượng người dùng
	$scope.count_user = function() {
		var url = `${host}/NguoiDung/SoLuong`
		$http.get(url).then(resp => {
			$scope.quantity_user = resp.data
			console.log("Success count quantity customer", resp)
		}).catch(error => {
			console.log("Error count quantity customer", error)
		})
	}

	//Đếm số lượng sản phẩm
	$scope.count_product = function() {
		var url = `${host}/SanPham/SoLuong`
		$http.get(url).then(resp => {
			$scope.quantity_product = resp.data
			console.log("Success count quantity product", resp)
		}).catch(error => {
			console.log("Error count quantity product", error)
		})
	}

	//Đếm số lượng đơn hàng
	$scope.count_bill = function() {
		var url = `${host}/DonHang/SoLuong`
		$http.get(url).then(resp => {
			$scope.quantity_bill = resp.data
			console.log("Success count quantity bill", resp)
		}).catch(error => {
			console.log("Error count quantity bill", error)
		})
	}

	//Thống kê doanh thu theo tháng
	$scope.revenue_statistics = function() {
		var url = `${host}/ThongKe/DoanhThu/TheoThang`
		$http.get(url).then(resp => {
			if ($scope.present_chart) {
				$scope.present_chart.destroy()
			}
			var statistical_data = resp.data
			var labels = statistical_data.map(item => item.thang + "/" + item.nam)
			var data = statistical_data.map(item => item.doanhThu)
			//Khởi tạo biểu đồ bằng Chart.js 
			var ctx = document.getElementById("bieuDoDoanhThuTheoThang").getContext("2d")
			$scope.present_chart = new Chart(ctx, {
				type: "bar",
				data: {
					labels: labels,
					datasets: [{
						label: "Doanh thu theo tháng",
						data: data,
						backgroundColor: 'rgba(75, 192, 192, 0.6)',
						borderColor: 'rgba(75, 192, 192, 1)',
						borderWidth: 2
					}]
				},
				options: {
					indexAxis: 'y'
				}
			})
		}).catch(error => {
			console.log("Error getTkDoanhThuTheoThang",error)
		})
	}

	// Hàm tạo màu ngẫu nhiên
	$scope.generateRandomColors = function(numColors) {
		let colors = [];
		for (let i = 0; i < numColors; i++) {
			// Các giá trị RGB dưới 128 để tạo màu tối
			const color = `rgb(${Math.floor(Math.random() * 128)}, ${Math.floor(Math.random() * 128)}, ${Math.floor(Math.random() * 128)})`;
			colors.push(color);
			//const color = `rgb(${Math.floor(Math.random() * 256)}, ${Math.floor(Math.random() * 256)}, ${Math.floor(Math.random() * 256)})`;
		}
		return colors;
	}

	//Thống kê doanh thu theo chủng loại
	$scope.revenue_category_statistics = function() {
		var url = `${host}/ThongKe/DoanhThu/TheoChungLoai`
		$http.get(url).then(resp => {
			$scope.present_chart.destroy()
			var data = resp.data.map(item => item.doanhThu)
			var labels = resp.data.map(item => item.tenCl)
			//Khởi tạo biểu đồ
			var ctx = document.getElementById("bieuDoDoanhThuTheoChungLoai").getContext("2d")
			var colors = $scope.generateRandomColors(data.length)
			$scope.present_chart = new Chart(ctx, {
				type: "doughnut",
				data: {
					labels: labels,
					datasets: [{
						label: "Doanh thu theo chung loai",
						data: data,
						backgroundColor: colors
					}]
				},
				options: {
					cutout: '40%', // Điều chỉnh phần rỗng bên trong biểu đồ, giá trị càng cao thì chu vi càng nhỏ
					responsive: true,
					maintainAspectRatio: true
				}
			})
		}).catch(error =>{
			console.log("Error get ThongKeDoanhThuTheoCl",error)
		})
	}
	
	//Thống kê doanh thu theo nha cung cấp
	$scope.provider_statistics = function(){
		var url = `${host}/ThongKe/DoanhThu/TheoNhaCungCap`
		$http.get(url).then(resp => {
			$scope.present_chart.destroy()
			var data = resp.data.map(item => item.doanhThu)
			var labels = resp.data.map(item => item.tenNcc)
			var colors = $scope.generateRandomColors(data.length)
			//Khởi tạo biểu đồ
			var ctx = document.getElementById("bieuDoDoanhThuTheoNhaCungCap").getContext("2d")
			$scope.present_chart = new Chart(ctx, {
				type: "doughnut",
				data: {
					labels: labels ,
					datasets:[{
						label: "Doanh thu theo nhà cung cấp",
						data: data,
						backgroundColor: colors
					}]
				},
				options:{
					cutout: '40%', // Điều chỉnh phần rỗng bên trong biểu đồ, giá trị càng cao thì chu vi càng nhỏ
				}
			})
		}).catch(error => {
			console.log("Error get ThongKeDoanhThuTheoNcc", error)
		})
	}
	
	//Thống kê số lượng khách đăng ký theo tháng 
	$scope.amount_customer_register_by_month_statistics = function(){
		var url = `${host}/ThongKe/NguoiDung/SoLuongMoiTheoThang`
		$http.get(url).then(resp => {
			$scope.present_chart.destroy()
			var labels = resp.data.map(item => item.thang+"/"+item.nam )
			var data = resp.data.map(item => item.soLuong)
			//Khởi tạo biểu đồ
			var ctx = document.getElementById("bieuDoDoanhThuSoLuongKhachDangKyTheoThang").getContext("2d")
			$scope.present_chart = new Chart(ctx, {
				type: "bar",
				data:{
					labels: labels,
					datasets:[{
						label: "Số lượng người dùng mới theo tháng",
						data:data,
						backgroundColor: 'rgba(75, 192, 192, 0.6)',
						borderColor: 'rgba(75, 192, 192, 1)',
						borderWidth: 2
					}]
				},
				options: {
					indexAxis: 'y'
				}
			})
		}).catch(error => {
			console.log("Error create chart customer", error)
		})
	}

	//Tự động chạy khi mở tab
	$scope.count_user()
	$scope.count_product()
	$scope.count_bill()
	$scope.revenue_statistics()

})