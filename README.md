# Bikability-and-Walkability-Scores

### Steps I Use to Create this CSV file:

1. Download the 2018 Census Data from census.gov
2. Filter the cities so only population 50,000-200,000 remain
3. Cleaned up the names of the cities so they are in CityName, StateName format
4. Manually determined which cities are suburbs or if it is the largest city in area. I found the best way to do this was to copy and paste the city into google maps, and see if it is surrounded by farms.
5. Used the Kotlin files to get the bikability and walkability scores
6. Standardized the data. Multiplied biking score by 10.
7. Set the missing values for biking and walking score to the average of the column. Didn't include 0 when computing average
8. Created a column for the average between walking and biking score