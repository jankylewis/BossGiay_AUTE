import * as abstract from "../../../pageObjects/AbtractPage"
import * as sortSandal from "../../../pageObjects/CollectionPage"

//declare usable variables
let appendedURL
let sandalName

beforeEach(() => {
    cy.log("TEST CASE IS TRIGGERED!")
    appendedURL=""
    abstract.navigateToURL(appendedURL);
})
afterEach(() =>{
    cy.log("TEST CASE IS TERMINATED!")
})

describe.only('User navigates to home page, sort product by name and observe the listing returned of product',{}, () => {
    it.only('Test Case 001 - Sort Slides/Sandals Of MLB, verify that each Sneakers Name of the returned list of Slides/Sandals name contains MLB', () => {
        //assign value for variables
        sandalName= "mlb"
        //click Slide/Sandal navigation
        sortSandal.clickSandalNav()
        //click on MLB Sandal Name at selection range
        sortSandal.clickSortSandalByName(sandalName)
        // //assert all Sneakers Name whether it contains MLB
        sortSandal.assertSandalNameContainsClickedSandalName(sandalName)
    })//close Test Case 001
    it.only('Test Case 002 - Sort Slides/Sandals Of PUMA, verify that each Sneakers Name of the returned list of Slides/Sandals name contains PUMA', () => {
        //assign value for variables
        sandalName= "puma"
        //click Slide/Sandal navigation
        sortSandal.clickSandalNav()
        //click on PUMA Sandal Name at selection range
        sortSandal.clickSortSandalByName(sandalName)
        // //assert all Sneakers Name whether it contains PUMA
        sortSandal.assertSandalNameContainsClickedSandalName(sandalName)
    })//close Test Case 002
})