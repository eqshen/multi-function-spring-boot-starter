package com.eqshen.func.spring;

import com.eqshen.func.annotation.MultiFunc;
import com.eqshen.func.config.FuncRegistry;
import com.eqshen.func.service.IMultiFuncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author eqshen
 * @description
 * @date 2021/4/19
 */
@Component
public class FuncRegisterProcessor implements BeanPostProcessor {

    private Logger log = LoggerFactory.getLogger(FuncRegisterProcessor.class);

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        this.register(bean,beanName);
        return bean;
    }

    private void register(Object bean,String beanName){
        if(bean == null || StringUtils.isEmpty(beanName)){
            return;
        }
        //符合条件的
        if (bean.getClass().isAnnotationPresent(MultiFunc.class)) {
            final MultiFunc multiFuncAnno = bean.getClass().getAnnotation(MultiFunc.class);
            String name = multiFuncAnno.name();
            if(StringUtils.isEmpty(name)){
                name = beanName;
            }
            log.info("注册bean:{}",name);
            FuncRegistry.register(name, (IMultiFuncService) bean);
        }
    }
}
