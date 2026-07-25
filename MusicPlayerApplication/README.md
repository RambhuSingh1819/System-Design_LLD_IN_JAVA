# Music Player Application (LLD Prototype)

A Low-Level Design (LLD) prototype of a Music Player Application built in Java to demonstrate Object-Oriented Programming, SOLID Principles, and commonly used Design Patterns. This project focuses on designing a scalable, maintainable, and extensible architecture rather than building a graphical user interface.

---

# Overview

This project represents my understanding of Low-Level Design (LLD) by designing a real-world Music Player Application.

The objective of this project is to learn how production-level software is architected using clean coding practices and software design principles.

Instead of focusing on UI development, the project emphasizes:

- Object-Oriented Programming (OOP)
- SOLID Principles
- Design Patterns
- Scalable Architecture
- Extensible Components
- Loose Coupling
- High Cohesion

The application simulates the internal workflow of a music player including playlist management, playback strategies, audio devices, and system coordination.

---

# Project Goals

The primary objective of this project is to understand how large-scale software applications are designed.

The project addresses questions such as:

- How should songs be managed?
- How should playlists work?
- How can multiple audio output devices be supported?
- How can playback behavior be changed without modifying existing code?
- How can future features be added without affecting current implementations?

---

# Functional Requirements

The application currently supports the following features:

- Play Song
- Pause Song
- Resume Song
- Stop Song
- Play Next Song
- Play Previous Song
- Create Playlist
- Add Songs to Playlist
- Display Playlist
- Shuffle Playlist
- Sequential Playback
- Random Playback
- Support Multiple Audio Output Devices
- Device Switching
- Bluetooth Speaker Support
- Wired Headphone Support
- Internal Speaker Support

---

# Future Enhancements

The architecture has been designed so that new features can be added with minimal changes.

Future features may include:

- Equalizer
- Volume Control
- Repeat One
- Repeat All
- Recently Played Songs
- Favorite Songs
- Queue Management
- Song Search
- Lyrics Support
- Podcast Support
- Online Music Streaming
- Cloud Playlist Synchronization
- Sleep Timer

---

# High-Level Architecture

```text
                 MusicPlayerApplication
                         │
     ┌───────────────────┼────────────────────┐
     │                   │                    │
 PlaylistManager    AudioEngineFacade    DeviceManager
     │                   │                    │
 Playlist            AudioSession       AudioOutputDevice
     │                   │                    │
   Song          PlaybackStrategy      Device APIs
```

The system is divided into independent modules where every component has a single responsibility.

---

# Project Structure

```text
music-player
│
├── models
│   ├── Song
│   └── Playlist
│
├── managers
│   ├── PlaylistManager
│   ├── DeviceManager
│   └── PlaybackManager
│
├── strategies
│   ├── SequentialPlayStrategy
│   ├── RandomPlayStrategy
│   └── CustomPlayStrategy
│
├── devices
│   ├── BluetoothSpeaker
│   ├── WiredHeadphones
│   └── InternalSpeaker
│
├── interfaces
│   ├── AudioOutputDevice
│   └── PlayAudio
│
├── services
│   ├── AudioSession
│   └── MusicPlayerFacade
│
└── MusicPlayerApplication
```

---

# Design Patterns Implemented

## Singleton Pattern

Singleton ensures that only one instance of a class exists throughout the application.

Implemented in:

- MusicPlayerApplication
- PlaylistManager
- DeviceManager

Benefits:

- Single Source of Truth
- Global Access Point
- Controlled Object Creation

---

## Strategy Pattern

Different playback algorithms are encapsulated into separate strategy classes.

Supported Strategies:

- Sequential Playback
- Random Playback
- Custom Playback

Benefits:

- Easy to add new playback modes
- No modification of existing classes
- Follows the Open/Closed Principle

---

## Facade Pattern

The Facade Pattern provides a single entry point to multiple subsystems.

Instead of interacting directly with different managers, the client communicates through a single facade.

Benefits:

- Simplifies client interaction
- Reduces coupling
- Improves code readability

---

## Adapter Pattern

Different hardware devices expose different APIs.

Adapters convert those APIs into one common interface.

Supported Devices:

- Bluetooth Speaker
- Wired Headphones
- Internal Speaker

Benefits:

- Easily supports new devices
- Existing code remains unchanged
- Provides better abstraction

---

# Core Classes

## MusicPlayerApplication

Acts as the main entry point of the application.

Responsibilities:

- Register Songs
- Maintain Song Library
- Create Playlists
- Coordinate Application Components

---

## Song

Represents an individual music file.

Attributes:

- Title
- Artist
- Path
- Duration

---

## Playlist

Represents a collection of songs.

Responsibilities:

- Add Song
- Remove Song
- Display Songs
- Shuffle Songs

---

## PlaylistManager

Responsible for:

- Playlist Creation
- Playlist Management
- Song Management

Implemented using the Singleton Pattern.

---

## AudioSession

Responsible for controlling music playback.

Responsibilities:

- Play
- Pause
- Resume
- Stop

Uses the Strategy Pattern internally.

---

## DeviceManager

Responsible for:

- Detecting Audio Devices
- Connecting Devices
- Switching Active Devices
- Managing Current Output Device

---

# Playback Flow

```text
User
 │
 ▼
MusicPlayerApplication
 │
 ▼
PlaylistManager
 │
 ▼
Playlist
 │
 ▼
PlaybackStrategy
 │
 ▼
AudioSession
 │
 ▼
AudioOutputDevice
 │
 ▼
Hardware API
```

---

# Device Architecture

```text
            AudioOutputDevice
                   ▲
       ┌───────────┼────────────┐
       │           │            │
Bluetooth      Headphones   Internal Speaker
 Adapter         Adapter         Adapter
       │           │            │
       ▼           ▼            ▼
Bluetooth API Headphone API Speaker API
```

This architecture follows the Open/Closed Principle, allowing new devices to be introduced without modifying the existing business logic.

---

# SOLID Principles Demonstrated

## Single Responsibility Principle (SRP)

Each class has one well-defined responsibility.

Examples:

- PlaylistManager handles playlist management.
- DeviceManager manages audio devices.
- AudioSession controls playback.

---

## Open/Closed Principle (OCP)

The system is open for extension but closed for modification.

Examples:

- New playback strategies
- New audio devices

---

## Liskov Substitution Principle (LSP)

Every implementation of AudioOutputDevice can replace another implementation without affecting application behavior.

---

## Interface Segregation Principle (ISP)

Small, focused interfaces ensure classes implement only the methods they require.

---

## Dependency Inversion Principle (DIP)

High-level modules depend on abstractions rather than concrete implementations.

---

# Scalability

The architecture is designed for future growth.

Future integrations may include:

- Spotify Integration
- YouTube Music
- Apple Music
- AI Playlist Recommendation
- Voice Commands
- Podcast Support
- Cloud Synchronization
- Download Manager
- Smart Device Discovery

The architecture is flexible enough to accommodate these features with minimal changes.

---


# Example Usage

```java
MusicPlayerApplication app = MusicPlayerApplication.getInstance();

app.createSong(
    "Believer",
    "Imagine Dragons",
    "/songs/believer.mp3",
    204
);

app.createPlaylist("Workout");

app.addSong("Believer", "Workout");
```

---

# Technologies Used

- Java
- Object-Oriented Programming
- Low-Level Design
- UML Class Diagrams
- SOLID Principles
- Design Patterns
- Java Collections Framework
- CopyOnWriteArrayList

---

# Learning Outcomes

Through this project, I strengthened my understanding of:

- Designing scalable object-oriented systems
- Applying SOLID Principles
- Using Design Patterns effectively
- UML Modeling
- Software Architecture
- Code Reusability
- Loose Coupling
- High Cohesion
- Extensible System Design

---

# Key Takeaways

- Real-world Low-Level Design
- Scalable Architecture
- Clean Object-Oriented Design
- SOLID Principles
- Design Patterns
- Extensible System
- Maintainable Codebase
- Industry-Oriented Software Design

---

# Author

**Rambhu Singh**

Java Backend Developer | System Design Enthusiast | Low-Level Design Learner

If you found this project helpful, consider giving it a Star on GitHub.
