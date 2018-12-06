package xinsheng.login;

import java.util.List;

import javax.annotation.Resource;
@Resource
public interface LoginMapper {
	/**
	 * 
	 * @param token
	 * @return tokenId
	 */
	public LoginEntity getToken(String token);
	public Integer updateToken(LoginEntity updateTokenEntity);
	public List<LoginEntity> getUser(Integer id);

}
