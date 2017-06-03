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

package org.springframework.context.annotation;

import java.lang.annotation.*;

/**
 * 添加对{@code @Aspectj}注解的支持.和xml文件中{@code <aop:aspectj-autoproxy>}效果一致
 * 用于有{@link Configuration}注解的类
 * Enables support for handling components marked with AspectJ's {@code @Aspect} annotation,
 * similar to functionality found in Spring's {@code <aop:aspectj-autoproxy>} XML element.
 * To be used on @{@link Configuration} classes as follows:
 * <p>
 * <pre class="code">
 *
 * @author Chris Beams
 * @author Juergen Hoeller
 * @Configuration
 * @EnableAspectJAutoProxy public class AppConfig {
 * @Bean public FooService fooService() {
 * return new FooService();
 * }
 *
 * @Bean
 * public MyAspect myAspect() {
 *      return new MyAspect();
 * }
 * }</pre>
 * <p>
 * Where {@code FooService} is a typical POJO component and {@code MyAspect} is an
 * {@code @Aspect}-style aspect:
 * <p>
 * <pre class="code">
 * public class FooService {
 *
 * // various methods
 * }</pre>
 *
 * <pre class="code">
 * @Aspect
 * public class MyAspect {
 * <p>
 * @Before("execution(* FooService+.*(..))")
 * public void advice() {
 * // advise FooService methods as appropriate
 * }
 * }</pre>
 * <p>
 * In the scenario above, {@code @EnableAspectJAutoProxy} ensures that {@code MyAspect}
 * will be properly processed and that {@code FooService} will be proxied mixing in the
 * advice that it contributes.
 * <p>
 * <p>Users can control the type of proxy that gets created for {@code FooService} using
 * the {@link #proxyTargetClass()} attribute. The following enables CGLIB-style 'subclass'
 * proxies as opposed to the default interface-based JDK proxy approach.
 * <p>
 * <pre class="code">
 * @Configuration
 * @EnableAspectJAutoProxy(proxyTargetClass=true)
 * public class AppConfig {
 * // ...
 * }</pre>
 * <p>
 * <p>Note that {@code @Aspect} beans may be component-scanned like any other. Simply
 * mark the aspect with both {@code @Aspect} and {@code @Component}:
 * <p>
 * <pre class="code">
 * package com.foo;
 * <p>
 * @Component
 * public class FooService { ... }
 * <p>
 * @Aspect
 * @Component
 * public class MyAspect { ... }</pre>
 * <p>
 * Then use the @{@link ComponentScan} annotation to pick both up:
 * <p>
 * <pre class="code">
 * @Configuration
 * @ComponentScan("com.foo")
 * @EnableAspectJAutoProxy
 * public class AppConfig {
 * <p>
 * // no explicit &#064Bean definitions required
 * }</pre>
 * @see org.aspectj.lang.annotation.Aspect
 * @since 3.1
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AspectJAutoProxyRegistrar.class)
public @interface EnableAspectJAutoProxy {

    /**
     * Indicate whether subclass-based (CGLIB) proxies are to be created as opposed
     * to standard Java interface-based proxies. The default is {@code false}.
     */
    boolean proxyTargetClass() default false;

    /**
     * Indicate that the proxy should be exposed by the AOP framework as a {@code ThreadLocal}
     * for retrieval via the {@link org.springframework.aop.framework.AopContext} class.
     * Off by default, i.e. no guarantees that {@code AopContext} access will work.
     *
     * @since 4.3.1
     */
    boolean exposeProxy() default false;

}
