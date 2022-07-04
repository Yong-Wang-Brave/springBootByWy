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
