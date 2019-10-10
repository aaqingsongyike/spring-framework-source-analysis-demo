package com.yyb.spring.source.analysis.ioc.conditional;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 快速导入方法二ImportSelector
 * 可以自定义逻辑
 * 需要结合@Import注解使用
 */
public class MyImportSelector implements ImportSelector {
    // 返回值，就是要给容器中的组件全类名（不要返回null）
    // AnnotationMetadata：当前标注@Import，@Controller注解的类的所有信息
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {


        return new String[]{"Bule",
                "Yellow"};
    }
}
