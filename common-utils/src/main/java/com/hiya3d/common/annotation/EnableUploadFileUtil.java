package com.hiya3d.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import com.hiya3d.common.file.FileUtil;
import com.hiya3d.common.file.SysConfig;

/**
 * 引入文件导入导出配置
 * @author rex.tan
 * @date 2019年11月19日 下午4:43:57
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Inherited
@Import({ EnableUploadFileUtil.FileUtilImportSelector.class })
public @interface EnableUploadFileUtil {

	static class FileUtilImportSelector implements ImportSelector {

		@Override
		public String[] selectImports(AnnotationMetadata importingClassMetadata) {
			return new String[] { FileUtil.class.getName(), SysConfig.class.getName() };
		}
	}
}