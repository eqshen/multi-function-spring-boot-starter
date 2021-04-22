# 工程简介
multi-function-多功能拓展方法模块（随便起的名字-_-）

### 使用方法
见 test/，正常来说应该创建一个新test项目并依赖本项目（懒得要死）。
还是简单说下正常流程：
1. 依赖本项目
2. 实现自己的拓展功能，实现IMultiFuncService接口并在实现类上标注`@MultiFunc(name = "xxx")`;其中name属性在适用的时候要用到。
   示例见：`com.eqshen.func.funImpl.RateLimitServiceImpl`和 `com.eqshen.func.funImpl.WhitelistFuncImpl`
3. 在自己的业务类上适用自己步骤2中定义的拓展功能,适用 `@ApplyFunc` 注解，具体见：`com.eqshen.func.service.TestService.getUserInfo`
  
4.测试入口:com.eqshen.func.MultiFunctionTest
### 说明
仅学习练手项目，抽象封装程度并不够好，希望可以慢慢进步。