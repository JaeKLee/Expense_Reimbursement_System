# Expense_Reimbursement_System

## Project Description

The Expense Reimbursement System (ERS) will manage the process of reimbursing employees for expenses incurred while on company time. All employees in the company can login and submit requests for reimbursement and view their past tickets and pending requests. Finance managers can log in and view all reimbursement requests and past history for all employees in the company. Finance managers are authorized to approve and deny requests for expense reimbursement.

## Technologies Used

* Java
* JavaScript
* HTML
* CSS
* Bootstrap
* JDBC
* PostgreSQL 
* Javalin

## Features

List of features ready and TODOs for future development
* Login with either user name or email of a specific user.
* Employee can view their reimbursements. 
* Employee can add new reimbursements.
* Manager can view all employee reimbursements.
* Manager can update the status of a specific reimbursement.
* Both employee and manager can filter by status.

To-do list:
* Add more tests.

## Getting Started
   
1. ``git clone https://github.com/JaeKLee/Expense_Reimbursement_System.git``
2. Set the environment in your system: 
    1. Search **env** in your Windows machine 
    2. Select **Edit the system environment variables**
    3. Down in the right corner, select **Environment Variables...**
    4. Add the following variables:
      - TRAINING_DB_ENDPOINT - The URL to your database
      - TRAINING_DB_NAME - The database name
      - TRAINING_DB_PASSWORD - The password to your database
      - TRAINING_DB_USERNAME - The username of your database
3. Run MainDriver from Spring Tool Suite 4
4. Go to localhost ``http://localhost:9009/``
      
## Usage


As a Manager, login with the credentials then you will see buttons that say Pending. Click on the desired Pending button and select Approved or Denied from the dropdown menu then click Submit. 

As a Employee, login with the credentials then you will see Create New Reimbursement button. Click on the Create New button to create new reimbursement and fill out the fields. Amount and Type is required while Description is optional then click Submit.

For both accounts, if you wish to filter, then there is a dropdown menu. Click on the desired filter to filter according to the criterias.

## License

This project uses the following license: [MIT License](https://github.com/JaeKLee/Expense_Reimbursement_System/blob/main/LICENSE).
