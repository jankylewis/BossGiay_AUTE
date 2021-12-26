import * as abstract from "../../../pageObjects/AbtractPage"
import * as sort from "../../../pageObjects/CollectionPage"

let appendedURL
let sortCategory
//start Test Suite
describe ('User navigates to home page, sort product and observe the listing returned of product',{}, () => {
    beforeEach(() => {
        cy.log("TEST CASE IS TRIGGERED!")
        appendedURL=""
        abstract.navigateToURL(appendedURL);
    })
    it.skip('Test Case 001 - Verify Product price is sort ascendingly when selecting "Giá: Tăng dần" sort category', () => {
        //assign value for variables
        sortCategory= "Giá: Tăng dần"
        //click on Sneaker navigation
        sort.clickSneakerNav()
        //select Sort category by ascending price
        sort.clickSortCategory(sortCategory)
        //sort Product price ascendingly
        sort.assertProductPriceAsc()
    })//close Test Case 001
    afterEach(() =>{
        cy.log("TEST CASE IS TERMINATED!")
    })
})//close Test Suite

