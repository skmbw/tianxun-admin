package com.vteba.tianxun.score.action;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vteba.tianxun.score.model.Score;
import com.vteba.tianxun.score.service.spi.ScoreService;

import com.vteba.web.action.GenericAction;
import com.vteba.web.action.JsonBean;

/**
 * 用户积分控制器
 * @author yinlei
 * @date 2016-3-24 19:22:01
 */
@Controller
@RequestMapping("/score")
public class ScoreAction extends GenericAction<Score> {
	private static final Logger LOGGER = LogManager.getLogger(ScoreAction.class);
	
	@Inject
	private ScoreService scoreServiceImpl;
	
	/**
     * 获得用户积分List，初始化列表页。
     * @param model 参数
     * @return 用户积分List
     */
    @RequestMapping("/initial")
    public String initial(Score model, Map<String, Object> maps) {
    	try {
    		List<Score> list = scoreServiceImpl.pagedList(model);
            maps.put("list", list);
		} catch (Exception e) {
			LOGGER.error("get record list error, errorMsg=[{}].", e.getMessage());
			return "common/error";
		}
        return "score/initial";
    }
	
	/**
	 * 获得用户积分List，Json格式。
	 * @param model 参数
	 * @return 用户积分List
	 */
	@ResponseBody
	@RequestMapping("/list")
	public List<Score> list(Score model) {
		List<Score> list = null;
		try {
			list = scoreServiceImpl.pagedList(model);
		} catch (Exception e) {
			LOGGER.error("get record list error, errorMsg=[{}].", e.getMessage());
		}
		return list;
	}
	
	/**
     * 根据Id获得用户积分实体，Json格式。
     * @param id 参数id
     * @return 用户积分实体
     */
    @ResponseBody
    @RequestMapping("/get")
    public Score get(String id) {
    	Score model = null;
    	try {
    		model = scoreServiceImpl.get(id);
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
        return "score/add";
    }
    
    /**
     * 执行实际的新增操作
     * @param model 要新增的数据
     * @return 新增页面逻辑视图
     */
    @ResponseBody
    @RequestMapping("/doAdd")
    public JsonBean doAdd(Score model) {
    	JsonBean bean = new JsonBean();
    	try {
    		int result = scoreServiceImpl.save(model);
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
     * 查看用户积分详情页。
     * @param model 查询参数，携带ID
     * @return 用户积分详情页
     */
    @RequestMapping("/detail")
    public String detail(Score model, Map<String, Object> maps) {
    	try {
    		model = scoreServiceImpl.get(model.getId());
            maps.put("model", model);//将model放入视图中，供页面视图使用
		} catch (Exception e) {
			LOGGER.error("query record detail, errorMsg=[{}].", e.getMessage());
			return "common/error";
		}
        return "score/detail";
    }
    
    /**
     * 跳转到编辑信息页面
     * @param model 查询参数，携带ID
     * @return 编辑页面
     */
    @RequestMapping("/edit")
    public String edit(Score model, Map<String, Object> maps) {
        model = scoreServiceImpl.get(model.getId());
        maps.put("model", model);
        return "score/edit";
    }
    
    /**
     * 更新用户积分信息
     * @param model 要更新的用户积分信息，含有ID
     * @return 操作结果信息
     */
    @ResponseBody
    @RequestMapping("/update")
    public JsonBean update(Score model) {
    	JsonBean bean = new JsonBean();
    	try {
    		int result = scoreServiceImpl.updateById(model);
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
     * 删除用户积分信息
     * @param id 要删除的用户积分ID
     */
    @ResponseBody
    @RequestMapping("/delete")
    public JsonBean delete(String id) {
    	JsonBean bean = new JsonBean();
    	try {
    		int result = scoreServiceImpl.deleteById(id);
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
