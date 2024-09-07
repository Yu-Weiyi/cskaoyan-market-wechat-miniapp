# 项目二

## 第三方库提供的界面

当项目启动后，可访问以下由第三方库提供的开发辅助界面：
- OpenAPI 文档（展示所有已写接口，可充当前端调试接口）
  - ~~http://127.0.0.1:8083/wx/api-docs~~
  - ~~http://127.0.0.1:8083/wx/swagger-ui/index.html~~
  - http://127.0.0.1:8083/wx/doc.html#/home
- Druid 监控（查看SQL执行情况）
  - http://localhost:8083/wx/druid/login.html

## 注意事项

- 逻辑删除
- 认证
- 错误码
- 分页
- 清理threadlocal

## 附加功能

### 完成
- 健康检查接口

### 计划
- API统计
- 修改前端（后端返回401，前端居然不删除Token）（貌似前端会对200-501反应）
- @RequestParam required，会HTTP400，应捕捉异常。
- 手机号格式校验
- 优惠券过期检查
- user/index 与 order/list 不一致