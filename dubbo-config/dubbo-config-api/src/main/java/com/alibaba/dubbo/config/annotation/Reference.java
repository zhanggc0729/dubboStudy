/*
 * Copyright 1999-2012 Alibaba Group.
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
package com.alibaba.dubbo.config.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Reference
 * 
 * @author william.liangf
 * @export
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface Reference {
    //服务接口名  默认将 interfaceClass 和interfaceName 改为interface
    Class<?> interfaceClass() default void.class;

    String interfaceName() default "";
//服务版本，与服务提供者的版本一致
    String version() default "";
//服务分组，当一个接口有多个实现，可以用分组区分，必需和服务提供方一致
    String group() default "";
//点对点直连服务提供者地址，将绕过注册中心
    String url() default "";
//客户端传输类型设置，如Dubbo协议的netty或mina。
    String client() default "";
//是否缺省泛化接口，如果为泛化接口，将返回GenericService
    boolean generic() default false;

    boolean injvm() default false;
//启动时检查提供者是否存在，true报错，false忽略
    boolean check() default false;
//是否在afterPropertiesSet()时饥饿初始化引用，否则等到有人注入或引用该实例时再初始化。
    boolean init() default false;

    boolean lazy() default false;

    boolean stubevent() default false;

    String reconnect() default "";

    boolean sticky() default false;
//选择动态代理实现策略，可选：javassist, jdk
    String proxy() default "";
//服务接口客户端本地代理类名，用于在客户端执行本地逻辑，如本地缓存等，
// 该本地代理类的构造函数必须允许传入远程代理对象，构造函数如：public XxxServiceLocal(XxxService xxxService)
    String stub() default "";
//集群方式，可选：failover/failfast/failsafe/failback/forking
    String cluster() default "";
//对每个提供者的最大连接数，rmi、http、hessian等短连接协议表示限制连接数，dubbo等长连接协表示建立的长连接个数
    int connections() default 0;

    int callbacks() default 0;

    String onconnect() default "";

    String ondisconnect() default "";
//调用服务负责人，用于服务治理，请填写负责人公司邮箱前缀
    String owner() default "";
//服务调用者所在的分层。如：biz、dao、intl:web、china:acton。
    String layer() default "";
//远程服务调用重试次数，不包括第一次调用，不需要重试请设为0
    int retries() default 0;
//负载均衡策略，可选值：random,roundrobin,leastactive，分别表示：随机，轮循，最少活跃调用
    String loadbalance() default "";
//是否异步执行，不可靠异步，只是忽略返回值，不阻塞执行线程
    boolean async() default false;
//每服务消费者每服务每方法最大并发调用数
    int actives() default 0;

    boolean sent() default false;
//服务接口调用失败Mock实现类名，该Mock类必须有一个无参构造函数，与Local的区别在于
// ，Local总是被执行，而Mock只在出现非业务异常(比如超时，网络异常等)时执行，
// Local在远程调用之前执行，Mock在远程调用后执行。
    String mock() default "";
//是否启用JSR303标准注解验证，如果启用，将对方法参数上的注解进行校验
    String validation() default "";
//服务方法调用超时时间(毫秒)
    int timeout() default 0;
//以调用参数为key，缓存返回结果，可选：lru, threadlocal, jcache等
    String cache() default "";
//服务消费方远程调用过程拦截器名称，多个名称用逗号分隔
    String[] filter() default {};
//服务消费方引用服务监听器名称，多个名称用逗号分隔
    String[] listener() default {};

    String[] parameters() default {};

    String application() default "";

    String module() default "";

    String consumer() default "";

    String monitor() default "";
//从指定注册中心注册获取服务列表，在多个注册中心时使用，值为<dubbo:registry>的id属性，多个注册中心ID用逗号分隔
    String[] registry() default {};

}
