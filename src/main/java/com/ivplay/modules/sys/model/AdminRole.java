package com.ivplay.modules.sys.model;

import javax.persistence.Table;

import com.ivplay.common.base.model.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户角色中间表
 * @author cuiP
 * Created by JK on 2017/2/13.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "t_admin_role")
public class AdminRole extends BaseEntity {
	private static final long serialVersionUID = -6977520012355570819L;
	private Long userId;
    private Long roleId;
}
