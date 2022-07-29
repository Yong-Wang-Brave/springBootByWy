09 springBoot
1 springboot 不是什么 
不是应用服务器
不是JAVAEE之间的规范
不是代码生成器
不是spring Framework的升级版

2 springboot的特性
●方便地创建可独立运行的Spring应用程序
●直接内嵌Tomcat、Jetty 或Undertow
●简化了项目的构建配置
●为Spring及第三方库提供自动配置
●提供生产级特性
●无需生成代码或进行XML配置

3 spring Boot的四大核心
自动配置 Auto Configuration
起步依赖 Starter Denpendency
命令行界面 Spring Boot CLI
Actuator

4了 解自动配置

自动配置

●基于添加的JAR依赖自动对Spring Boot应用程序进行配置●spring-boot -autoconfigura on )

开启自动配置

●@EnableAutoConfiguration

●exclude = Class<?>[]

●@SpringBootApplication

5 自动配置的实现原理

条件注解

●@Conditional

●@ConditionalOnClass

●@ConditionalOnBean

●@Condi tionalOnMissingBean
●@ConditionalOnProperty

