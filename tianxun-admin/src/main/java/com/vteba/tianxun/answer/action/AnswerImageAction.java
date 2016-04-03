package com.vteba.tianxun.answer.action;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vteba.tianxun.answer.model.AnswerImage;
import com.vteba.tianxun.answer.service.spi.AnswerImageService;

import com.vteba.web.action.GenericAction;
import com.vteba.web.action.JsonBean;

/**
 * 问题回答图片表控制器
 * @author yinlei
 * @date 2016-3-26 20:34:42
 */
@Controller
@RequestMapping("/answerImage")
public class AnswerImageAction extends GenericAction<AnswerImage> {
	private static final Logger LOGGER = LogManager.getLogger(AnswerImageAction.class);
	
	@Inject
	private AnswerImageService answerImageServiceImpl;
	
	/**
     * 获得问题回答图片表List，初始化列表页。
     * @param model 参数
     * @return 问题回答图片表List
     */
    @RequestMapping("/initial")
    public String initial(AnswerImage model, Map<String, Object> maps) {
    	try {
    		List<AnswerImage> list = answerImageServiceImpl.pagedList(model);
            maps.put("list", list);
		} catch (Exception e) {
			LOGGER.error("get record list error, errorMsg=[{}].", e.getMessage());
			return "common/error";
		}
        return "answerImage/initial";
    }
	
	/**
	 * 获得问题回答图片表List，Json格式。
	 * @param model 参数
	 * @return 问题回答图片表List
	 */
	@ResponseBody
	@RequestMapping("/list")
	public List<AnswerImage> list(AnswerImage model) {
		List<AnswerImage> list = null;
		try {
			list = answerImageServiceImpl.pagedList(model);
		} catch (Exception e) {
			LOGGER.error("get record list error, errorMsg=[{}].", e.getMessage());
		}
		return list;
	}
	
	/**
     * 根据Id获得问题回答图片表实体，Json格式。
     * @param id 参数id
     * @return 问题回答图片表实体
     */
    @ResponseBody
    @RequestMapping("/get")
    public AnswerImage get(String id) {
    	AnswerImage model = null;
    	try {
    		model = answerImageServiceImpl.get(id);
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
        return "answerImage/add";
    }
    
    /**
     * 执行实际的新增操作
     * @param model 要新增的数据
     * @return 新增页面逻辑视图
     */
    @ResponseBody
    @RequestMapping("/doAdd")
    public JsonBean doAdd(AnswerImage model) {
    	JsonBean bean = new JsonBean();
    	try {
    		int result = answerImageServiceImpl.save(model);
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
     * 查看问题回答图片表详情页。
     * @param model 查询参数，携带ID
     * @return 问题回答图片表详情页
     */
    @RequestMapping("/detail")
    public String detail(AnswerImage model, Map<String, Object> maps) {
    	try {
    		model = answerImageServiceImpl.get(model.getId());
            maps.put("model", model);//将model放入视图中，供页面视图使用
		} catch (Exception e) {
			LOGGER.error("query record detail, errorMsg=[{}].", e.getMessage());
			return "common/error";
		}
        return "answerImage/detail";
    }
    
    /**
     * 跳转到编辑信息页面
     * @param model 查询参数，携带ID
     * @return 编辑页面
     */
    @RequestMapping("/edit")
    public String edit(AnswerImage model, Map<String, Object> maps) {
        model = answerImageServiceImpl.get(model.getId());
        maps.put("model", model);
        return "answerImage/edit";
    }
    
    /**
     * 更新问题回答图片表信息
     * @param model 要更新的问题回答图片表信息，含有ID
     * @return 操作结果信息
     */
    @ResponseBody
    @RequestMapping("/update")
    public JsonBean update(AnswerImage model) {
    	JsonBean bean = new JsonBean();
    	try {
    		int result = answerImageServiceImpl.updateById(model);
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
     * 删除问题回答图片表信息
     * @param id 要删除的问题回答图片表ID
     */
    @ResponseBody
    @RequestMapping("/delete")
    public JsonBean delete(String id) {
    	JsonBean bean = new JsonBean();
    	try {
    		int result = answerImageServiceImpl.deleteById(id);
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
