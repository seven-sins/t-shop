package com.hiya3d.mapper.gb.sys;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hiya3d.model.gb.sys.SysUser;
import com.hiya3d.model.gb.sys.vo.SysUserVo;

import tk.mybatis.mapper.common.BaseMapper;

/**
 * 公司
 * @author seven sins
 * @date 2020年8月30日 下午12:38:11
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
	
	/**
	 * 列表查询s
	 * @author Rex.Tan
	 * @date 2020-10-19 14:22:43
	 * @param sysUser
	 * @return
	 */
	List<SysUserVo> find(SysUserVo sysUser);
}
