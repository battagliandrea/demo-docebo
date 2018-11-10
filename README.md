# Kotlin MVP
Android kotlin mvp sample. Used to clone and start with a new project

## Dependencies
In this project I have used many of most popular Android's dependencies:
1. Retrofit
2. RxJava2
3. Dagger2

## Mvp 
I implemented the mvp pattern through dagger2. Into AppComponent.class there are all modules which the app needed to work. Instead into PresenterComponent we find all modules injection. 
Every module with @PresenterScope annotation provide the presenter instance and in the constructor we can pass the appropriate Repository