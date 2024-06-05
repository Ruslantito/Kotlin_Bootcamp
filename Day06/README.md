## Exercise 0. Create a new android project

**Requirement!** The project with modules should look like: an Android project, containing packages for each module. Each package should contain source code (and resources if needed, like strings, layouts, etc.) of a module.

## Exercise 1. Develop the feature modules

- The app must have feature modules for the tools: Circles (or Circles-2), Prime numbers, Thermometer (or Thermohydrometer) - from the Day00 project
- For each module consider UI input and output. It must be implemented using standard android components (input fields, checkboxes, text fields, buttons). Each tool/module should have its own screen.
- Declare these modules in Gradle

**Advice!** Read the official documentation about declaration of modules in Gradle (e.g. using `include` in the root settings.gradle)

### Exercise 2. Develop core modules
Create a logger module
- It should be a small lightweight module, that contains class/classes with logic for logging
- The easy-to-implement way is to create a Kotlin `object`, that will be an abstraction layer over the standard `Log` class methods. The idea is to hide the concrete realisation (it can be `Log` or `Timber` or whatever) behind the facade of your own methods, so if we want to change concrete realisation, we have no need to change it in the bunch of our project modules. Also, in our facade we can declare some methods and constants specific for our app (e.g. default logging TAG or some enhanced exception info logging)
- Declare this module in Gradle

## Exercise 3. Come together! Develop the app module

**Advice!** Read the official documentation about creating dependencies between modules with Gradle. Be careful with the dependencies between modules. For example, feature modules shouldn't depend on each other, if there is no need (cross-logic), but each of modules using logger should have dependency on it.

- In the app module we have our main app logic. The app must have a main screen with buttons navigate to the tools. It must be an entry point in our Application.
- Connect all modules. There should be the app module, feature modules and core modules
- Setup navigation between the app module and feature modules
- Use the logging module to log any operations in the feature modules. Add some "analytics": log button taps and screen opening. Usually, it's a good practice to use logging where an exception may occur and when you're obtaining info (e.g. input) or preparing it before some logic (e.g. before calculations). Also, you can add logs to the Android framework events to get better acquainted with it (e.g. activity/fragment lifecycle) 

## Bonus exercise 4: More features
- The Smart Calculator now is a very-easy-to-extend application. Decide with you team, what functionality do you want to add next and write a new module/a couple of modules
- New modules can be of different types: a new feature, a new module with core functionality

