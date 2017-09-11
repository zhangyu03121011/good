package com.ivplay.modules.sys.model;

import com.ivplay.common.base.model.BaseEntity;
import com.ivplay.common.validator.group.All;
import com.ivplay.common.validator.group.Create;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * 角色
 * Created by cuiP on 2017/2/8.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "t_role")
public class Role extends BaseEntity {
	private static final long serialVersionUID = -5457587307805621355L;

	//超级管理员标识
    public static final String ROLE_TYPE = "ROEL_ADMIN";

    /**
     * 角色名
     */
    @NotNull(message = "角色不能为空!", groups = {All.class})
    private String name;

    /**
     * 角色标识
     */
    @NotNull(message = "角色标识不能为空!", groups = {All.class})
    private String perms;

    /**
     * 备注
     */
    @NotNull(message = "备注不能为空!", groups = {Create.class})
    private String remark;
}
