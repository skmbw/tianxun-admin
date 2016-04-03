package com.vteba.tianxun.user.action;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vteba.common.constant.CommonConst;
import com.vteba.tianxun.user.model.User;
import com.vteba.tianxun.user.service.spi.UserService;
import com.vteba.utils.id.ObjectId;
import com.vteba.web.action.GenericAction;
import com.vteba.web.action.JsonBean;

/**
 * 用户表控制器
 * @author yinlei
 * @date 2016-3-24 20:14:12
 */
@Controller
@RequestMapping("/user")
public class UserAction extends GenericAction<User> {
	private static final Logger LOGGER = LogManager.getLogger(UserAction.class);
	private static final Pattern MOBILE = Pattern.compile("1\\d{10}$");
	
	@Inject
	private UserService userServiceImpl;
	@Inject
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	/**
     * 去注册页面
     * @param model 参数
     * @return 注册页
     */
    @RequestMapping("/register")
    public String register(User model) {
        return "user/register";
    }
	
    /**
     * 去登录页面
     * @param model 参数
     * @return 注册页
     */
    @RequestMapping("/login")
    public String login(User model) {
        return "user/login";
    }
    
	/**
     * 获得用户表List，初始化列表页。
     * @param model 参数
     * @return 用户表List
     */
    @RequestMapping("/initial")
    public String initial(User model, Map<String, Object> maps) {
    	try {
    		List<User> list = userServiceImpl.pagedList(model);
            maps.put("list", list);
		} catch (Exception e) {
			LOGGER.error("get record list error, errorMsg=[{}].", e.getMessage());
			return "common/error";
		}
        return "user/initial";
    }
	
	/**
	 * 获得用户表List，Json格式。
	 * @param model 参数
	 * @return 用户表List
	 */
	@ResponseBody
	@RequestMapping("/list")
	public List<User> list(User model) {
		List<User> list = null;
		try {
			list = userServiceImpl.pagedList(model);
		} catch (Exception e) {
			LOGGER.error("get record list error, errorMsg=[{}].", e.getMessage());
		}
		return list;
	}
	
	/**
     * 根据Id获得用户表实体，Json格式。
     * @param id 参数id
     * @return 用户表实体
     */
    @ResponseBody
    @RequestMapping("/get")
    public User get(String id) {
    	User model = null;
    	try {
    		model = userServiceImpl.get(id);
		} catch (Exception e) {
			LOGGER.error("get record error, errorMsg=[{}].", e.getMessage());
		}
        return model;
    }
	
    /**
     * 执行实际的新增操作
     * @param model 要新增的数据
     * @return 新增页面逻辑视图
     */
    @ResponseBody
    @RequestMapping("/doAdd")
    public JsonBean doAdd(@RequestBody User model) {
    	JsonBean bean = new JsonBean();
    	try {
    		String password = model.getPassword();
    		String checkPasswd = model.getCheckPasswd();
    		String mobile = model.getMobile(); // 手机号码和账户都应该做唯一行认证
    		String authCode = model.getAuthCode();
    		String account = model.getAccount();
    		
    		if (StringUtils.isAnyBlank(password, checkPasswd, mobile, authCode, account)) {
    			bean.setMessage("注册信息填写不完整");
    			bean.setCode(2);
    			return bean;
    		}
    		
    		String random = (String) getSessionParameter("random");
    		if (!authCode.equalsIgnoreCase(random)) {
    			bean.setMessage("验证码错误");
    			bean.setCode(8);
    			return bean;
    		}
    		
    		if (!password.equals(checkPasswd)) {
    			bean.setMessage("两次输入的密码不相同");
    			bean.setCode(3);
    			return bean;
    		}
    		
    		if (password.length() < 6) {
    			bean.setMessage("密码长度不能小于6位");
    			bean.setCode(4);
    			return bean;
    		}
    		
    		if (!MOBILE.matcher(mobile).matches()) {
    			bean.setMessage("手机号码不正确");
    			bean.setCode(5);
    			return bean;
    		}
    		try {
    			User params = new User();
    			params.setAccount(account);
    			params.setMobile(mobile);
    			params = userServiceImpl.unique(params);
    			if (params != null) {
    				bean.setCode(6); // 存在一个
    				bean.setMessage("账号或手机号码重复");
    				return bean;
    			}
			} catch (TooManyResultsException e) {
				bean.setCode(7); // 有多个，这种情况本身就是错误的
				bean.setMessage("账号或手机号码已存在");
				return bean;
			}
    		
    		password = bcryptPasswordEncoder.encode(password);
    		model.setPassword(password);
    		model.setId(ObjectId.get().toString());
    		if (StringUtils.isBlank(model.getNickName())) {
    			model.setNickName(account);
    		}
    		Date date = new Date();
    		model.setRegsiterDate(date);
    		model.setLastLoginDate(date);
    		
    		int result = userServiceImpl.save(model);
            if (result == 1) {
                bean.setMessage(SUCCESS);
                bean.setCode(1);
                LOGGER.info("save user success.");
            } else {
                bean.setMessage(ERROR);
                LOGGER.error("save user error.");
            }
		} catch (Exception e) {
			LOGGER.error("save user error, errorMsg=[{}].", e.getMessage());
			bean.setMessage(ERROR);
		}
        return bean;
    }
    
    /**
     * 用户登录
     * @param user 登录参数
     * @return 登录成功跳转到首页
     */
    @ResponseBody
    @RequestMapping("/doLogin")
    public JsonBean doLogin(@RequestBody User user) {
    	JsonBean bean = new JsonBean();
    	try {
			String password = user.getPassword();
			String authCode = user.getAuthCode();
			String account = user.getAccount();
			if (StringUtils.isAnyBlank(password, authCode, account)) {
				bean.setMessage("登录信息填写不完整");
    			bean.setCode(2);
    			return bean;
			}
			String random = (String) getSessionParameter("random");
    		if (!authCode.equalsIgnoreCase(random)) {
    			bean.setMessage("验证码错误");
    			bean.setCode(8);
    			return bean;
    		}
    		if (password.length() < 6) {
    			bean.setMessage("用户名或密码错误"); // 登录的时候小于6位肯定错了，不提示精确的错误信息
    			bean.setCode(4);
    			return bean;
    		}
//    		if (MOBILE.matcher(account).matches()) { // 如果账户满足手机号码的格式，那么使用了手机号登录？手机号没法严格验证
//    			
//    		}
    		User params = new User();
    		params.setAccount(account);
    		params = userServiceImpl.unique(params);
    		if (params == null) {
    			bean.setCode(3);
    			bean.setMessage("用户名或密码输入错误");
    			return bean;
    		} else {
    			boolean equals =bcryptPasswordEncoder.matches(password, params.getPassword());
    			if (!equals) { // 密码不相等
    				bean.setCode(5);
        			bean.setMessage("用户名或密码输入错误");
        			return bean;
    			}
    		}
    		bean.setCode(1);
    		bean.setMessage(SUCCESS);
    		// 如果有重定向url，返回
//    		String redirectUrl = user.getRedirectUrl();
//    		if (redirectUrl != null) {
//    			Map<String, String> data = Maps.newHashMap();
//    			data.put("redirectUrl", redirectUrl);
//    		}
    		setAttributeToSession(CommonConst.CONTEXT_USER, params);
		} catch (Exception e) {
			LOGGER.error("user login error, msg=[{}]", e.getMessage());
			bean.setMessage(ERROR);
		}
    	return bean;
    }
    
    /**
     * 用户中心
     * @param model 查询参数，携带ID
     * @return 用户表详情页
     */
    @RequestMapping("/center")
    public String center(User model, Map<String, Object> maps) {
    	try {
    		model = userServiceImpl.get(model.getId());
            maps.put("model", model);
		} catch (Exception e) {
			LOGGER.error("query user error, errorMsg=[{}].", e.getMessage());
			return "common/error";
		}
        return "user/center";
    }
    
    /**
     * 查看用户表详情页。
     * @param model 查询参数，携带ID
     * @return 用户表详情页
     */
    @RequestMapping("/detail")
    public String detail(User model, Map<String, Object> maps) {
    	try {
    		model = userServiceImpl.get(model.getId());
            maps.put("model", model);//将model放入视图中，供页面视图使用
		} catch (Exception e) {
			LOGGER.error("query user error, errorMsg=[{}].", e.getMessage());
			return "common/error";
		}
        return "user/detail";
    }
    
    /**
     * 跳转到编辑信息页面
     * @param model 查询参数，携带ID
     * @return 编辑页面
     */
    @RequestMapping("/edit")
    public String edit(User model, Map<String, Object> maps) {
        model = userServiceImpl.get(model.getId());
        maps.put("model", model);
        return "user/edit";
    }
    
    /**
     * 更新用户表信息
     * @param model 要更新的用户表信息，含有ID
     * @return 操作结果信息
     */
    @ResponseBody
    @RequestMapping("/update")
    public JsonBean update(User model) {
    	JsonBean bean = new JsonBean();
    	try {
    		int result = userServiceImpl.updateById(model);
            if (result == 1) {
                bean.setMessage(SUCCESS);
                bean.setCode(1);
                LOGGER.info("update record success.");
            } else {
                bean.setMessage(ERROR);
                LOGGER.error("update record error.");
            }
		} catch (Exception e) {
			LOGGER.error("update record error, errorMsg=[{}].", e.getMessage());
			bean.setMessage(ERROR);
		}
        return bean;
    }
    
    /**
     * 删除用户表信息
     * @param id 要删除的用户表ID
     */
    @ResponseBody
    @RequestMapping("/delete")
    public JsonBean delete(String id) {
    	JsonBean bean = new JsonBean();
    	try {
    		int result = userServiceImpl.deleteById(id);
    		if (result == 1) {
    			bean.setMessage(SUCCESS);
    			bean.setCode(1);
    			LOGGER.info("delete record success, id=[{}].", id);
    		} else {
    			bean.setMessage(ERROR);
    			LOGGER.error("delete record error.");
    		}
		} catch (Exception e) {
			LOGGER.error("delete record error, id=[{}], errorMsg=[{}].", id, e.getMessage());
			bean.setMessage(ERROR);
		}
        return bean;
    }
}
