package xinsheng.login;

import java.util.List;

public interface LoginMapper {
	/**
	 * 
	 * @param token
	 * @return tokenId
	 */
	public Integer getToken(String token);
	public Integer updateToken(LoginEntity updateTokenEntity);
	public List<LoginEntity> getUser(Integer id);

}
