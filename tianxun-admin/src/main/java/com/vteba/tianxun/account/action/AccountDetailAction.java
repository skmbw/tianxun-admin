package com.vteba.tianxun.account.action;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vteba.tianxun.account.model.AccountDetail;
import com.vteba.tianxun.account.service.spi.AccountDetailService;

import com.vteba.web.action.GenericAction;
import com.vteba.web.action.JsonBean;

/**
 * 用户资金积分明细控制器
 * @author yinlei
 * @date 2016-3-24 19:58:06
 */
@Controller
@RequestMapping("/accountDetail")
public class AccountDetailAction extends GenericAction<AccountDetail> {
	private static final Logger LOGGER = LogManager.getLogger(AccountDetailAction.class);
	
	@Inject
	private AccountDetailService accountDetailServiceImpl;
	
	/**
     * 获得用户资金积分明细List，初始化列表页。
     * @param model 参数
     * @return 用户资金积分明细List
     */
    @RequestMapping("/initial")
    public String initial(AccountDetail model, Map<String, Object> maps) {
    	try {
    		List<AccountDetail> list = accountDetailServiceImpl.pagedList(model);
            maps.put("list", list);
		} catch (Exception e) {
			LOGGER.error("get record list error, errorMsg=[{}].", e.getMessage());
			return "common/error";
		}
        return "accountDetail/initial";
    }
	
	/**
	 * 获得用户资金积分明细List，Json格式。
	 * @param model 参数
	 * @return 用户资金积分明细List
	 */
	@ResponseBody
	@RequestMapping("/list")
	public List<AccountDetail> list(AccountDetail model) {
		List<AccountDetail> list = null;
		try {
			list = accountDetailServiceImpl.pagedList(model);
		} catch (Exception e) {
			LOGGER.error("get record list error, errorMsg=[{}].", e.getMessage());
		}
		return list;
	}
	
	/**
     * 根据Id获得用户资金积分明细实体，Json格式。
     * @param id 参数id
     * @return 用户资金积分明细实体
     */
    @ResponseBody
    @RequestMapping("/get")
    public AccountDetail get(String id) {
    	AccountDetail model = null;
    	try {
    		model = accountDetailServiceImpl.get(id);
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
        return "accountDetail/add";
    }
    
    /**
     * 执行实际的新增操作
     * @param model 要新增的数据
     * @return 新增页面逻辑视图
     */
    @ResponseBody
    @RequestMapping("/doAdd")
    public JsonBean doAdd(AccountDetail model) {
    	JsonBean bean = new JsonBean();
    	try {
    		int result = accountDetailServiceImpl.save(model);
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
     * 查看用户资金积分明细详情页。
     * @param model 查询参数，携带ID
     * @return 用户资金积分明细详情页
     */
    @RequestMapping("/detail")
    public String detail(AccountDetail model, Map<String, Object> maps) {
    	try {
    		model = accountDetailServiceImpl.get(model.getId());
            maps.put("model", model);//将model放入视图中，供页面视图使用
		} catch (Exception e) {
			LOGGER.error("query record detail, errorMsg=[{}].", e.getMessage());
			return "common/error";
		}
        return "accountDetail/detail";
    }
    
    /**
     * 跳转到编辑信息页面
     * @param model 查询参数，携带ID
     * @return 编辑页面
     */
    @RequestMapping("/edit")
    public String edit(AccountDetail model, Map<String, Object> maps) {
        model = accountDetailServiceImpl.get(model.getId());
        maps.put("model", model);
        return "accountDetail/edit";
    }
    
    /**
     * 更新用户资金积分明细信息
     * @param model 要更新的用户资金积分明细信息，含有ID
     * @return 操作结果信息
     */
    @ResponseBody
    @RequestMapping("/update")
    public JsonBean update(AccountDetail model) {
    	JsonBean bean = new JsonBean();
    	try {
    		int result = accountDetailServiceImpl.updateById(model);
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
     * 删除用户资金积分明细信息
     * @param id 要删除的用户资金积分明细ID
     */
    @ResponseBody
    @RequestMapping("/delete")
    public JsonBean delete(String id) {
    	JsonBean bean = new JsonBean();
    	try {
    		int result = accountDetailServiceImpl.deleteById(id);
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
