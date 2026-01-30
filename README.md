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

AircraftWar-Game is a classic 2D aircraft shooting game developed in Java, featuring multiple enemy types, power-ups, and engaging gameplay mechanics. The game implements object-oriented programming principles with a focus on clean architecture and modular design. It provides an immersive gaming experience with various difficulty levels, boss battles, and power-up systems.

## Key Features

- **Multiple Enemy Types**: Mob enemies, elite enemies, and powerful boss enemies with unique behaviors
- **Power-ups System**: Health supplies, firepower boosts, and super fire abilities
- **Dynamic Shooting Strategies**: Straight, spread, and circular shooting patterns
- **Immersive Audio**: Background music and sound effects for enhanced gameplay
- **Multiple Backgrounds**: Variety of game backgrounds for visual diversity
- **Score System**: Track your progress and compete for high scores
- **Game States**: Start, pause, and game over states with appropriate transitions
- **Modular Architecture**: Clean code structure with design patterns implementation

## Technology Stack

| Category | Technology |
|---------|------------|
| Programming Language | Java |
| Game Development | Java AWT/Swing |
| Audio | Java Sound API |
| Build Tool | IntelliJ IDEA |
| Version Control | Git |
| External Libraries | Apache Commons Lang 3.8.1 |

## Project Structure

```
AircraftWar-Game/
├── .idea/                     # IntelliJ IDEA project configuration
│   ├── inspectionProfiles/    # Code inspection profiles
│   └── ...                    # IDE configuration files
├── lib/                       # External libraries
│   └── commons-lang3-3.8.1.jar # Apache Commons Lang library
├── src/                       # Source code
│   ├── edu/hitsz/             # Main package
│   │   ├── aircraft/          # Aircraft classes hierarchy
│   │   ├── application/       # Main game application
│   │   ├── basic/             # Basic game objects
│   │   ├── bullet/            # Bullet classes
│   │   ├── factory/           # Factory patterns for enemies and supplies
│   │   │   ├── enemy_factory/ # Enemy aircraft factories
│   │   │   └── supply_factory/ # Power-up supply factories
│   │   ├── music/             # Audio controllers
│   │   ├── strategy/          # Shooting strategies
│   │   └── supply/            # Power-up supplies
│   ├── images/                # Game graphics
│   └── videos/                # Audio files
├── AircraftWar-base.iml       # IntelliJ IDEA module file
├── LICENSE                    # MIT License file
└── README.md                  # Project documentation
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
   - Wait for IntelliJ to index the project and resolve dependencies

3. **Run the game**
   - Locate `Main.java` in `src/edu/hitsz/application/`
   - Right-click and select "Run Main.main()"

## How to Play

### Controls

- **Arrow Keys**: Move the hero aircraft in respective directions
- **Automatic Firing**: The aircraft automatically fires bullets

### Gameplay

1. **Objective**: Destroy enemy aircraft and survive as long as possible
2. **Scoring**: Earn points by destroying enemy aircraft
3. **Power-ups**: Collect falling power-ups to enhance your aircraft
4. **Boss Battles**: Defeat powerful boss enemies for bonus points
5. **Game Over**: The game ends when your aircraft's health reaches zero

### Power-ups

- **Green**: Health boost - increases your aircraft's health
- **Yellow**: Firepower increase - enhances your bullet damage
- **Blue**: Super fire ability - temporarily grants special shooting capabilities

## Architecture

### Design Patterns Implemented

- **Factory Pattern**: For creating enemy aircraft and power-up supplies
- **Strategy Pattern**: For different shooting behaviors across aircraft types
- **Singleton Pattern**: For game managers and controllers
- **Template Method Pattern**: For common aircraft behaviors and lifecycle

### Key Components

1. **Aircraft System**
   - `HeroAircraft`: Player-controlled aircraft with customizable attributes
   - `AbstractEnemyAircraft`: Base class for all enemy types
   - `BossEnemy`: Powerful boss with unique abilities and multiple health stages
   - `EliteEnemy`: Advanced enemy with enhanced capabilities
   - `MobEnemy`: Basic enemy with simple behavior

2. **Shooting System**
   - Multiple shooting strategies for different aircraft types
   - Bullet collision detection and damage calculation
   - Projectile management and lifecycle

3. **Supply System**
   - Random supply drops from destroyed enemies
   - Temporary power-ups for the player
   - Supply collection and effect application

4. **Audio System**
   - Background music for different game states
   - Sound effects for actions and events
   - Audio volume control and management

5. **Game Management**
   - Game state handling and transitions
   - Score tracking and display
   - Collision detection and physics

## Code Quality

The project follows best practices for Java development:

- **Clean Code**: Well-organized and readable code
- **Modularity**: Separation of concerns into distinct packages
- **Encapsulation**: Proper access control and information hiding
- **Inheritance**: Hierarchical class structure for aircraft and game objects
- **Polymorphism**: Runtime behavior variation through interfaces and abstract classes

## Contributing

Contributions are welcome! Here's how you can help:

1. **Fork the repository** on GitHub
2. **Create a new branch** for your feature or bug fix
   ```bash
   git checkout -b feature/AmazingFeature
   ```
3. **Make your changes** with clear commit messages
4. **Push to the branch**
   ```bash
   git push origin feature/AmazingFeature
   ```
5. **Open a Pull Request** on GitHub

Please ensure your code follows the existing style and includes appropriate documentation.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgements

- **Java Documentation**: For comprehensive Java API references
- **Game Development Resources**: For inspiration and best practices
- **Open Source Community**: For valuable tools and libraries
- **Apache Commons Lang**: For utility classes and methods

## Contact

- **Project Link**: [https://github.com/GregReynaldi/AircraftWar-Game](https://github.com/GregReynaldi/AircraftWar-Game)
- **Author**: [Gregorius Reynaldi](https://github.com/GregReynaldi)

---

Built with dedication by [Gregorius Reynaldi](https://github.com/GregReynaldi)
