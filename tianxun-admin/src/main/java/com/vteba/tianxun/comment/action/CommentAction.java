package com.vteba.tianxun.comment.action;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vteba.tianxun.comment.model.Comment;
import com.vteba.tianxun.comment.service.spi.CommentService;

import com.vteba.web.action.GenericAction;
import com.vteba.web.action.JsonBean;

/**
 * 评论表控制器
 * @author yinlei
 * @date 2016-4-3 12:13:48
 */
@Controller
@RequestMapping("/comment")
public class CommentAction extends GenericAction<Comment> {
	private static final Logger LOGGER = LogManager.getLogger(CommentAction.class);
	
	@Inject
	private CommentService commentServiceImpl;
	
	/**
     * 获得评论表List，初始化列表页。
     * @param model 参数
     * @return 评论表List
     */
    @RequestMapping("/initial")
    public String initial(Comment model, Map<String, Object> maps) {
    	try {
    		List<Comment> list = commentServiceImpl.pagedList(model);
            maps.put("list", list);
		} catch (Exception e) {
			LOGGER.error("get record list error, errorMsg=[{}].", e.getMessage());
			return "common/error";
		}
        return "comment/initial";
    }
	
	/**
	 * 获得评论表List，Json格式。
	 * @param model 参数
	 * @return 评论表List
	 */
	@ResponseBody
	@RequestMapping("/list")
	public List<Comment> list(Comment model) {
		List<Comment> list = null;
		try {
			list = commentServiceImpl.pagedList(model);
		} catch (Exception e) {
			LOGGER.error("get record list error, errorMsg=[{}].", e.getMessage());
		}
		return list;
	}
	
	/**
     * 根据Id获得评论表实体，Json格式。
     * @param id 参数id
     * @return 评论表实体
     */
    @ResponseBody
    @RequestMapping("/get")
    public Comment get(String id) {
    	Comment model = null;
    	try {
    		model = commentServiceImpl.get(id);
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
        return "comment/add";
    }
    
    /**
     * 执行实际的新增操作
     * @param model 要新增的数据
     * @return 新增页面逻辑视图
     */
    @ResponseBody
    @RequestMapping("/doAdd")
    public JsonBean doAdd(Comment model) {
    	JsonBean bean = new JsonBean();
    	try {
    		int result = commentServiceImpl.save(model);
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
     * 查看评论表详情页。
     * @param model 查询参数，携带ID
     * @return 评论表详情页
     */
    @RequestMapping("/detail")
    public String detail(Comment model, Map<String, Object> maps) {
    	try {
    		model = commentServiceImpl.get(model.getId());
            maps.put("model", model);//将model放入视图中，供页面视图使用
		} catch (Exception e) {
			LOGGER.error("query record detail, errorMsg=[{}].", e.getMessage());
			return "common/error";
		}
        return "comment/detail";
    }
    
    /**
     * 跳转到编辑信息页面
     * @param model 查询参数，携带ID
     * @return 编辑页面
     */
    @RequestMapping("/edit")
    public String edit(Comment model, Map<String, Object> maps) {
        model = commentServiceImpl.get(model.getId());
        maps.put("model", model);
        return "comment/edit";
    }
    
    /**
     * 更新评论表信息
     * @param model 要更新的评论表信息，含有ID
     * @return 操作结果信息
     */
    @ResponseBody
    @RequestMapping("/update")
    public JsonBean update(Comment model) {
    	JsonBean bean = new JsonBean();
    	try {
    		int result = commentServiceImpl.updateById(model);
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
     * 删除评论表信息
     * @param id 要删除的评论表ID
     */
    @ResponseBody
    @RequestMapping("/delete")
    public JsonBean delete(String id) {
    	JsonBean bean = new JsonBean();
    	try {
    		int result = commentServiceImpl.deleteById(id);
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
