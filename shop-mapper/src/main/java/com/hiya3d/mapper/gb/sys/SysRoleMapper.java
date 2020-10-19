package com.hiya3d.mapper.gb.sys;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hiya3d.model.gb.sys.SysRole;
import com.hiya3d.model.gb.sys.vo.SysRoleVo;

import tk.mybatis.mapper.common.BaseMapper;

/**
 * 公司
 * @author seven sins
 * @date 2020年8月30日 下午12:38:11
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
	
	/**
	 * 列表查询
	 * @author Rex.Tan
	 * @date 2020-10-19 14:50:35
	 * @param sysRole
	 * @return
	 */
	List<SysRoleVo> find(SysRoleVo sysRole);
}
