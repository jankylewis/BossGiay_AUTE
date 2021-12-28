//applying POM on performing TS
//declare locators
const BTN_ADD_PRODUCT_TO_CART_LOCATOR= '//button[@id= "add-to-cart" and @type= "button"]'
const LBL_PRODUCT_NAME_VIEW_PRODUCT_PAGE_LOCATOR= '//div[@class= "product-title"]//h1'
const LBL_PRODUCT_NAME_VIEW_PRODUCT_POPUP_LOCATOR= '//table[@id= "cart-view"]//a[@class= "pro-title-view"]'
const BTN_LINK_TO_CART_LOCATOR= '//a[contains(@class, "linktocart")]'
const LBL_PRODUCT_NAME_MY_CART_PAGE_LOCATOR= '//div[@class= "item-info"]//h3'
const LBL_QUANTITY_LOCATOR= '//input[contains(@class, "quantity") and @data-price]'
const LBL_INTO_MONEY= '//div[@class= "price"]//span[2]'
const LBL_TOTAL_MONEY= '//div[@class= "summary-total"]//span'
const BTN_BUY_NOW_LOCATOR= '//div[@class= "row-flex"]//button[2][@id= "buy-now"]'
const BTN_CHECKOUT_POPUP_LOCATOR= '//a[contains(@class, "linktocheckout")]'

//declare handy variables
let proNameGetTxtViewProPageStore
let proNameGetTxtViewProPopupStore
let proNameGetTxtViewMyCartStore
let proQuantityGetTxtStore
let proIntoMoneyGetTxtStore
let proTotalMoneyGetTxtStore

export function clickOnCheckOutPopupBtn() {
    cy.xpath(BTN_CHECKOUT_POPUP_LOCATOR).click()
}

export function clickOnBuyNowBtn() {
    cy.xpath(BTN_BUY_NOW_LOCATOR).click()
}

export function clickOnAddProductToCartBtn() {
    cy.xpath(BTN_ADD_PRODUCT_TO_CART_LOCATOR).click()
}

export function clickOnLinkToCartBtn() {
    cy.xpath(BTN_LINK_TO_CART_LOCATOR).click()
}

export function verifyProductNameIsMatched(proName) {
    cy.xpath(LBL_PRODUCT_NAME_VIEW_PRODUCT_PAGE_LOCATOR).then($proNameGetTxtViewProPage => {
        proNameGetTxtViewProPageStore= $proNameGetTxtViewProPage.text()
        if (expect(proNameGetTxtViewProPageStore).to.contain(proName)) {
            cy.log("//--💥💥💥💥---- \nASSERTED SUCCESSFULLY THE "+ proName+ "! \n//--💥💥💥💥----")
        }
        else {
            cy.log("ASSERTED UNSUCCESSFULLY THE "+ proName+ "!")
            cy.end()
        }
    })    
}

export function verifyProductNamePopupIsMatched(proName) {
    cy.xpath(LBL_PRODUCT_NAME_VIEW_PRODUCT_POPUP_LOCATOR).then($proNameGetTxtViewProPopup => {
        proNameGetTxtViewProPopupStore= $proNameGetTxtViewProPopup.text()
        if (expect(proNameGetTxtViewProPopupStore).to.contain(proName)) {
            cy.log("//--💥💥💥💥---- \nASSERTED SUCCESSFULLY THE "+ proName+ "! \n//--💥💥💥💥----")
        }
        else {
            cy.log("ASSERTED UNSUCCESSFULLY THE "+ proName+ "!")
            cy.end()
        }
    })    
}

export function verifyProductNameMyCartIsMatched(proName) {
    cy.xpath(LBL_PRODUCT_NAME_MY_CART_PAGE_LOCATOR).then($proNameGetTxtMyCart => {
        proNameGetTxtViewMyCartStore= $proNameGetTxtMyCart.text()
        if (expect(proNameGetTxtViewMyCartStore).to.contain(proName)) {
            cy.log("//--💥💥💥💥---- \nASSERTED SUCCESSFULLY THE "+ proName+ "! \n//--💥💥💥💥----")
        }
        else {
            cy.log("ASSERTED UNSUCCESSFULLY THE "+ proName+ "!")
            cy.end()
        }
    })    
}

export function verifyProductQuantityMyCartIsMatched(proQuantity) {
    cy.xpath(LBL_QUANTITY_LOCATOR).then($proQuantityGetTxtMyCart => {
        proQuantityGetTxtStore= ($proQuantityGetTxtMyCart.val())
        if (expect(proQuantityGetTxtStore).to.contain(proQuantity)) {
            cy.log("//--💥💥💥💥---- \nASSERTED SUCCESSFULLY THE "+ proQuantity+ "! \n//--💥💥💥💥----")
        }
        else {
            cy.log("ASSERTED UNSUCCESSFULLY THE "+ proQuantity+ "!")
            cy.end()
        }
    })    
}

export function verifyProductIntoMoneyIsMatched(proIntoMoney) {
    cy.xpath(LBL_INTO_MONEY).then($proIntoMoneyGetTxtMyCart => {
        proIntoMoneyGetTxtStore= ($proIntoMoneyGetTxtMyCart.text())
        if (expect(productPriceGetTxtProcessing(proIntoMoneyGetTxtStore)).to.equal(parseInt(proIntoMoney))) {
            cy.log("//--💥💥💥💥---- \nASSERTED SUCCESSFULLY THE "+ proIntoMoneyGetTxtStore+ "! \n//--💥💥💥💥----")
        }
        else {
            cy.log("ASSERTED UNSUCCESSFULLY THE "+ proIntoMoney+ "!")
            cy.end()
        }
    })    
}

export function verifyProductTotalMoneyIsMatched(proTotalMoney) {
    cy.xpath(LBL_TOTAL_MONEY).then($proTotalMoneyGetTxtMyCart => {
        proTotalMoneyGetTxtStore= ($proTotalMoneyGetTxtMyCart.text())
        if (expect(productPriceGetTxtProcessing(proTotalMoneyGetTxtStore)).to.equal(parseInt(proTotalMoney))) {
            cy.log("//--💥💥💥💥---- \nASSERTED SUCCESSFULLY THE "+ proTotalMoneyGetTxtStore+ "! \n//--💥💥💥💥----")
        }
        else {
            cy.log("ASSERTED UNSUCCESSFULLY THE "+ proTotalMoney+ "!")
            cy.end()
        }
    })    
}

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