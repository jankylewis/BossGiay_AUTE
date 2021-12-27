import * as abstract from "../../../pageObjects/AbtractPage"
import * as sortSandal from "../../../pageObjects/CollectionPage"

//declare usable variables
let appendedURL
let sandalName
let sandalAttr

beforeEach(() => {
    cy.log("TEST CASE IS TRIGGERED!")
    appendedURL=""
    abstract.navigateToURL(appendedURL);
})
afterEach(() =>{
    cy.log("TEST CASE IS TERMINATED!")
})

describe.only('User navigates to home page, sort product by name and observe the listing returned of product',{}, () => {
    it('Test Case 001 - Sort Slides/Sandals Of MLB, verify that each Slides/Sandals Name of the returned list of Slides/Sandals name contains MLB', () => {
        //assign value for variables
        sandalName= "mlb"
        //click Slide/Sandal navigation
        sortSandal.clickSandalNav()
        //click on MLB Sandal Name at selection range
        cy.wait(100)
        sortSandal.clickSortSandalByName(sandalName)
        // //assert all Sneakers Name whether it contains MLB
        cy.wait(100)
        sortSandal.assertSandalNameContainsClickedSandalName(sandalName)
    })//close Test Case 001
    it('Test Case 002 - Sort Slides/Sandals Of PUMA, verify that each Slides/Sandals Name of the returned list of Slides/Sandals name contains PUMA', () => {
        //assign value for variables
        sandalName= "puma"
        //click Slide/Sandal navigation
        sortSandal.clickSandalNav()
        //click on PUMA Sandal Name at selection range
        cy.wait(100)
        sortSandal.clickSortSandalByName(sandalName)
        // //assert all Sneakers Name whether it contains PUMA
        cy.wait(100)
        sortSandal.assertSandalNameContainsClickedSandalName(sandalName)
    })//close Test Case 002
    it('Test Case 003 - Sort Slides/Sandals, verify that each Slides/Sandals Price of the returned list of Slides/Sandals with price is less than 1M', () => {
        //assign value for variables
        sandalAttr= "p1"
        //click Slide/Sandal navigation
        sortSandal.clickSandalNav()
        //click on <1M Label of Sandal Price at selection range
        cy.wait(100)
        sortSandal.clickSortSandalByPrice(sandalAttr)
        // //assert all Sandal Price whether it is less than 1M for each price
        cy.wait(100)
        sortSandal.assertSandalPriceLessThan1M()
    })//close Test Case 003
    it('Test Case 004 - Sort Slides/Sandals, verify that each Slides/Sandals Price of the returned list of Slides/Sandals with price in range of 1M-2M', () => {
        //assign value for variables
        sandalAttr= "p2"
        //click Slide/Sandal navigation
        sortSandal.clickSandalNav()
        //click on 1M-2M Label of Sandal Price at selection range
        cy.wait(100)
        sortSandal.clickSortSandalByPrice(sandalAttr)
        // //assert all Sandal Price whether it is less than 2M and greater than 1M for each price
        cy.wait(100)
        sortSandal.assertSandalPriceIn1MTo2M()
    })//close Test Case 004
})