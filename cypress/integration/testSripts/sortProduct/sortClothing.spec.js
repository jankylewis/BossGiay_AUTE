import * as abstract from "../../../pageObjects/AbtractPage"
import * as sortClo from "../../../pageObjects/CollectionPage"

//declare usable variables
let appendedURL
let clothingName
let clothingArr

beforeEach(() => {
    cy.log("TEST CASE IS TRIGGERED!")
    appendedURL=""
    abstract.navigateToURL(appendedURL);
})
afterEach(() =>{
    cy.log("TEST CASE IS TERMINATED!")
})
describe('User navigates to home page, sort product by name and observe the listing returned of product',{}, () => {
    it('Test Case 001 - Sort Clothing Of SUPREME, verify that each Clothing Name of the returned list of Clothing name contains SUPREME', () => {
        //assign value for variables
        clothingName= "supreMe"
        //click Clothing navigation
        sortClo.clickClothingNav()
        //click on SUPREME Clothing Name at selection range
        cy.wait(150)
        sortClo.clickSortClothingByName(clothingName)
        //assert all Clothings Name whether it contains SUPREME
        cy.wait(150)
        sortClo.assertClothingNameContainsClickedClothingName(clothingName)
    })//close Test Case 001
    it('Test Case 002 - Sort Clothing Of UNIQLO, verify that each Clothing Name of the returned list of Clothing name contains UNIQLO', () => {
        //assign value for variables
        clothingName= "UNIQLO"
        //click Clothing navigation
        sortClo.clickClothingNav()
        //click on UNIQLO Clothing Name at selection range
        cy.wait(150)
        sortClo.clickSortClothingByName(clothingName)
        //assert all Clothings Name whether it contains UNIQLO
        cy.wait(150)
        sortClo.assertClothingNameContainsClickedClothingName(clothingName)
    })//close Test Case 002
    it('Test Case 003 - Sort Clothing Of MLB, verify that each Clothing Name of the returned list of Clothing name contains MLB', () => {
        //assign value for variables
        clothingName= "MLB"
        //click Clothing navigation
        sortClo.clickClothingNav()
        //click on MLB Clothing Name at selection range
        cy.wait(150)
        sortClo.clickSortClothingByName(clothingName)
        //assert all Clothings Name whether it contains MLB
        cy.wait(150)
        sortClo.assertClothingNameContainsClickedClothingName(clothingName)
    })//close Test Case 003
    it('Test Case 004 - Sort Clothing, verify that each Clothing Price of the returned list of Clothing with price is less than 1M', () => {
        //assign value for variables
        clothingArr= "p1"
        //click Clothing navigation
        sortClo.clickClothingNav()
        //click on 1M Label of Clothing Price at selection range
        cy.wait(150)
        sortClo.clickSortClothingByPrice(clothingArr)
        //assert all Clothings Price whether it is less than 1M
        cy.wait(150)
        sortClo.assertClothingPriceLessThan1M()
    })//close Test Case 004
    it('Test Case 005 - Sort Clothing, verify that each Clothing Price of the returned list of Clothing with price is in range of 1M-2M', () => {
        //assign value for variables
        clothingArr= "p2"
        //click Clothing navigation
        sortClo.clickClothingNav()
        //click on 1M-2M Label of Clothing Price at selection range
        cy.wait(150)
        sortClo.clickSortClothingByPrice(clothingArr)
        //assert all Clothings Price whether it is in range of 1M-2M
        cy.wait(150)
        sortClo.assertClothingPriceIn1MTo2M()
    })//close Test Case 005
    it.only('Test Case 006 - Sort Clothing, verify that each Clothing Price of the returned list of Clothing with price is in range of 2M-3M5', () => {
        //assign value for variables
        clothingArr= "p3"
        //click Clothing navigation
        sortClo.clickClothingNav()
        //click on 2M-3M5 Label of Clothing Price at selection range
        cy.wait(150)
        sortClo.clickSortClothingByPrice(clothingArr)
        //assert all Clothings Price whether it is in range of 2M-3M5
        cy.wait(150)
        sortClo.assertClothingPriceIn2MTo3M5()
    })//close Test Case 005
})