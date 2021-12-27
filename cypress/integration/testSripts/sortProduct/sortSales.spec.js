import * as abstract from "../../../pageObjects/AbtractPage"
import * as sortSale from "../../../pageObjects/CollectionPage"

//declare usable variables
let appendedURL
let saleName

beforeEach(() => {
    cy.log("TEST CASE IS TRIGGERED!")
    appendedURL=""
    abstract.navigateToURL(appendedURL);
})
afterEach(() =>{
    cy.log("TEST CASE IS TERMINATED!")
})

describe.only('User navigates to home page, sort product by name and observe the listing returned of product',{}, () => {
    it('Test Case 001 - Sort Sales Of DOMBA, verify that each Sales Name of the returned list of Sales name contains DOMBA', () => {
        //assign value for variables
        saleName= "DOMBA"
        //click Sales navigation
        sortSale.clickSaleNav()
        //click on DOMBA Sales Name at selection range
        sortSale.clickSortSaleByName(saleName)
        //assert all Sales Name whether it contains DOMBA
        sortSale.assertSaleNameContainsClickedSaleName(saleName)
    })//close Test Case 001
    it('Test Case 002 - Sort Sales Of NIKE, verify that each Sales Name of the returned list of Sales name contains NIKE', () => {
        //assign value for variables
        saleName= "nike"
        //click Sales navigation
        sortSale.clickSaleNav()
        //click on NIKE Sales Name at selection range
        sortSale.clickSortSaleByName(saleName)
        //assert all Sales Name whether it contains NIKE
        sortSale.assertSaleNameContainsClickedSaleName(saleName)
    })//close Test Case 002
})