//applying POM on testing

//declare a myriad of locators
const SEL_SORT_CATEGORY_LOCATOR= '//select'
const NAV_SNEAKER_LOCATOR= '//nav//preceding-sibling::a[@title= "Sneaker"]'
const LBL_PRODUCT_PRICE_LOCATOR= '//p[contains(@class, "pro-price")]'
const BTN_NUMBERING_LOCATOR= '//div[@id= "pagination"]//a'
const BTN_NEXT_LOCATOR= '//a[@class= "next"]'
const LBL_PRODUCT_NAME_TEXT_LOCATOR= '//h3[@class= "pro-name"]//a'
const NAV_SANDAL_LOCATOR= '//nav//preceding-sibling::a[contains(@title, "Sandal")]'

//initialize variables
let pageNumber
let pricePartition

var productPriceGetTxtStore
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

export function clickSortSneakerByName(snkName) {
    cy.xpath('//label[contains(@for, "data-brand") and contains(text(), "'+ (
        snkName.toUpperCase())+ '")]').click()
}

export function clickSortSandalByName(sandalName) {
    cy.xpath('//label[contains(@for, "data-brand") and contains(text(), "'+ (
        sandalName.toUpperCase())+ '")]').click()
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
    
    // for (var arrayIndex= 0; arrayIndex< 128; arrayIndex++) {
    //     if (arrayIndex>=1) {
    //         if (productPriceArr[arrayIndex]>= productPriceArr[arrayIndex-1]) {
    //             cy.log("Asserted successfully the product number "+ arrayIndex)
    //         }
    //     }
    // }
    
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
    

 
