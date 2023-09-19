# springBootByWy

20220316 新增线程池ThreadPoolTaskExecutor的使用  
参考文档：
https://blog.csdn.net/u012102536/article/details/123521295
https://blog.csdn.net/u012102536/article/details/123521594
文件夹：thread
20220323-1  SpringBoot静态获取 bean的三种方式，你学会了吗？
参考文档 https://blog.csdn.net/u012102536/article/details/123682329
文件夹：getBeanMethod

20220323-2 SpringBoot 启动时实现自动执行代码的几种方式讲解
参考文档：https://blog.csdn.net/u012102536/article/details/123684411
文件夹：启动时自动执行代码

20220323-3 druid的使用与配置
参考文档：https://blog.csdn.net/u012102536/article/details/123690114
文件夹：

20220324 如何读取配置文件的属性
路径： 启动时自动执行代码\读取properties的文件

20220324 枚举类提取成公共的
参考的：https://blog.csdn.net/u012102536/article/details/123711166
路径 enumDemo-枚举工具类

20220324 guava中map的使用
参考： https://blog.csdn.net/u012102536/article/details/123715177
路径：guava
说明：multimap  双键table rangeMap

20220325 自定义注解实现自定义统一返回
参考：https://blog.csdn.net/u012102536/article/details/123730699
路径：亮点：自定义注解实现统一返回

20220325 读写分离在readWriteSeparate分支实现 但是报错了
参考:https://note.youdao.com/s/P6sopuH4
路径：亮点：自定义注解实现统一返回

20220325 读写分离在readWriteSeparate分支实现 但是报错了

20220328 rule读取json文件

20220329 切面实现接口调用前后的执行时间出入参等  ；内存溢出demo
路径：aop切面  ;OverDemo.java

20220239 全局拼接异常 
路径：GlobalExceptionHandler2的BindException

20220330 日志文件配置
路径：log日志
参考：https://blog.csdn.net/u012102536/article/details/123853515

20220330 全局流水号
路径：全局日志id

20200331 git工具使用
路径：git工具使用


20220407  GitHub Copilot 的入门
路径： 机器人
参考：https://github.com/github/copilot-docs/blob/main/docs/jetbrains/gettingstarted.md#getting-started-with-github-copilot-in-jetbrains

20200411 诸葛jvm
路径：图灵.jvm

20220411图片上传
路径：韩曙平io图片上传

20220418 lambda peek的使用
路径：lambda

20220418  mybatis拦截器 拦截拼接sql  打印sql
路径：mybatiesInterceptor2

20220419  设计模式
路径： 设计模式 策略模式
场景：根据类型判断多个场景的替换。

20220429  优雅的策略模式
路径：spring

20220429 拦截器3
路径： mybatiesInterceptor3

20220511 mybaits自动生成代码
路径：mybatis/generator

20220512 swagger添加
路径：config  SwaggerConfiguration

20220518 工具类添加
路径：Tools  
参考：https://mp.weixin.qq.com/s/eknljWG9W8QBH5o3Yl_ECA

20220520   从request的head里面获取属性。
扩展：目录： filter.headToSession

20200522 从容器中获取对象
目录：com.wy.demo.SpringContext
场景：静态方法中无法直接使用@AutoWired对象
参考：https://blog.csdn.net/u012102536/article/details/124915772

20220523 logback日志文件
目录（文件）：logback.xml
提示：需要吧xml中的log字眼全部注释掉。
参考：hhttps://blog.csdn.net/u012102536/article/details/124931331
提示：可以参照另一个github项目mybatisDemo


20220524 字符集乱码解决
目录：luanma
链接：攀博课堂

20220525 如何保证 Controller 的并发安全
目录：线程安全
链接：https://mp.weixin.qq.com/s/ZXyQeY1Xa0GvK4yuyCdELQ
提示：controller不要定义变量。因为是单例的 线程不安全。

20220525 HandlerMethodArgumentResolver可以解析区分入参的类型（自定义注解）
目录：other
链接：https://blog.csdn.net/u012102536/article/details/124966970

20220526  自定义注解实现校验身份证号
目录：自定义注解
链接：https://blog.csdn.net/u012102536/article/details/124978290

20220525 HandlerMethodArgumentResolver的例子
目录：other.小案例20220526
场景：获取用户名，直接根据实体类就可以填充
链接：https://blog.csdn.net/u012102536/article/details/124986131

20200526 自定义注解demo 
目录：自定义注解  demo1 demo2
场景：1）有注解标注的会当成切点  2）有注解的会被拦截
https://blog.csdn.net/u012102536/article/details/124987988


20220526 java8函数消灭if else
目录：java8 
场景：函数的妙用
1)处理抛出异常的if
2)处理if分支操作
3)如果存在值执行消费操作，否则执行基于空的操作
https://blog.csdn.net/u012102536/article/details/12499333
20220527 泛型
目录：泛型
https://mp.weixin.qq.com/s/Gr8kLpM9bgocCuC-Ve_dZg
20220610 pageHelper  pageInfo(20220614)
文件：pageHelperController

20220623 多数据源配置
目录：druid
备注：SourceConfig 是默认数据源  因为里面有@primary
HealthRecordDataSourceConfig 是第二个数据源 可以指定扫描的目录与mapper。
20220626 测试类新写法 
文件：RedisTest.java
20220701  spring与mybaits集合处理crud
代理类、bean工厂、bean注册，将我们一个没有实现类的接口安排的明明白白
，让他执行啥就执行啥，那么你是否可以想到，
这个没有实现类的接口，可以通过我们的折腾，去调用到我们的mybaits呢！

通过这些核心关键类的实现；
SqlSessionFactoryBean、MapperScannerConfigurer、SqlSessionFactoryBean，
我们将spring与mybaits集合起来使用，解决了没有实现类的接口怎么处理数据库CRUD操作

20220704
A
目录：spring-proxy
链接：http://t.csdn.cn/i8kE6
B
20220704  测试类
文件：DemoApplicationTest.java

20220704 spring获取bean的几种方式
文件：SpringContextHelper2
链接：https://blog.csdn.net/m0_67401606/article/details/124047896

20220705 参数校验
文件：方式一：ValidatorUtil.java
方式二：TestCelueController.java

20220706 lamba optional的用法
文件： com.wy.demo.java8.lambda.optional
连接：https://mp.weixin.qq.com/s/5RbpFLpyFKzdQewg-aoEzg

20220711 策略模式 实战版本
文件：策略模式20220708
链接：https://mp.weixin.qq.com/s/MjLCnZR07BA5B2a_7kWzbQ
20220713 hubhook   
文件夹：testWebHook   

20220715 整合log4j2
文件：log4j2-dev.xml
地址：https://www.jianshu.com/p/4172ca423920
说明：每次会从最近的文件往后覆盖

20220718 jwt的引入
目录：jwt
链接：https://github.com/yehongzhi/learningSummary

20220719 swagger可视化界面美化

20220721 java8
目录：java8.lambda.flatmap


20220729
目录：丁雪峰
bean初始化配置
1 继承InitializingBean
2 @PostConstruct 注解
实现接口ApplicationContextAware 获取spring上下文

20220801
SpringBoot 缓存之 @Cacheable 详细介绍
地址：https://blog.csdn.net/u012102536/article/details/126097079?csdn_share_tail=%7B%22type%22%3A%22blog%22%2C%22rType%22%3A%22article%22%2C%22rId%22%3A%22126097079%22%2C%22source%22%3A%22u012102536%22%7D&ctrtid=N0K5G
例子：极客时间第10章 ：indicator-demo   geektime.spring.springbucks.waiter.controller.CoffeeController.getAll

20220801
stopwatch统计接口耗时
参看：极客时间第10章 indicator-demo   PerformanceInteceptor

20220802 
过滤器实现
目录：filter
场景：过滤返回值。

20220803 
监听器listener
目录：监听器listener
地址：https://blog.csdn.net/xp_lx1/article/details/121835173

20220804  WebLogAspect优化
文件：JacksonUtil

20220804  拦截器新增
文件：MqsHandler

20220804 获取最真实的ip
文件：NetworkUtil.getLocalHostIpAddress

20220805 读取自定义配置文件
目录：config zidingyi 

20200805 入参非空校验
文件：ValidateUtil
注意点：GlobalExceptionHandler2 中的@ExceptionHandler 不能重复定义

20220810 mybatis的TypeHandler
文件夹：mybatis byme
文件：SourceConfig.java 加入配置 NameHandler.java

注意点：#此配置应该无效。
application.properties  #mybatis.type-handlers-package=com.wy.demo.mybatis

20220811 spring注解的说明
文件夹：src/main/java/com/wy/demo/spring/annotation

20220824 Html2Text
说明：富文本处理文件夹
文件Html2Text.java

20220824 jsonSerializer的使用
说明：返回前端字段的特殊处理
步骤：1 工具类： DesensitizationUtil
     2 继承接口利用工具类 PhoneSerializer
     3 实体类上绑定字段   SortCourse

20220825 restTemplate的优化 远程调用
目录：spring -Restemplate
20220915 feign的专属拦截器
FeignConfiguration

20220918 枚举 
文件：LevelEnum
获取某一个类型对应的枚举

2022 设计模式  输出该接口的所有实现类
文件：
TestGetBeansOfTYpe

20220922  根据token获取用户中心信息。放到threadLocal 或者注解直接获取。

文件： WebConfigSecurity.java

目录： tokenGetUserInfo

说明： 拦截器实现 将信息放入threadLocal

20221008 自定义注解加载指定数据源

目录： zhidingshujuyuan（没实现）

20221012 java8 重构设计模式
目录：设计模式 **20221012
连接：https://mp.weixin.qq.com/s/T3WhNm73Wcvfo2LwChz0_Q

20221012注解@value

20221021  读写分离分离

20221124 重写hashcode
文件夹：javajichu 
Test
20221125 bigdecimal的使用
文件夹  bigdecimal

20221219   mybaits拦截器输出全量sql
文件：SqlCostInterceptor

20221230 分页工具类

20220113 问卷表整理
#问卷   题目  选项
SELECT * FROM vit_life_assess_qs_detail  vqd
LEFT JOIN vit_life_assess_subject vs  ON  vqd.subject_code=vs.subject_code
LEFT JOIN vit_life_assess_option  vo  ON vo.subject_code = vqd.subject_code

#答案
SELECT * FROM vit_life_assess_result_detail vrd LEFT JOIN vit_life_assess_qs_detail vqd ON

vrd.subject_code=vqd.subject_code

#   result_masseg  可以存整套问卷的答案
vit_life_assess_result

20230116  redis实践
RedisController
枚举类
ENUM
20230117 
过滤器：往session里面放数据
WebConfigSecurity
SecurityFilter2
20230117  前端页面返回指定的错误。
GlobalExceptionHandler20231017
WebLogAspect

20230117 通过拦截器放到了从request获取信息放到了threadLocal里面。
UserContextInterceptor


20230128 feign使用總結

welcomeController的/get/someStudent    被调用者  springboot-8082

20230129   

批量查插入
路径：batchInsert.java

 mybatis数据库配置（包含数据源 mapper的接口和xml文件的地址）
路径：com.wy.demo.mybatis.config

 多线程
路径：ThreadPoolConfig.java
welcomeController的  /testThreadPoolExecutor

自定义校验
文件夹：validGroup


XSS脚本攻击
文件：todo

远程调用
RestemplatePoolConfig.java

拦截器
文件 WebConfigSecurity.java的 SecurityFilterRegister
FilterAddToSession.java

自定义注解
文件夹 zidingyizhujie
welcomeController.java 

20230130 
多数据源
文件夹：com.wy.demo.mybatis.config
路径： /getAllCourse  /getAllCourse2


自定义注解 

文件夹：自定义注解  
demo1 切面
demo2 拦截器实现
demo3 反射实现 入库手机号加密  读取的时候解密
参考 测试类：CryptUtilTest

工厂设计模式
TestCelueController

20230131 

分页配置
文件：pageHelperController.java

强哥经典
唯一id设置
AdminRequestIdHandler

分布式锁

RedisUtils

2023年3月8号
lambda 8 list转map

2023Nian 3月21号
自定义注解
com.wy.demo.自定义注解.demo4  

动态代理的实现
com.wy.demo.动态代理
场景：多个方法有重复的方法 ：记录耗时，可以使用动态代理。

2023年3月23号

mysql批量更新
UserBathController 

2023年5月15号
场景：

消费方：
1) feignconfig  继承feign拦截器将请求头传给服务方。
服务方：
   2)feign调用，将请求头的信息通过UserFilter 放入MDC.FilterConfig注入userFilter.

2023/5/19号
map+函数式接口  替换if else
GrantTypeController
引用地址：https://mp.weixin.qq.com/s/L_RrjutUma7XqMfx1dCe8g

2023/5/22 
自定义redis分布式锁
DistributedLockTest

2023/09/18
filter与MDC的使用
接口的请求头放到经过filter放到MDC,并从MDC获取请求头信息。
重点文件 FilterConfig.java  UserFilter.java