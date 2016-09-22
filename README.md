# TODOMVP
A sample Todo application built using MVP Architecture and Event Bus

Hello Guys...!!
Having tough time to understand and implement MVP pattern in Android ?

So, here is how I started to learn and Implement.

To me, MVP is: 

Model - A place to perform all CRUD operations on Entities, whether by calling web service, or performing it on Local SQLite Database.
This should be performed in Android's Service component for better performance. Question yourself (What data is to be returned to Presenter)

View - A place to perform any operation in which view is involved. Some common tasks may be getting and setting text from TextView, and so on. (Ask question to yourself, what a view has to be done ? Looking at the UI, You should get answer like, Some Text is to be read from TextView, or some text has to be set on EditText, etc.) Views should deal with only "WHAT IS TO BE DONE by a View ?" Also, No View should be directly accessing any Model. Views typically, include Activities, Fragments, etc.

Presenter - A place where you write code that binds Model to a View. Like fetching data from Model and Displaying it to View. A middleware kind of thing. (When SHOULD IT BE DONE by a view or When to bring data from Model)

The Only difference I see between MVC and MVP is,

In MVC - Views and Models can interact directly, whereas,
In MVP - View and Models talk only through Presenter

Model <-- --> Presenter <-- --> View

Thats it...!!! This is what MVP is.. 

For Every Activity, Fragment, View or Dialog, or whatever, I created atleast 3 files:

1. class TActivity
2. interface TView (which TActivity will be implementing on it)
3. class TPresenter (which we will initialize in TActivity)

For Model, created a class IntentService which will be serving to requests from Presenter.

One more thing, I have used EventBus to provide communication between Model and Presenter.

Clone it, run it and try to understand it the way I explained. It would be a child play for you after this...!!!
