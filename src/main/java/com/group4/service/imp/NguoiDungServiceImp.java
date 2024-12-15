package com.group4.service.imp;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.group4.config.SecurityUser;
import com.group4.entity.PhanQuyen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.group4.dao.NguoiDungDAO;
import com.group4.dto.SoLuongNguoiDungMoiTheoThangDTO;
import com.group4.entity.NguoiDung;
import com.group4.service.NguoiDungService;
import com.group4.util.SessionUtil;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NguoiDungServiceImp implements NguoiDungService {

    @Autowired
    NguoiDungDAO ndDao;
    
    @Autowired
    SessionUtil session;

    @Autowired
    NguoiDungDAO nguoiDungDAO;

    @Autowired
    @Lazy
    PasswordEncoder passwordEncoder;

    // @Autowired
    // private PasswordEncoder passwordEncoder; // Inject PasswordEncoder
    @Override
    public boolean validateCredentials(String email, String password) {
        return nguoiDungDAO.existsByEmailAndMatKhau(email, passwordEncoder.encode(password));
    }

    @Override
    public int recoverPassword(String password, String email) {
        return nguoiDungDAO.updatePassword(password, email);
    }
    
    @Override
    public boolean emailExists(String email) {
        return nguoiDungDAO.findByEmail(email).isPresent();
    }



    @Override
    public List<NguoiDung> findAll() {
        return ndDao.findAll();
    }

    @Override
	public List<NguoiDung> findAllByTrangThai(Boolean trangThai) {
		return ndDao.findAllByTrangThaiIs(trangThai);
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
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<NguoiDung> userByUsername = nguoiDungDAO.findByEmail(email);
        if (userByUsername.isEmpty()) {
            System.out.println("Không thể tìm thấy người dùng với email: {}");
            throw new UsernameNotFoundException("Invalid credentials!");
        }
        NguoiDung user = userByUsername.get();
        if (!user.getEmail().equals(email)) {
            System.out.println("Could not find user with that email: {}");
            throw new UsernameNotFoundException("Invalid credentials!");
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (PhanQuyen role : user.getPhanQuyens()) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getVaiTro().getMaVaiTro()));
        }
        //Gan session cho nguoi dung
        session.remove("user");
        session.set("user", userByUsername.get());
        //
        System.out.println(grantedAuthorities);
        return new SecurityUser(user.getEmail(), user.getMatKhau(), true, true, true, true, grantedAuthorities,
                user.getEmail());
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
	}*/

}
