# TargetCampus

[中文说明](README-zh.md)

**Minecraft Mod for Fabric 1.21.1**

TargetCampus is a simple Fabric mod inspired by Dream's “Hunter Game”. It allows you to track other players in-game using an on-screen arrow or display their coordinates.

> ⚠️ Currently only supports **Fabric** and **Minecraft 1.21.1**.
> If you need other versions or platforms, please open an issue.

---

## Features

### 1. Arrow Tracking

* **Command**:

  ```
  /track start <player name>
  ```

    * Shows a red arrow at the **bottom-left** of the screen pointing to the specified player.
    * Arrow does **not cross dimensions**. If the player disappears or moves to another dimension, the arrow points to their **last known location**.

* **Stop tracking**:

  ```
  /track stop
  ```

---

### 2. Broadcast Player Location

* **Command**:

  ```
  /broadcast location <player name>
  ```

    * Displays the player's coordinates at the **bottom-center** of the screen, updated in real-time.

* **Stop broadcasting**:

  ```
  /broadcast stop
  ```

---

## Usage Examples

1. Track player Alice:

   ```
   /track start Alice
   ```

2. Stop arrow tracking:

   ```
   /track stop
   ```

3. Show Bob’s coordinates:

   ```
   /broadcast location Bob
   ```

4. Stop broadcasting:

   ```
   /broadcast stop
   ```

---

## Installation

1. Install **Fabric Loader** and **Fabric API** (Minecraft 1.21.1).
2. Download `TargetCampus-<version>.jar`.
3. Place it in your Minecraft `mods` folder.
4. Start the game with the Fabric profile.

---

## Notes

* Only works on **single-player or servers with Fabric**.
* Arrow tracking does not cross dimensions.
* Designed for **fun tracking gameplay**; use responsibly.

---

## Contribution

* Open an **issue** if you want support for other versions or platforms.
* Pull requests are welcome.
