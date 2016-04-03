package com.vteba.tianxun.question.action;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vteba.tianxun.question.model.Category;
import com.vteba.tianxun.question.service.spi.CategoryService;

import com.vteba.web.action.GenericAction;
import com.vteba.web.action.JsonBean;

/**
 * 问题分类控制器
 * @author yinlei
 * @date 2016-3-26 20:41:37
 */
@Controller
@RequestMapping("/category")
public class CategoryAction extends GenericAction<Category> {
	private static final Logger LOGGER = LogManager.getLogger(CategoryAction.class);
	
	@Inject
	private CategoryService categoryServiceImpl;
	
	/**
     * 获得问题分类List，初始化列表页。
     * @param model 参数
     * @return 问题分类List
     */
    @RequestMapping("/initial")
    public String initial(Category model, Map<String, Object> maps) {
    	try {
    		List<Category> list = categoryServiceImpl.pagedList(model);
            maps.put("list", list);
		} catch (Exception e) {
			LOGGER.error("get record list error, errorMsg=[{}].", e.getMessage());
			return "common/error";
		}
        return "category/initial";
    }
	
	/**
	 * 获得问题分类List，Json格式。
	 * @param model 参数
	 * @return 问题分类List
	 */
	@ResponseBody
	@RequestMapping("/list")
	public List<Category> list(Category model) {
		List<Category> list = null;
		try {
			list = categoryServiceImpl.pagedList(model);
		} catch (Exception e) {
			LOGGER.error("get record list error, errorMsg=[{}].", e.getMessage());
		}
		return list;
	}
	
	/**
     * 根据Id获得问题分类实体，Json格式。
     * @param id 参数id
     * @return 问题分类实体
     */
    @ResponseBody
    @RequestMapping("/get")
    public Category get(Integer id) {
    	Category model = null;
    	try {
    		model = categoryServiceImpl.get(id);
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
        return "category/add";
    }
    
    /**
     * 执行实际的新增操作
     * @param model 要新增的数据
     * @return 新增页面逻辑视图
     */
    @ResponseBody
    @RequestMapping("/doAdd")
    public JsonBean doAdd(Category model) {
    	JsonBean bean = new JsonBean();
    	try {
    		int result = categoryServiceImpl.save(model);
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
     * 查看问题分类详情页。
     * @param model 查询参数，携带ID
     * @return 问题分类详情页
     */
    @RequestMapping("/detail")
    public String detail(Category model, Map<String, Object> maps) {
    	try {
    		model = categoryServiceImpl.get(model.getCode());
            maps.put("model", model);//将model放入视图中，供页面视图使用
		} catch (Exception e) {
			LOGGER.error("query record detail, errorMsg=[{}].", e.getMessage());
			return "common/error";
		}
        return "category/detail";
    }
    
    /**
     * 跳转到编辑信息页面
     * @param model 查询参数，携带ID
     * @return 编辑页面
     */
    @RequestMapping("/edit")
    public String edit(Category model, Map<String, Object> maps) {
        model = categoryServiceImpl.get(model.getCode());
        maps.put("model", model);
        return "category/edit";
    }
    
    /**
     * 更新问题分类信息
     * @param model 要更新的问题分类信息，含有ID
     * @return 操作结果信息
     */
    @ResponseBody
    @RequestMapping("/update")
    public JsonBean update(Category model) {
    	JsonBean bean = new JsonBean();
    	try {
    		int result = categoryServiceImpl.updateById(model);
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
     * 删除问题分类信息
     * @param id 要删除的问题分类ID
     */
    @ResponseBody
    @RequestMapping("/delete")
    public JsonBean delete(Integer id) {
    	JsonBean bean = new JsonBean();
    	try {
    		int result = categoryServiceImpl.deleteById(id);
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
