Importing to Intellij
--
- Clone the repository
- In Intellij -> import project -> sbt


Running the Application
--

Open a command window at the PAMM seed root folder and enter the following command

	activator svc/startPAMM

This will start the H2 database server on port 9092, and the Play application on port 9000.

Enter the following URL in a browser

    http://localhost:9000

The PAMM login page should be presented. The seed has no authentication configured for this initial draft version, so by entering any username and password, the PAMM dashboard should be displayed. The angular client is integrated with the Play backend and any actions on the Angular client will be routed to the Play backend.

To shutdown the database and Play application open a command window at the project root folder and enter the following command

	activator svc/stopPAMM


**Packaging the Application
--

Open a command window at the PAMM seed root folder and enter the following command

	activator svc/dist
