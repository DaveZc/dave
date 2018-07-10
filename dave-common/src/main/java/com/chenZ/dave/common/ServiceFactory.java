package com.chenZ.dave.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 *
 * @author chen.z
 * @date 2018/7/10
 */
public class ServiceFactory implements ApplicationContextAware {

    private static ApplicationContext context = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        context = applicationContext;
    }

    /**
     * 通过class获取实例化bean
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBeanByClass(Class<T> clazz) {
        return context.getBean(clazz);
    }

    /**
     * 通过beanName获取实例化bean
     *
     * @param beanName
     * @return
     */
    public static Object getBeanByName(String beanName) {
        return context.getBean(beanName);
    }

    /***
     * 获取上下文容器
     * @return
     */
    public static ApplicationContext getContext() {
        return context;
    }
}
