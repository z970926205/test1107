package xinsheng.register;

public interface RegisterService {
	/**
	 * ������û�
	 * @param user
	 * @return
	 */
	public ResponseResult<String> insertUser(RegisterEntity user);
}
