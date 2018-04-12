package com.king.api.smp;

import java.util.List;
import java.util.Map;

import com.king.dal.gen.model.smp.SysMenu;
import com.king.dal.gen.model.smp.SysRole;
import com.king.dal.gen.service.BaseService;

/**
 * 角色
 * @author King chen
 * @email 396885563@qq.com
 * @date 2017年12月29日
 */
public interface SysRoleService extends BaseService<SysRole>{
	
	/**
	 * 保存或更新角色用户相关联
	 */
	void saveOrUpdate_R_U(Long userId, List<Long> roleIdList);
	
	/**
	 * 根据用户ID，获取角色ID列表
	 */
	List<Long> queryRoleIdList(Long userId);
	
	/**
	 * 根据角色ID，获取授权用户ID列表
	 */
	List<Long> queryUserIdList(Long roleId);
	
	/**
	 * 根据用户ID，删除角色用户关联
	 */
	void delete_R_U(Long userId);

	/**
	 * 更新角色
	 */
	void update(SysRole sysRole,String token);
}
