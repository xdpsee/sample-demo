package sample.demo.netty.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class AutowireUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AutowireUtils.applicationContext = applicationContext;
    }

    public static <T> T autowire(T object) {

        assert applicationContext != null;

        if (object != null) {
            applicationContext.getAutowireCapableBeanFactory().autowireBean(object);
        }

        return object;
    }
}
