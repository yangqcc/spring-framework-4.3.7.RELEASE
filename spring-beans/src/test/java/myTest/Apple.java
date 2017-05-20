/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package myTest;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * 
 * @author Administrator
 * @since 4.3
 */
public class Apple implements BeanFactoryAware{

	private String name;
	private BeanFactory beanFactory;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString(){
		return name;
	}
	
	//自定义初始化方法
	private void setName() {
		name = "期成";
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.BeanFactoryAware#setBeanFactory(org.springframework.beans.factory.BeanFactory)
	 */
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}

	public static void main(String[] args) {
		// XmlBeanFactory被废弃，用XmlBeanDefinitionReader和DefaultListableBeanFactory替代
		// BeanFactory factory = new XmlBeanFactory(new
		// ClassPathResource("applicationContext.xml"));
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader XmlBeanDefinitionReader = new XmlBeanDefinitionReader(factory);
		XmlBeanDefinitionReader.loadBeanDefinitions(new ClassPathResource("applicationContext.xml"));
		System.out.println(factory.getBean("apple"));
		System.out.println("----");
		//由于用了denpend-on属性，所以fruit会在car前实例化
		System.out.println(factory.getBean("car"));
		System.out.println("----");
		System.out.println(((Apple)factory.getBean("apple")).getBeanFactory().getBean("fruit"));
	}

}
