package cn.wolfcode.shop.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


@Component
public class ApplicationContextHolder implements ApplicationContextAware{

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHolder.applicationContext = applicationContext;
    }

    /**
     * 提供一个获取spring容器中的bean的方法
     */
    public static <T>T getBean(String beanName){
        return (T)ApplicationContextHolder.applicationContext.getBean(beanName);
    }
}
