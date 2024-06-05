## Exercise 1: A basic game

Rules of the game:
- The interface consists of four buttons. When a button is pressed, a short sound is played. Each button has its sound, the sounds are of the same theme (e.g. 4 different sounds of drums, 4 different sounds of notes, animals...)
- At first, the application plays a sequence of sounds with a small delay (e.g 500ms). Then, a user must repeat this sequence by successively pressing the desired buttons
- The game is played by levels. With each level, one more sound is added to the playback sequence (previous elements of this sequence are the same). The sequence starts with one sound.

Write the basic logic of the game
- On the application start, the main screen with buttons is opened. For simplicity, a game begins immediately
- The App is playing sounds sequence (with delays between sounds): on each sound, the button linked with this sound should be highlighted. At this time, the buttons are locked for a user
- Your sounds can be of any theme, but they have to be of the same length, no longer than 1 second
- Then the user has to repeat the sequence played by the App: he pushes buttons in the same order, each button plays sound and becomes highlighted (only on push)
- If the user repeats the sequence correctly, the App plays a next level sequence. It consists of the previous sequence + one new button. E.g. Level 1: b2->b4->b1->b3. Level 2: b2->b4->b1->b3->b2 
- There should be a level counter on the screen
- The first incorrectly pressed button causes a game loss. A dialog should appear with the resulting number of levels and a "Restart" button.
- The buttons are placed into 2 lines (2 x 2). They should be big enough, almost fill the entire screen
- Buttons must be of different colors. They can have pictures or numbers in the middle
- The App saves a top result of all games - number of levels (use shared preferences). It should be shown near the current level on the screen

**Advice!** See an example screen `misc/images/Ex1.png`

## Exercise 2: Free Play
Add the "Free Play" item to menu after the "New Game". Free play mode allows you to open the game screen, but the sequences are not played and there is no level counter. It is possible to press the buttons in any sequence. In the upper left corner there is a back arrow (to exit the menu).

## Exercise 3: Menu
Let’s add new features to the game:
- Add to the app a menu with items:
  - New game
  - About
- The "New game" button should launch the main game from exercise 1. Now on the screen with the game in the upper left corner there is a back arrow (to exit to the menu). In the dialog that appears after the loss, add the button "Menu"
- "About" button opens a new screen, where the record, game conditions and information about the author are written. In the upper left corner there is a back arrow (to exit the menu)

**Advice!** Jetpack Navigation Components can be used to navigate between screens. It's pretty handy and easy to understand, good for small projects. https://developer.android.com/guide/navigation/navigation-getting-started

## Bonus exercise 4: Settings
Add the "Settings" item to the menu after the "Free play". On the settings screen, there are the following options:
  - Sound (on/off) - turns the sound in both game modes (New game and Free play) on or off.
  - Delay between sounds in a chain (from 100ms to 1 second with 100ms step)
    - Use a slider to implement this behavior  

In the upper left corner there is a back arrow (to exit the menu).

## Bonus exercise 5: Hard mode
Add in Settings the option "Button highlight", which disables/enables a visible effect (highlight) of pressing buttons in the game

## Bonus exercise 6: Sound banks
Add to Settings the ability to change 2-3 sound themes
