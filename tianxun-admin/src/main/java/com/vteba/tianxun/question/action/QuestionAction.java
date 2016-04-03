package com.vteba.tianxun.question.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vteba.common.exception.BasicException;
import com.vteba.tianxun.question.model.Question;
import com.vteba.tianxun.question.service.spi.QuestionService;
import com.vteba.tianxun.user.model.User;
import com.vteba.tianxun.utils.common.UserUtils;
import com.vteba.utils.id.ObjectId;
import com.vteba.web.action.GenericAction;
import com.vteba.web.action.JsonBean;

/**
 * 问题表控制器
 * @author yinlei
 * @date 2016-3-26 20:41:36
 */
@Controller
@RequestMapping("/question")
public class QuestionAction extends GenericAction<Question> {
	private static final Logger LOGGER = LogManager.getLogger(QuestionAction.class);
	
	@Inject
	private QuestionService questionServiceImpl;
	
	/**
     * 获得问题表List，初始化列表页。
     * @param model 参数
     * @return 问题表List
     */
    @RequestMapping("/initial")
    public String initial(Question model, Map<String, Object> maps) {
    	try {
    		List<Question> list = questionServiceImpl.pagedList(model);
            maps.put("list", list);
		} catch (Exception e) {
			LOGGER.error("get record list error, errorMsg=[{}].", e.getMessage());
			return "common/error";
		}
        return "question/initial";
    }
	
	/**
	 * 获得问题表List，Json格式。
	 * @param model 参数
	 * @return 问题表List
	 */
	@ResponseBody
	@RequestMapping("/list")
	public List<Question> list(Question model) {
		List<Question> list = null;
		try {
			list = questionServiceImpl.pagedList(model);
		} catch (Exception e) {
			LOGGER.error("get record list error, errorMsg=[{}].", e.getMessage());
		}
		return list;
	}
	
	/**
     * 根据Id获得问题表实体，Json格式。
     * @param id 参数id
     * @return 问题表实体
     */
    @ResponseBody
    @RequestMapping("/get")
    public Question get(String id) {
    	Question model = null;
    	try {
    		model = questionServiceImpl.get(id);
		} catch (Exception e) {
			LOGGER.error("get record error, errorMsg=[{}].", e.getMessage());
		}
        return model;
    }
	
	/**
     * 去提问，问千寻
     * @return 提问页
     */
    @RequestMapping("/ask")
    public String ask() {
        return "question/ask";
    }
    
    /**
     * 执行实际的新增操作，需要做敏感关键字过滤
     * @param model 要新增的数据
     * @return 新增页面逻辑视图
     */
    @ResponseBody
    @RequestMapping("/doAdd")
    public JsonBean doAdd(@RequestBody Question model) {
    	JsonBean bean = new JsonBean();
    	try {
    		String authCode = model.getAuthCode();
    		if (StringUtils.isBlank(authCode)) {
    			bean.setMessage("验证码错误");
    			bean.setCode(3);
    			return bean;
    		}
    		String random = (String) getSessionParameter("random");
    		if (!authCode.equalsIgnoreCase(random)) {
    			bean.setMessage("验证码错误");
    			bean.setCode(8);
    			return bean;
    		}
    		String content = model.getContent();
    		String title = model.getTitle();
    		if (StringUtils.isAnyBlank(content, title)) {
    			bean.setCode(2);
    			bean.setMessage("提问内容或标题不能为空。");
    		}
    		model.getPrice();
    		
    		User user = UserUtils.getUser();
    		model.setAsker(user.getAccount());
    		model.setAskerName(user.getNickName());
    		model.setId(ObjectId.get().toHexString());
    		Date date = new Date();
    		model.setAskDate(date);
    		
    		int result = questionServiceImpl.save(model);
            if (result == 1) {
                bean.setMessage(SUCCESS);
                bean.setCode(1);
                LOGGER.info("save record success.");
            } else {
                bean.setMessage(ERROR);
                LOGGER.error("save record error.");
            }
		} catch (BasicException e) {
			String message = e.getMessage();
			LOGGER.error("提问，出现Basic异常，msg=[{}].", message);
			bean.setMessage(message);
		} catch (Exception e) {
			LOGGER.error("提问出错, errorMsg=[{}].", e.getMessage());
			bean.setMessage(ERROR);
		}
        return bean;
    }
    
    /**
     * 查看问题表详情页。
     * @param model 查询参数，携带ID
     * @return 问题表详情页
     */
    @RequestMapping("/detail")
    public String detail(Question model, Map<String, Object> maps) {
    	try {
    		model = questionServiceImpl.get(model.getId());
            maps.put("model", model);//将model放入视图中，供页面视图使用
		} catch (Exception e) {
			LOGGER.error("query record detail, errorMsg=[{}].", e.getMessage());
			return "common/error";
		}
        return "question/detail";
    }
    
    /**
     * 跳转到编辑信息页面
     * @param model 查询参数，携带ID
     * @return 编辑页面
     */
    @RequestMapping("/edit")
    public String edit(Question model, Map<String, Object> maps) {
        model = questionServiceImpl.get(model.getId());
        maps.put("model", model);
        return "question/edit";
    }
    
    /**
     * 更新问题表信息
     * @param model 要更新的问题表信息，含有ID
     * @return 操作结果信息
     */
    @ResponseBody
    @RequestMapping("/update")
    public JsonBean update(Question model) {
    	JsonBean bean = new JsonBean();
    	try {
    		int result = questionServiceImpl.updateById(model);
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
     * 删除问题表信息
     * @param id 要删除的问题表ID
     */
    @ResponseBody
    @RequestMapping("/delete")
    public JsonBean delete(String id) {
    	JsonBean bean = new JsonBean();
    	try {
    		int result = questionServiceImpl.deleteById(id);
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
