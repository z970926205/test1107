package xinsheng.register;

import java.util.List;

import javax.annotation.Resource;
@Resource
public interface RegisterMapper {
	public Integer setUser(RegisterEntity user);
	public Integer setToken(String token);
	public List<RegisterEntity> getUser(Integer id);

}
