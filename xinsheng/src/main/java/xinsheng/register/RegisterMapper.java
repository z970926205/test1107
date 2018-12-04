package xinsheng.register;

import java.util.List;

public interface RegisterMapper {
	public Integer setUser(RegisterEntity user);
	public List<RegisterEntity> getUser(Integer id);

}
