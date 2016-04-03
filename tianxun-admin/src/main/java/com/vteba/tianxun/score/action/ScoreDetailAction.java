package com.vteba.tianxun.score.action;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vteba.tianxun.score.model.ScoreDetail;
import com.vteba.tianxun.score.service.spi.ScoreDetailService;

import com.vteba.web.action.GenericAction;
import com.vteba.web.action.JsonBean;

/**
 * 积分明细控制器
 * @author yinlei
 * @date 2016-3-24 19:22:02
 */
@Controller
@RequestMapping("/scoreDetail")
public class ScoreDetailAction extends GenericAction<ScoreDetail> {
	private static final Logger LOGGER = LogManager.getLogger(ScoreDetailAction.class);
	
	@Inject
	private ScoreDetailService scoreDetailServiceImpl;
	
	/**
     * 获得积分明细List，初始化列表页。
     * @param model 参数
     * @return 积分明细List
     */
    @RequestMapping("/initial")
    public String initial(ScoreDetail model, Map<String, Object> maps) {
    	try {
    		List<ScoreDetail> list = scoreDetailServiceImpl.pagedList(model);
            maps.put("list", list);
		} catch (Exception e) {
			LOGGER.error("get record list error, errorMsg=[{}].", e.getMessage());
			return "common/error";
		}
        return "scoreDetail/initial";
    }
	
	/**
	 * 获得积分明细List，Json格式。
	 * @param model 参数
	 * @return 积分明细List
	 */
	@ResponseBody
	@RequestMapping("/list")
	public List<ScoreDetail> list(ScoreDetail model) {
		List<ScoreDetail> list = null;
		try {
			list = scoreDetailServiceImpl.pagedList(model);
		} catch (Exception e) {
			LOGGER.error("get record list error, errorMsg=[{}].", e.getMessage());
		}
		return list;
	}
	
	/**
     * 根据Id获得积分明细实体，Json格式。
     * @param id 参数id
     * @return 积分明细实体
     */
    @ResponseBody
    @RequestMapping("/get")
    public ScoreDetail get(String id) {
    	ScoreDetail model = null;
    	try {
    		model = scoreDetailServiceImpl.get(id);
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
        return "scoreDetail/add";
    }
    
    /**
     * 执行实际的新增操作
     * @param model 要新增的数据
     * @return 新增页面逻辑视图
     */
    @ResponseBody
    @RequestMapping("/doAdd")
    public JsonBean doAdd(ScoreDetail model) {
    	JsonBean bean = new JsonBean();
    	try {
    		int result = scoreDetailServiceImpl.save(model);
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
     * 查看积分明细详情页。
     * @param model 查询参数，携带ID
     * @return 积分明细详情页
     */
    @RequestMapping("/detail")
    public String detail(ScoreDetail model, Map<String, Object> maps) {
    	try {
    		model = scoreDetailServiceImpl.get(model.getId());
            maps.put("model", model);//将model放入视图中，供页面视图使用
		} catch (Exception e) {
			LOGGER.error("query record detail, errorMsg=[{}].", e.getMessage());
			return "common/error";
		}
        return "scoreDetail/detail";
    }
    
    /**
     * 跳转到编辑信息页面
     * @param model 查询参数，携带ID
     * @return 编辑页面
     */
    @RequestMapping("/edit")
    public String edit(ScoreDetail model, Map<String, Object> maps) {
        model = scoreDetailServiceImpl.get(model.getId());
        maps.put("model", model);
        return "scoreDetail/edit";
    }
    
    /**
     * 更新积分明细信息
     * @param model 要更新的积分明细信息，含有ID
     * @return 操作结果信息
     */
    @ResponseBody
    @RequestMapping("/update")
    public JsonBean update(ScoreDetail model) {
    	JsonBean bean = new JsonBean();
    	try {
    		int result = scoreDetailServiceImpl.updateById(model);
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
     * 删除积分明细信息
     * @param id 要删除的积分明细ID
     */
    @ResponseBody
    @RequestMapping("/delete")
    public JsonBean delete(String id) {
    	JsonBean bean = new JsonBean();
    	try {
    		int result = scoreDetailServiceImpl.deleteById(id);
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
