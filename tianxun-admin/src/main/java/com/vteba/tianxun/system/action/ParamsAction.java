package com.vteba.tianxun.system.action;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vteba.tianxun.system.model.Params;
import com.vteba.tianxun.system.service.spi.ParamsService;

import com.vteba.web.action.GenericAction;
import com.vteba.web.action.JsonBean;

/**
 * 系统参数表控制器
 * @author yinlei
 * @date 2016-3-24 20:16:02
 */
@Controller
@RequestMapping("/params")
public class ParamsAction extends GenericAction<Params> {
	private static final Logger LOGGER = LogManager.getLogger(ParamsAction.class);
	
	@Inject
	private ParamsService paramsServiceImpl;
	
	/**
     * 获得系统参数表List，初始化列表页。
     * @param model 参数
     * @return 系统参数表List
     */
    @RequestMapping("/initial")
    public String initial(Params model, Map<String, Object> maps) {
    	try {
    		List<Params> list = paramsServiceImpl.pagedList(model);
            maps.put("list", list);
		} catch (Exception e) {
			LOGGER.error("get record list error, errorMsg=[{}].", e.getMessage());
			return "common/error";
		}
        return "params/initial";
    }
	
	/**
	 * 获得系统参数表List，Json格式。
	 * @param model 参数
	 * @return 系统参数表List
	 */
	@ResponseBody
	@RequestMapping("/list")
	public List<Params> list(Params model) {
		List<Params> list = null;
		try {
			list = paramsServiceImpl.pagedList(model);
		} catch (Exception e) {
			LOGGER.error("get record list error, errorMsg=[{}].", e.getMessage());
		}
		return list;
	}
	
	/**
     * 根据Id获得系统参数表实体，Json格式。
     * @param id 参数id
     * @return 系统参数表实体
     */
    @ResponseBody
    @RequestMapping("/get")
    public Params get(String id) {
    	Params model = null;
    	try {
    		model = paramsServiceImpl.get(id);
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
        return "params/add";
    }
    
    /**
     * 执行实际的新增操作
     * @param model 要新增的数据
     * @return 新增页面逻辑视图
     */
    @ResponseBody
    @RequestMapping("/doAdd")
    public JsonBean doAdd(Params model) {
    	JsonBean bean = new JsonBean();
    	try {
    		int result = paramsServiceImpl.save(model);
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
     * 查看系统参数表详情页。
     * @param model 查询参数，携带ID
     * @return 系统参数表详情页
     */
    @RequestMapping("/detail")
    public String detail(Params model, Map<String, Object> maps) {
    	try {
    		model = paramsServiceImpl.get(model.getId());
            maps.put("model", model);//将model放入视图中，供页面视图使用
		} catch (Exception e) {
			LOGGER.error("query record detail, errorMsg=[{}].", e.getMessage());
			return "common/error";
		}
        return "params/detail";
    }
    
    /**
     * 跳转到编辑信息页面
     * @param model 查询参数，携带ID
     * @return 编辑页面
     */
    @RequestMapping("/edit")
    public String edit(Params model, Map<String, Object> maps) {
        model = paramsServiceImpl.get(model.getId());
        maps.put("model", model);
        return "params/edit";
    }
    
    /**
     * 更新系统参数表信息
     * @param model 要更新的系统参数表信息，含有ID
     * @return 操作结果信息
     */
    @ResponseBody
    @RequestMapping("/update")
    public JsonBean update(Params model) {
    	JsonBean bean = new JsonBean();
    	try {
    		int result = paramsServiceImpl.updateById(model);
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
     * 删除系统参数表信息
     * @param id 要删除的系统参数表ID
     */
    @ResponseBody
    @RequestMapping("/delete")
    public JsonBean delete(String id) {
    	JsonBean bean = new JsonBean();
    	try {
    		int result = paramsServiceImpl.deleteById(id);
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
