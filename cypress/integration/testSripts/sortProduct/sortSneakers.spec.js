import { before } from "lodash"
import * as abstract from "../../../pageObjects/AbtractPage"
import * as sortSnk from "../../../pageObjects/CollectionPage"

//declare usable variables
let appendedURL
let sneakerName

beforeEach(() => {
    cy.log("TEST CASE IS TRIGGERED!")
    appendedURL=""
    abstract.navigateToURL(appendedURL);
})
describe.only('User navigates to home page, sort product by name and observe the listing returned of product',{}, () => {
    it.only('Test Case 001 - Sort Sneakers Of DOMBA, verify that each Sneakers Name of the returned list of Sneakers name contains DOMBA', () => {
        //assign value for variables
        sneakerName= "domba"
        //click Sneaker navigation
        sortSnk.clickSneakerNav()
        //click on DOMBA Sneaker Name at selection range
        sortSnk.clickSortSneakerByName(sneakerName)
        //assert all Sneakers Name whether it contains DOMBA
        sortSnk.assertSneakerNameContainsClickedSneakerName(sneakerName)
    })//close Test Case 001
    it.only('Test Case 002 - Sort Sneakers Of CONVERSES, verify that each Sneakers Name of the returned list of Sneakers name contains CONVERSES', () => {
        //assign value for variables
        sneakerName= "cOnverse"
        //click Sneaker navigation
        sortSnk.clickSneakerNav()
        //click on CONVERSE Sneaker Name at selection range
        sortSnk.clickSortSneakerByName(sneakerName)
        //assert all Sneakers Name whether it contains CONVERSE
        sortSnk.assertSneakerNameContainsClickedSneakerName(sneakerName)
    })//close Test Case 002
    it.only('Test Case 003 - Sort Sneakers Of FILA, verify that each Sneakers Name of the returned list of Sneakers name contains FILA', function() {
        //assign value for variables
        sneakerName= "fila"
        //click Sneaker navigation
        sortSnk.clickSneakerNav()
        //click on FILA Sneaker Name at selection range
        sortSnk.clickSortSneakerByName(sneakerName)
        //assert all Sneakers Name whether it contains FILA
        sortSnk.assertSneakerNameContainsClickedSneakerName(sneakerName)
    })//close Test Case 003
    it.only('Test Case 004 - Sort Sneakers Of VANS, verify that each Sneakers Name of the returned list of Sneakers name contains VANS', function() {
        //assign value for variables
        sneakerName= "vans"
        //click Sneaker navigation
        sortSnk.clickSneakerNav()
        //click on VANS Sneaker Name at selection range
        sortSnk.clickSortSneakerByName(sneakerName)
        //assert all Sneakers Name whether it contains VANS
        sortSnk.assertSneakerNameContainsClickedSneakerName(sneakerName)
    })//close Test Case 004
    it.only('Test Case 005 - Sort Sneakers Of MLB, verify that each Sneakers Name of the returned list of Sneakers name contains MLB', function() {
        //assign value for variables
        sneakerName= "mlB"
        //click Sneaker navigation
        sortSnk.clickSneakerNav()
        //click on MLB Sneaker Name at selection range
        sortSnk.clickSortSneakerByName(sneakerName)
        //assert all Sneakers Name whether it contains MLB
        sortSnk.assertSneakerNameContainsClickedSneakerName(sneakerName)
    })//close Test Case 005
    it.only('Test Case 006 - Sort Sneakers Of JORDAN, verify that each Sneakers Name of the returned list of Sneakers name contains JORDAN', ()=> {
        //assign value for variables
        sneakerName= "JORdAN"
        //click Sneaker navigation
        sortSnk.clickSneakerNav()
        //click on JORDAN Sneaker Name at selection range
        sortSnk.clickSortSneakerByName(sneakerName)
        //assert all Sneakers Name whether it contains JORDAN
        sortSnk.assertSneakerNameContainsClickedSneakerName(sneakerName)
    })//close Test Case 006
    it.only('Test Case 007 - Sort Sneakers Of PUMA, verify that each Sneakers Name of the returned list of Sneakers name contains PUMA', ()=> {
        //assign value for variables
        sneakerName= "puma"
        //click Sneaker navigation
        sortSnk.clickSneakerNav()
        //click on PUMA Sneaker Name at selection range
        sortSnk.clickSortSneakerByName(sneakerName)
        //assert all Sneakers Name whether it contains PUMA
        sortSnk.assertSneakerNameContainsClickedSneakerName(sneakerName)
    })//close Test Case 007
})//close Test Suite
afterEach(() =>{
    cy.log("TEST CASE IS TERMINATED!")
})