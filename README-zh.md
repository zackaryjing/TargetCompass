# TargetCompass

[English Ver.](README.md)

**Minecraft Fabric Mod 1.21.1**

TargetCompass 是一个简单的 Fabric mod，灵感来源于 Dream 的“猎人游戏”。可以在屏幕上显示箭头或坐标，用于追踪指定玩家的位置。

> ⚠️ 当前仅支持 **Fabric** 和 **Minecraft 1.21.1**。
> 如果你需要其他版本或平台，请 **提出 issue**。

---

## 功能

### 1️⃣ 箭头追踪

* **命令**：

  ```
  /track start <玩家名>
  ```

    * 在 **屏幕左下角**显示红色箭头，指向指定玩家
    * 不跨维度。如果玩家消失或进入其他维度，箭头指向玩家 **最后出现的位置**

* **停止追踪**：

  ```
  /track stop
  ```

---

### 2️⃣ 坐标广播

* **命令**：

  ```
  /broadcast location <玩家名>
  ```

    * 在 **屏幕中下方**显示玩家实时坐标

* **停止显示**：

  ```
  /broadcast stop
  ```

---

## 使用示例

1. 追踪玩家 Alice：

   ```
   /track start Alice
   ```

2. 停止箭头追踪：

   ```
   /track stop
   ```

3. 显示 Bob 的坐标：

   ```
   /broadcast location Bob
   ```

4. 停止坐标显示：

   ```
   /broadcast stop
   ```

---

## 安装

1. 安装 **Fabric Loader** 和 **Fabric API**（Minecraft 1.21.1）
2. 下载 `TargetCompass-<版本>.jar`
3. 将 `.jar` 文件放入 Minecraft `mods` 文件夹
4. 使用 Fabric 启动游戏

---

## 注意事项

* 仅支持 **单机或 Fabric 服务器**
* 箭头追踪不跨维度
* 仅用于娱乐追踪玩法，请合理使用

---

## 贡献

* 如果需要支持其他版本或平台，请 **提出 issue**
* 欢迎提交 Pull Request

