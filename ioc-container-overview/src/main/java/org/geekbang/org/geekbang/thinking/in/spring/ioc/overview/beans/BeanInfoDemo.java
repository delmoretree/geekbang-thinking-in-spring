package org.geekbang.org.geekbang.thinking.in.spring.ioc.overview.beans;

import java.beans.*;
import java.util.stream.Stream;

public class BeanInfoDemo {

    public static void main(String[] args) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class, Object.class);

        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

        Stream.of(propertyDescriptors).forEach(propertyDescriptor -> {
            System.out.println(propertyDescriptor);

            String propertyName = propertyDescriptor.getName();

            if (propertyName.equals("age")) {
                propertyDescriptor.setPropertyEditorClass(AgeEditorClass.class);
            }
        });
    }

    static class AgeEditorClass extends PropertyEditorSupport {
        public void setAsText(String text) throws java.lang.IllegalArgumentException {
            this.setValue(Integer.valueOf(text));
        }
    }
}
