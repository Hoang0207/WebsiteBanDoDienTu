// language.js

// Định nghĩa từ điển ngôn ngữ
var translations = {
    "vi": {
		"history":"lịch sử đặt hàng",
        "personal_information":"thông tin cá nhân",
        "About": "Về chúng tôi",
        "contact": "Liên hệ",
        "Help": "Trợ giúp",
        "Hello": "Xin chào,",
        "logout": "Đăng xuất",
        "login": "Đăng nhập",
        "register": "Đăng kí",
        "customer_care": "Chăm sóc khách hàng",
        "components": "Linh kiện",
        "printer": "Máy In",
        "home": "Trang Chủ",
        "FAQs": "Câu hỏi thường gặp",
        "search_placeholder": "Tìm kiếm",
        "Laptop": "Laptop",
        "PC": "PC",
        "List":"Danh sách",
        "product":"Sản phẩm",
        "cart":"Giỏ Hàng",
        /*footer*/
        "Buy now":"Mua sắm nhanh",
        "location":"Công viên phần mềm,Quận 12,Thành Phố HCM",
        "follow":"THEO DÕI CHÚNG TÔI",
        /*cart*/
        "cart_title": "Giỏ Hàng",
        "home": "Trang Chủ",
        "shop": "Cửa Hàng",
        "cart": "Giỏ Hàng",
        "product": "Sản Phẩm",
        "price": "Giá",
        "quantity": "Số Lượng",
        "total": "Tổng",
        "remove": "Xóa",
        "discount_code": "Mã Giảm Giá",
        "apply_discount": "Áp Dụng Mã Giảm Giá",
        "cart_summary": "Tóm Tắt Giỏ Hàng",
        "subtotal": "Tạm Tính",
        "discount": "Giảm giá",
        "total_amount": "Tổng Cộng",
        "order_info": "Thông Tin Đặt Hàng",
        "address_label": "Địa chỉ:",
        "place_order": "Tiến Hành Đặt Hàng",
        /* Contact Page */
        "contact_title": "Liên Hệ",
        "contact_form_title": "Điền Thông Tin Của Bạn",
        "name_label": "Họ Tên",
        "email_label": "Email",
        "message_label": "Tin Nhắn",
        "submit_button": "Gửi",
        /*detail*/
        "product_name": "Tên sản phẩm",
            "quantity_label": "Số lượng",
            "add_to_cart": "Thêm vào giỏ hàng",
            "share_on": "Chia sẻ trên",
            "description_title": "Mô tả",
            "increase_quantity": "Tăng số lượng",
            "decrease_quantity": "Giảm số lượng",
            /*đơn hàng chi tiêt*/
            "order_detail_title": "Chi Tiết Đơn Hàng",
            "product": "Sản phẩm",
            "product_name": "Tên sản phẩm",
            "quantity": "Số lượng",
            "price": "Giá tiền",
            "total_price": "Tổng tiền",
            /*home*/
            "home.title": "Trang Chủ",
    "carousel.laptop": "Laptop Giá Rẻ",
    "carousel.offer": "Ưu Đãi Tốt Nhất Thị Trường",
    "carousel.view-now": "Xem Ngay",
    "feature.quality": "Sản phẩm chất lượng",
    "feature.free-shipping": "Miễn phí vận chuyển",
    "feature.return-policy": "Đổi trả trong 14 ngày",
    "feature.support": "Hỗ trợ 24/7",
    "categories.title": "Danh mục sản phẩm",
    "categories.product-count": "100 sản phẩm",
    "New Release": "Mới ra mắt",
    "New product":"Sản phẩm mới",
    "sale":"Giảm",
    "special_offer":"Ưu đãi đặc biệt",
    /*history cart*/
    "order-history.title": "Lịch Sử Đặt Hàng",
    "order-history.date": "Ngày lập đơn: ",
    "order-history.status": "Trạng thái",
    "order-history.address": "Địa chỉ giao: ",
    "order-history.view-details": "Xem chi tiết",
    "order-history.no-orders": "Không có đơn hàng nào.",
    /*shop*/
    "products.title": "Sản Phẩm",
    "products.home": "Trang Chủ",
    "products.products": "Sản Phẩm",
    "products.product-list": "Danh Sách Sản Phẩm",
    "products.price-filter": "Lọc Theo Giá",
    "products.min-price": "Giá thấp nhất:",
    "products.max-price": "Giá cao nhất:",
    "products.category-filter": "Lọc Theo Chủng Loại",
    "products.select-category": "Chọn Chủng Loại:",
    "products.all": "Tất cả",
    "products.supplier-filter": "Lọc Theo Nhà Cung Cấp",
    "products.select-supplier": "Chọn Nhà Cung Cấp:",
    "products.clear-filter": "Xóa Lọc",
    "products.filter": "Lọc",
    "products.grid-view": "Chế Độ Lưới",
    "products.list-view": "Chế Độ Danh Sách",
    "products.first": "Đầu Tiên",
    "products.prev": "Trước",
    "products.page": "Trang",
    "products.next": "Tiếp Theo",
    "products.last": "Cuối Cùng",
    //info//
    "newpass":"Mật khẩu mới",
    "re-enter":"Nhập lại mật khẩu",
    "update":"Cập nhật",
    "security":"Bảo mật",
    "update pass":"Cập Nhật Mật Khẩu",
    "chance":"Thay đổi thông tin",
    "gender":"Giới tính",
    "male":"name",
    "female":"nữ",
    "local":"Địa chỉ",
    "birthday":"Ngày sinh",
    "name":"Họ tên",
    "avatar":"Hình đại diện",
    "img":"Chọn hình",
    "phone":"Số điện thoại",
    },
    "en": {
        //info//
        "history":"Order history", 
        "newpass":"new password",
        "re-enter":"re-enter new password",
        "update":"Update",
        "security":"security",
    "update pass":"update pass",
        "chance":"Chance",
        "gender":"gender",
        "male":"male",
        "female":"female",
        "birthday":"birthday",
        "phone":"phone",
        "name":"name",
        "avatar":"avatar",
        "img":"select photo",
        "local":"Location",
        //info//
        "personal_information":"personal information",
        "product":"product",
        "About": "About",
        "contact": "Contact",
        "Help": "Help",
        "Hello": "Hello,",
        "logout": "Logout",
        "login": "Login",
        "register": "Register",
        "customer_care": "Customer Care",
        "components": "Components",
        "printer": "Printer",
        "home": "Home",
        "FAQs": "FAQs",
        "search_placeholder": "Search",
        "list": "Category List",
        "Laptop": "Laptop",
        "PC": "PC",
        "cart":"cart",
        /*footer*/
       "location":"Software Park, District 12, Ho Chi Minh City",
       "Buy now":"Buy now",
       "follow":"FOLLOW US",
        /*cart*/
        "cart_title": "Shopping Cart",
        "home": "Home",
        "shop": "Shop",
        "cart": "Cart",
        "product": "Product",
        "price": "Price",
        "quantity": "Quantity",
        "total": "Total",
        "remove": "Remove",
        "discount_code": "Discount Code",
        "apply_discount": "Apply Discount",
        "cart_summary": "Cart Summary",
        "subtotal": "Subtotal",
        "discount": "Discount",
        "total_amount": "Total Amount",
        "order_info": "Order Information",
        "address_label": "Address:",
        "place_order": "Place Order",
        /* Contact Page */
        "contact_title": "Contact",
        "contact_form_title": "Fill Out Your Information",
        "name_label": "Full Name",
        "email_label": "Email",
        "message_label": "Message",
        "submit_button": "Submit",
         /* detail */
         "product_name": "Product Name",
         "quantity_label": "Quantity",
         "add_to_cart": "Add to Cart",
         "share_on": "Share On",
         "description_title": "Description",
         /*đơn hàng chi tiết*/
         "order_detail_title": "Order Details",
         "product": "Product",
         "product_name": "Product Name",
         "quantity": "Quantity",
         "price": "Price",
         "total_price": "Total Price",
         /*home*/
         "home.title": "Home",
         "carousel.laptop": "Laptop Discount",
         "carousel.offer": "Best Offer in the Market",
         "carousel.view-now": "View Now",
         "feature.quality": "High Quality Products",
         "feature.free-shipping": "Free Shipping",
         "feature.return-policy": "14 Days Return Policy",
         "feature.support": "24/7 Support",
         "categories.title": "Categories",
         "categories.product-count": "100 Products",
         "New Release": "New Release",
         "New product":"New Product",
         "sale":"Sale", 
         "special_offer":"special offer",
                     /*history cart*/
    "order-history.title": "Order History",
    "order-history.date": "Order Date: ",
    "order-history.status": "Status",
    "order-history.address": "Delivery Address: ",
    "order-history.view-details": "View Details",
    "order-history.no-orders": "No orders available.",
    /*shop*/
    "products.title": "Products",
    "products.home": "Home",
    "products.products": "Products",
    "products.product-list": "Product List",
    "products.price-filter": "Filter by Price",
    "products.min-price": "Min Price:",
    "products.max-price": "Max Price:",
    "products.category-filter": "Filter by Category",
    "products.select-category": "Select Category:",
    "products.all": "All",
    "products.supplier-filter": "Filter by Supplier",
    "products.select-supplier": "Select Supplier:",
    "products.clear-filter": "Clear Filter",
    "products.filter": "Filter",
    "products.grid-view": "Grid View",
    "products.list-view": "List View",
    "products.first": "First",
    "products.prev": "Prev",
    "products.page": "Page",
    "products.next": "Next",
    "products.last": "Last"
    }
};

// Hàm dịch trang
function translatePage(language) {
    var elements = document.querySelectorAll("[data-translate]");

    elements.forEach(function(element) {
        var key = element.getAttribute("data-translate");
        if (translations[language] && translations[language][key]) {
            element.innerText = translations[language][key];
        }
    });

    // Thay đổi tiêu đề trang theo ngôn ngữ
    document.title = translations[language]["home"] || "Cửa Hàng Điện Máy HTV";
}

// Cập nhật thuộc tính lang của thẻ <html>
function updateHtmlLang(language) {
    document.documentElement.setAttribute('lang', language);
}

// Đảm bảo khi tải trang sẽ áp dụng ngôn ngữ đã được lưu trong session
document.addEventListener("DOMContentLoaded", function() {
    // Gửi yêu cầu để lấy ngôn ngữ từ session (sử dụng API)
    fetch('/api/get-language')
        .then(response => response.json())
        .then(data => {
            var language = data.language || 'vi';  // Nếu không có, mặc định là tiếng Việt
            translatePage(language);
            updateHtmlLang(language);
        });
});
