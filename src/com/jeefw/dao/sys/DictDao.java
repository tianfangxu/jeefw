package com.jeefw.dao.sys;

import com.jeefw.model.sys.Dict;

import com.jeefw.model.sys.param.DicModel;
import core.dao.Dao;
import core.support.JqGridPageView;

/**
 * 字典的数据持久层的接口
 * @JC
 */
public interface DictDao extends Dao<Dict> {
    JqGridPageView<Dict> getDicByCondition(DicModel model);

}
