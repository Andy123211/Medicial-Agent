
---

# Medical-Agent 项目介绍

## 项目简介

Medical-Agent 是一个基于 Java 的智能医疗助手系统，旨在为医疗场景提供智能问答、预约管理、知识检索等功能。项目采用前后端分离架构，后端基于 Spring Boot，前端基于 Vue.js，支持多种医疗知识的管理与查询，助力医院智能化服务升级。

## 主要功能

- **智能问答**：集成大语言模型，支持医疗相关知识的智能问答。
- **预约管理**：支持用户进行医疗预约的创建、查询与管理。
- **知识库管理**：支持多种格式（如 md、txt、pdf）的医疗知识文档导入与检索。
- **多角色支持**：可扩展为医生、患者等多种角色的智能助手。
- **前后端分离**：后端 API 提供数据与服务，前端界面友好交互。

## 目录结构

```
Medical-Agent/
├── pom.xml                        # Maven 配置文件
├── src/
│   ├── main/
│   │   ├── java/com/lin/assistant/    # 智能助手核心代码
│   │   ├── java/com/lin/bean/         # Bean 实体类
│   │   ├── java/com/lin/config/       # 配置类
│   │   ├── java/com/lin/controller/   # 控制器
│   │   ├── java/com/lin/entity/       # 业务实体
│   │   ├── java/com/lin/mapper/       # 数据库映射
│   │   ├── java/com/lin/service/      # 业务服务
│   │   ├── java/com/lin/store/        # 存储相关
│   │   ├── java/com/lin/tools/        # 工具类
│   │   └── resources/                 # 配置与知识库文件
│   └── test/                         # 测试代码
├── xiaozhi-ui/                    # 前端 Vue 项目
└── README.md                      # 项目说明文档
```

## 技术栈

- **后端**：Java 21, Spring Boot, Maven, MongoDB
- **前端**：Vue 3, Vite, JavaScript
- **其他**：PDF/Markdown/TXT 知识文档解析

## 快速开始

1. **克隆项目**
   ```bash
   git clone https://github.com/your-repo/Medical-Agent.git
   ```

2. **后端启动**
   - 配置 `src/main/resources/application.properties` 数据库等信息
   - 使用 IDE 或命令行运行 `MadicalApp.java`

3. **前端链接**
   ```bash
   https://github.com/Andy123211/medical-chat-assistant-ui
   ```

4. **访问系统**
   - 前端默认地址: `http://localhost:5173`
   - 后端 API: `http://localhost:8080`

## 贡献指南

欢迎提交 Issue 或 Pull Request 参与项目建设。请遵循代码规范和提交说明。

## 许可证

本项目采用 MIT License。

---
