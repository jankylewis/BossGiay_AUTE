//declare locators
const BTN_CONTINUE_PAYMENT_LOCATOR= '//form[@id= "form_next_step"]//child::button[@type= "submit"]'
const LBL_ERR_MSG_REQUIRED_FULLNAME_LOCATOR= '//div[@class= "inventory_location_data"]//following::div[contains(@class, "field-error")][1]//following-sibling::p'
const LBL_ERR_MSG_REQUIRED_EMAIL_LOCATOR= '//div[@class= "inventory_location_data"]//following::div[contains(@class, "field-error")][2]//following-sibling::p'
const LBL_ERR_MSG_REQUIRED_PHONE_LOCATOR= '//div[@class= "inventory_location_data"]//following::div[contains(@class, "field-error")][3]//following-sibling::p'
const LBL_ERR_MSG_REQUIRED_LOCATION_LOCATOR= '//div[@class= "inventory_location_data"]//following::div[contains(@class, "field-error")][4]//following-sibling::p'
const LBL_ERR_MSG_REQUIRED_PROVINCE_LOCATOR= '//div[@class= "inventory_location_data"]//following::div[contains(@class, "field-error")][5]//following-sibling::p'
const LBL_ERR_MSG_REQUIRED_DISTRICT_LOCATOR= '//div[@class= "inventory_location_data"]//following::div[contains(@class, "field-error")][6]//following-sibling::p'
const LBL_ERR_MSG_REQUIRED_WARD_LOCATOR= '//div[@class= "inventory_location_data"]//following::div[contains(@class, "field-error")][7]//following-sibling::p'
const LBL_PROVISIONAL_MONEY_LOCATOR= '//tr[contains(@class, "subtotal")]//td[@class= "total-line-price"]//span[@class= "order-summary-emphasis"]'
const LBL_TOTAL_MONEY_LOCATOR= '//span[@class= "payment-due-price"]'

//declare handy variables
let errMsgRequiredFullnameGetTxtStore
let errMsgRequiredEmailGetTxtStore
let errMsgRequiredPhoneGetTxtStore
let errMsgRequiredLocationGetTxtStore
let errMsgRequiredProvinceGetTxtStore
let errMsgRequiredDistrictGetTxtStore
let errMsgRequiredWardGetTxtStore
let proProviMoneyGetTxtStore
let proTotalMoneyGetTxtStore

//define function that is gonna be utilized
function productPriceGetTxtProcessing(productPriceGetTxt) {

    //define this function to sharpen the product price from raw to parse int 
    var productPriceGetTxtTrim= productPriceGetTxt.trim()
    if ((productPriceGetTxtTrim).length==8) {
        var productPriceGetTxtSubString= productPriceGetTxtTrim.substring(0, 7)
    }
    else if ((productPriceGetTxtTrim).length>=8 && (productPriceGetTxtTrim).length<=10) {
        var productPriceGetTxtSubString= productPriceGetTxtTrim.substring(0, 9)
    }
    else if ((productPriceGetTxtTrim).length>=10 && (productPriceGetTxtTrim).length<=22) {
        var productPriceGetTxtSubString= productPriceGetTxtTrim.substring(0, 7)
    }
    else if ((productPriceGetTxtTrim).length>=22 && (productPriceGetTxtTrim).length<=24) {
        var productPriceGetTxtSubString= productPriceGetTxtTrim.substring(0, 7)
    }
    if ((productPriceGetTxtTrim).length==18) {
        var productPriceGetTxtSubString= productPriceGetTxtTrim.substring(0, 9)
    }
    else if ((productPriceGetTxtTrim).length>=24 && (productPriceGetTxtTrim).length<=26) {
        var productPriceGetTxtSubString= productPriceGetTxtTrim.substring(0, 9)
    }
    if (productPriceGetTxtSubString.length==7) {
        var split= (productPriceGetTxtSubString.split(","))
        var productPriceGetTxtSplit= split[0]+ split[1]
        var productPriceGetTxtInt= parseInt(productPriceGetTxtSplit)
    }
    if (productPriceGetTxtSubString.length==9) {
        var split= (productPriceGetTxtSubString.split(","))
        var productPriceGetTxtSplit= split[0]+ split[1]+ split[2]
        var productPriceGetTxtInt= parseInt(productPriceGetTxtSplit)
    }
    return productPriceGetTxtInt
}

export function verifyErrMsgRequiredFullname(expErrMsgReqFullname) {
    cy.xpath(LBL_ERR_MSG_REQUIRED_FULLNAME_LOCATOR).then($errMsgRequiredFullnameGetTxt => {
        errMsgRequiredFullnameGetTxtStore= $errMsgRequiredFullnameGetTxt.text()
        if (expect(errMsgRequiredFullnameGetTxtStore).to.contain(expErrMsgReqFullname)){
            cy.log("!💥💥ERROR MESSAGE REQUIRED TO FILL FULLNAME FIELD: "+ errMsgRequiredFullnameGetTxtStore)
        }
        else {
            cy.end()
        }
    })
}

export function verifyErrMsgRequiredEmail(expErrMsgReqEmail) {
    cy.xpath(LBL_ERR_MSG_REQUIRED_EMAIL_LOCATOR).then($errMsgRequiredEmailGetTxt => {
        errMsgRequiredEmailGetTxtStore= $errMsgRequiredEmailGetTxt.text()
        if (expect(errMsgRequiredEmailGetTxtStore).to.contain(expErrMsgReqEmail)){
            cy.log("!💥💥ERROR MESSAGE REQUIRED TO FILL EMAIL FIELD: "+ errMsgRequiredEmailGetTxtStore)
        }
        else {
            cy.end()
        }
    })
}

export function verifyErrMsgRequiredPhone(expErrMsgReqPhone) {
    cy.xpath(LBL_ERR_MSG_REQUIRED_PHONE_LOCATOR).then($errMsgRequiredPhoneGetTxt => {
        errMsgRequiredPhoneGetTxtStore= $errMsgRequiredPhoneGetTxt.text()
        if (expect(errMsgRequiredPhoneGetTxtStore).to.contain(expErrMsgReqPhone)){
            cy.log("!💥💥ERROR MESSAGE REQUIRED TO FILL PHONE FIELD: "+ errMsgRequiredPhoneGetTxtStore)
        }
        else {
            cy.end()
        }
    })
}

export function verifyErrMsgRequiredLocation(expErrMsgReqLocation) {
    cy.xpath(LBL_ERR_MSG_REQUIRED_LOCATION_LOCATOR).then($errMsgRequiredLocationGetTxt => {
        errMsgRequiredLocationGetTxtStore= $errMsgRequiredLocationGetTxt.text()
        if (expect(errMsgRequiredLocationGetTxtStore).to.contain(expErrMsgReqLocation)){
            cy.log("!💥💥ERROR MESSAGE REQUIRED TO FILL LOCATION FIELD: "+ errMsgRequiredLocationGetTxtStore)
        }
        else {
            cy.end()
        }
    })
}

export function verifyErrMsgRequiredProvince(expErrMsgReqProvince) {
    cy.xpath(LBL_ERR_MSG_REQUIRED_PROVINCE_LOCATOR).then($errMsgRequiredProvinceGetTxt => {
        errMsgRequiredProvinceGetTxtStore= $errMsgRequiredProvinceGetTxt.text()
        if (expect(errMsgRequiredProvinceGetTxtStore).to.contain(expErrMsgReqProvince)){
            cy.log("!💥💥ERROR MESSAGE REQUIRED TO FILL PROVINCE FIELD: "+ errMsgRequiredProvinceGetTxtStore)
        }
        else {
            cy.end()
        }
    })
}

export function verifyErrMsgRequiredDistrict(expErrMsgReqDistrict) {
    cy.xpath(LBL_ERR_MSG_REQUIRED_DISTRICT_LOCATOR).then($errMsgRequiredDistrictGetTxt => {
        errMsgRequiredDistrictGetTxtStore= $errMsgRequiredDistrictGetTxt.text()
        if (expect(errMsgRequiredDistrictGetTxtStore).to.contain(expErrMsgReqDistrict)){
            cy.log("!💥💥ERROR MESSAGE REQUIRED TO FILL DISTRICT FIELD: "+ errMsgRequiredDistrictGetTxtStore)
        }
        else {
            cy.end()
        }
    })
}

export function verifyErrMsgRequiredWard(expErrMsgReqWard) {
    cy.xpath(LBL_ERR_MSG_REQUIRED_WARD_LOCATOR).then($errMsgRequiredWardGetTxt => {
        errMsgRequiredWardGetTxtStore= $errMsgRequiredWardGetTxt.text()
        if (expect(errMsgRequiredWardGetTxtStore).to.contain(expErrMsgReqWard)){
            cy.log("!💥💥ERROR MESSAGE REQUIRED TO FILL WARD FIELD: "+ errMsgRequiredWardGetTxtStore)
        }
        else {
            cy.end()
        }
    })
}

export function clickOnContinuePaymentBtn() {
    cy.xpath(BTN_CONTINUE_PAYMENT_LOCATOR).click()
}

export function verifyProductProvisionalMoneyIsMatched(proProviMoney) {
    cy.xpath(LBL_PROVISIONAL_MONEY_LOCATOR).then($proProviMoneyGetTxt => {
        proProviMoneyGetTxtStore= ($proProviMoneyGetTxt.text())
        if (expect(productPriceGetTxtProcessing(proProviMoneyGetTxtStore)).to.equal(parseInt(proProviMoney))) {
            cy.log("//--💥💥💥💥---- \nASSERTED SUCCESSFULLY THE "+ proProviMoneyGetTxtStore+ "! \n//--💥💥💥💥----")
        }
        else {
            cy.log("ASSERTED UNSUCCESSFULLY THE "+ proProviMoney+ "!")
            cy.end()
        }
    })    
}

export function verifyProductTotalMoneyIsMatched(proTotalMoney) {
    cy.xpath(LBL_TOTAL_MONEY_LOCATOR).then($proTotalMoneyGetTxt => {
        proTotalMoneyGetTxtStore= ($proTotalMoneyGetTxt.text())
        if (expect(productPriceGetTxtProcessing(proTotalMoneyGetTxtStore)).to.equal(parseInt(proTotalMoney))) {
            cy.log("//--💥💥💥💥---- \nASSERTED SUCCESSFULLY THE "+ proTotalMoneyGetTxtStore+ "! \n//--💥💥💥💥----")
        }
        else {
            cy.log("ASSERTED UNSUCCESSFULLY THE "+ proTotalMoney+ "!")
            cy.end()
        }
    })    
}

export function verifyProductProviMoneyIsMatchedFrom2Quantity(proMoney, proQuantity) {
    cy.xpath(LBL_PROVISIONAL_MONEY_LOCATOR).then($proProviMoneyGetTxt => {
        proProviMoneyGetTxtStore= ($proProviMoneyGetTxt.text())
        if (expect(productPriceGetTxtProcessing(proProviMoneyGetTxtStore)).to.equal((parseInt(proMoney))*proQuantity)) {
            cy.log("//--💥💥💥💥---- \nASSERTED SUCCESSFULLY THE "+ proProviMoneyGetTxtStore+ "! \n//--💥💥💥💥----")
        }
        else {
            cy.log("ASSERTED UNSUCCESSFULLY THE "+ proMoney*proQuantity+ "!")
            cy.end()
        }
    })    
}

export function verifyProductTotalMoneyIsMatchedFrom2Quantity(proMoney, proQuantity) {
    cy.xpath(LBL_TOTAL_MONEY_LOCATOR).then($proTotalMoneyGetTxt => {
        proTotalMoneyGetTxtStore= ($proTotalMoneyGetTxt.text())
        if (expect(productPriceGetTxtProcessing(proTotalMoneyGetTxtStore)).to.equal((parseInt(proMoney))*proQuantity)) {
            cy.log("//--💥💥💥💥---- \nASSERTED SUCCESSFULLY THE "+ proTotalMoneyGetTxtStore+ "! \n//--💥💥💥💥----")
        }
        else {
            cy.log("ASSERTED UNSUCCESSFULLY THE "+ proMoney*proQuantity+ "!")
            cy.end()
        }
    })    
}