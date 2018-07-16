# izhauntou

项目描述：基于分布式的MSA架构
项目开发目的：迭代izhuantou1.0框架，使用基于Maven的分布式框架为 PC，App ，后台管理系统 3大模块提供对应服务和应用，构建全新的izhauntou2.0。
项目模块划分如下:
	* izhauntou-admin 后台管理系统
	* izhuantou-app-api 为App端提供服务的接口
	* izhauntou-common 工具类
	* izhuantou-dao Dao层
	* izhauntou-data 异步数据处理，定时任务系统
	* izhauntou-domain pojo,Bean
	* izhauntou-fund 资金系统，负责有关资金的操作
	* izhuantou-portal 门户网站系统
	* izhuantou-service 基本业务处理(CRUD)
	* izhauntou-third 负责调用第三服务的系统
	* izhuantou-*-api 所有需要暴露的接口
中间件/3方插件：
	* dubbo 系统之间的服务调用
	* zookeeper 服务注册中心
	* redisMQ 异步消息处理框架
	* XXl-job 定时任务系统
环境配置
	* dev-env 本地环境
	* prod-env 生产环境
	* test-env 测试环境
本地开发环境启动
	* maven-tomcat7  使用maven内置的tomcat 命令 tomcat7:run
	
项目详细使用请参考	项目获取文档
		