package xinsheng.login;

import io.jsonwebtoken.Claims;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import xinsheng.config.ConfigureContent;
import xinsheng.utils.JinxUtils;

@Service
public class LoginServiceImpl implements LoginService {
	private final Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private LoginMapper LoginMapper;
	@Autowired
	private JinxUtils utils;
	@Autowired
	private ConfigureContent cfc;

	public ResponseResult<LoginEntity> login(String token) {
		logger.info("token:" + token);
		ResponseResult<LoginEntity> page = null;
		Integer pageFalg = null;
		String message = null;
		LoginEntity rrEntity = null;//need return entity
		String newToken = null;
		Integer dbTokenId = null;//old token id
		try {
			dbTokenId = LoginMapper.getToken(token).getTokenId();
			if (dbTokenId > 0) {
				logger.info("token is exist");
				Claims tokenClaims = utils.parseJWT(token);
				if (tokenClaims != null) {
					Date nowDate = new Date();
					Date expirationDate = tokenClaims.getExpiration();
					if (nowDate.before(expirationDate)) {
						List<LoginEntity> returnList = LoginMapper
								.getUser(Integer.parseInt(tokenClaims.getId()
										.trim()));
						if (returnList != null && returnList.size() > 0) {
							rrEntity = returnList.get(0);
							// Less than minimum replaceable
							if (utils.transformMillistoDay(expirationDate,
									nowDate) <= cfc.MINREPLACEABLEDAYS) {
								newToken = utils.createJWT(tokenClaims.getId(),
										tokenClaims.getIssuer(),
										tokenClaims.getSubject());
								if (newToken != null) {
									logger.info("newToken:" + newToken);
									LoginEntity updateTokenEntity = new LoginEntity();
									updateTokenEntity.setTokenId(dbTokenId);
									updateTokenEntity.setNewToken(newToken);
									if (LoginMapper.updateToken(updateTokenEntity) == 1) {
										rrEntity.setNewToken(newToken);
									} else {
										logger.error("updateToken is error");
										throw new RuntimeException();
									}
								} else {
									logger.error("newToken is null");
									throw new RuntimeException();
								}
							}
							pageFalg = 0;
							message = "success";
						} else {
							pageFalg = 1;
							message = "getUser is error";
						}
					} else {
						pageFalg = 1;
						message = "token out of date";
					}
				} else {
					message = "Parsing exceptions";
					throw new RuntimeException();
				}
			} else {
				pageFalg = 1;
				message = "token not found";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			pageFalg = 1;
			if (message == null) {
				message = "SystemException";
			}
			// Actively rollback transactions
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
		}
		page = new ResponseResult<LoginEntity>(pageFalg, message);
		page.setData(rrEntity);
		return page;
	}
}
