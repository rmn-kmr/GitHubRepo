## GitHubRepo

This sample project (shows lists of google github repository) which implements MVVM architecture using ViewModel, LiveData, Retrofit. 


## MVVM
 MVVM is one of the architectural patterns which enhances separation of concerns, it allows separating the user interface logic from the business (or the back-end) logic. Its target (with other MVC patterns goal) is to achieve the following principle “Keeping UI code simple and free of app logic in order to make it easier to manage”.

 MVVM has mainly the following layers:

### Model
 - Model represents the data and business logic of the app. One of the recommended implementation strategies of this layer, is to expose its data through observables to be decoupled completely from ViewModel or any other observer/consumer (This will be illustrated in our MVVM sample app below).
 
### ViewModel
 - ViewModel interacts with model and also prepares observable(s) that can be observed by a View. ViewModel can optionally provide hooks for the view to pass events to the model. 
One of the important implementation strategies of this layer is to decouple it from the View, i.e, ViewModel should not be aware about the view who is interacting with.

### View
 - Finally, the view role in this pattern is to observe (or subscribe to) a ViewModel observable to get data in order to update UI elements accordingly.
 
 ![image](https://cdn-images-1.medium.com/max/1600/1*BpxMFh7DdX0_hqX6ABkDgw.png)
 
## LiveData 
 A data holder class that follows the observer pattern, which means that it can be observed. Always holds/caches latest version of data. Notifies its observers when the data has changed. Generally, UI components observe relevant data. LiveData is lifecycle aware, so it automatically manages stopping and resuming observation based on the state of its observing activity or fragment.
 
 ![image](https://cdn-images-1.medium.com/max/1600/1*M60CvQO3ClCt_oPZ5U-Jmw.png)
 
## ViewModel 
 Provides data to the UI and acts as a communication center between the Repository and the UI. Hides the backend from the UI. ViewModel instances survive device configuration changes.
 
 ![image](https://cdn-images-1.medium.com/max/1200/1*uWXunt0A6fKUFU8PsTLkfA.png)
  
