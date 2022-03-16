# gsfw
springboot框架基础包及Sample  
jdk1.8

集成内容包括：  
1.双数据源  
2.mysql连接  
3.orcale连接  
4.mybatis plus  
5.swaager  
6.spring security  
7.JWT  
8.基于注解的验证规则示例  
9.jpa集成

# 目录结构
项⽬根⽬录/src/main/java ：放置项⽬Java源代码

|_annotation：放置项⽬⾃定义注解  
|_aspect：放置切⾯代码  
|_common：放置一些共通处理(例如:全局异常handle)  
|_config：放置配置类  
|_constant：放置常量、枚举等定义  
&ensp;&ensp;&ensp;&ensp;|__consist：存放常量定义  
&ensp;&ensp;&ensp;&ensp;|__enums：存放枚举定义  
|_controller：放置控制器代码  
|_filter：放置⼀些过滤、拦截相关的代码  
|_convert：放置entity与dto相互转换类  
|_model：放置数据模型代码  
&ensp;&ensp;&ensp;&ensp;|__entity：放置数据库实体对象定义  
&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;|__jpa：jpa实体类  
&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;|__mybatis：mybatis实体类  
&ensp;&ensp;&ensp;&ensp;|__dto：存放数据传输对象定义  
|_repository：库方法  
&ensp;&ensp;&ensp;&ensp;|__jpa：jpa库方法  
&ensp;&ensp;&ensp;&ensp;|__mybatis：mybatis库方法  
|_service：放置具体的业务逻辑代码（接⼝和实现分离）  
&ensp;&ensp;&ensp;&ensp;|__intf：存放业务逻辑接⼝定义  
&ensp;&ensp;&ensp;&ensp;|__impl：存放业务逻辑实际实现  
|_third：三方库  
|_utils：放置⼯具类和辅助代码  

项⽬根⽬录/src/main/resources ：放置项⽬静态资源和配置⽂件

|_mapper：存放mybatis的XML映射文件（如果是mybatis项目）  
|_static：存放网页静态资源，比如下面的js/css/img  
&ensp;&ensp;&ensp;&ensp;|__js：  
&ensp;&ensp;&ensp;&ensp;|__css：  
&ensp;&ensp;&ensp;&ensp;|__img：  
&ensp;&ensp;&ensp;&ensp;|__font：  
|_template：存放网页模板，比如thymeleaf/freemarker模板等  
&ensp;&ensp;&ensp;&ensp;|__header  
&ensp;&ensp;&ensp;&ensp;|__sidebar  
&ensp;&ensp;&ensp;&ensp;|__bottom  
&ensp;&ensp;&ensp;&ensp;|__XXX.html等等  
|_application.yml       基本配置文件  
|_application-dev.yml   开发环境配置文件  
|_application-test.yml  测试环境配置文件  
|_application-prod.yml  生产环境配置文件  

项⽬根⽬录/src/test/java ：放置项⽬测试⽤例代码

# mybatis plus CRUD 接口
https://baomidou.com/pages/49cc81/#service-crud-%E6%8E%A5%E5%8F%A3

# shell介绍
compile.sh clean后编译项目  
install.sh 生成生成环境jar包 // 生成的jar包在/target/路径下  
run.sh 启动jar包  

# 初始测试账号
admin  
123456  

# swagger地址
http://localhost:8081/swagger-ui.html  
注：认证处需要手动加上Bearer  

# 作者联系方式
email：liuchengyao1989@126.com
