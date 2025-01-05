Feature: Login Functionality for openCart E-commerce website

  As a user of the openCart Website
  I want to be able to login in my account
  So that I can access my account-related features and manage my orders

  Background:
    Given I am on the openCart Login Page

    @smoke
  Scenario: Successful Login with Login Credentials
    Given I have entered a valid username and password
    When I click on Login Button
    Then I should be logged in successfully

      @Sanity
  Scenario Outline: Unsuccessful Login with invalid or empty Credentials
    Given I have entered invalid <username> and <password>
    When I click on Login Button
    Then I should see an error message indicating "<error_message>"

    Examples:
      | username          | password        | error_message                                         |
      | invalid@gmail.com | invalidPassword | Warning: No match for E-mail Address and/or Password. |
      | abccc             | validPassword   | Warning: No match for E-mail Address and/or Password. |
      | valid@gmail.com   | abccc           | Warning: No match for E-mail Address and/or Password. |

    @smoke
   Scenario: Navigating to the forgotten password page
     When I click on the "Forgotten Password" link
     Then I should be redirected to the password reset page

  @Sanity
  Scenario: Navigating to the forgotten password page
    When I click on the "Forgotten Password" link
    Then I should be redirected to the password reset page