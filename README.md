# CodeMind AI

## 项目介绍

CodeMind AI 是一个基于 Java 与大语言模型技术构建的企业级智能研发助手平台。

项目目标是利用大模型能力帮助企业开发团队降低项目理解成本，提高研发效率，实现企业知识智能化管理。

---

## 项目背景

随着企业软件系统规模不断扩大，代码、接口文档、数据库设计以及历史问题记录逐渐分散在不同系统中。

新成员加入项目时需要花费大量时间理解业务背景，同时资深开发人员需要重复回答已有问题，导致研发效率下降。

CodeMind AI旨在通过AI能力结合企业私有知识库，辅助研发人员完成项目理解、问题分析和开发工作。

---

## 技术栈

### 后端

- Java
- Spring Boot
- MyBatis Plus
- Spring Security

### 数据存储

- MySQL
- Redis
- Milvus

### AI技术

- LangChain4j
- RAG
- Agent
- MCP

### 部署

- Docker

---

## 项目结构

CodeMind-AI

├── backend        后端服务
 ├── frontend       前端项目
 ├── ai-service     AI能力服务
 ├── deploy         部署配置
 └── docs           项目文档