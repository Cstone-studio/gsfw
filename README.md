# gsfw
springboot框架基础包及Sample

内容包括：双数据源，mysql连接，orcale连接，mybatis plus，swaager，spring security, JWT

# 目录结构
项⽬根⽬录/src/main/java ：放置项⽬Java源代码

|_annotation：放置项⽬⾃定义注解
|_aspect：放置切⾯代码
|_common：放置一些共通处理(例如:全局异常handle)
|_config：放置配置类
|_constant：放置常量、枚举等定义
    |__consist：存放常量定义
    |__enums：存放枚举定义
|_controller：放置控制器代码
|_filter：放置⼀些过滤、拦截相关的代码
|_convert：放置entity与dto相互转换类
|_model：放置数据模型代码
    |__entity：放置数据库实体对象定义
    |__dto：存放数据传输对象定义
|_service：放置具体的业务逻辑代码（接⼝和实现分离）
    |__intf：存放业务逻辑接⼝定义
    |__impl：存放业务逻辑实际实现
|_third：三方库
|_utils：放置⼯具类和辅助代码

项⽬根⽬录/src/main/resources ：放置项⽬静态资源和配置⽂件

|_mapper：存放mybatis的XML映射文件（如果是mybatis项目）
|_static：存放网页静态资源，比如下面的js/css/img
    |__js：
    |__css：
    |__img：
    |__font：
|_template：存放网页模板，比如thymeleaf/freemarker模板等
    |__header
    |__sidebar
    |__bottom
    |__XXX.html等等
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




