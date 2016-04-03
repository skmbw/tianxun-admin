package com.vteba.tianxun.answer.action;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vteba.tianxun.answer.model.Answer;
import com.vteba.tianxun.answer.service.spi.AnswerService;

import com.vteba.web.action.GenericAction;
import com.vteba.web.action.JsonBean;

/**
 * 问题回答表控制器
 * @author yinlei
 * @date 2016-3-26 20:34:40
 */
@Controller
@RequestMapping("/answer")
public class AnswerAction extends GenericAction<Answer> {
	private static final Logger LOGGER = LogManager.getLogger(AnswerAction.class);
	
	@Inject
	private AnswerService answerServiceImpl;
	
	/**
     * 获得问题回答表List，初始化列表页。
     * @param model 参数
     * @return 问题回答表List
     */
    @RequestMapping("/initial")
    public String initial(Answer model, Map<String, Object> maps) {
    	try {
    		List<Answer> list = answerServiceImpl.pagedList(model);
            maps.put("list", list);
		} catch (Exception e) {
			LOGGER.error("get record list error, errorMsg=[{}].", e.getMessage());
			return "common/error";
		}
        return "answer/initial";
    }
	
	/**
	 * 获得问题回答表List，Json格式。
	 * @param model 参数
	 * @return 问题回答表List
	 */
	@ResponseBody
	@RequestMapping("/list")
	public List<Answer> list(Answer model) {
		List<Answer> list = null;
		try {
			list = answerServiceImpl.pagedList(model);
		} catch (Exception e) {
			LOGGER.error("get record list error, errorMsg=[{}].", e.getMessage());
		}
		return list;
	}
	
	/**
     * 根据Id获得问题回答表实体，Json格式。
     * @param id 参数id
     * @return 问题回答表实体
     */
    @ResponseBody
    @RequestMapping("/get")
    public Answer get(String id) {
    	Answer model = null;
    	try {
    		model = answerServiceImpl.get(id);
		} catch (Exception e) {
			LOGGER.error("get record error, errorMsg=[{}].", e.getMessage());
		}
        return model;
    }
	
	/**
     * 跳转到新增页面
     * @return 新增页面逻辑视图
     */
    @RequestMapping("/add")
    public String add() {
        return "answer/add";
    }
    
    /**
     * 执行实际的新增操作
     * @param model 要新增的数据
     * @return 新增页面逻辑视图
     */
    @ResponseBody
    @RequestMapping("/doAdd")
    public JsonBean doAdd(Answer model) {
    	JsonBean bean = new JsonBean();
    	try {
    		int result = answerServiceImpl.save(model);
            if (result == 1) {
                bean.setMessage(SUCCESS);
                bean.setCode(1);
                LOGGER.info("save record success.");
            } else {
                bean.setMessage(ERROR);
                LOGGER.error("save record error.");
            }
		} catch (Exception e) {
			LOGGER.error("save record error, errorMsg=[{}].", e.getMessage());
			bean.setMessage(ERROR);
		}
        return bean;
    }
    
    /**
     * 查看问题回答表详情页。
     * @param model 查询参数，携带ID
     * @return 问题回答表详情页
     */
    @RequestMapping("/detail")
    public String detail(Answer model, Map<String, Object> maps) {
    	try {
    		model = answerServiceImpl.get(model.getId());
            maps.put("model", model);//将model放入视图中，供页面视图使用
		} catch (Exception e) {
			LOGGER.error("query record detail, errorMsg=[{}].", e.getMessage());
			return "common/error";
		}
        return "answer/detail";
    }
    
    /**
     * 跳转到编辑信息页面
     * @param model 查询参数，携带ID
     * @return 编辑页面
     */
    @RequestMapping("/edit")
    public String edit(Answer model, Map<String, Object> maps) {
        model = answerServiceImpl.get(model.getId());
        maps.put("model", model);
        return "answer/edit";
    }
    
    /**
     * 更新问题回答表信息
     * @param model 要更新的问题回答表信息，含有ID
     * @return 操作结果信息
     */
    @ResponseBody
    @RequestMapping("/update")
    public JsonBean update(Answer model) {
    	JsonBean bean = new JsonBean();
    	try {
    		int result = answerServiceImpl.updateById(model);
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
     * 删除问题回答表信息
     * @param id 要删除的问题回答表ID
     */
    @ResponseBody
    @RequestMapping("/delete")
    public JsonBean delete(String id) {
    	JsonBean bean = new JsonBean();
    	try {
    		int result = answerServiceImpl.deleteById(id);
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
