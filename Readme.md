1)To run project through command line :

        gradle test -Denv=QA -Dheadless=true -Dcucumber.filter.tags="@Tag2"

        1) -Denv value needs to be updated based on environment run  (mandatory VM argument)

        2) -Dheadless  to be made false ,if suite to be run without headless else true(mandatory VM argument)

        3) -Dcucumber.filter.tags ,to be provided only if there is a need to run selective Tags.Else can be ignored if need to run all tags.
            to run multiple tags provide comma seperated eg @Tag1,Tag2 ..  (optional VM argument)


2)There are 2 application built around the suite:
    1)Login app (http://selenium1234.pythonanywhere.com/login)
    2)Checkbox,dropdown,iframe verification app(http://selenium1234.pythonanywhere.com/v4 )


3)Excel api made to generate test data in json format :http://selenium1234.pythonanywhere.com/getData

    -It can be used as GET/POST
    -GET can be accesed through Chrome browser

4)Properties are read from resources folder ,specific to environment

        test -resources
                    -QA  - test.properties
                    -DEV - test.properties


5)Email sender recipients TO,CC needs to be added in test.properties file

6)Test Data from excel is structured under :
                resources-
                    testData-
                        test.xlsx

7)This project is made for MAC ,so for chromedriver to run on windows,download chromedriver.exe and put under
            resources-
                    chromedriver

8)DriverManager.class/ReadProperty.class is made singleton

9)Cucumber email API built to create HTML email body :http://selenium1234.pythonanywhere.com/getcucumbersummary


