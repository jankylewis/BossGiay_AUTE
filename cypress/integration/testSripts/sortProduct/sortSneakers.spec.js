import { before } from "lodash"
import * as abstract from "../../../pageObjects/AbtractPage"
import * as sortSnk from "../../../pageObjects/CollectionPage"

//declare usable variables
let appendedURL
let sneakerName
let sneakerPriceAttr

beforeEach(() => {
    cy.log("TEST CASE IS TRIGGERED!")
    appendedURL=""
    abstract.navigateToURL(appendedURL);
})
describe.only('User navigates to home page, sort product by name and observe the listing returned of product',{}, () => {
    it('Test Case 001 - Sort Sneakers Of DOMBA, verify that each Sneakers Name of the returned list of Sneakers name contains DOMBA', () => {
        //assign value for variables
        sneakerName= "domba"
        //click Sneaker navigation
        sortSnk.clickSneakerNav()
        //click on DOMBA Sneaker Name at selection range
        cy.wait(200)
        sortSnk.clickSortSneakerByName(sneakerName)
        //assert all Sneakers Name whether it contains DOMBA
        cy.wait(200)
        sortSnk.assertSneakerNameContainsClickedSneakerName(sneakerName)
    })//close Test Case 001
    it('Test Case 002 - Sort Sneakers Of CONVERSES, verify that each Sneakers Name of the returned list of Sneakers name contains CONVERSES', () => {
        //assign value for variables
        sneakerName= "cOnverse"
        //click Sneaker navigation
        sortSnk.clickSneakerNav()
        //click on CONVERSE Sneaker Name at selection range
        cy.wait(200)
        sortSnk.clickSortSneakerByName(sneakerName)
        //assert all Sneakers Name whether it contains CONVERSE
        cy.wait(200)
        sortSnk.assertSneakerNameContainsClickedSneakerName(sneakerName)
    })//close Test Case 002
    it('Test Case 003 - Sort Sneakers Of FILA, verify that each Sneakers Name of the returned list of Sneakers name contains FILA', function() {
        //assign value for variables
        sneakerName= "fila"
        //click Sneaker navigation
        sortSnk.clickSneakerNav()
        //click on FILA Sneaker Name at selection range
        cy.wait(200)
        sortSnk.clickSortSneakerByName(sneakerName)
        //assert all Sneakers Name whether it contains FILA
        cy.wait(200)
        sortSnk.assertSneakerNameContainsClickedSneakerName(sneakerName)
    })//close Test Case 003
    it('Test Case 004 - Sort Sneakers Of VANS, verify that each Sneakers Name of the returned list of Sneakers name contains VANS', function() {
        //assign value for variables
        sneakerName= "vans"
        //click Sneaker navigation
        sortSnk.clickSneakerNav()
        //click on VANS Sneaker Name at selection range
        sortSnk.clickSortSneakerByName(sneakerName)
        cy.wait(200)
        //assert all Sneakers Name whether it contains VANS
        cy.wait(200)
        sortSnk.assertSneakerNameContainsClickedSneakerName(sneakerName)
    })//close Test Case 004
    it('Test Case 005 - Sort Sneakers Of MLB, verify that each Sneakers Name of the returned list of Sneakers name contains MLB', function() {
        //assign value for variables
        sneakerName= "mlB"
        //click Sneaker navigation
        sortSnk.clickSneakerNav()
        //click on MLB Sneaker Name at selection range
        sortSnk.clickSortSneakerByName(sneakerName)
        cy.wait(200)
        //assert all Sneakers Name whether it contains MLB
        cy.wait(200)
        sortSnk.assertSneakerNameContainsClickedSneakerName(sneakerName)
    })//close Test Case 005
    it('Test Case 006 - Sort Sneakers Of JORDAN, verify that each Sneakers Name of the returned list of Sneakers name contains JORDAN', ()=> {
        //assign value for variables
        sneakerName= "JORdAN"
        //click Sneaker navigation
        sortSnk.clickSneakerNav()
        //click on JORDAN Sneaker Name at selection range
        sortSnk.clickSortSneakerByName(sneakerName)
        cy.wait(200)
        //assert all Sneakers Name whether it contains JORDAN
        cy.wait(200)
        sortSnk.assertSneakerNameContainsClickedSneakerName(sneakerName)
    })//close Test Case 006
    it('Test Case 007 - Sort Sneakers Of PUMA, verify that each Sneakers Name of the returned list of Sneakers name contains PUMA', ()=> {
        //assign value for variables
        sneakerName= "puma"
        //click Sneaker navigation
        sortSnk.clickSneakerNav()
        //click on PUMA Sneaker Name at selection range
        cy.wait(200)
        sortSnk.clickSortSneakerByName(sneakerName)
        //assert all Sneakers Name whether it contains PUMA
        cy.wait(200)
        sortSnk.assertSneakerNameContainsClickedSneakerName(sneakerName)
    })//close Test Case 007
    it('Test Case 008 - Sort Sneakers, verify that each Sneakers Price of the returned list of Sneakers Price is less than 1M', ()=> {
        //assign value for variables
        sneakerPriceAttr= "p1"
        //click Sneaker navigation
        sortSnk.clickSneakerNav()
        //click on Less than 1M Label of Sneaker Price at selection range
        cy.wait(200)
        sortSnk.clickSortSneakerByPrice(sneakerPriceAttr)
        //assert all Sneakers Price if they are less than 1M
        cy.wait(200)
        sortSnk.assertSneakerPriceLessThan1M()
    })//close Test Case 008
    it.skip('Test Case 009 - Sort Sneakers, verify that each Sneakers Price of the returned list of Sneakers Price is in the range 1M-2M', ()=> {
        //assign value for variables
        sneakerPriceAttr= "p2"
        //click Sneaker navigation
        sortSnk.clickSneakerNav()
        //click on From 1M To 2M Label of Sneaker Price at selection range
        sortSnk.clickSortSneakerByPrice(sneakerPriceAttr)
        //assert all Sneakers Price if they are in the range From 1M To 2M
        sortSnk.assertSneakerPriceIn1MTo2M()
    })//close Test Case 009
    it('Test Case 010 - Sort Sneakers, verify that each Sneakers Price of the returned list of Sneakers Price is in the range 2M-3M5', ()=> {
        //assign value for variables
        sneakerPriceAttr= "p3"
        //click Sneaker navigation
        sortSnk.clickSneakerNav()
        //click on From 2M To 3M5 Label of Sneaker Price at selection range
        cy.wait(200)
        sortSnk.clickSortSneakerByPrice(sneakerPriceAttr)
        //assert all Sneakers Price if they are in the range From 2M To 3M5
        cy.wait(200)
        sortSnk.assertSneakerPriceIn2MTo3M5()
    })//close Test Case 010
    it('Test Case 011 - Sort Sneakers, verify that each Sneakers Price of the returned list of Sneakers Price is in the range 3M-5M', ()=> {
        //assign value for variables
        sneakerPriceAttr= "p4"
        //click Sneaker navigation
        sortSnk.clickSneakerNav()
        //click on From 3M To 5M Label of Sneaker Price at selection range
        cy.wait(200)
        sortSnk.clickSortSneakerByPrice(sneakerPriceAttr)
        //assert all Sneakers Price if they are in the range From 3M To 5M
        cy.wait(200)
        sortSnk.assertSneakerPriceIn3MTo5M()
    })//close Test Case 011
    it('Test Case 012 - Sort Sneakers, verify that each Sneakers Price of the returned list of Sneakers Price is greater than 5M', ()=> {
        //assign value for variables
        sneakerPriceAttr= "p5"
        //click Sneaker navigation
        sortSnk.clickSneakerNav()
        //click on >5M Label of Sneaker Price at selection range
        cy.wait(200)
        sortSnk.clickSortSneakerByPrice(sneakerPriceAttr)
        //assert all Sneakers Price if they are greater than 5M
        cy.wait(200)
        sortSnk.assertSneakerPriceGreaterThan5M()
    })//close Test Case 012
})//close Test Suite
afterEach(() =>{
    cy.log("TEST CASE IS TERMINATED!")
})