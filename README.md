
# Wumpus-Game

This is an adventure game based on the classic "Hunt the Wumpus," where an agent must explore a labyrinth filled with dangers, such as pits and a monster called the Wumpus. The goal is to collect the gold and escape without being killed.


## Features

- **Intelligent Agent:** The agent moves on a 4x4 board, exploring rooms in search of gold.
- **Dangers:**
    - **Wumpus:** A deadly creature. The agent dies if it enters the room where the Wumpus resides.
    - **Pits:** Cause death if the agent falls into them.
- **Sensors:**
    - **Breeze:** Indicates a pit in an adjacent room.
    - **Stench:** Indicates the Wumpus is in an adjacent room.
    - **Glitter:** Indicates gold is in the current room.
- **Arrow Shooting:** The agent has a single arrow that can be shot in an adjacent direction to attempt to kill the Wumpus.







## Technologies Used

**Java:** For the graphical interface and game control.

**JPL (Java-Prolog Library):** To integrate Prolog logic rules with Java.

**Prolog:** Defines the Wumpus world logic, including movement rules, danger detection, and arrow shooting.

## Installation 

### Prerequisites
- Java 8+
- SWI-Prolog
- PL Library: To integrate Prolog with Java. To configure
    - Install JPL and ensure it’s set up with Java.
    - Add the necessary libraries and dependencies to 
    
## Game Rules

#### Movement

 The agent can move to any adjacent room. Movement rules are checked in Prolog.

#### Sensors

- **Breeze:** Warning of a nearby pit.
- **Stench:** Warning that the Wumpus is in an adjacent room.
- **Glitter:**  Indicates that gold is in the current room.

#### Arrow Shooting:

- The agent can shoot the arrow toward the Wumpus if it is in an adjacent room.
- The arrow is single-use; once fired, it cannot be recovered.
- The Wumpus is eliminated if the arrow hits its exact position.


## Project Structure

```org.example.WumpusGameGUI:``` Code for the graphical interface and user interaction logic.

```wumpus.pl:``` Defines game rules in Prolog, including agent movement, sensor presence, and arrow shooting.

## Gameplay Example

- **Movement:**  Click on an adjacent room to move the agent. If the move is invalid, a message will appear.

- **Sensors:** Upon entering a room, the agent is notified of any breeze, stench, or glitter present.

- **Arrow Shooting:** Use the "Shoot Arrow" button when the Wumpus is in an adjacent room. The arrow will fire in the direction of the Wumpus.

## Contribuindo

Contributions are welcome! Feel free to open issues and pull requests.

