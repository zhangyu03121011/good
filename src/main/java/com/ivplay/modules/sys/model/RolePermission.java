package com.ivplay.modules.sys.model;

import javax.persistence.Table;

import com.ivplay.common.base.model.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色权限中间表
 * @author cuiP
 * Created by JK on 2017/2/13.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "t_role_permission")
public class RolePermission extends BaseEntity {
	private static final long serialVersionUID = 921284711214512553L;
	private Long roleId;
    private Long permissionId;
}
