#Author: Anusha Jakkala

Feature: Sanity test for Alyte web application
	
	@alyte_01 @alyte
	Scenario Outline: To create and run a logout trip Upload file -> Mandatory fields + Gender(2F1M-all DP locations different)
		Given Login into Alyte application with userName and passWord as "<TestCase Number>"
		When Navigate to Upload Trips
		And Select From and To date drom datepicker "<FromDate>" "<ToDate>"
		And Click on Upload Trips 
		Then Routes uploaded for Login with External Id toast message shown to user
		When Navigate to Routing page
		And Select the details and click the Find button "<DateToEnter>"
		Then Trip with ID mentioned should be present "<Ext_Trip_ID>"
		When Click the Vehicle Type icon edit icon for "<Ext_Trip_ID>"
		And Select Sedan from the dropdown and click the Done button
		Then Vehicle type updated Successfully toast mesasge displayed to user
		And SED should be diaplayed on trip widget for "<Ext_Trip_ID>"
		When Select required vehicle "<PlateNumber>" for "<Ext_Trip_ID>"
		Then Vehicle Assigned Successfully toast mesasge displayed to user 
		When Select the checkbox on top left of widget and click Finalize button for "<Ext_Trip_ID>"
		Then Routes finalize for trip id trip_id toast message displayed to user
		When Navigate to TripBoard 
		Then verify Shift type Plate no Currect status  details against our external ID "<Ext_Trip_ID>" "<PlateNumber>" and "<DateToEnter>"
		
		Examples: 
	    | TestCase Number  |Ext_Trip_ID| FromDate 			| ToDate  			 |DateToEnter			|PlateNumber|
	    | TC001_Alyte      |2827       |January 30, 2022|January 30, 2022|January 30, 2022|MH01ZA1234 |
 
	@alyte_02 @alyte
	Scenario Outline: Creating a trip with no guard, no vehicle number & no vehicle type specified
		Given Login into Alyte application with userName and passWord as "<TestCase Number>"
		When Select site from top right Site dropdown
		When Navigate to Single Trips page
		And Enter single trip details with type as "<Vehicle_Type>" with trip id as "<Ext_Trip_ID>" Date as "<Date>"
		And Click the ADD SINGLE TRIP button
		Then Trip Created Successfully toast mesasge displayed to user
		When Navigate to Routing page
		And Select the details and click the Find button "<DateToEnter>"
		Then Trip with ID mentioned should be present "<Ext_Trip_ID>"
		When Click the Vehicle Type icon edit icon for "<Ext_Trip_ID>"
		And Select Sedan from the dropdown and click the Done button
		Then Vehicle type updated Successfully toast mesasge displayed to user
		And SED should be diaplayed on trip widget for "<Ext_Trip_ID>"
		When Select required vehicle "<PlateNumber>" for "<Ext_Trip_ID>"
		Then Vehicle Assigned Successfully toast mesasge displayed to user 
		When Select the checkbox on top left of widget and click Finalize button for "<Ext_Trip_ID>"
		Then Routes finalize for trip id trip_id toast message displayed to user
		When Navigate to TripBoard 
		Then verify Shift type Plate no Currect status  details against our external ID "<Ext_Trip_ID>" "<PlateNumber>" and "<DateToEnter>"

		Examples: 
		  | TestCase Number| Ext_Trip_ID		|Vehicle_Type|Date			 |DateToEnter    |PlateNumber|
		  | TC002_Alyte 	 |1046						|SUV				 |2022-01-30|January 30, 2022|MH05QA1212|
	      
       @sanity @MPODD
	Scenario Outline: CD to CD-->Plant flow (with Manual Upload)
		Given Login as CD01 and select location as "<Location>" for "<TestCase Number>"
		When Click on Inbound and click inward material with upload
		And Material Details have been added Manually
		Then Record should be submitted successfully with proper pop up message
		When Click on Inbound and click Inbound create LR
		And Enter the details for create LR with "<A0003>" as location
		Then Record should be submitted successfully and LR no should be generated
		When Click on Inbound and click Inbound Print LR
		And User will enter valid vehicle number in search box and Clicks on search button for A0003
		Then LR related to entered vehical no should be displayed for A0003
		And Click on Create Shipment option
		When Login as "<A0003>" 
		And Click on Inbound and Inward Material at PlantCD 
		And User will enter valid vehicle number in search box and Clicks on search button for A0003
		And Click on Create Inward Entry option
		Then Shipment is created for the LR created
		
		 Examples: 
		   | TestCase Number  |Location											 |A0003| 
		   | MPSC01_mPODD      |CD01 - NASIK CROSS DOCK       |CD10 - BANGALORE CROSS DOCK|
	      
      @Regressionapp
@TS003
Scenario Outline: verify to compliance app
Given user launches and logs into compliance application using valid credentials for "<TestCase Number>"
And user clicks on menu icon
And user navigate to create and assign trip page
And user selects site
And user creates single trip card
And user navigates to assign trip page
And user find trip based on shift time and date
And user click on assign
And user finalizes the trip in compliance
And user acknowledges the confirmation message
And user navigates to trip board
When user selects shift time and shift date
Then user should verify the trip status
Examples:
| TestCase Number|
| ALSP13DCO003 |
      