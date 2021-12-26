import * as abstract from "../../../pageObjects/AbtractPage"
import * as sortBag from "../../../pageObjects/CollectionPage"

//declare usable variables
let appendedURL
let bagName

beforeEach(() => {
    cy.log("TEST CASE IS TRIGGERED!")
    appendedURL=""
    abstract.navigateToURL(appendedURL);
})
afterEach(() =>{
    cy.log("TEST CASE IS TERMINATED!")
})