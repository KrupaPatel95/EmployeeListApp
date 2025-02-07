<h1 align="center">Employee List App</h1>

<p align="center">  
This app demonstrates modern Android development with Hilt, Coroutines, Flow, Jetpack ( ViewModel, Startup, UI), and  the MVVM architecture.
</p>

## Tech stack & Open-source libraries
- Minimum SDK level 21
- [Kotlin](https://kotlinlang.org/) based, 
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- [Hilt](https://dagger.dev/hilt/) for dependency injection.
- Jetpack
  - App Startup - Initialize components at application startup (use single content provider).
  - Lifecycle - Observe Android lifecycles and handle UI states accordingly upon lifecycle changes.
  - ViewModel - Manages UI-related data holders and lifecycle aware. Allows data to survive configuration changes such as screen rotations.
  - View - Creates Jetpack Compose Screen.
- Architecture - MVVM Architecture (Model - View  - ViewModel)
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - Construct the REST APIs.

## Setup
>Note: To compile, build, and run this app, I used Android Studio.
- Open your project in Android Studio and select File > Settings... > Build, Execution, Deployment > Build Tools > Gradle (Android Studio > Preferences... > Build, Execution, Deployment > Build Tools > Gradle on a Mac).
- Under Gradle JDK, choose the Embedded JDK option or you could add a an existing jdk.
- Supports any device with a minimum SDK of 21.
- Tested and developed using Google Pixel 7 device.

## What areas of the app did you focus on?
I focused more on the architecture so as to come up with a robust app and, at the same time achieve less boilerplate code. 
In this project, 
I spent time on handling errors in the app in an efficient way and avoiding boilerplate code. 
I am always concerned about how scalable and testable the app I develop will be. 

## What was the reason for your focus? What problems were you trying to solve?
I believe choosing the right architecture plays a vital role in the development lifecycle. 
This defines the scalability, robustness, and testability of the app. 
I was trying to come up with an efficient app and I believe I was able to cover most of it. Using app startup (efficiency), global  error handling (reducing boilerplate code), and hilt (reusable, refactorable, testable).

## How long did you spend on this project? 
Around 4-5 hours. Spread it over 2 days as I was building this app after my work hours. 
I really enjoyed while working on this project.
I didn't just stick with my way of doing things but have gone ahead to try out new things, like different ways to handle errors in the application. So, it was great fun and satisfying to see how nicely it turned out. 

## Did you make any trade-offs for this project? What would you have done differently with more time?
- Follow the repository pattern, using a data mapper, which I believe in the long run, will reduce technical debt and make it easier to maintain and test.  
- Would have made this into a muti-module app.
- try to implement better ui part.
- Use resource files like styles extensively. And thus, it will be easier later to handle, especially if we need to use different themes. 
- Data cached in ViewModel, but should have handled that in the repository as ViewModel shouldn't know anything about if or how the data is cached. (Didn't focus a lot on this as this should be a small demo project)

## What do you think is the weakest part of your project?
- If we were to extend this app and as it grows, the app will get complicated as the base is not quite robust yet. I haven't concentrated much on bitmap loading which is known for its memory conception issue. 
- I depended on the coil library for image loading, keeping in mind that a lot of images needed to be handled and that the library uses coroutine in the back.
- Test, test, test: I have written unit tests, but that is never enough. I didn't check for any memory leaks or how many times the layout is drawn (believe avoided re-draw using DiffUtil and flat layouts).

## Did you copy any code or dependencies? Please make sure to attribute them here!
I go through Android developer guide for write proper dependencies name.

## Is there any other information you’d like us to know?
I believe we have covered most of the important aspects already through this fun small project. 
I have one thing to let you know is, I didn't implement swipe refresh layout because i believe we have live data observer and jetpack screen uiModel so it's Automatically update data.
I am looking forward to learning and working with all team.