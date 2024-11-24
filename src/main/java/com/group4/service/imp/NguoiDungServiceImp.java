package com.group4.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.group4.dao.NguoiDungDAO;
import com.group4.dto.SoLuongNguoiDungMoiTheoThangDTO;
import com.group4.entity.NguoiDung;
import com.group4.service.NguoiDungService;
import com.group4.util.SessionUtil;

@Service
public class NguoiDungServiceImp implements NguoiDungService, UserDetailsService { // Implement cả NguoiDungService và UserDetailsService

    @Autowired
    NguoiDungDAO ndDao;
    
    @Autowired
    SessionUtil session;
   
    // @Autowired
    // private PasswordEncoder passwordEncoder; // Inject PasswordEncoder
    

    @Override
    public List<NguoiDung> findAll() {
        return ndDao.findAll();
    }

    @Override
    public Optional<NguoiDung> findById(String id) {
        return ndDao.findById(id);
    }

    @Override
    public boolean existsById(String id) {
        return ndDao.existsById(id);
    }
    
    @Override
    public NguoiDung save(NguoiDung nd) {
    	  // Mã hóa mật khẩu trước khi lưu vào cơ sở dữ liệu
       // String encodedPassword = passwordEncoder.encode(nd.getMatKhau());
       // nd.setMatKhau(encodedPassword); // Lưu mật khẩu đã mã hóa
        return ndDao.save(nd);
    }
    
    @Override
    public void deleteById(String id) {
        ndDao.deleteById(id);
    }
    
    // Thống kê số lượng khách hàng
    @Override
    public int getSoLuongNguoiDung() {
        return ndDao.findAll().size();
    }

    // Lấy người dùng từ trong session
    @Override
    public NguoiDung getInSession() {
        NguoiDung nd = session.get("user");
        return nd;
    }

    // Thống kê số lượng khách đăng ký theo tháng
    @Override
    public List<SoLuongNguoiDungMoiTheoThangDTO> getTkSoLuongNguoiDungMoiTheoThang() {
        return ndDao.getThongKeNguoiDungMoiTheoThang();
    }

    // Implement loadUserByUsername từ UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<NguoiDung> nguoiDung = ndDao.findByEmail(email);

        if (nguoiDung.isEmpty()) {
            throw new UsernameNotFoundException("Không tìm thấy người dùng với email: " + email);
        }

        NguoiDung user = nguoiDung.get();
        return User.builder()
                .username(user.getEmail())   // Sử dụng email làm tên người dùng
                .password(user.getMatKhau()) // Sử dụng mật khẩu người dùng từ cơ sở dữ liệu
                .roles("STAF") // Thiết lập quyền cho người dùng (có thể thay đổi theo hệ thống của bạn)
                .build();
    }
    
    
    /*Head
     @Override
	public List<NguoiDung> findAll() {
		return ndDao.findAll();
	}

	@Override
	public Optional<NguoiDung> findById(String id) {
		return ndDao.findById(id);
	}

	@Override
	public boolean existsById(String id) {
		return ndDao.existsById(id);
	}
	
	@Override
	public NguoiDung save(NguoiDung nd) {
		return ndDao.save(nd);
	}
	
	@Override
	public void deleteById(String id) {
		ndDao.deleteById(id);
	}
	
	//Thống kê số lượng khách hàng
	@Override
	public int getSoLuongNguoiDung() {
		return ndDao.findAll().size();
	}

	//Lấy người dùng từ trong session
	@Override
	public NguoiDung getInSession() {
		NguoiDung nd = session.get("user");
		return nd;
	}

	// Thống kê số lượng khách đăng ký theo tháng
	@Override
	public List<SoLuongNguoiDungMoiTheoThangDTO> getTkSoLuongNguoiDungMoiTheoThang() {
		return ndDao.getThongKeNguoiDungMoiTheoThang();
	}

	@Override
	public List<NguoiDung> findAllByTrangThai(Boolean trangThai) {
		return ndDao.findAllByTrangThaiIs(trangThai);
	}
     
     */
}
