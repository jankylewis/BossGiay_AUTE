//applying POM on testing

//declare a myriad of locators
const SEL_SORT_CATEGORY_LOCATOR= '//select'
const NAV_SNEAKER_LOCATOR= '//nav//preceding-sibling::a[@title= "Sneaker"]'
const LBL_PRODUCT_PRICE_LOCATOR= '//p[contains(@class, "pro-price")]'
const BTN_NUMBERING_LOCATOR= '//div[@id= "pagination"]//a'
const BTN_NEXT_LOCATOR= '//a[@class= "next"]'
const LBL_PRODUCT_NAME_TEXT_LOCATOR= '//h3[@class= "pro-name"]//a'
const NAV_SANDAL_LOCATOR= '//nav//preceding-sibling::a[contains(@title, "Sandal")]'
const NAV_BAG_LOCATOR= '//nav//preceding-sibling::a[contains(@title, "Bag")]'
const NAV_CLOTHING_LOCATOR= '//nav//preceding-sibling::a[contains(@title, "Clothing")]'
const NAV_SALE_LOCATOR= '//nav//preceding-sibling::a[contains(@title, "Sales")]'

//initialize variables
let pageNumber
let pricePartition

var productPriceGetTxtStore
var productPriceLessThan1MGetTxtStore
var productPriceLessThan1MGetTxt
var productPriceIn1MTo2MGetTxtStore
var productPriceIn1MTo2MGetTxt
var productPriceIn2MTo3M5GetTxtStore
var productPriceIn2MTo3M5GetTxt
var productPriceIn3MTo5MGetTxtStore
var productPriceIn3MTo5MGetTxt
var productPriceGreaterThan5MGetTxtStore
var productPriceGreaterThan5MGetTxt
var sneakersNameGetTxtStore

//generate objects
export function clickSortCategory(category) {
    cy.xpath(SEL_SORT_CATEGORY_LOCATOR).select(category)
}

export function clickSneakerNav() {
    cy.xpath(NAV_SNEAKER_LOCATOR).click()
}

export function clickSandalNav() {
    cy.xpath(NAV_SANDAL_LOCATOR).click()
}

export function clickBagNav() {
    cy.xpath(NAV_BAG_LOCATOR).click()
}

export function clickClothingNav() {
    cy.xpath(NAV_CLOTHING_LOCATOR).click()
}

export function clickSaleNav() {
    cy.xpath(NAV_SALE_LOCATOR).click()
}

export function clickSortSaleByName(saleName) {
    cy.xpath('//label[contains(@for, "data-brand") and contains(text(), "'+ (
        saleName.toUpperCase())+ '")]').click()
}

export function clickSortBagByName(bagName) {
    cy.xpath('//label[contains(@for, "data-brand") and contains(text(), "'+ (
        bagName.toUpperCase())+ '")]').click()
}

export function clickSortSneakerByName(snkName) {
    cy.xpath('//label[contains(@for, "data-brand") and contains(text(), "'+ (
        snkName.toUpperCase())+ '")]').click()
}

export function clickSortSandalByName(sandalName) {
    cy.xpath('//label[contains(@for, "data-brand") and contains(text(), "'+ (
        sandalName.toUpperCase())+ '")]').click()
}

export function clickSortClothingByName(clothingName) {
    cy.xpath('//label[contains(@for, "data-brand") and contains(text(), "'+ (
        clothingName.toUpperCase())+ '")]').click()
}

export function clickSortSneakerByPrice(snkAttr) {
    cy.xpath('//label[@for= "'+ snkAttr+ '"]').click()
}

export function clickSortSandalByPrice(sandalAttr) {
    cy.xpath('//label[@for= "'+ sandalAttr+ '"]').click()
}

export function clickSortBagByPrice(bagAttr) {
    cy.xpath('//label[@for= "'+ bagAttr+ '"]').click()
}

export function clickSortClothingByPrice(clothingAttr) {
    cy.xpath('//label[@for= "'+ clothingAttr+ '"]').click()
}

export function assertSneakerPriceLessThan1M() {
    cy.xpath(LBL_PRODUCT_PRICE_LOCATOR).then(productPriceListingPages => {
        const productPriceListingCounterPages = Cypress.$(productPriceListingPages).length;
        expect(productPriceListingPages).to.have.length(productPriceListingCounterPages)
        for (var productIndex= 0; productIndex< productPriceListingCounterPages; productIndex++) {
            cy.log("PRICE OF PRODUCT NUMBER "+ (productIndex+1)+ ":\n")
            cy.xpath('//div['+ (((productIndex)+1))+ ']/div/div[2]/div/div/p[contains(@class, "pro-price")]').then(($productPriceLessThan1MGetTxt)=> {
                productPriceLessThan1MGetTxtStore= productPriceGetTxtProcessing($productPriceLessThan1MGetTxt.text())
                cy.log("PRODUCT PRICE AF PROCESSING: "+ productPriceLessThan1MGetTxtStore)
                cy.wait(2)
                if (expect(productPriceLessThan1MGetTxtStore).to.be.lessThan(1000000)) {
                    cy.log("PRODUCT PRICE IS LESS THAN 1,000,000VND")
                }
                else {
                    cy.log("TEST CASE IS FAILED!")
                    cy.end
                }
            })
        }
    })
}

export function assertSandalPriceLessThan1M() {
    cy.xpath(LBL_PRODUCT_PRICE_LOCATOR).then(productPriceListing => {
        const productPriceListingCounter = Cypress.$(productPriceListing).length;
        expect(productPriceListing).to.have.length(productPriceListingCounter)
        for (var productIndex= 0; productIndex< productPriceListingCounter; productIndex++) {
            cy.log("PRICE OF PRODUCT NUMBER "+ (productIndex+1)+ ":\n")
            cy.xpath('//div['+ (((productIndex)+1))+ ']/div/div[2]/div/div/p[contains(@class, "pro-price")]').then(($productPriceLessThan1MGetTxt)=> {
                productPriceLessThan1MGetTxtStore= productPriceGetTxtProcessing($productPriceLessThan1MGetTxt.text())
                cy.log("PRODUCT PRICE AF PROCESSING: "+ productPriceLessThan1MGetTxtStore)
                cy.wait(2)
                if (expect(productPriceLessThan1MGetTxtStore).to.be.lessThan(1000000)) {
                    cy.log("PRODUCT PRICE IS LESS THAN 1,000,000VND")
                }
                else {
                    cy.log("TEST CASE IS FAILED!")
                    cy.end
                }
            })
        }
    })
}

export function assertBagPriceLessThan1M() {
    cy.xpath(LBL_PRODUCT_PRICE_LOCATOR).then(productPriceListing => {
        const productPriceListingCounter = Cypress.$(productPriceListing).length;
        expect(productPriceListing).to.have.length(productPriceListingCounter)
        for (var productIndex= 0; productIndex< productPriceListingCounter; productIndex++) {
            cy.log("PRICE OF PRODUCT NUMBER "+ (productIndex+1)+ ":\n")
            cy.xpath('//div['+ (((productIndex)+1))+ ']/div/div[2]/div/div/p[contains(@class, "pro-price")]').then(($productPriceLessThan1MGetTxt)=> {
                productPriceLessThan1MGetTxtStore= productPriceGetTxtProcessing($productPriceLessThan1MGetTxt.text())
                cy.log("PRODUCT PRICE AF PROCESSING: "+ productPriceLessThan1MGetTxtStore)
                cy.wait(2)
                if (expect(productPriceLessThan1MGetTxtStore).to.be.lessThan(1000000)) {
                    cy.log("PRODUCT PRICE IS LESS THAN 1,000,000VND")
                }
                else {
                    cy.log("TEST CASE IS FAILED!")
                    cy.end
                }
            })
        }
    })
}

export function assertClothingPriceLessThan1M() {
    cy.xpath(LBL_PRODUCT_PRICE_LOCATOR).then(productPriceListing => {
        const productPriceListingCounter = Cypress.$(productPriceListing).length;
        expect(productPriceListing).to.have.length(productPriceListingCounter)
        for (var productIndex= 0; productIndex< productPriceListingCounter; productIndex++) {
            cy.log("PRICE OF PRODUCT NUMBER "+ (productIndex+1)+ ":\n")
            cy.xpath('//div['+ (((productIndex)+1))+ ']/div/div[2]/div/div/p[contains(@class, "pro-price")]').then(($productPriceLessThan1MGetTxt)=> {
                productPriceLessThan1MGetTxtStore= productPriceGetTxtProcessing($productPriceLessThan1MGetTxt.text())
                cy.log("PRODUCT PRICE AF PROCESSING: "+ productPriceLessThan1MGetTxtStore)
                cy.wait(2)
                if (expect(productPriceLessThan1MGetTxtStore).to.be.lessThan(1000000)) {
                    cy.log("PRODUCT PRICE IS LESS THAN 1,000,000VND")
                }
                else {
                    cy.log("TEST CASE IS FAILED!")
                    cy.end
                }
            })
        }
    })
}

export function assertClothingPriceIn1MTo2M() {
    cy.xpath(LBL_PRODUCT_PRICE_LOCATOR).then(productPriceListing => {
        const productPriceListingCounter = Cypress.$(productPriceListing).length;
        expect(productPriceListing).to.have.length(productPriceListingCounter)
        for (var productIndex= 0; productIndex< productPriceListingCounter; productIndex++) {
            cy.log("PRICE OF PRODUCT NUMBER "+ (productIndex+1)+ ":\n")
            cy.xpath('//div['+ (((productIndex)+1))+ ']/div/div[2]/div/div/p[contains(@class, "pro-price")]').then(($productPriceIn1MTo2MGetTxt)=> {
                productPriceIn1MTo2MGetTxtStore= productPriceGetTxtProcessing($productPriceIn1MTo2MGetTxt.text())
                cy.log("PRODUCT PRICE AF PROCESSING: "+ productPriceIn1MTo2MGetTxtStore)
                cy.wait(2)
                if (expect(productPriceIn1MTo2MGetTxtStore).to.be.lessThan(2000000)
                    && expect(productPriceIn1MTo2MGetTxtStore).to.be.greaterThan(1000000)) {
                    cy.log("PRODUCT PRICE IS LESS THAN 3,500,000VND AND GREATER THAN 2,000,000VND")
                }
                else {
                    cy.log("TEST CASE IS FAILED!")
                    cy.end
                }
            })
        }
    })
}

export function assertClothingPriceIn2MTo3M5() {
    cy.xpath(LBL_PRODUCT_PRICE_LOCATOR).then(productPriceListing => {
        const productPriceListingCounter = Cypress.$(productPriceListing).length;
        expect(productPriceListing).to.have.length(productPriceListingCounter)
        for (var productIndex= 0; productIndex< productPriceListingCounter; productIndex++) {
            cy.log("PRICE OF PRODUCT NUMBER "+ (productIndex+1)+ ":\n")
            cy.xpath('//div['+ (((productIndex)+1))+ ']/div/div[2]/div/div/p[contains(@class, "pro-price")]').then(($productPriceIn2MTo3M5GetTxt)=> {
                productPriceIn2MTo3M5GetTxtStore= productPriceGetTxtProcessing($productPriceIn2MTo3M5GetTxt.text())
                cy.log("PRODUCT PRICE AF PROCESSING: "+ productPriceIn2MTo3M5GetTxtStore)
                cy.wait(2)
                if (expect(productPriceIn2MTo3M5GetTxtStore).to.be.lessThan(3500000)
                    && expect(productPriceIn2MTo3M5GetTxtStore).to.be.greaterThan(2000000)) {
                    cy.log("PRODUCT PRICE IS LESS THAN 2,000,000VND AND GREATER THAN 1,000,000VND")
                }
                else {
                    cy.log("TEST CASE IS FAILED!")
                    cy.end
                }
            })
        }
    })
}

export function assertSandalPriceIn1MTo2M() {
    cy.xpath(LBL_PRODUCT_PRICE_LOCATOR).then(productPriceListing => {
        const productPriceListingCounter = Cypress.$(productPriceListing).length;
        expect(productPriceListing).to.have.length(productPriceListingCounter)
        for (var productIndex= 0; productIndex< productPriceListingCounter; productIndex++) {
            cy.log("PRICE OF PRODUCT NUMBER "+ (productIndex+1)+ ":\n")
            cy.xpath('//div['+ (((productIndex)+1))+ ']/div/div[2]/div/div/p[contains(@class, "pro-price")]').then(($productPriceIn1MTo2MGetTxt)=> {
                productPriceIn1MTo2MGetTxtStore= productPriceGetTxtProcessing($productPriceIn1MTo2MGetTxt.text())
                cy.log("PRODUCT PRICE AF PROCESSING: "+ productPriceIn1MTo2MGetTxtStore)
                cy.wait(2)
                if (expect(productPriceIn1MTo2MGetTxtStore).to.be.lessThan(2000000)
                    && expect(productPriceIn1MTo2MGetTxtStore).to.be.greaterThan(1000000)) {
                    cy.log("PRODUCT PRICE IS LESS THAN 2,000,000VND AND GREATER THAN 1,000,000VND")
                }
                else {
                    cy.log("TEST CASE IS FAILED!")
                    cy.end
                }
            })
        }
    })
}

export function assertBagPriceIn1MTo2M() {
    cy.xpath(LBL_PRODUCT_PRICE_LOCATOR).then(productPriceListing => {
        const productPriceListingCounter = Cypress.$(productPriceListing).length;
        expect(productPriceListing).to.have.length(productPriceListingCounter)
        for (var productIndex= 0; productIndex< productPriceListingCounter; productIndex++) {
            cy.log("PRICE OF PRODUCT NUMBER "+ (productIndex+1)+ ":\n")
            cy.xpath('//div['+ (((productIndex)+1))+ ']/div/div[2]/div/div/p[contains(@class, "pro-price")]').then(($productPriceIn1MTo2MGetTxt)=> {
                productPriceIn1MTo2MGetTxtStore= productPriceGetTxtProcessing($productPriceIn1MTo2MGetTxt.text())
                cy.log("PRODUCT PRICE AF PROCESSING: "+ productPriceIn1MTo2MGetTxtStore)
                cy.wait(2)
                if (expect(productPriceIn1MTo2MGetTxtStore).to.be.lessThan(2000000)
                    && expect(productPriceIn1MTo2MGetTxtStore).to.be.greaterThan(1000000)) {
                    cy.log("PRODUCT PRICE IS LESS THAN 3,500,000VND AND GREATER THAN 2,000,000VND")
                }
                else {
                    cy.log("TEST CASE IS FAILED!")
                    cy.end
                }
            })
        }
    })
}

export function assertBagPriceIn2MTo3M5() {
    cy.xpath(LBL_PRODUCT_PRICE_LOCATOR).then(productPriceListing => {
        const productPriceListingCounter = Cypress.$(productPriceListing).length;
        expect(productPriceListing).to.have.length(productPriceListingCounter)
        for (var productIndex= 0; productIndex< productPriceListingCounter; productIndex++) {
            cy.log("PRICE OF PRODUCT NUMBER "+ (productIndex+1)+ ":\n")
            cy.xpath('//div['+ (((productIndex)+1))+ ']/div/div[2]/div/div/p[contains(@class, "pro-price")]').then(($productPriceIn2MTo3M5GetTxt)=> {
                productPriceIn2MTo3M5GetTxtStore= productPriceGetTxtProcessing($productPriceIn2MTo3M5GetTxt.text())
                cy.log("PRODUCT PRICE AF PROCESSING: "+ productPriceIn2MTo3M5GetTxtStore)
                cy.wait(2)
                if (expect(productPriceIn2MTo3M5GetTxtStore).to.be.lessThan(3500000)
                    && expect(productPriceIn2MTo3M5GetTxtStore).to.be.greaterThan(2000000)) {
                    cy.log("PRODUCT PRICE IS LESS THAN 2,000,000VND AND GREATER THAN 1,000,000VND")
                }
                else {
                    cy.log("TEST CASE IS FAILED!")
                    cy.end
                }
            })
        }
    })
}

export function assertSneakerPriceIn2MTo3M5() {
    cy.xpath(LBL_PRODUCT_PRICE_LOCATOR).then(productPriceListing1 => {
        const productPriceListingCounter = Cypress.$(productPriceListing1).length;
        expect(productPriceListing1).to.have.length(productPriceListingCounter)
        for (var productIndex= 0; productIndex< productPriceListingCounter; productIndex++) {
            cy.log("PRICE OF PRODUCT NUMBER "+ (productIndex+1)+ ":\n")
            cy.xpath('//div['+ (((productIndex)+1))+ ']/div/div[2]/div/div/p[contains(@class, "pro-price")]').then(($productPriceIn2MTo3M5GetTxt)=> {
                productPriceIn2MTo3M5GetTxtStore= productPriceGetTxtProcessing($productPriceIn2MTo3M5GetTxt.text())
                cy.log("PRODUCT PRICE AF PROCESSING: "+ productPriceIn2MTo3M5GetTxtStore)
                // cy.log("LENGTHHHHH: "+ ($productPriceIn2MTo3M5GetTxt.text()).trim().length)
                //asertion
                cy.wait(10)
                expect(productPriceIn2MTo3M5GetTxtStore).to.be.greaterThan(2000000)
                expect(productPriceIn2MTo3M5GetTxtStore).to.be.lessThan(3500000)
            })
        }
    })
}

export function assertSneakerPriceIn3MTo5M() {
    cy.xpath(LBL_PRODUCT_PRICE_LOCATOR).then(productPriceListing => {
        const productPriceListingCounter = Cypress.$(productPriceListing).length;
        expect(productPriceListing).to.have.length(productPriceListingCounter)
        for (var productIndex= 0; productIndex< productPriceListingCounter; productIndex++) {
            cy.log("PRICE OF PRODUCT NUMBER "+ (productIndex+1)+ ":\n")
            cy.xpath('//div['+ (((productIndex)+1))+ ']/div/div[2]/div/div/p[contains(@class, "pro-price")]').then(($productPriceIn3MTo5MGetTxt)=> {
                productPriceIn3MTo5MGetTxtStore= productPriceGetTxtProcessing($productPriceIn3MTo5MGetTxt.text())
                cy.log("PRODUCT PRICE AF PROCESSING: "+ productPriceIn3MTo5MGetTxtStore)
                //asertion
                cy.wait(10)
                expect(productPriceIn3MTo5MGetTxtStore).to.be.greaterThan(3000000)
                expect(productPriceIn3MTo5MGetTxtStore).to.be.lessThan(5000000)
            })
        }
    })
}

export function assertSneakerPriceGreaterThan5M() {
    cy.xpath(LBL_PRODUCT_PRICE_LOCATOR).then(productPriceListing => {
        const productPriceListingCounter = Cypress.$(productPriceListing).length;
        expect(productPriceListing).to.have.length(productPriceListingCounter)
        for (var productIndex= 0; productIndex< productPriceListingCounter; productIndex++) {
            cy.log("PRICE OF PRODUCT NUMBER "+ (productIndex+1)+ ":\n")
            cy.xpath('//div['+ (((productIndex)+1))+ ']/div/div[2]/div/div/p[contains(@class, "pro-price")]').then(($productPriceGreaterThan5MGetTxt)=> {
                productPriceGreaterThan5MGetTxtStore= productPriceGetTxtProcessing($productPriceGreaterThan5MGetTxt.text())
                cy.log("PRODUCT PRICE AF PROCESSING: "+ productPriceGreaterThan5MGetTxtStore)
                //asertion
                cy.wait(10)
                expect(productPriceGreaterThan5MGetTxtStore).to.be.greaterThan(5000000)
            })
        }
    })
}

export function assertSneakerPriceIn1MTo2M() {
    pageNumber=1
    cy.xpath(BTN_NUMBERING_LOCATOR).then(numbering => {
        const numberingCounter = Cypress.$(numbering).length;
        cy.log("Total pages: "+ numberingCounter)
        //
        for (var pageIndex= 0; pageIndex< numberingCounter; pageIndex++) {
            cy.log("User is on the page "+ (pageIndex+1))
            //
            cy.xpath(LBL_PRODUCT_PRICE_LOCATOR).then(productPriceListingPages => {
                const productPriceListingCounterPages = Cypress.$(productPriceListingPages).length;
                // expect(productPriceListingPages).to.have.length(productPriceListingCounterPages)
                cy.log("Total product found is "+ (productPriceListingCounterPages)+ " located at page "+ pageNumber)
                pageNumber+=1
                // for (var productIndex= 0; productIndex< productPriceListingCounterPages; productIndex++) {
                //     cy.log("PRICE OF PRODUCT NUMBER "+ (productIndex+1)+ ":\n")
                //     cy.xpath('//div['+ (((productIndex)+1))+ ']/div/div[2]/div/div/p[contains(@class, "pro-price")]').then(($productPriceIn1MTo2MGetTxt)=> {
                //         productPriceIn1MTo2MGetTxt= productPriceGetTxtProcessing($productPriceIn1MTo2MGetTxt.text())
                //         cy.log("PRODUCT PRICE AF PROCESSING: "+ productPriceIn1MTo2MGetTxt)
                //         if (productPriceIn1MTo2MGetTxt< 2000000) {
                //             cy.log("PRODUCT PRICE IS IN THE RANGE: 1,000,000VND - 2,000,000VND")
                //         }
                //         else {
                //             cy.log("TEST CASE IS FAILED!")
                //             cy.end
                //         }
                //     })
                // }
            })
            if (pageIndex<numberingCounter-1) {
                //
                cy.xpath(BTN_NEXT_LOCATOR).click()
            }
        }
    })
}



export function assertSaleNameContainsClickedSaleName(saleName) {
    //
    cy.xpath(LBL_PRODUCT_NAME_TEXT_LOCATOR).then(productNameListing => {
        const productNameListingCounter = Cypress.$(productNameListing).length;
        expect(productNameListing).to.have.length(productNameListingCounter)
        cy.log("Total product found: "+ (productNameListingCounter))
        //browse each Sale Name 
        for (var productIndex= 0; productIndex< productNameListingCounter; productIndex++) {
            cy.log("NAME OF PRODUCT NUMBER "+ (productIndex+1)+ ":\n")
            const LBL_SALE_NAME_LOCATOR= '//div['+ (productIndex+1)+ ']/div/div[2]/div/h3/a[@href]'
            cy.xpath(LBL_SALE_NAME_LOCATOR).invoke('text').then((productNameGetTxt)=> {
                cy.log(productNameGetTxt)
                if (expect(productNameGetTxt).to.contain(saleName.toUpperCase())) {
                    cy.log("ASSERTED SUCCESSFULLY SNEAKER NAME!")  
                }
                else {
                    cy.log("TEST CASE IS FAILED!")
                    cy.end()
                }
            })
        }
    })
}

export function assertClothingNameContainsClickedClothingName(clothingName) {
    //
    cy.xpath(LBL_PRODUCT_NAME_TEXT_LOCATOR).then(productNameListing => {
        const productNameListingCounter = Cypress.$(productNameListing).length;
        expect(productNameListing).to.have.length(productNameListingCounter)
        cy.log("Total product found: "+ (productNameListingCounter))
        //browse each Clothing Name 
        for (var productIndex= 0; productIndex< productNameListingCounter; productIndex++) {
            cy.log("NAME OF PRODUCT NUMBER "+ (productIndex+1)+ ":\n")
            const LBL_CLOTHING_NAME_LOCATOR= '//div['+ (productIndex+1)+ ']/div/div[2]/div/h3/a[@href]'
            cy.xpath(LBL_CLOTHING_NAME_LOCATOR).invoke('text').then((productNameGetTxt)=> {
                cy.log(productNameGetTxt)
                if (expect(productNameGetTxt).to.contain(clothingName.toUpperCase())) {
                    cy.log("ASSERTED SUCCESSFULLY SNEAKER NAME!")  
                }
                else {
                    cy.log("TEST CASE IS FAILED!")
                    cy.end()
                }
            })
        }
    })
}

export function assertSneakerNameContainsClickedSneakerName(snkName) {
    //
    cy.xpath(LBL_PRODUCT_NAME_TEXT_LOCATOR).then(productNameListing => {
        const productNameListingCounter = Cypress.$(productNameListing).length;
        expect(productNameListing).to.have.length(productNameListingCounter)
        cy.log("Total product found: "+ (productNameListingCounter))
        //browse each Sneaker Name 
        for (var productIndex= 0; productIndex< productNameListingCounter; productIndex++) {
            cy.log("NAME OF PRODUCT NUMBER "+ (productIndex+1)+ ":\n")
            const LBL_SNEAKER_NAME_LOCATOR= '//div['+ (productIndex+1)+ ']/div/div[2]/div/h3/a[@href]'
            cy.xpath(LBL_SNEAKER_NAME_LOCATOR).invoke('text').then((productNameGetTxt)=> {
                cy.log(productNameGetTxt)
                if (expect(productNameGetTxt).to.contain(snkName.toUpperCase())) {
                    cy.log("ASSERTED SUCCESSFULLY SNEAKER NAME!")  
                }
                else {
                    cy.log("TEST CASE IS FAILED!")
                    cy.end()
                }
            })
        }
    })
}

function productPriceGetTxtProcessing(productPriceGetTxt) {
    //define this function to sharpen the product price from raw to parse int
    //lengths: 8, 19, 22, 24, 26
    //8: [490,000₫]
    //10: [1,190,000₫]
    //22: [750,000₫ 890,000₫]
    //24: [890,000₫ 1,590,000₫]
    //26: [1,190,000₫ 1,790,000₫]
    //
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

export function assertSandalNameContainsClickedSandalName(sandalName) {
    //
    cy.xpath(LBL_PRODUCT_NAME_TEXT_LOCATOR).then(productNameListing => {
        const productNameListingCounter = Cypress.$(productNameListing).length;
        // expect(productNameListing).to.have.length(productNameListingCounter)
        cy.log("Total product found: "+ (productNameListingCounter))
        //browse each Sandal Name 
        for (var productIndex= 0; productIndex< productNameListingCounter; productIndex++) {
            cy.log("NAME OF PRODUCT NUMBER "+ (productIndex+1)+ ":\n")
            const LBL_SANDAL_NAME_LOCATOR= '//div['+ (productIndex+1)+ ']/div/div[2]/div/h3/a[@href]'
            cy.xpath(LBL_SANDAL_NAME_LOCATOR).invoke('text').then((productNameGetTxt)=> {
                cy.log(productNameGetTxt)
                if (expect(productNameGetTxt).to.contain(sandalName.toUpperCase())) {
                    cy.log("ASSERTED SUCCESSFULLY SLIDE/SANDAL NAME!")  
                }
                else {
                    cy.log("TEST CASE IS FAILED!")
                    cy.end()
                }
            })
        }
    })
}

export function assertBagNameContainsClickedBagName(bagName) {
    //
    cy.xpath(LBL_PRODUCT_NAME_TEXT_LOCATOR).then(productNameListing => {
        const productNameListingCounter = Cypress.$(productNameListing).length;
        // expect(productNameListing).to.have.length(productNameListingCounter)
        cy.log("Total product found: "+ (productNameListingCounter))
        //browse each Bag Name 
        for (var productIndex= 0; productIndex< productNameListingCounter; productIndex++) {
            cy.log("NAME OF PRODUCT NUMBER "+ (productIndex+1)+ ":\n")
            const LBL_BAG_NAME_LOCATOR= '//div['+ (productIndex+1)+ ']/div/div[2]/div/h3/a[@href]'
            cy.xpath(LBL_BAG_NAME_LOCATOR).invoke('text').then((productNameGetTxt)=> {
                cy.log(productNameGetTxt)
                if (expect(productNameGetTxt).to.contain(bagName.toUpperCase())) {
                    cy.log("ASSERTED SUCCESSFULLY BAG NAME!")  
                }
                else {
                    cy.log("TEST CASE IS FAILED!")
                    cy.end()
                }
            })
        }
    })
}

export function assertProductPriceAsc() {
    var productPriceArr= []
    pageNumber=1
    cy.xpath(BTN_NUMBERING_LOCATOR).then(numbering => {
        const numberingCounter = Cypress.$(numbering).length;
        //
        for (var pageIndex= 0; pageIndex< numberingCounter; pageIndex++) {
            cy.log("User is on the page "+ (pageIndex+1))
            //
            cy.xpath(LBL_PRODUCT_PRICE_LOCATOR).then(productPriceListingPages => {
                const productPriceListingCounterPages = Cypress.$(productPriceListingPages).length;
                expect(productPriceListingPages).to.have.length(productPriceListingCounterPages)
                cy.log("Total product found is "+ (productPriceListingCounterPages)+ " located at page "+ pageNumber)
                pageNumber+=1
                for (var productIndex= 0; productIndex< productPriceListingCounterPages; productIndex++) {
                    cy.log("PRICE OF PRODUCT NUMBER "+ (productIndex+1)+ ":\n")
                    cy.xpath('//div['+ (((productIndex)+1))+ ']/div/div[2]/div/div/p[contains(@class, "pro-price")]').then(($productPriceGetTxt)=> {
                        productPriceGetTxtStore= productPriceGetTxtProcessing($productPriceGetTxt.text())
                        cy.log("PRODUCT PRICE AF PROCESSING: "+ productPriceGetTxtStore)
                        
                        productPriceArr.push(productPriceGetTxtStore)
                        cy.log(productPriceArr[productIndex])
                    })
                }
            })
            if (pageIndex<numberingCounter-1) {
                //
                cy.xpath(BTN_NEXT_LOCATOR).click()
            }
        }
        
        //assert the Product Price in the array whether it is sorted ascendingly

    })
    
    for (var arrayIndex= 0; arrayIndex< 128; arrayIndex++) {
        if (arrayIndex>=1) {
            if (productPriceArr[arrayIndex]>= productPriceArr[arrayIndex-1]) {
                cy.log("Asserted successfully the product number "+ arrayIndex)
            }
        }
    }
    
    //the below code used to test the JavaScript function
    
    // var testArr= []   
    // for (var i= 0; i< 15; i+=1) {
    //     testArr.push("1")
    // }
    // cy.log(testArr)
    // for (var i2=0; i2<15; i2++) {
    //     cy.log(testArr[i2])
    // }
    // cy.log(testArr.length)
    // for (var i3=0; i3<testArr.length; i3++) {
    //     cy.log("i3: "+i3)
    //     if (testArr[i3]>= testArr[i3-1]) {
    //         cy.log("less than or equal")
    //     }
    // }
    
}
    

 
