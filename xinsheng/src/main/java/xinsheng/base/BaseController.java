package xinsheng.base;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * ͨ��
 * 
 * @author GP
 *
 */
@Controller
public class BaseController {
	// private static final Log logger = LogFactory.getLog(.class);
	private final Logger logger =Logger.getLogger(this.getClass());
	@Autowired
	private BaseService baseService;
	@Autowired
	private HttpSession session;

	private BaseEntity sessionUser;

	public boolean setSessionUser(Integer userId) {
		logger.info(userId);
		BaseEntity entity;
		BaseEntity returnEntity;
		try {
			entity = new BaseEntity();
			entity.setId(userId);
			returnEntity = baseService.getUser(entity);
			if (returnEntity != null) {
				session.setAttribute("user", returnEntity);
				sessionUser = returnEntity;
				logger.info("session:"
						+ ((BaseEntity) session.getAttribute("user"))
								.toString());
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("error");
		}
		return false;
	}

	public BaseEntity getSessionUser() {
		return sessionUser;
	}

	public boolean updateSessionUser(Integer userId) {
		logger.info(userId);
		return setSessionUser(userId);
	}

	public boolean DeleteSessionUser() {
		logger.info("");
		try {
			session.removeAttribute("user");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("error");
		}
		return false;
	}

	public boolean checkUserName(String userName) {
		BaseEntity entity;
		try {
			entity = new BaseEntity();
			entity.setUserName(userName);
			if (baseService.getUser(entity) != null)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("error");
		}
		return false;
	}

	public boolean setSingleUserDate(String singleUserDate) {
		logger.info("singleUserDate:" + singleUserDate);
		try {
			session.setAttribute("userDate", singleUserDate);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("error");
		}
		return false;
	}
	public boolean DelSingleUserDate() {
		logger.info("");
		try {
			session.removeAttribute("userDate");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error");
		}
		return false;
	}
	public String getSingleUserDate() {
		logger.info("");
		String registerDate;
		try {
			registerDate = (String)session.getAttribute("userDate");
			return registerDate;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error");
		}
		return null;
	}
}
