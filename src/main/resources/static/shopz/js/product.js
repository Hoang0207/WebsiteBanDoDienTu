let host = "http://localhost:8080/api";
const app = angular.module("app", []);
app.controller("controller", function($scope, $http){
    $scope.form = {}
    $scope.items = []
    
    $scope.reset = function(){
        // $scope.form = {gender: true, country:'VN'};
    }
    $scope.load_all = function(){
        var url = `${host}/SanPham`;
        $http.get(url).then(resp => {
            $scope.items = resp.data;
            console.log("Success", resp)
        }).catch(error => {
            console.log("Error", error)
        });
    }
    $scope.edit = function(id){
        var url = `${host}/products/${id}`;
        $http.get(url).then(resp => {
            $scope.form = resp.data;
            console.log("Success", resp)
        }).catch(error => {
            console.log("Error", error)
        });
    }
    $scope.create = function(){
        var item = angular.copy($scope.form);
        var url = `${host}/products`;
        $http.post(url, item).then(resp => {
            $scope.items.push(item);
            $scope.reset();
            console.log("Success", resp)
        }).catch(error => {
            console.log("Error", error)
        });
    }
    $scope.update = function(){
        var item = angular.copy($scope.form);
        var url = `${host}/products/${$scope.form.id}`;
        $http.put(url, item).then(resp => {
            var index = $scope.items.findIndex(item => item.id == $scope.form.id);
            $scope.items[index] = resp.data;
            console.log("Success", resp)
        }).catch(error => {
            console.log("Error", error)
        });
    }
    $scope.delete = function(id){
        var url = `${host}/products/${id}`;
        $http.delete(url).then(resp => {
            var index = $scope.items.findIndex(item => item.id == id);
            $scope.items.splice(index, 1);
            $scope.reset();
            console.log("Success", resp)
        }).catch(error => {
            console.log("Error", error)
        });
    }
    $scope.load_all();
    $scope.reset();

    $scope.cart = {
        items:[],
        add(id){
            alert(id);
            var item = this.items.find(item=>item.id == id);
            if(item){
                item.qty++;
                this.saveToLocalStorage();
            }else{
                $http.get(`http://localhost:8080/rest/products/${id}`).then(resp=>{
                    resp.data.qty = 1;
                    this.items.push(resp.data);
                    console.log(resp.data)
                    this.saveToLocalStorage();
                })
            }
        },
        get count(){
            return this.items
            	.map(item=>item.qty)
            	.reduce((total, qty) => total += qty, 0);
        }/*,
        get count(){ // tính tổng số lượng các mặt hàng trong giỏ
            return this.items
            	.map(item => item.qty)
                .reduce((total, qty) => total += qty, 0);
        }*/,
        get amount(){
            return this.items.map(item=>item.qty * item.price).reduce((total, qty)=> total+=qty,0);
        },
        saveToLocalStorage(){
            var json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem("cart", json);
        },
        loadFormLocalStorage(){
            var json = localStorage.getItem("cart");
            this.items = json?JSON.parse(json):[];
        },
        remove(id){
            var index = this.items.findIndex(item => item.id == id)
            this.items.splice(index,1);
            this.saveToLocalStorage();
        },
        clear(){
            this.items=[]
            this.saveToLocalStorage();
        }
    }
    $scope.cart.loadFormLocalStorage();
    
    $scope.order = {
		createDate: new Date(),
		address:"",
		account: {username: $("#username").text()},
		get orderDetails(){
			return $scope.cart.items.map(item =>{
				return {
					product:{id:item.id},
					price:item.price,
					quantity:item.qty
				}
			});
		},
		purchase(){
			var order = angular.copy(this);
			//thuc hien dat hanh
			$http.post("http://localhost:8080/rest/orders", order).then(resp=>{
				alert("dat hang thanh cong");
				$scope.cart.clear();
				window.location.href = `http://localhost:8080/order/detail/` + resp.data.id;
				console.log(resp.data.id)
			}).catch(error=>{
				alert("loi dat hang");
				console.log(error);
			})
		}
	}
      
    //phan trang
	$scope.pager = {
		page:0,
		size:8,
		get items(){
			var start = this.page * this.size;
			return $scope.items.slice(start, start + this.size);
		},
		get count(){
			return Math.ceil(1.0*$scope.items.length/ this.size)
		},
		first(){
			this.page=0;
		},
		prev(){
			this.page--;
		},
		next(){
			this.page++;
		},
		last(){
			this.page = this.count-1;
		}
	}
});