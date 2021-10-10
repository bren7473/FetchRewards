# FetchRewards
This app retrieves the supplied data and sorts by list Id and then name using sql.
Data is displayed using a recycler view containing a table layout inside of a card.
The project is designed with MVVM structure and reactive patterns.
Data is stored in a room database so after an initial download the app retains displayable data regardless of internet service.
The room database updates and replaces data as the api source data changes.
LiveData and Databinding are used to display data to the user.
Dependency Injection is implemented using Hilt.
Some simple theming.
