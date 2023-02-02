package exercise;

import java.lang.reflect.Proxy;

import exercise.calculator.Calculator;
import exercise.calculator.CalculatorImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// BEGIN
@Component
public class CustomBeanPostProcessor implements BeanPostProcessor{

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomBeanPostProcessor.class);
    private Map<Class, String> beansMapWithInspect = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(Inspect.class)) {
             beansMapWithInspect.put(beanClass,
                     beanClass.getAnnotation(Inspect.class).level());
        }

        return bean;
    }
    // String loggerString = "Was called method: " + method.getName() + " with arguments: " + Arrays.toString(method.getParameters());
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class beanClass = beansMapWithInspect.containsKey(bean.getClass()) ? bean.getClass() : null;

        if (beanClass != null) {
            return Proxy.newProxyInstance(
                    beanClass.getClassLoader(),
                    beanClass.getInterfaces(),
                    (proxy, method, args) -> {
                        String loggerString = "Was called method: " + method.getName() + "() with arguments: " + Arrays.toString(args);
                        if (beansMapWithInspect.get(beanClass).equals("info")) {
                            LOGGER.info(loggerString);
                        } else {
                            LOGGER.debug(loggerString);
                        }

                        Object retVal = method.invoke(bean, args);
                        return retVal;
                    }
            );
        }
        return bean;
    }
}
// END
