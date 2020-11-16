package org.geekbang.org.geekbang.thinking.in.spring.ioc.overview.dependency.lookup;

import org.geekbang.org.geekbang.thinking.in.spring.ioc.overview.annotation.Super;
import org.geekbang.org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;
import java.util.logging.Logger;

public class DependencyLookupDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup-context.xml");
        lookupInLazy(beanFactory);
        lookupInRealTime(beanFactory);
        lookupByType(beanFactory);
        lookByCollectionType(beanFactory);
        lookupByAnnotation(beanFactory);
    }

    private static void lookupByAnnotation(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, Object> users = listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("通过标注查找所有User集合对象：" + users);
        }
    }

    private static void lookByCollectionType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> userMap = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找到的所有User集合对象："+userMap);
        }
    }

    private static void lookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println("通过类型实时查找"+user);
    }

    @SuppressWarnings("unchecked")
    private static void lookupInLazy(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("延迟查找"+user);
    }

    private static void lookupInRealTime(BeanFactory beanFactory) {
        User user = (User)beanFactory.getBean("user");
        System.out.println("实时查找"+user);
    }
}
