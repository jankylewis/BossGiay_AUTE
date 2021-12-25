import * as homepage from "../../pageObjects/HomePage"  
import * as abstract from "../../pageObjects/AbtractPage";

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
    it('Test case 001 - Search Product successfully when inputting an existing keyword', function() {
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

    it('Test case 002 - Search Product unsuccessfully when entering a non-existing keyword', function() {
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
    })
    //close Test Case 002
    afterEach(() =>{
        cy.log("TEST CASE IS TERMINATED!")
    })
})//close Test Suite

