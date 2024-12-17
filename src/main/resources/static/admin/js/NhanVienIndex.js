let host = "http://localhost:8080/api"

//Angular Js code
var app = angular.module('myApp', ['ngRoute']);
app.controller('myCtrl', function($http, $scope) {
	
	$scope.nd = null
	
	$scope.init = function(){
		var url = `${host}/NguoiDung/Session`
		$http.get(url).then(resp => {
			$scope.nd = resp.data
			console.log(resp.data)
		})
	}
	
	$scope.init()
	
})
app.config(function($routeProvider) {
	$routeProvider
		.when('/QuanLyDonHang', {
			templateUrl: '/admin/html/pages/QuanLyDonHang.html',
			controller: 'QuanLyDonHangCtrl'
		})
		.when('/QuanLySanPham', {
			templateUrl: '/admin/html/pages/QuanLySanPham.html',
			controller: 'QuanLySanPhamCtrl'
		})
		.when('/QuanLyChungloai', {
			templateUrl: '/admin/html/pages/QuanLyChungloai.html',
			controller: 'QuanLyChungloaiCtrl'
		})
		.when('/QuanLyNhaCungCap', {
			templateUrl: '/admin/html/pages/QuanLyNhaCungCap.html',
			controller: 'QuanLyNhaCungCapCtrl'
		})
		.when('/QuanLyThuocTinhDacBiet', {
			templateUrl: '/admin/html/pages/QuanLyThuocTinhDacBiet.html',
			controller: 'QuanLyThuocTinhDacBietCtrl'
		})
		.otherwise({
			templateUrl: '/admin/html/pages/QuanLyDonHang.html',
			controller: 'QuanLyDonHangCtrl'
		})
})

//Gắn sự kiện cho menuAside sau khi load bằng ng-include
app.directive('menuAsideLoaded', function() {
	return {
		restrict: 'A',
		link: function(scope, element) {
			// Gắn các sự kiện JavaScript tại đây
			//Bắt sự kiện khi bấm vào button bên menuAside
			const buttionElementMenu = document.querySelectorAll('.menuAside button')
			buttionElementMenu.forEach(
				button => {
					button.addEventListener('click', function() {
						buttionElementMenu.forEach(btn => btn.classList.remove('buttonMenuSelected'))
						this.classList.add('buttonMenuSelected')
					})
				}
			)
		}
	};
});




// Đảm bảo các sự kiện được gắn khi trang lần đầu tải
document.addEventListener("DOMContentLoaded", function() {
	//Bắt sự kiện khi bấm vào button bên menuAside
	const buttionElementMenu = document.querySelectorAll('.menuAside button')
	buttionElementMenu.forEach(
		button => {
			button.addEventListener('click', function() {
				buttionElementMenu.forEach(btn => btn.classList.remove('buttonMenuSelected'))
				this.classList.add('buttonMenuSelected')
			})
		}
	)

	//Gắn sự kiện cho các nút tab dơn hàng
	attachOrderButtonEvents();
})

// Hàm gắn sự kiện cho các nút trong tab Đơn hàng
function attachOrderButtonEvents() {
	//Bắt sự kiện khi bấm vào các thẻ theo dõi đơn hàng
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

/*
function loadContent(url) {
	// Sử dụng AJAX để gửi yêu cầu GET tới server
	fetch(url, {
		method: 'GET',
		headers: {
			'X-Requested-With': 'XMLHttpRequest' // Đảm bảo request là AJAX
		}
	})
		.then(response => response.text()) // Lấy nội dung từ response
		.then(html => {
			// Cập nhật nội dung của phần tử có id="content"
			document.getElementById('content').innerHTML = html;
			// Gắn lại sự kiện sau khi nội dung được tải lại
			attachOrderButtonEvents();
		})
		.catch(error => {
			console.error('Lỗi khi tải nội dung:', error);
		});
}
*/
