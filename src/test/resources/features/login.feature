Feature: Login Page


@Tag1
Scenario:User able to login to app
  Given user opens application url
  When user enters user credentials
  Then verify successful operation


@Tag2
Scenario Outline: User able to login to app with multiple users

Given user opens application url
  When user enters user credentials for "<username>" and "<password>"
  Then verify successful message "<message>"

  Examples:
    | username | password | message                                     |
    |admin1	   |admin2	  |Error: Invalid Credentials. Please try again.|
    |admin	   |admin	  |Successful login                             |
    |admin1	   |admin2	  |Error: Invalid Credentials. Please try again.|
    |admin2	   |admin3	  |Error: Invalid Credentials. Please try again.|
    |admin3	   |admin4	  |Error: Invalid Credentials. Please try again.|
    |admin	   |admin	  |Successful login                             |
