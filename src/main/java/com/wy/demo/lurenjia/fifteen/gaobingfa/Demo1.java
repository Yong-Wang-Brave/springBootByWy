package com.wy.demo.lurenjia.fifteen.gaobingfa;

import
 java
.
util
.
concurrent
.
TimeUnit
;



/**

 * 微信公众号：路人甲Java，专注于java技术分享（带你玩转 爬虫、分布式事务、异步消息服务、任务调度、分库分表、大数据等），喜欢请关注！

 */

public
 
class
 
Demo1
 
{



    
public
 
volatile
 
static
 
boolean
 exit 
=
 
false
;



    
public
 
static
 
class
 T 
extends
 
Thread
 
{

        
@Override

        
public
 
void
 run
()
 
{

            
while
 
(
true
)
 
{

                
//循环处理业务

                
if
 
(
exit
)
 
{

                    
break
;

                
}

            
}

        
}

    
}



    
public
 
static
 
void
 setExit
()
 
{

        exit 
=
 
true
;

    
}



    
public
 
static
 
void
 main
(
String
[]
 args
)
 
throws
 
InterruptedException
 
{

        T t 
=
 
new
 T
();

        t
.
start
();

        
TimeUnit
.
SECONDS
.
sleep
(
3
);

        setExit
();

    
}

}