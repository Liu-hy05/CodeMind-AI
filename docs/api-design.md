# CodeMind AI API接口设计文档

# 1. 接口设计概述

CodeMind AI采用前后端分离架构设计。

系统通过HTTP API实现：

- 前端与后端通信
- 后端业务模块之间通信
- AI能力模块调用

接口设计目标：

- 保证模块之间低耦合
- 统一数据交互格式
- 提高系统可维护性
- 支持未来服务扩展

当前系统主要包含：

- 用户服务（codemind-user）
- 知识库服务（codemind-knowledge）
- AI服务（codemind-ai）

------

# 2. 接口设计规范

## 2.1 通信协议

系统采用：

HTTP / HTTPS

接口风格：

RESTful API

数据格式：

JSON

示例：

请求：

```
POST /api/user/login
```

返回：

```
{
    "code":200,
    "message":"success",
    "data":{}
}
```

------

# 3. 统一响应结构设计

为了保证不同模块返回格式一致，系统统一定义响应对象。

结构：

```
Result<T>
```

包含：

## code

状态码。

例如：

200：

请求成功。

400：

参数错误。

401：

未登录。

500：

服务器异常。

------

## message

返回信息。

例如：

```
登录成功
```

或者：

```
用户不存在
```

------

## data

业务数据。

例如：

用户信息：

```
{
 username:"Liu",
 role:"ADMIN"
}
```

------

# 4. 用户模块接口设计

对应模块：

```
codemind-user
```

负责：

- 用户注册
- 用户登录
- 用户认证
- 权限管理

------

# 4.1 用户注册接口

接口：

```
POST /api/user/register
```

功能：

创建系统用户。

请求参数：

```
{
    "username":"test",
    "password":"123456",
    "email":"test@email.com"
}
```

处理流程：

```
用户提交注册信息

↓

User Service验证参数

↓

保存用户信息

↓

返回注册结果
```

------

# 4.2 用户登录接口

接口：

```
POST /api/user/login
```

功能：

用户身份认证。

请求参数：

```
{
    "username":"test",
    "password":"123456"
}
```

处理流程：

```
用户登录

↓

验证账号密码

↓

生成Token

↓

保存Redis

↓

返回Token
```

------

# 4.3 获取用户信息接口

接口：

```
GET /api/user/info
```

功能：

获取当前登录用户信息。

返回：

```
{
    "id":1,
    "username":"test",
    "role":"USER"
}
```

------

# 5. 知识库模块接口设计

对应模块：

```
codemind-knowledge
```

负责：

- 知识库创建
- 文件上传
- 文档管理
- 知识处理

------

# 5.1 创建知识库接口

接口：

```
POST /api/knowledge/create
```

功能：

创建企业知识库。

请求：

```
{
    "name":"Java项目知识库",
    "description":"项目代码和文档"
}
```

流程：

```
用户创建知识库

↓

保存knowledge_base

↓

返回知识库ID
```

------

# 5.2 上传文件接口

接口：

```
POST /api/knowledge/document/upload
```

功能：

上传企业资料。

支持：

- PDF
- Word
- Markdown
- Java源码

流程：

```
用户上传文件

↓

保存文件信息

↓

解析文件

↓

文本切割

↓

生成Embedding

↓

保存Milvus
```

------

# 5.3 查询知识库接口

接口：

```
GET /api/knowledge/list
```

功能：

查询用户拥有的知识库。

返回：

```
[
 {
   id:1,
   name:"Java项目知识库"
 }
]
```

------

# 6. AI模块接口设计

对应模块：

```
codemind-ai
```

负责：

- AI问答
- 模型调用
- RAG流程

------

# 6.1 AI问答接口

接口：

```
POST /api/ai/chat
```

功能：

基于企业知识库回答用户问题。

请求：

```
{
    "question":"订单退款失败怎么办？"
}
```

处理流程：

```
用户问题

↓

AI Service接收

↓

Knowledge Service检索知识

↓

Milvus搜索相关内容

↓

组合Prompt

↓

调用LLM

↓

返回答案
```

返回：

```
{
 "answer":
 "根据订单流程文档，退款失败可能由于支付状态异常..."
}
```

------

# 7. 权限控制设计

系统采用用户角色权限控制。

角色：

## USER

普通用户。

权限：

- 查询知识库
- 使用AI问答

------

## ADMIN

管理员。

权限：

- 管理用户
- 创建知识库
- 上传企业资料

------

# 8. 服务之间通信设计

当前模块关系：

```
Frontend

↓

Backend API

↓

codemind-user

codemind-knowledge

codemind-ai
```

业务调用：

用户登录：

```
Frontend

↓

User Service
```

知识查询：

```
Frontend

↓

Knowledge Service

↓

Milvus
```

AI问答：

```
Frontend

↓

AI Service

↓

Knowledge Service

↓

LLM
```

------

# 9. 后续接口扩展

未来版本支持：

## 项目代码分析接口

例如：

```
POST /api/code/analyze
```

功能：

- 分析项目结构
- 理解代码关系
- 生成项目说明

------

## Agent任务接口

例如：

```
POST /api/agent/task
```

功能：

- 自动执行研发任务
- 调用工具
- 生成分析报告

------

## 自动测试生成接口

例如：

```
POST /api/test/generate
```

功能：

根据代码生成测试建议。

------

# 10. 总结

CodeMind AI通过统一API设计，实现：

- 前后端分离
- 模块职责清晰
- 服务之间低耦合

当前系统通过：

用户服务

↓

知识库服务

↓

AI服务

形成完整的企业AI研发助手基础架构。

未来可以进一步扩展：

- Agent能力
- 自动代码分析
- AI研发自动化流程