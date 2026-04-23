# Monopoly Game (OOP + Swing)

A Java Swing Monopoly-style game project focused on OOP structure and UI interaction.

## Project Structure

- `Main.java`: application entry point (Java convention)
- `PlayerSelection.java`: choose number of players
- `MonopolyBoardSwing.java`: main game UI and gameplay flow
- `BoardData.java`: board constants (spaces, token colors, coordinates)
- `BoardBackgroundPanel.java`: reusable board image panel
- `Player.java`: player state (wallet, position, jail cards)
- `Space.java`: board space model
- `Dice.java`: dice state and rendering
- `Chance.java`: Chance card deck
- `CommunityChest.java`: Community Chest card deck

## Build

From the repository root:

```bash
javac *.java
```

## Run

After building:

```bash
java Main
```

## Gameplay Status

Implemented:

- Player count selection (2-8)
- Turn-based token movement with dice
- Pass GO reward (+$200)
- Property purchase flow
- Rent payment to property owner
- Tax spaces (Income Tax / Luxury Tax)
- Chance cards (multiple effects)
- Community Chest cards (multiple effects)
- Wallet updates in side panel
- Active player highlighting
- Responsive/laptop-friendly UI scaling
- Live board relayout on window resize

Partially implemented / simplified rules:

- No full monopoly ruleset (houses/hotels/mortgages/trades/auctions)
- Jail system is basic (no full turn/roll logic)
- Bankruptcy/win conditions are not finalized
- Some card effects are simplified

## Notes

- Uses default package for simplicity in this repo.
- `monopoly.png` must remain in project root for board rendering.
