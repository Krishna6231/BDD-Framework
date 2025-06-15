Feature: Login and Add Product to Cart
    Scenario: User Logs in and adds product to cart
      Given I am on the SauceDemo login page
      When I login with username "standard_user" and password "secret_sauce"
      And I add the product "Sauce Labs Backpack" to the cart
      And I go to cart page
      Then I should see "Sauce Labs Backpack" in the cart