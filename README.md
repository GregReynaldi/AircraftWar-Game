# AircraftWar-Game

<div align="center">
  <img src="src/images/hero.png" alt="Aircraft War Game" width="200"/>
  <h3>🚀 A classic aircraft shooting game built with Java</h3>
  <p>
    <a href="https://github.com/GregReynaldi/AircraftWar-Game">
      <img src="https://img.shields.io/github/stars/GregReynaldi/AircraftWar-Game?style=social" alt="GitHub stars"/>
    </a>
    <a href="https://github.com/GregReynaldi/AircraftWar-Game/network/members">
      <img src="https://img.shields.io/github/forks/GregReynaldi/AircraftWar-Game?style=social" alt="GitHub forks"/>
    </a>
  </p>
</div>

## Project Overview

AircraftWar-Game is a classic 2D aircraft shooting game developed in Java, featuring multiple enemy types, power-ups, and engaging gameplay mechanics. The game implements object-oriented programming principles with a focus on clean architecture and modular design.

## Key Features

- **Multiple Enemy Types**: Mob enemies, elite enemies, and powerful boss enemies
- **Power-ups System**: Health supplies, firepower boosts, and super fire abilities
- **Dynamic Shooting Strategies**: Straight, spread, and circular shooting patterns
- **Immersive Audio**: Background music and sound effects for enhanced gameplay
- **Multiple Backgrounds**: Variety of game backgrounds for visual diversity
- **Score System**: Track your progress and compete for high scores
- **Game States**: Start, pause, and game over states with appropriate transitions

## Technology Stack

| Category | Technology |
|---------|------------|
| Programming Language | Java |
| Game Development | Java AWT/Swing |
| Audio | Java Sound API |
| Build Tool | IntelliJ IDEA |
| Version Control | Git |

## Project Structure

```
AircraftWar-Game/
├── src/
│   ├── edu/hitsz/
│   │   ├── aircraft/          # Aircraft classes hierarchy
│   │   ├── application/       # Main game application
│   │   ├── basic/             # Basic game objects
│   │   ├── bullet/            # Bullet classes
│   │   ├── factory/           # Factory patterns for enemies and supplies
│   │   ├── music/             # Audio controllers
│   │   ├── strategy/          # Shooting strategies
│   │   └── supply/            # Power-up supplies
│   ├── images/                # Game graphics
│   └── videos/                # Audio files
├── uml/                       # UML diagrams
├── .gitignore
├── AircraftWar-base.iml
└── README.md
```

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- IntelliJ IDEA or any Java IDE

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/GregReynaldi/AircraftWar-Game.git
   cd AircraftWar-Game
   ```

2. **Open the project in IntelliJ IDEA**
   - Select "Open" and navigate to the project directory
   - Wait for IntelliJ to index the project

3. **Run the game**
   - Locate `Main.java` in `src/edu/hitsz/application/`
   - Right-click and select "Run Main.main()"

## How to Play

1. **Controls**:
   - Use arrow keys to move the hero aircraft
   - The aircraft automatically fires bullets

2. **Gameplay**:
   - Destroy enemy aircraft to earn points
   - Collect power-ups to enhance your aircraft
   - Avoid enemy bullets and collisions
   - Defeat the boss enemy for bonus points

3. **Power-ups**:
   - 🟢 Green: Health boost
   - 🟡 Yellow: Firepower increase
   - 🔵 Blue: Super fire ability

## Architecture

### Design Patterns Implemented

- **Factory Pattern**: For creating enemy aircraft and supplies
- **Strategy Pattern**: For different shooting behaviors
- **Singleton Pattern**: For game managers and controllers
- **Template Method Pattern**: For common aircraft behaviors

### Key Components

1. **Aircraft System**
   - `HeroAircraft`: Player-controlled aircraft
   - `AbstractEnemyAircraft`: Base class for all enemy types
   - `BossEnemy`: Powerful boss with unique abilities

2. **Shooting System**
   - Multiple shooting strategies for different aircraft types
   - Bullet collision detection and damage calculation

3. **Supply System**
   - Random supply drops from destroyed enemies
   - Temporary power-ups for the player

4. **Audio System**
   - Background music for different game states
   - Sound effects for actions and events

## UML Diagrams

The project includes comprehensive UML diagrams in the `uml/` directory:

- **Class Hierarchy**: Inheritance relationships between game objects
- **Factory Patterns**: Enemy and supply creation mechanisms
- **Shooting Strategies**: Different shooting behavior implementations
- **Singleton Patterns**: Game management components

## Contributing

Contributions are welcome! Here's how you can help:

1. Fork the repository
2. Create a new branch (`git checkout -b feature/AmazingFeature`)
3. Make your changes
4. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
5. Push to the branch (`git push origin feature/AmazingFeature`)
6. Open a Pull Request

Please ensure your code follows the existing style and includes appropriate documentation.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgements

- **Java Documentation**: For comprehensive Java API references
- **Game Development Resources**: For inspiration and best practices
- **Open Source Community**: For valuable tools and libraries

## Contact

- **Project Link**: [https://github.com/GregReynaldi/AircraftWar-Game](https://github.com/GregReynaldi/AircraftWar-Game)
- **Author**: [Gregorius Reynaldi](https://github.com/GregReynaldi)

---

<div align="center">
  <p>Built with ❤️ by <a href="https://github.com/GregReynaldi">Gregorius Reynaldi</a></p>
</div>
