package org.geekbang.org.geekbang.thinking.in.spring.ioc.overview.dependency.injection;

import org.geekbang.org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.geekbang.org.geekbang.thinking.in.spring.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DependencyInjectionDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");
        UserRepository userRepository = beanFactory.getBean("userRepository", UserRepository.class);
        System.out.println(userRepository.getUsers());

        // 依赖注入
        // 通过`objectFactory`注入
        System.out.println(userRepository.getBeanFactory());
        System.out.println(userRepository.getBeanFactory() == beanFactory);

        ObjectFactory userFactory = userRepository.getUserObjectFactory();
        System.out.println(userFactory.getObject() == beanFactory);

        // 依赖查找，beanFactory并不是一个普通的bean
//        System.out.println(beanFactory.getBean(BeanFactory.class));
    }
}
