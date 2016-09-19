# ToddDiagnoser

### About
The app lets you obtain an estimate result on how likely is for a person to have Todd’s Syndrome.

### Running the project
- The project was created using the latest [beta version](http://tools.android.com/download/studio/builds/2-2-rc-2) of Android Studio.
- Once downloaded, should the project can be imported without issues.

### Project Structure
- **app** module: it contains the code for the Android application.
- **diseasediagnoser** module: library in charge of perfoming the calculations needed based on the given criteria. The library supports new implementations for calculating different diseases.

### App module structure
- The project uses MVP and repository pattern for presentation and data layers respectively.
- A local database implementation of the **DiagnoseDataSource** is used to store and retrieve the information. The repository pattern makes it easier to make a new implementation using a REST API. 
As the project already uses RxJava to return the data back to the presentation layer and in the repository itself, it would be quite easy to use Retrofit 2 library to implement the remote repository.
