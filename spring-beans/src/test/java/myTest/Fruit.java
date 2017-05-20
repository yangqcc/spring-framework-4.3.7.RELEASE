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

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author yangqc
 * @since 4.3
 */
public class Fruit implements InitializingBean{

	@Autowired
	private Apple apple;

	/**
	 * @return the apple
	 */
	public Apple getApple() {
		return apple;
	}

	public void myInitMthod(){
		System.out.println("this is initMethod!");
	}
	/**
	 * @param apple the apple to set
	 */
	public void setApple(Apple apple) {
		this.apple = apple;
	}

	public String toString() {
		return apple.toString();
	}

	/* (non-Javadoc)
	 * 在属性设置完后，但在自定义初始化方法前调用
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("this is initializingBeanMethod!");
	}
}
