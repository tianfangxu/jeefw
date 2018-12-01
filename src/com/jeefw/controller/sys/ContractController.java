package com.jeefw.controller.sys;

import com.jeefw.core.Constant;
import com.jeefw.core.JavaEEFrameworkBaseController;
import com.jeefw.model.sys.Contract;
import com.jeefw.model.sys.ContractFile;
import com.jeefw.model.sys.SysUser;
import com.jeefw.model.sys.param.model.BigContractModel;
import com.jeefw.service.sys.ContractService;
import com.jeefw.service.sys.SysUserService;
import core.support.JqGridPageView;
import core.util.CommonUtil;
import core.util.ParamUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.HashMap;

/**
 * 合同管理的控制层
 *
 * @JC
 */
@Controller
@RequestMapping("/sys/contract")
public class ContractController extends JavaEEFrameworkBaseController<Contract> implements Constant {

    @Resource
    private ContractService contractService;

    @Resource
    private SysUserService sysUserService;

    // 查询信息发布的表格，包括分页、搜索和排序
    @RequestMapping(value = "/getContractByCondition", method = {RequestMethod.POST, RequestMethod.GET})
    public void getCompact(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            BigContractModel model = new ParamUtils<BigContractModel>().getparams(request, BigContractModel.class);
            JqGridPageView<Contract> contractJqGridPageView = null;
            contractJqGridPageView = contractService.getContractByCondition(model);
            writeJSON(response, contractJqGridPageView);
        } catch (Exception e) {
            e.printStackTrace();
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("message", "系统出错，请稍后重试");
            writeJSON(response, map);
        }
    }

    /***
     * 保存
     */
    @RequestMapping(value = "/saveContract", method = {RequestMethod.POST})
    public void saveContract(HttpServletRequest request, HttpServletResponse response, @RequestBody BigContractModel model) throws IOException {
        HashMap<String, String> map = new HashMap<String, String>();
        try {
            SysUser sysuser = sysUserService.get(((SysUser) request.getSession().getAttribute(SESSION_SYS_USER)).getId());
            model.setLoginuser(sysuser);
            contractService.saveContract(model);
            map.put("message", "添加成功");
            writeJSON(response, map);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", "系统出错，请稍后重试");
            writeJSON(response, map);
        }
    }

    // 删除信息发布
    @RequestMapping(value = "/deleteContract", method = {RequestMethod.POST})
    public void deleteCompact(HttpServletRequest request, HttpServletResponse response, @RequestBody BigContractModel model) throws IOException {
        HashMap<String, String> map = new HashMap<String, String>();
        try {
            SysUser sysuser = sysUserService.get(((SysUser) request.getSession().getAttribute(SESSION_SYS_USER)).getId());
            model.setLoginuser(sysuser);
            String string = contractService.deleteCompact(model);
            map.put("message", string);
            writeJSON(response, map);
        } catch (Exception e) {
            map.put("message", "系统出错，请稍后重试");
            writeJSON(response, map);
        }

    }

    /**
     * 获取合同文件
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/getContractFileByCondition", method = {RequestMethod.POST, RequestMethod.GET})
    public void getContractFileByCondition(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HashMap<String, String> map = new HashMap<String, String>();
        try {
            BigContractModel model = new ParamUtils<BigContractModel>().getparams(request, BigContractModel.class);
            JqGridPageView<ContractFile> contractFileJqGridPageView = null;
            contractFileJqGridPageView = contractService.getContractFileByCondition(model);
            writeJSON(response, contractFileJqGridPageView);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", "系统出错，请稍后重试");
            writeJSON(response, map);
        }
    }

    /**
     * 导出execl
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/exportContract", method = {RequestMethod.POST, RequestMethod.GET})
    public void operateInformation(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("application/msexcel;charset=UTF-8");
        try {
            response.addHeader("Content-Disposition", "attachment;filename=file.xls");
            OutputStream out = response.getOutputStream();
            out.write(URLDecoder.decode(request.getParameter("csvBuffer"), "UTF-8").getBytes());
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过合同id获取合同所有的信息
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/getContractWithInfoById", method = {RequestMethod.POST, RequestMethod.GET})
    public void getContractWithInfoById(HttpServletRequest request, HttpServletResponse response,@RequestBody BigContractModel model) throws Exception {
        HashMap<String, String> map = new HashMap<String, String>();
        try {
            if(CommonUtil.isNotNull(model.getId())){
                SysUser sysuser = sysUserService.get(((SysUser) request.getSession().getAttribute(SESSION_SYS_USER)).getId());
                model.setLoginuser(sysuser);
                JqGridPageView<BigContractModel> bigContractModelJqGridPageView = null;
                bigContractModelJqGridPageView = contractService.getContractWithInfoById(model);
                writeJSON(response, bigContractModelJqGridPageView);
            }else{
                map.put("message", "id为空");
                writeJSON(response, map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", "系统出错，请稍后重试");
            writeJSON(response, map);
        }
    }

    /***
     * 保存
     */
    @RequestMapping(value = "/updateContract", method = {RequestMethod.POST})
    public void updateContract(HttpServletRequest request, HttpServletResponse response, @RequestBody BigContractModel model) throws IOException {
        HashMap<String, String> map = new HashMap<String, String>();
        try {
            SysUser sysuser = sysUserService.get(((SysUser) request.getSession().getAttribute(SESSION_SYS_USER)).getId());
            model.setLoginuser(sysuser);
            contractService.updateContract(model);
            map.put("message", "编辑成功");
            writeJSON(response, map);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", "系统出错，请稍后重试");
            writeJSON(response, map);
        }
    }
}
