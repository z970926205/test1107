package xinsheng.login;

public interface LoginService {
	/**
	 * 
	 * @param token
	 * @return
	 */
	public ResponseResult<LoginEntity> login(String token);

}
