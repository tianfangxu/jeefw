package com.jeefw.service.sys;

import java.util.List;

import com.jeefw.model.sys.Dict;

import com.jeefw.model.sys.param.DicModel;
import core.service.Service;
import core.support.JqGridPageView;

/**
 * 字典的业务逻辑层的接口
 * @JC
 */
public interface DictService extends Service<Dict> {

	List<Dict> queryDictWithSubList(List<Dict> resultList);

	JqGridPageView<Dict> getDicByCondition(DicModel model);


}
