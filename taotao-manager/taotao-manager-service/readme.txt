1.编写服务的实现接口，打包方式jar，通常存放服务的具体实现类，调用DAO层，实现事务，类似与传统项目中的服务实现类。
2.编写Spring的配置文件，数据库数据库连接池，Spring事务等。
3.Spring的融合都在这里配置。Spring+SpringMVC+Mybatis的融和都在这里进行。
4.所有的service实现类都放到spring容器中管理。由spring创建数据库连接池，并有spring管理实务。
5.整合MMS
mybatis的配置文件(SqlMapConfig.xml)
     配置分页插件
配置数据库文件（db.properties）
      数据库名称、url、用户名和密码
Spring整合mybatis配置文件(applicationContext-dao.xml)
      配置数据库连接池
      加载配置文件——数据库连接池——让spring管理sqlsessionfactory 使用mybatis和spring整合包中（ 数据库连接池——加载mybatis的全局配置文件）
Spring整合service配置文件(applicationContext-service.xml)
      配置服务(自动扫描服务)
Spring整合事务配置文件(applicationContext-trans.xml)
      配置spring事务
      事务管理器 （数据源 ）——通知（传播行为）——切面 
Spring配置文件加载（web.xml）
     初始化spring容器 ，加载spring容器
