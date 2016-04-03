package com.vteba.tianxun.question.action;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vteba.tianxun.question.model.Tags;
import com.vteba.tianxun.question.service.spi.TagsService;

import com.vteba.web.action.GenericAction;
import com.vteba.web.action.JsonBean;

/**
 * 热门标签控制器
 * @author yinlei
 * @date 2016-3-26 20:41:37
 */
@Controller
@RequestMapping("/tags")
public class TagsAction extends GenericAction<Tags> {
	private static final Logger LOGGER = LogManager.getLogger(TagsAction.class);
	
	@Inject
	private TagsService tagsServiceImpl;
	
	/**
     * 获得热门标签List，初始化列表页。
     * @param model 参数
     * @return 热门标签List
     */
    @RequestMapping("/initial")
    public String initial(Tags model, Map<String, Object> maps) {
    	try {
    		List<Tags> list = tagsServiceImpl.pagedList(model);
            maps.put("list", list);
		} catch (Exception e) {
			LOGGER.error("get record list error, errorMsg=[{}].", e.getMessage());
			return "common/error";
		}
        return "tags/initial";
    }
	
	/**
	 * 获得热门标签List，Json格式。
	 * @param model 参数
	 * @return 热门标签List
	 */
	@ResponseBody
	@RequestMapping("/list")
	public List<Tags> list(Tags model) {
		List<Tags> list = null;
		try {
			list = tagsServiceImpl.pagedList(model);
		} catch (Exception e) {
			LOGGER.error("get record list error, errorMsg=[{}].", e.getMessage());
		}
		return list;
	}
	
	/**
     * 根据Id获得热门标签实体，Json格式。
     * @param id 参数id
     * @return 热门标签实体
     */
    @ResponseBody
    @RequestMapping("/get")
    public Tags get(String id) {
    	Tags model = null;
    	try {
    		model = tagsServiceImpl.get(id);
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
        return "tags/add";
    }
    
    /**
     * 执行实际的新增操作
     * @param model 要新增的数据
     * @return 新增页面逻辑视图
     */
    @ResponseBody
    @RequestMapping("/doAdd")
    public JsonBean doAdd(Tags model) {
    	JsonBean bean = new JsonBean();
    	try {
    		int result = tagsServiceImpl.save(model);
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
     * 查看热门标签详情页。
     * @param model 查询参数，携带ID
     * @return 热门标签详情页
     */
    @RequestMapping("/detail")
    public String detail(Tags model, Map<String, Object> maps) {
    	try {
    		model = tagsServiceImpl.get(model.getId());
            maps.put("model", model);//将model放入视图中，供页面视图使用
		} catch (Exception e) {
			LOGGER.error("query record detail, errorMsg=[{}].", e.getMessage());
			return "common/error";
		}
        return "tags/detail";
    }
    
    /**
     * 跳转到编辑信息页面
     * @param model 查询参数，携带ID
     * @return 编辑页面
     */
    @RequestMapping("/edit")
    public String edit(Tags model, Map<String, Object> maps) {
        model = tagsServiceImpl.get(model.getId());
        maps.put("model", model);
        return "tags/edit";
    }
    
    /**
     * 更新热门标签信息
     * @param model 要更新的热门标签信息，含有ID
     * @return 操作结果信息
     */
    @ResponseBody
    @RequestMapping("/update")
    public JsonBean update(Tags model) {
    	JsonBean bean = new JsonBean();
    	try {
    		int result = tagsServiceImpl.updateById(model);
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
     * 删除热门标签信息
     * @param id 要删除的热门标签ID
     */
    @ResponseBody
    @RequestMapping("/delete")
    public JsonBean delete(String id) {
    	JsonBean bean = new JsonBean();
    	try {
    		int result = tagsServiceImpl.deleteById(id);
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
