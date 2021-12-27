import * as abstract from "../../pageObjects/AbtractPage"
import * as sortBag from "../../pageObjects/CollectionPage"
import * as addToCart from "../../pageObjects/HomePage"
import * as viewProduct from "../../pageObjects/ViewProductPage"
import * as myCart from "../../pageObjects/MyCartPage"

//declare usable variables
let appendedURL
let speProductName
let speProductQuantity
let speProductIntoMoney
let speProductTotalMoney

beforeEach(() => {
    cy.log("TEST CASE IS TRIGGERED!")
    appendedURL=""
    abstract.navigateToURL(appendedURL);
})
afterEach(() =>{
    cy.log("TEST CASE IS TERMINATED!")
})

describe("Add Product to Cart - User redirets to Home page, then click on a specific product on the Home page => click on Add to cart button",{}, function() {
    it('Test Case 001 - Verify that Cart displays correctly when adding 1 Product to Cart with quantity is 1', function() {
        //assign value for variables
        speProductName= "JORDAN 1 MID SMOKE GREY NOIR GS".toUpperCase()
        speProductQuantity= "1"
        speProductIntoMoney= 7290000
        speProductTotalMoney= speProductIntoMoney
        //click on a desired Product Name inputted from keyboard
        cy.wait(10)
        addToCart.clickOnSpecificProductName(speProductName)
        //assert the Product Name whether it matches the desired Product Name (spec name)
        cy.wait(10)
        viewProduct.verifyProductNameIsMatched(speProductName)
        //click on Add to Cart button
        cy.wait(100)
        viewProduct.clickOnAddProductToCartBtn()
        //assert the Product Name whether it matches the desired Product Name (spec name)
        cy.wait(10)
        viewProduct.verifyProductNamePopupIsMatched(speProductName)
        //click on Link to Cart button
        cy.wait(100)
        viewProduct.clickOnLinkToCartBtn()
        cy.wait(10)
        //assert the Product Name whether it matches the desired Product Name (spec name)
        myCart.verifyProductNameMyCartIsMatched(speProductName)
        //assert the quantity of Product that is added to cart (in this case is '1')
        myCart.verifyProductQuantityMyCartIsMatched(speProductQuantity)
        //assert the "into money" of the added Product 
        myCart.verifyProductIntoMoneyIsMatched(parseInt(speProductIntoMoney))
        //assert the "total money" of the added Product 
        myCart.verifyProductTotalMoneyIsMatched(parseInt(speProductTotalMoney))
    })//close Test Case 001
    it.only('Test Case 002 - Verify that Cart displays correctly when adding 1 Product to Cart with quantity is more than 1', function() {
        //assign value for variables
        speProductName= "JORDAN 1 MID SMOKE GREY NOIR GS".toUpperCase()
        speProductQuantity= "15"
        speProductIntoMoney= 7290000
        // speProductTotalMoney= speProductIntoMoney
        //click on a desired Product Name inputted from keyboard
        cy.wait(10)
        addToCart.clickOnSpecificProductName(speProductName)
        //assert the Product Name whether it matches the desired Product Name (spec name)
        cy.wait(10)
        viewProduct.verifyProductNameIsMatched(speProductName)
        //click on Add to Cart button
        cy.wait(100)
        viewProduct.clickOnAddProductToCartBtn()
        //assert the Product Name whether it matches the desired Product Name (spec name)
        cy.wait(10)
        viewProduct.verifyProductNamePopupIsMatched(speProductName)
        //click on Link to Cart button
        cy.wait(100)
        viewProduct.clickOnLinkToCartBtn()
        //click to add quantity of Product
        cy.wait(10)
        myCart.clickOnQuantityPlusButton(speProductQuantity)
        //assert the quantity of Product that is added to cart (in this case is '1')
        cy.wait(120)
        myCart.verifyProductQuantityMyCartIsMatched(speProductQuantity)
        //assert the "into money" of the added Product 
        cy.wait(120)
        myCart.verifyProductIntoMoneyIsMatchedFrom2Quantity(speProductIntoMoney, speProductQuantity)
        //assert the "total money" of the added Product 
        myCart.verifyProductTotalMoneyIsMatchedFrom2Quantity(speProductIntoMoney, speProductQuantity)
    })//close Test Case 002
})