import * as homepage from "../../pageObjects/HomePage"  
import * as abstract from "../../pageObjects/AbtractPage"

let appendedURL
let searchKey
let expSearchTextLbl
let checkSpellingLbl
let noResultTxt
let noResultsFoundTxt
let didntHaveAnyResultsTxt

describe ('User navigates to home page, search product and observe the listing returned of product',{}, () => {
    beforeEach(() => {
        cy.log("TEST CASE IS TRIGGERED!")
        appendedURL=""
        abstract.navigateToURL(appendedURL);
    })
    it.only('Test case 001 - Search Product successfully when inputting an existing keyword and there is only one page of returned products name', function() {
        //redeclare the keyword to search
        searchKey= "adidas"
        //click on Search/Spy Glass icon to trigger the search product section
        homepage.clickSearchIcon()
        //enter search key to search textbox
        homepage.inputSearchText(searchKey)
        //press enter key to start searching product
        homepage.pressEnterKey()
        //verify the label text whether it contains the inputted searchkey
        homepage.assertLblContainsSearchKey(searchKey)
        //verify each product name whether it contains the inputted searchkey with being upper case
        homepage.assertProductNameContainsSearchKey(searchKey)
    })//close Test Case 001

    it.skip('Test case 002 - Search Product unsuccessfully when entering a non-existing keyword', function() {
        //declare the variables to run test
        searchKey= "synth"
        expSearchTextLbl= "Tìm kiếm"
        noResultsFoundTxt= "Không tìm thấy nội dung bạn yêu cầu"
        didntHaveAnyResultsTxt= "Không tìm thấy"
        checkSpellingLbl= "Vui lòng kiểm tra chính tả, sử dụng các từ tổng quát hơn và thử lại!"
        //click on Search/Spy Glass icon to trigger the search product section
        homepage.clickSearchIcon()
        //enter search key to search textbox
        homepage.inputSearchText(searchKey)
        //press enter key to start searching product
        homepage.pressEnterKey()
        //verify whether website returns "No Results Found" text
        homepage.assertNoResultsFoundTexts(searchKey, 
            expSearchTextLbl, 
            noResultsFoundTxt, 
            didntHaveAnyResultsTxt, 
            checkSpellingLbl)
    })//close Test Case 002
    it.skip('Test case 003 - Search Product unsuccessfully when entering a non-existing keyword. There are more than one page along with the presence of pagination', ()=> {
        //declare the variables to run test
        searchKey= "b"
        //click on Search/Spy Glass icon to trigger the search product section
        homepage.clickSearchIcon()
        //enter search key to search textbox
        homepage.inputSearchText(searchKey)
        //press enter key to start searching product
        homepage.pressEnterKey()
        //verify each Product name if it encompasses the inputted Search key that is entered by User
        homepage.assertProductNameContainsSearchKeyPages(searchKey)
    })//close Test Case 003
    afterEach(() =>{
        cy.log("TEST CASE IS TERMINATED!")
    })
})//close Test Suite

