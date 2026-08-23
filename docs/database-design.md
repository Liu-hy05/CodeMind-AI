# CodeMind AI 数据库设计文档

## 1. 数据存储设计概述

CodeMind AI 根据不同类型数据特点，采用多存储架构设计。

系统主要使用以下数据存储方案：

- MySQL
- Redis
- Milvus

其中：

MySQL负责保存系统核心业务数据。

Redis负责缓存高频访问数据，提高系统响应速度。

Milvus负责保存企业知识库中的向量数据，实现语义检索能力。

系统整体数据架构：

```
业务数据
    |
    ↓
  MySQL


缓存数据
    |
    ↓
  Redis


知识向量数据
    |
    ↓
  Milvus
```

------

# 2. MySQL数据库设计

## 2.1 设计目标

MySQL作为系统主要业务数据库，用于保存结构化业务数据。

主要包括：

- 用户信息
- 权限信息
- 知识库信息
- 文件元数据
- AI聊天记录

选择MySQL原因：

### 1. 数据结构稳定

用户、文件、权限等企业业务数据具有明确的数据关系。

### 2. 支持事务

保证业务操作过程中数据一致性。

### 3. 查询能力强

适合管理企业业务数据。

------

# 3. 用户模块数据库设计

## 3.1 用户表（user）

### 表说明

用户表用于保存系统用户基本信息。

主要用于：

- 用户登录
- 身份认证
- 权限控制

------

### 字段设计

| 字段         | 类型     | 说明         |
| ------------ | -------- | ------------ |
| id           | bigint   | 用户唯一编号 |
| username     | varchar  | 用户名       |
| password     | varchar  | 用户密码     |
| email        | varchar  | 用户邮箱     |
| role         | varchar  | 用户角色     |
| status       | tinyint  | 用户状态     |
| created_time | datetime | 创建时间     |
| updated_time | datetime | 更新时间     |

------

## 3.2 用户角色设计

系统初期支持两种角色：

### 普通用户（USER）

权限：

- 使用AI问答
- 查询知识库

### 管理员（ADMIN）

权限：

- 管理用户
- 管理知识库
- 上传企业资料

------

# 4. 知识库模块数据库设计

## 4.1 知识库表（knowledge_base）

### 表说明

用于保存企业知识库信息。

例如：

- Java项目知识库
- 产品文档知识库
- 接口文档知识库

------

### 字段设计

| 字段         | 类型     | 说明       |
| ------------ | -------- | ---------- |
| id           | bigint   | 知识库编号 |
| name         | varchar  | 知识库名称 |
| description  | varchar  | 描述       |
| creator_id   | bigint   | 创建者     |
| created_time | datetime | 创建时间   |
| updated_time | datetime | 更新时间   |

------

### 数据关系

一个用户可以创建多个知识库。

关系：

```
User

1

↓

N

Knowledge Base
```

------

# 5. 文件管理数据库设计

## 5.1 文件表（document）

### 表说明

用于保存用户上传文件信息。

注意：

文件内容不会直接存储在MySQL中。

MySQL只保存文件元数据。

------

### 字段设计

| 字段              | 类型     | 说明         |
| ----------------- | -------- | ------------ |
| id                | bigint   | 文件编号     |
| knowledge_base_id | bigint   | 所属知识库   |
| file_name         | varchar  | 文件名称     |
| file_type         | varchar  | 文件类型     |
| file_path         | varchar  | 文件路径     |
| status            | varchar  | 文件处理状态 |
| created_time      | datetime | 上传时间     |

------

# 6. AI聊天记录设计

## 6.1 聊天记录表（chat_record）

### 表说明

用于保存用户和AI之间的交互记录。

作用：

- 保存历史问题
- 分析用户行为
- 优化AI效果

------

### 字段设计

| 字段         | 类型     | 说明      |
| ------------ | -------- | --------- |
| id           | bigint   | 记录编号  |
| user_id      | bigint   | 用户ID    |
| question     | text     | 用户问题  |
| answer       | text     | AI回答    |
| model        | varchar  | 使用模型  |
| token_usage  | int      | Token消耗 |
| created_time | datetime | 创建时间  |

------

# 7. Redis缓存设计

## 7.1 登录缓存

用途：

保存用户登录状态。

示例：

```
login:token:{userId}
```

------

## 7.2 AI回答缓存

用途：

减少重复问题调用模型。

示例：

```
ai:answer:{questionHash}
```

优势：

- 降低模型调用次数
- 减少Token消耗
- 提高响应速度

------

# 8. Milvus向量数据库设计

## 8.1 使用目的

Milvus用于存储企业知识向量。

解决传统数据库无法完成的问题：

- 语义搜索
- 相似内容匹配
- 文档关联查询

------

## 8.2 文档向量

保存：

- 文档内容向量
- 文档编号
- 知识库编号
- 原始文本

流程：

```
文件

↓

文本切割

↓

Embedding

↓

向量数据

↓

Milvus
```

------

# 9. 数据处理流程

## 9.1 知识库构建流程

```
用户上传文件

↓

保存文件信息

↓

文件解析

↓

文本切割

↓

生成向量

↓

保存Milvus

↓

完成知识库建立
```

------

## 9.2 AI问答流程

```
用户提出问题

↓

问题向量化

↓

Milvus检索相关知识

↓

生成Prompt

↓

调用大模型

↓

返回答案
```



------

# 10. 总结

CodeMind AI采用：

**MySQL + Redis + Milvus**

的多数据库架构。

通过：

- MySQL保证业务数据可靠性
- Redis提升系统访问性能
- Milvus实现企业知识语义检索

为后续：

- RAG知识问答
- AI代码理解
- Agent智能任务

提供数据基础。