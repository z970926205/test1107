package xinsheng.register;

public interface RegisterService {
	/**
	 * 添加新用户
	 * @param user
	 * @return
	 */
	public ResponseResult<String> insertUser(RegisterEntity user);
}
