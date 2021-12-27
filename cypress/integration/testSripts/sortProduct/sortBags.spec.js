import * as abstract from "../../../pageObjects/AbtractPage"
import * as sortBag from "../../../pageObjects/CollectionPage"

//declare usable variables
let appendedURL
let bagName
let bagAttr

beforeEach(() => {
    cy.log("TEST CASE IS TRIGGERED!")
    appendedURL=""
    abstract.navigateToURL(appendedURL);
})
afterEach(() =>{
    cy.log("TEST CASE IS TERMINATED!")
})
describe.only('User navigates to home page, sort product by name and observe the listing returned of product',{}, () => {
    it('Test Case 001 - Sort Bags Of MLB, verify that each Bags Name of the returned list of Bags name contains MLB', () => {
        //assign value for variables
        bagName= "mlb"
        //click Bag navigation
        sortBag.clickBagNav()
        //click on MLB Bag Name at selection range
        cy.wait(600)
        sortBag.clickSortBagByName(bagName)
        // //assert all Bags Name whether it contains MLB
        cy.wait(600)
        sortBag.assertBagNameContainsClickedBagName(bagName)
    })//close Test Case 001
    it('Test Case 002 - Sort Bags Of THRASHER, verify that each Bags Name of the returned list of Bags name contains THRASHER', () => {
        //assign value for variables
        bagName= "thrasher"
        //click Bag navigation
        sortBag.clickBagNav()
        //click on THRASHER Bag Name at selection range
        cy.wait(600)
        sortBag.clickSortBagByName(bagName)
        // //assert all Bags Name whether it contains THRASHER
        cy.wait(600)
        sortBag.assertBagNameContainsClickedBagName(bagName)
    })//close Test Case 002
    it('Test Case 003 - Sort Bags Of JORDAN, verify that each Bags Name of the returned list of Bags name contains JORDAN', () => {
        //assign value for variables
        bagName= "JORDAN"
        //click Bag navigation
        sortBag.clickBagNav()
        //click on JORDAN Bag Name at selection range
        cy.wait(600)
        sortBag.clickSortBagByName(bagName)
        // //assert all Bags Name whether it contains JORDAN
        cy.wait(600)
        sortBag.assertBagNameContainsClickedBagName(bagName)
    })//close Test Case 003
    it('Test Case 004 - Sort Bags Of ADIDAS, verify that each Bags Name of the returned list of Bags name contains ADIDAS', () => {
        //assign value for variables
        bagName= "adidas"
        //click Bag navigation
        sortBag.clickBagNav()
        //click on ADIDAS Bag Name at selection range
        cy.wait(600)
        sortBag.clickSortBagByName(bagName)
        // //assert all Bags Name whether it contains ADIDAS
        cy.wait(600)
        sortBag.assertBagNameContainsClickedBagName(bagName)
    })//close Test Case 004
    it('Test Case 005 - Sort Bags Of JANSPORT, verify that each Bags Name of the returned list of Bags name contains JANSPORT', () => {
        //assign value for variables
        bagName= "janSport"
        //click Bag navigation
        sortBag.clickBagNav()
        //click on JANSPORT Bag Name at selection range
        cy.wait(600)
        sortBag.clickSortBagByName(bagName)
        // //assert all Bags Name whether it contains JANSPORT
        cy.wait(600)
        sortBag.assertBagNameContainsClickedBagName(bagName)
    })//close Test Case 005
    it('Test Case 006 - Sort Bags, verify that each Bags Price of the returned list of Bags with price is less than 1M', () => {
        //assign value for variables
        bagAttr= "p1"
        //click Bag navigation
        sortBag.clickBagNav()
        //click on 1M Label of Bag Price at selection range
        cy.wait(600)
        sortBag.clickSortBagByPrice(bagAttr)
        // //assert all Bags Price whether it less than 1M for each Bag
        cy.wait(600)
        sortBag.assertBagPriceLessThan1M()
    })//close Test Case 006
    it('Test Case 007 - Sort Bags, verify that each Bags Price of the returned list of Bags with price is less than 2M and greater than 1M for each Bag', () => {
        //assign value for variables
        bagAttr= "p2"
        //click Bag navigation
        sortBag.clickBagNav()
        //click on 1M-2M Label of Bag Price at selection range
        cy.wait(600)
        sortBag.clickSortBagByPrice(bagAttr)
        // //assert all Bags Price whether it less than 2M and greater than 1M for each Bag
        cy.wait(600)
        sortBag.assertBagPriceIn1MTo2M()
    })//close Test Case 007
    it('Test Case 008 - Sort Bags, verify that each Bags Price of the returned list of Bags with price is less than 3M5 and greater than 2M for each Bag', () => {
        //assign value for variables
        bagAttr= "p3"
        //click Bag navigation
        sortBag.clickBagNav()
        //click on 2M-3M5 Label of Bag Price at selection range
        cy.wait(600)
        sortBag.clickSortBagByPrice(bagAttr)
        // //assert all Bags Price whether it less than 3M5 and greater than 2M for each Bag
        cy.wait(600)
        sortBag.assertBagPriceIn2MTo3M5()
    })//close Test Case 008
})