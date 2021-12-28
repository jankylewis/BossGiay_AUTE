import * as abstract from "../../pageObjects/AbtractPage"
import * as collect from "../../pageObjects/CollectionPage"
import * as add from "../../pageObjects/HomePage"
import * as viewProduct from "../../pageObjects/ViewProductPage"
import * as myCart from "../../pageObjects/MyCartPage"
import * as pay from "../../pageObjects/CheckOutPage"

//initialize variables
let appendedURL
let speProductName
let expErrMsgReqFullname
let expErrMsgReqEmail
let expErrMsgReqPhone
let expErrMsgReqLocation
let expErrMsgReqProvince
let expErrMsgReqDistrict
let expErrMsgReqWard
let expProProviMoney
let expProTotalMoney
let speProductQuantity
let expProMoney

beforeEach(() => {
    cy.log("TEST CASE IS TRIGGERED!")
    appendedURL=""
    abstract.navigateToURL(appendedURL)
    collect.clickSneakerNav()
    cy.log("CLICKED ON SNEAKER NAVIGATION")
})
afterEach(() =>{
    cy.log("TEST CASE IS TERMINATED!")
})

describe("Product Payment - User selects Product then navigates to Check Out Page to conduct payment",{}, function() {
    it('Test Case 001 - Verify that user checks out unsuccessfully with 1 Product (Quantity: 1) when providing lack of payment information', ()=> {
        //assign value for variables
        speProductName= "JORDAN 1 MID SMOKE GREY NOIR GS".toUpperCase()
        expErrMsgReqFullname= "Vui lòng nhập họ tên"
        expErrMsgReqEmail= "Địa chỉ email không được trống"
        expErrMsgReqLocation= "Địa chỉ không được trống"
        expErrMsgReqProvince= "Vui lòng chọn tỉnh thành"
        expErrMsgReqDistrict= "Vui lòng chọn quận huyện"
        expErrMsgReqWard= "Vui lòng chọn phường xã"
        expErrMsgReqPhone= "Số điện thoại không được trống"
        expProProviMoney= "7290000"
        expProTotalMoney= "7290000"

        //click on a desired Product Name inputted from keyboard
        cy.wait(10)
        add.clickOnSpecificProductName(speProductName)

        //click on Buy Now button facilitate navigating to Check Out Page
        cy.wait(25)
        viewProduct.clickOnBuyNowBtn()

        //click on Continue Payment button
        cy.wait(10)
        pay.clickOnContinuePaymentBtn()

        //assert Error Messages after are impeccable clicking on Continue Payment button without any filling actions
        cy.wait(10)
        pay.verifyErrMsgRequiredFullname(expErrMsgReqFullname)
        pay.verifyErrMsgRequiredEmail(expErrMsgReqEmail)
        pay.verifyErrMsgRequiredPhone(expErrMsgReqPhone)
        pay.verifyErrMsgRequiredLocation(expErrMsgReqLocation)
        pay.verifyErrMsgRequiredProvince(expErrMsgReqProvince)
        pay.verifyErrMsgRequiredDistrict(expErrMsgReqDistrict)
        pay.verifyErrMsgRequiredWard(expErrMsgReqWard)
        pay.verifyProductProvisionalMoneyIsMatched(parseInt(expProProviMoney))
        pay.verifyProductTotalMoneyIsMatched(parseInt(expProTotalMoney))
    })//close Test Case 001
    it.only('Test Case 002 - Verify that user checks out unsuccessfully with 1 Product (Quantity: >1) when providing lack of payment information', ()=> {
        //assign value for variables
        speProductName= "MLB CHUNKY LA".toUpperCase()
        expErrMsgReqFullname= "Vui lòng nhập họ tên"
        expErrMsgReqEmail= "Địa chỉ email không được trống"
        expErrMsgReqLocation= "Địa chỉ không được trống"
        expErrMsgReqProvince= "Vui lòng chọn tỉnh thành"
        expErrMsgReqDistrict= "Vui lòng chọn quận huyện"
        expErrMsgReqWard= "Vui lòng chọn phường xã"
        expErrMsgReqPhone= "Số điện thoại không được trống"
        expProMoney= "1790000"
        speProductQuantity= "3"

        //click on a desired Product Name inputted from keyboard
        cy.wait(10)
        add.clickOnSpecificProductName(speProductName)

        //click on Buy Now button facilitate navigating to Check Out Page
        cy.wait(120)
        viewProduct.clickOnAddProductToCartBtn()

        cy.wait(650)
        viewProduct.clickOnLinkToCartBtn()
        myCart.clickOnQuantityPlusButton(speProductQuantity)

        cy.wait(10)
        myCart.clickOnCheckOutBtn()

        //click on Continue Payment button
        cy.wait(10)
        pay.clickOnContinuePaymentBtn()

        // //assert Error Messages after are impeccable clicking on Continue Payment button without any filling actions
        // cy.wait(10)
        pay.verifyErrMsgRequiredFullname(expErrMsgReqFullname)
        pay.verifyErrMsgRequiredEmail(expErrMsgReqEmail)
        pay.verifyErrMsgRequiredPhone(expErrMsgReqPhone)
        pay.verifyErrMsgRequiredLocation(expErrMsgReqLocation)
        pay.verifyErrMsgRequiredProvince(expErrMsgReqProvince)
        pay.verifyErrMsgRequiredDistrict(expErrMsgReqDistrict)
        pay.verifyErrMsgRequiredWard(expErrMsgReqWard)
        // pay.verifyProductProvisionalMoneyIsMatched(parseInt(expProProviMoney))
        // pay.verifyProductTotalMoneyIsMatched(parseInt(expProTotalMoney))
        pay.verifyProductProviMoneyIsMatchedFrom2Quantity(expProMoney, speProductQuantity)
        pay.verifyProductTotalMoneyIsMatchedFrom2Quantity(expProMoney, speProductQuantity)
    })//close Test Case 002
})