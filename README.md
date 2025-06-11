# Fireroof

**Fireroof** is a lightweight Minecraft plugin for Spigot/Paper 1.20+ that creates a dynamic fiery roof effect above players when they cross certain height thresholds in the Overworld or Nether.

This can be used for aesthetic purposes, danger indicators, or custom map design effects.

## 🔥 Features

- Shows a fire-like particle roof above players who exceed certain Y-levels.
- Separate height thresholds for the Overworld and the Nether.
- Fully configurable via `config.yml`.
- Efficient and non-intrusive — particles spawn only when necessary.

## ⚙️ Configuration

The plugin comes with a simple config file:

```yaml
nether-burn-height: 128
overworld-burn-height: -65
