//declare locators
const BTN_ADD_PRODUCT_TO_CART_LOCATOR= '//button[@id= "add-to-cart" and @type= "button"]'
const LBL_PRODUCT_NAME_VIEW_PRODUCT_PAGE_LOCATOR= '//div[@class= "product-title"]//h1'
const LBL_PRODUCT_NAME_VIEW_PRODUCT_POPUP_LOCATOR= '//table[@id= "cart-view"]//a[@class= "pro-title-view"]'
const BTN_LINK_TO_CART_LOCATOR= '//a[contains(@class, "linktocart")]'
const LBL_PRODUCT_NAME_MY_CART_PAGE_LOCATOR= '//div[@class= "item-info"]//h3'
const LBL_QUANTITY_LOCATOR= '//input[contains(@class, "quantity") and @data-price]'
const LBL_INTO_MONEY= '//div[@class= "price"]//span[2]'
const LBL_TOTAL_MONEY= '//div[@class= "summary-total"]//span'
const BTN_QUANTITY_PLUS_LOCATOR= '//button[contains(@class, "qtyplus")]'
const BTN_QUANTITY_MINUS_LOCATOR= '//button[contains(@class, "qtyminus")]'
const BTN_CHECKOUT_LOCATOR= '//a[@class= "checkout-btn"]'

//declare handy variables
let proNameGetTxtViewProPageStore
let proNameGetTxtViewProPopupStore
let proNameGetTxtViewMyCartStore
let proQuantityGetTxtStore
let proIntoMoneyGetTxtStore
let proTotalMoneyGetTxtStore

export function clickOnQuantityPlusButton(times) {
    for (var clickTimes= 0; clickTimes< times-1; clickTimes+=1) {
        cy.xpath(BTN_QUANTITY_PLUS_LOCATOR).click()
    }
}

export function clickOnCheckOutBtn() {
    cy.xpath(BTN_CHECKOUT_LOCATOR).click()
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

export function verifyProductIntoMoneyIsMatchedFrom2Quantity(proIntoMoney, proQuantity) {
    cy.xpath(LBL_INTO_MONEY).then($proIntoMoneyGetTxtMyCart => {
        proIntoMoneyGetTxtStore= ($proIntoMoneyGetTxtMyCart.text())
        if (expect(productPriceGetTxtProcessing(proIntoMoneyGetTxtStore)).to.equal((parseInt(proIntoMoney))*proQuantity)) {
            cy.log("//--💥💥💥💥---- \nASSERTED SUCCESSFULLY THE "+ proIntoMoneyGetTxtStore+ "! \n//--💥💥💥💥----")
        }
        else {
            cy.log("ASSERTED UNSUCCESSFULLY THE "+ proIntoMoney*proQuantity+ "!")
            cy.end()
        }
    })    
}

export function verifyProductTotalMoneyIsMatchedFrom2Quantity(proTotalMoney, proQuantity) {
    cy.xpath(LBL_TOTAL_MONEY).then($proTotalMoneyGetTxtMyCart => {
        proTotalMoneyGetTxtStore= ($proTotalMoneyGetTxtMyCart.text())
        if (expect(productPriceGetTxtProcessing(proTotalMoneyGetTxtStore)).to.equal((parseInt(proTotalMoney))*proQuantity)) {
            cy.log("//--💥💥💥💥---- \nASSERTED SUCCESSFULLY THE "+ proTotalMoneyGetTxtStore+ "! \n//--💥💥💥💥----")
        }
        else {
            cy.log("ASSERTED UNSUCCESSFULLY THE "+ proTotalMoney*proQuantity+ "!")
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
        var productPriceGetTxtSubString= productPriceGetTxtTrim.substring(0, 10)
    }
    else if ((productPriceGetTxtTrim).length>=22 && (productPriceGetTxtTrim).length<=24) {
        var productPriceGetTxtSubString= productPriceGetTxtTrim.substring(0, 7)
    }
    if ((productPriceGetTxtTrim).length==18) {
        var productPriceGetTxtSubString= productPriceGetTxtTrim.substring(0, 9)
    }
    if ((productPriceGetTxtTrim).length==12) {
        var productPriceGetTxtSubString= productPriceGetTxtTrim.substring(0, 11)
    }
    else if ((productPriceGetTxtTrim).length>=24 && (productPriceGetTxtTrim).length<=26) {
        var productPriceGetTxtSubString= productPriceGetTxtTrim.substring(0, 9)
    }
    if (productPriceGetTxtSubString.length==7) {
        var split= (productPriceGetTxtSubString.split(","))
        var productPriceGetTxtSplit= split[0]+ split[1]
        var productPriceGetTxtInt= parseInt(productPriceGetTxtSplit)
    }
    if (productPriceGetTxtSubString.length==9 || productPriceGetTxtSubString.length==10
        || productPriceGetTxtSubString.length==11) {
        var split= (productPriceGetTxtSubString.split(","))
        var productPriceGetTxtSplit= split[0]+ split[1]+ split[2]
        var productPriceGetTxtInt= parseInt(productPriceGetTxtSplit)
    }
    
    return productPriceGetTxtInt
}




