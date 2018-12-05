package com.jeefw.controller.sys;

import com.jeefw.core.Constant;
import com.jeefw.core.JavaEEFrameworkBaseController;
import com.jeefw.model.sys.ContractFlow;
import com.jeefw.model.sys.SysUser;
import com.jeefw.model.sys.param.model.BigContractModel;
import com.jeefw.model.sys.param.model.ContractFlowHisModel;
import com.jeefw.model.sys.param.model.ContractFlowModel;
import com.jeefw.model.sys.param.model.SmallContractModel;
import com.jeefw.service.sys.ContractFlowHisService;
import com.jeefw.service.sys.ContractFlowService;
import com.jeefw.service.sys.ContractService;
import com.jeefw.service.sys.SysUserService;
import core.support.JqGridPageView;
import core.util.ParamUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * 审核的控制层
 *
 * @JC
 */
@Controller
@RequestMapping("/sys/flow")
public class FlowController extends JavaEEFrameworkBaseController<ContractFlow> implements Constant {

    @Resource
    private ContractService contractService;

    @Resource
    private SysUserService sysUserService;

    @Resource
    private ContractFlowService contractFlowService;

    @Resource
    private ContractFlowHisService contractFlowHisService;

    @RequestMapping(value = "/getContractByAudit", method = {RequestMethod.POST, RequestMethod.GET})
    public void getCompact(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            BigContractModel model = new ParamUtils<BigContractModel>().getparams(request, BigContractModel.class);
            SysUser sysuser = sysUserService.get(((SysUser) request.getSession().getAttribute(SESSION_SYS_USER)).getId());
            model.setLoginuser(sysuser);
            JqGridPageView<SmallContractModel> contractJqGridPageView = null;
            contractJqGridPageView = contractService.getContractByAudit(model);
            writeJSON(response, contractJqGridPageView);
        } catch (Exception e) {
            e.printStackTrace();
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("message", "系统出错，请稍后重试");
            writeJSON(response, map);
        }
    }


    /***
     * 合同初次提交审核
     */
    @RequestMapping(value = "/submitAudit", method = {RequestMethod.POST})
    public void submitAudit(HttpServletRequest request, HttpServletResponse response, @RequestBody ContractFlowModel model) throws IOException {
        HashMap<String, String> map = new HashMap<String, String>();
        try {
            SysUser sysuser = sysUserService.get(((SysUser) request.getSession().getAttribute(SESSION_SYS_USER)).getId());
            model.setLoginuser(sysuser);
            contractFlowService.submitAudit(model);
            map.put("message", "提交审核成功");
            writeJSON(response, map);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", "系统出错，请稍后重试");
            writeJSON(response, map);
        }
    }

    /***
     * 查看审核记录
     */
    @RequestMapping(value = "/getAuditRecords", method = {RequestMethod.POST})
    public void getAuditRecords(HttpServletRequest request, HttpServletResponse response, @RequestBody ContractFlowModel model) throws IOException {
        HashMap<String, String> map = new HashMap<String, String>();
        try {
            SysUser sysuser = sysUserService.get(((SysUser) request.getSession().getAttribute(SESSION_SYS_USER)).getId());
            model.setLoginuser(sysuser);
            JqGridPageView<ContractFlowHisModel> contractFlowHisModelJqGridPageView = contractFlowService.getAuditRecords(model);
            writeJSON(response, contractFlowHisModelJqGridPageView);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", "系统出错，请稍后重试");
            writeJSON(response, map);
        }
    }

    /***
     * 合同审核
     */
    @RequestMapping(value = "/dealActiviti", method = {RequestMethod.POST})
    public void dealActiviti(HttpServletRequest request, HttpServletResponse response, @RequestBody ContractFlowModel model) throws IOException {
        HashMap<String, String> map = new HashMap<String, String>();
        try {
            SysUser sysuser = sysUserService.get(((SysUser) request.getSession().getAttribute(SESSION_SYS_USER)).getId());
            model.setLoginuser(sysuser);
            contractFlowService.dealActiviti(model);
            writeJSON(response, map);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", "系统出错，请稍后重试");
            writeJSON(response, map);
        }
    }
}
