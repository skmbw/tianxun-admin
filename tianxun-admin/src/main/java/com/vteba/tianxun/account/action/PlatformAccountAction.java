package com.vteba.tianxun.account.action;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vteba.tianxun.account.model.PlatformAccount;
import com.vteba.tianxun.account.service.spi.PlatformAccountService;

import com.vteba.web.action.GenericAction;
import com.vteba.web.action.JsonBean;

/**
 * 平台资金账户控制器
 * @author yinlei
 * @date 2016-3-24 19:58:06
 */
@Controller
@RequestMapping("/platformAccount")
public class PlatformAccountAction extends GenericAction<PlatformAccount> {
	private static final Logger LOGGER = LogManager.getLogger(PlatformAccountAction.class);
	
	@Inject
	private PlatformAccountService platformAccountServiceImpl;
	
	/**
     * 获得平台资金账户List，初始化列表页。
     * @param model 参数
     * @return 平台资金账户List
     */
    @RequestMapping("/initial")
    public String initial(PlatformAccount model, Map<String, Object> maps) {
    	try {
    		List<PlatformAccount> list = platformAccountServiceImpl.pagedList(model);
            maps.put("list", list);
		} catch (Exception e) {
			LOGGER.error("get record list error, errorMsg=[{}].", e.getMessage());
			return "common/error";
		}
        return "platformAccount/initial";
    }
	
	/**
	 * 获得平台资金账户List，Json格式。
	 * @param model 参数
	 * @return 平台资金账户List
	 */
	@ResponseBody
	@RequestMapping("/list")
	public List<PlatformAccount> list(PlatformAccount model) {
		List<PlatformAccount> list = null;
		try {
			list = platformAccountServiceImpl.pagedList(model);
		} catch (Exception e) {
			LOGGER.error("get record list error, errorMsg=[{}].", e.getMessage());
		}
		return list;
	}
	
	/**
     * 根据Id获得平台资金账户实体，Json格式。
     * @param id 参数id
     * @return 平台资金账户实体
     */
    @ResponseBody
    @RequestMapping("/get")
    public PlatformAccount get(String id) {
    	PlatformAccount model = null;
    	try {
    		model = platformAccountServiceImpl.get(id);
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
        return "platformAccount/add";
    }
    
    /**
     * 执行实际的新增操作
     * @param model 要新增的数据
     * @return 新增页面逻辑视图
     */
    @ResponseBody
    @RequestMapping("/doAdd")
    public JsonBean doAdd(PlatformAccount model) {
    	JsonBean bean = new JsonBean();
    	try {
    		int result = platformAccountServiceImpl.save(model);
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
     * 查看平台资金账户详情页。
     * @param model 查询参数，携带ID
     * @return 平台资金账户详情页
     */
    @RequestMapping("/detail")
    public String detail(PlatformAccount model, Map<String, Object> maps) {
    	try {
    		model = platformAccountServiceImpl.get(model.getId());
            maps.put("model", model);//将model放入视图中，供页面视图使用
		} catch (Exception e) {
			LOGGER.error("query record detail, errorMsg=[{}].", e.getMessage());
			return "common/error";
		}
        return "platformAccount/detail";
    }
    
    /**
     * 跳转到编辑信息页面
     * @param model 查询参数，携带ID
     * @return 编辑页面
     */
    @RequestMapping("/edit")
    public String edit(PlatformAccount model, Map<String, Object> maps) {
        model = platformAccountServiceImpl.get(model.getId());
        maps.put("model", model);
        return "platformAccount/edit";
    }
    
    /**
     * 更新平台资金账户信息
     * @param model 要更新的平台资金账户信息，含有ID
     * @return 操作结果信息
     */
    @ResponseBody
    @RequestMapping("/update")
    public JsonBean update(PlatformAccount model) {
    	JsonBean bean = new JsonBean();
    	try {
    		int result = platformAccountServiceImpl.updateById(model);
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
     * 删除平台资金账户信息
     * @param id 要删除的平台资金账户ID
     */
    @ResponseBody
    @RequestMapping("/delete")
    public JsonBean delete(String id) {
    	JsonBean bean = new JsonBean();
    	try {
    		int result = platformAccountServiceImpl.deleteById(id);
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
