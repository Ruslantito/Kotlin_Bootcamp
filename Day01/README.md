## Exercise 1: Circles
Develop a mathematical module that determines if two given circles intersect or not.
- There are two circles on the coordinate plane: the first one is centered at the point (x1, y1) with radius r1, the second one is centered at the point (x2, y2) with radius r2.
- The program reads coordinates and radius for both circles one by one, see an example below
- The program works with floating point numbers
- The program determines the position of circles relative to each other and describes it in the result. Circles can intersect, touch, do not intersect, coincide or one can be inside another. _See an example below_
- The program doesn't fail on incorrect input. It writes "Couldn't parse a number. Please, try again" and repeats input for the failed parameter

_Example:_
  ```
  Input x1:
  0
  Input y1:
  0
  Input r1:
  3
  Input x2:
  4
  Input y2:
  4
  Input r2: 
  3
  Result: the circles intersect
  ```

## Exercise 2: Prime numbers
Develop a prime numbers module that works with the given number consisting of an arbitrary amount of digits. It has to receive a number and create a set of numbers out of it according to the following rules:
- The first number is equal to the units digit
- The second is made up of the units and tens digits
- Hundreds are added to the third
- Etc. For example, 4835 can give us a set of numbers: 5, 53, 538 and 5384.  

Also the grouping order is specified in the program arguments: lower (from lower to higher order ) or higher (from higher to lower order). Thus, two situations are possible:
- Starting from the lowest level (5, 53, 538, 5384)
- Starting from the highest order (4, 48, 483, 4835)

Then the module has to do the primality test for each in this set of numbers.

The program works with integers and fails on a wrong input. It should throw any exception to stop, e.g. `throw Exception("message")`

**Advice!** You can find information about adding program arguments to project configurations in the official documentation for IntelliJ IDEA.

_Example:_
```
The grouping order is: higher
Enter a number:
352

Result: 
3 - prime
35
352
```

## Exercise 3: Thermometer
The most comfortable temperature for a person is from 22 to 25 ˚C in summer and from 20 to 22 ˚C in winter. Write a module that receives the season and temperature and determines whether the temperature is comfortable.
- The temperature sensor (input temperature) works with a Celsius scale. The input number can be with floating point
- The program can output info in three modes: Kelvin, Celsius and Fahrenheit. The unit is specified in the program arguments (Celsius by default). But input still has to be in Celsius only
- The program doesn't fail on incorrect input of season or temperature. It suggests to try again, like "Incorrect input. Enter a temperature:"
- In addition, the program advices you to decrease or increase a temperature to make it comfortable if it isn’t.  

_Example:_
```
Output mode: Celsius
Enter a season - (W)inter or (S)ummer:
S
Season: Summer. Enter a temperature:
17

The temperature is 17 ˚C
The comfortable temperature is from 22 to 25 ˚C. 
Please, make it warmer by 5 degrees.
```

## Bonus exercise 4: Thermohydrometer
The comfortable humidity for a person is from 30% to 60% in summer and from 30% to 45% in winter. In exercise 4, for both seasons add comfortable humidity parameters. Input it after temperature and output whether it is comfortable or not (if not, advice to decrease or increase it by a certain number of units)
The program also doesn't fail on incorrect input. It suggests to try again, like "Incorrect input. Enter humidity:"

_Example:_
```
Output mode: Celsius
Enter a season - (W)inter or (S)ummer:
W
Season: Winter. Enter a temperature:
26
Enter humidity:
35

The temperature is 26 ˚C
The comfortable temperature is from 20 to 22 ˚C 
Please, make it colder by 4 degrees

The comfortable humidity is from 30% to 45%
The humidity is comfortable
```

## Bonus exercise 5: Circles-2
In exercise 1, if the circles intersect/touch, output the coordinates of the intersection/tangency points

## Bonus exercise 6: Speech module
As a child, everyone who loved science fiction and various kinds of mechanisms dreamed of a device that could understand words, receive voice commands and carry out appropriate tasks. Now voice control has become a familiar format for communicating with a computer, making our life easier and making this communication more natural. Our smart calculator certainly needs an intermediary module that translates the recognized speech into a language understandable to the computer.
Develop a speech module that reads integer numbers in digital format and translates them into numbers in words
- The module must accept numbers up to a billion inclusive (positive and negative)
- The module receives an integer number in digital format and prints it in words. In words means a set of words, separated by a hyphen, makes up a number. Example: one-hundred-fifty-two
- The number input-print process repeats until user prints "exit"
- At start the module prints "The program is running. Enter a number or type "exit" to stop:" before input
- On the second number and further the module prints "Enter a number:" before input
- Every 5th iteration, the module prints "Enter a number or type "exit" to stop:" before input
- If an input is incorrect (a word, a symbol or an unsupported number), the module prints "Incorrect format, try again.\nEnter a number:" or "The number is out of bounds, try again.\nEnter a number:" and listens to another input

_Example_
```
The program is running. Enter a number or type "exit" to stop:
yes
Incorrect format, try again

Enter a number:
34
Thirty-four

Enter a number:
exit

Bye!
```
