package com.hiya3d.common.conf.user.context;

/**
 * 用户上下文
 * @author Rex.Tan
 * @date 2019年8月24日 上午11:00:02
 */
public class UserContext {
	private static final ThreadLocal<SysUser> CONTEXT = new InheritableThreadLocal<>();

	/**
	 * 设置用户信息
	 * @param user
	 * @author Rex.Tan
	 * @date 2019年8月24日 上午10:59:33
	 */
	public static void set(SysUser user) {
		CONTEXT.remove();
		CONTEXT.set(user);
	}

	/**
	 * 获取用户信息
	 * @return
	 * @author Rex.Tan
	 * @date 2019年8月24日 上午10:59:38
	 */
	public static SysUser get() {
		return CONTEXT.get();
	}

	/**
	 * 移除用户信息
	 * @author Rex.Tan
	 * @date 2019年8月24日 上午10:59:45
	 */
	public static void remove() {
		CONTEXT.remove();
	}
	
	public static void clear() {
		CONTEXT.remove();
	}
}
