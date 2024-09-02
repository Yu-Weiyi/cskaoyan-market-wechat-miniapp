# 项目二

## 启动测试

以下是用于测试程序能否正常启动的步骤，若出现错误，请狠狠嘲笑我。

1. 刷新Maven，下载依赖；
2. 若IDEA的启动按钮变为“Application”，说明IDEA识别到了该SpringBoot项目，可以“Run”启动或“Debug”启动；
   3. SpringBoot空项目也会很笨重，启动时间长是正常现象.我启动需要半分钟。
   启动时，一般到了以下log会卡一会（等待Druid连接与初始化）：
  ```
  2024-09-02T19:35:24.449+08:00  INFO 18876 --- [  restartedMain] c.a.d.s.b.a.DruidDataSourceAutoConfigure : Init DruidDataSource
  ```
  启动成功后，会看到以下log结尾，且在上面几行会有赛博佛祖图案，表示项目启动成功；
  ```
  2024-09-02T19:35:51.648+08:00  INFO 18876 --- [  restartedMain] happy.coding.Application                 : happy.coding 项目二 SpringBoot 程序已启动。
  2024-09-02T19:35:51.982+08:00  INFO 18876 --- [(3)-172.25.16.1] o.a.c.c.C.[Tomcat].[localhost].[/wx]     : Initializing Spring DispatcherServlet 'dispatcherServlet'
  2024-09-02T19:35:51.982+08:00  INFO 18876 --- [(3)-172.25.16.1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
  2024-09-02T19:35:51.983+08:00  INFO 18876 --- [(3)-172.25.16.1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 1 ms
  ```
4. 启动成功后，可访问下面的OpenAPI文档地址，或者直接使用老师的前端进行登录和登出（账号user123，密码user123）；
5. 能证明登入登出功能正常的现象：
   - 登入时，前端出现头像与昵称，Redis数据库内写入了{userId: JWT}键值对；
   - 登出时，Redis数据库内数据删除。

## 第三方库提供的界面

当项目启动后，可访问以下由第三方库提供的开发辅助界面：
- OpenAPI 文档（展示所有已写接口，可充当前端调试接口）
  - ~~http://127.0.0.1:8083/wx/api-docs~~
  - ~~http://127.0.0.1:8083/wx/swagger-ui/index.html~~
  - http://127.0.0.1:8083/wx/doc.html#/home
- Druid 监控（查看SQL执行情况）
  - http://localhost:8083/wx/druid/login.html

## 
