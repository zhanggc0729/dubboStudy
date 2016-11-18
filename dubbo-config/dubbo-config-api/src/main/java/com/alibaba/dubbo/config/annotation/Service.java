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
 * Service
 * 
 * @author william.liangf
 * @export
 * 该类服务于使用注解
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Service {
    //服务接口名  默认将 interfaceClass 和interfaceName 改为interface
    Class<?> interfaceClass() default void.class;

    String interfaceName() default "";
//服务版本，建议使用两位数字版本，如：1.0，通常在接口不兼容时版本号才需要升级
    String version() default "";
//服务分组，当一个接口有多个实现，可以用分组区分
    String group() default "";
//服务路径 (注意：1.0不支持自定义路径，总是使用接口名，如果有1.0调2.0，配置服务路径可能不兼容)
    String path() default "";

    boolean export() default false;
//令牌验证，为空表示不开启，如果为true，表示随机生成动态令牌，否则使用静态令牌，令牌的作用是防止
// 消费者绕过注册中心直接访问，保证注册中心的授权功能有效，如果使用点对点调用，需关闭令牌功能
    String token() default "";
//服务是否过时，如果设为true，消费方引用时将打印服务过时警告error日志
    boolean deprecated() default false;
//服务是否动态注册，如果设为false，注册后将显示后disable状态，需人工启用，
// 并且服务提供者停止时，也不会自动取消册，需人工禁用。
    boolean dynamic() default false;
//设为true，将向logger中输出访问日志，也可填写访问日志文件路径，直接把访问日志输出到指定文件
    String accesslog() default "";
//服务提供者每服务每方法最大可并行执行请求数
    int executes() default 0;
//该协议的服务是否注册到注册中心
    boolean register() default false;
//服务权重
    int weight() default 0;
//服务文档URL
    String document() default "";
//延迟注册服务时间(毫秒) ，设为-1时，表示延迟到Spring容器初始化完成时暴露服务
    int delay() default 0;

    String local() default "";
//设为true，表示使用缺省代理类名，即：接口名 + Local后缀，服务接口客户端本地代理类名，
// 用于在客户端执行本地逻辑，如本地缓存等，该本地代理类的构造函数必须允许传入远程代理对象，
// 构造函数如：public XxxServiceLocal(XxxService xxxService)
    String stub() default "";
//集群方式，可选：failover/failfast/failsafe/failback/forking
    String cluster() default "";
//生成动态代理方式，可选：jdk/javassist
    String proxy() default "";
//对每个提供者的最大连接数，rmi、http、hessian等短连接协议表示限制连接数，dubbo等长连接协表示建立的长连接个数
    int connections() default 0;

    int callbacks() default 0;

    String onconnect() default "";

    String ondisconnect() default "";
//服务负责人，用于服务治理，请填写负责人公司邮箱前缀
    String owner() default "";
//服务提供者所在的分层。如：biz、dao、intl:web、china:acton。
    String layer() default "";

    int retries() default 0;
//负载均衡策略，可选值：random,roundrobin,leastactive，分别表示：随机，轮循，最少活跃调用
    String loadbalance() default "";
//是否缺省异步执行，不可靠异步，只是忽略返回值，不阻塞执行线程
    boolean async() default false;
//每服务消费者每服务每方法最大并发调用数
    int actives() default 0;

    boolean sent() default false;
//设为true，表示使用缺省Mock类名，即：接口名 + Mock后缀，服务接口调用失败Mock实现类，该Mock类必须有一个无参构造函数，
// 与Local的区别在于，Local总是被执行，而Mock只在出现非业务异常(比如超时，网络异常等)时执行，
// Local在远程调用之前执行，Mock在远程调用后执行。
    String mock() default "";

    String validation() default "";
//远程服务调用超时时间(毫秒)
    int timeout() default 0;

    String cache() default "";
//服务提供方远程调用过程拦截器名称，多个名称用逗号分隔
    String[] filter() default {};
//服务提供方导出服务监听器名称，多个名称用逗号分隔
    String[] listener() default {};

    String[] parameters() default {};

    String application() default "";

    String module() default "";

    String provider() default "";
//使用指定的协议暴露服务，在多协议时使用，值为<dubbo:protocol>的id属性，多个协议ID用逗号分隔
    String[] protocol() default {};

    String monitor() default "";
//向指定注册中心注册，在多个注册中心时使用，值为<dubbo:registry>的id属性，
// 多个注册中心ID用逗号分隔，如果不想将该服务注册到任何registry，可将值设为N/A
    String[] registry() default {};

}
