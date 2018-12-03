package xinsheng.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService{
	@Autowired
	private RegisterMapper registerMapper;
	
	public Integer insertUser(registerEntity user) {
		Integer returnId = null;
		try {
			registerMapper.setUser(user);
			returnId = user.getId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
