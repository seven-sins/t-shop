package com.hiya3d.common.conf.mybatis;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hiya3d.common.conf.mybatis.sql.SqlAnalysisUtil;
import com.hiya3d.common.conf.user.context.UserContext;

/**
 * 填充公共字段
 * @author rex.tan
 * @date 2018年12月9日 下午5:57:33
 */
@Intercepts({ 
	@Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class })
})
public class CustomMybatisInterceptor implements Interceptor {
    private static final Logger LOG = LoggerFactory.getLogger(CustomMybatisInterceptor.class);

    @SuppressWarnings("unchecked")
	@Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        Object parameter = invocation.getArgs()[1];
        if (parameter instanceof Map) {
            try {
                Map<String, Object> map = ((Map<String, Object>) parameter);
                if (!map.containsKey("param1")) {
                    return invocation.proceed();
                }
                parameter = map.get("param1");
            } catch (Exception e) {
                LOG.error("=============自动设置修改人以及修改时间时出错, 获取拦截参数失败: ", e);
                return invocation.proceed();
            }
        } else if (parameter instanceof String || parameter instanceof Integer || parameter instanceof Long) {
            return invocation.proceed();
        }

        if (parameter == null) {
            return invocation.proceed();
        }
        // 获取该类型声明的成员
        Field[] fields = parameter.getClass().getDeclaredFields();
        // 新增
        if (SqlCommandType.INSERT.equals(sqlCommandType)) {
            for (Field field : fields) {
                try {
                    // 设置当前用户信息
                    if ("createdBy".equals(field.getName())) {
                        field.setAccessible(true);
                        Object value = field.get(parameter);
                        if (value == null && UserContext.get() != null) {
                            field.set(parameter, UserContext.get().getUserName());
                        }
                        field.setAccessible(false);
                    }
                    // 设置当前用户ID
                    else if ("createdUserId".equals(field.getName())) {
                        field.setAccessible(true);
                        Object value = field.get(parameter);
                        if (value == null && UserContext.get() != null) {
                            field.set(parameter, UserContext.get().getUserId());
                        }
                        field.setAccessible(false);
                    }
                    // 设置当前时间
                    else if ("createdTime".equals(field.getName())) {
                        field.setAccessible(true);
                        Object value = field.get(parameter);
                        if (value == null) {
                            field.set(parameter, new Date());
                        }
                        field.setAccessible(false);
                    }
                    // 逻辑删除
                    else if ("isDeleted".equals(field.getName())) {
                        field.setAccessible(true);
                        field.set(parameter, 0);
                        field.setAccessible(false);
                    }
                    // 禁用状态
                    else if ("isDisabled".equals(field.getName())) {
                        field.setAccessible(true);
                        Object value = field.get(parameter);
                        if (value == null) {
                            field.set(parameter, 0);
                        }
                        field.setAccessible(false);
                    }
                    // 公司(店铺)ID
					//                    else if ("shopId".equals(field.getName())) {
					//                        field.setAccessible(true);
					//                        Object value = field.get(parameter);
					//                        if (value == null && UserContext.get() != null) {
					//                            field.set(parameter, UserContext.get().getShopId());
					//                        }
					//                        field.setAccessible(false);
					//                    }
                } catch (Exception e) {
                    LOG.error("=============自动设置创建人以及创建时间时出错", e);
                }
            }
        }
        // 更新
        else if (SqlCommandType.UPDATE.equals(sqlCommandType)) {
            for (Field field : fields) {
                try {
                    // 设置当前登录用户
                    if ("updatedBy".equals(field.getName())) {
                        field.setAccessible(true);
                        field.set(parameter, UserContext.get().getUserName());
                        field.setAccessible(false);
                    }
                    // 设置当前用户ID
                    else if ("updatedUserId".equals(field.getName())) {
                        field.setAccessible(true);
                        field.set(parameter, UserContext.get().getUserId());
                        field.setAccessible(false);
                    }
                    // 设置当前时间
                    else if ("updatedTime".equals(field.getName())) {
                        field.setAccessible(true);
                        field.set(parameter, new Date());
                        field.setAccessible(false);
                    }
                } catch (Exception e) {
                    LOG.error("=============自动设置修改人以及修改时间时出错", e);
                }
            }
        }
        
        // 输出sql
        try {
        	SqlAnalysisUtil.printSql(invocation);
        } catch(Exception e) {
        	LOG.error("=============解析sql出错", e);
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

}
