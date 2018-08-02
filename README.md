FlickerApp
----------

### Questions

#### What are some possible problems with this requirement?
 - Too many API calls. Probably many of the API calls are unnecessary.(User probably does not need all the response.)
 - If user is in slow network connection environment, performance of the application will result poorly. Probably results in lagging of the content. (e.g. API call is executed multiple times before there is response to the previous API calls. Before content is displayed to the recyclerview, new content will arrive...etc)
 - Consume data usage of the application device.
 - Drain device battery.

#### How can you satisfy the requirements but overcome these problems?
 - Reduce the API call by eliminating multiple API call in short amount of time. (e.g. implement something like [`debounce`](http://reactivex.io/documentation/operators/debounce.html).
 - Try to detect when user finish typing by setting some counter. (e.g. make an API call when there is no text change after 2 seconds...etc)

##### From API call response point of view:
 - Request to limit fewer response items at a time(Just enough number of items to fill the device screen) and make another API call to the next page as user scrolls towards end of the recyclerview.
 - Request to reduce the size of the image or have smaller image size such as thumnail.

 ### Libraries

 - RxJava2/RxAndroid2

    + Chaining multiple asynchronous operations are simpler and code is more readable than AsyncTask.
    + Verity of operators to transform the data flexibly.(e.g. filter, map...etc)
    + Able to release the reference when Activity/Fragment is destroyed to prevent memory leak.
    + Able to do unit testing.

 - Retrofit2

    + Interfaces creates more abstraction.
    + Simple and clean code especially when using with RxJava.
    + Very active open source library with many stackoverflow articles about the library. (code is constantly improving and more help from stackoverflow)

 - Glide

    + Image loading performance is slightly faster than Picasso library.
    + Library size is larger than Picasso library.

 - RecyclerView

    + Much better performance version of ListView.
    + ViewHolder holds the reference of the view instead of look for the view using `findViewById` multiple times.
    + View layout is flexible. (e.g. LinerLayout, GridView, horizontal scroll...etc)

 ### Other

 - MVP architecture pattern

  + Code is simpler to understand compare to other architecture pattern.
  + Creates abstraction data and view.
  + Code is more testable. Simple to write unit test for presenters.

