## Exercise 0: Try coroutines
This is not an obligatory task, but it's very important introduction for better understanding of the topic. In the official Kotlin coroutines documentation, there are a lot of code samples, literally on each term or feature. They are small and work as is. 
- Add coroutines to the android project
- Create a kotlin file and run these samples for entry themes: coroutine basics, cancellation and timeouts, context and dispatchers, 
- Log an every important step to the console - it'll make processes more clear.

## Exercise 1: Smart calculator with coroutines
- Perform all calculations in tools from the project team00 (multi-module app) using coroutines
- For a better understanding, add logging for all actions (inside and outside coroutines) using the Logging module from the team00

## Exercise 2: High intensive calculations
As we get acquainted with coroutines, we can add more highly loaded tasks to our calculator. Let's add a tool that will make for the entered number following calculations:
- Factorial. Starts with a separate button.
- Square and cube root. Both calculations are started with one button and must be performed in parallel using async. The screen displays the result of both calculations at once.
- Logarithms lg and ln. Both calculations are started with one button and must be performed in parallel using async. The screen displays the result of both calculations at once.
- Squaring and cube. Both calculations are started with one button and must be performed in parallel using async. The screen displays the result of both calculations at once.
- Simplicity test. Starts with a separate button.

This tool is a module with its own screen.

**Also:**
- In this task, we will also log all actions.
- There is a button to run all calculations at once (with async construction)
- Calculations can be interrupted at any time. During the calculation, the corresponding button’s text must be changed from “Run” to “Cancel”
- For the simplicity test, add a timeout (there is a suitable mechanism in coroutines) of 1 sec. In case of the timeout error, a dialog "An error has occurred. Please try again." should be displayed
