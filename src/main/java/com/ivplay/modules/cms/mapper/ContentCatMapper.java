package com.ivplay.modules.cms.mapper;

import com.ivplay.common.base.mapper.BaseMapper;
import com.ivplay.modules.cms.model.ContentCat;
import com.ivplay.modules.sys.vo.TreeNode;

import java.util.List;

/**
 * @author cuiP
 * Created by JK on 2017/4/19.
 */
public interface ContentCatMapper extends BaseMapper<ContentCat> {
    /**
     * 返回树列表
     * @return
     */
    List<TreeNode> findTreeList();
}
