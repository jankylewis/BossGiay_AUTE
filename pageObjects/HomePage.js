
const SVG_SEARCH_ICON_LOCATOR= '//*[name()="svg" and @class= "svg-search"]'
const TXT_SEARCH_INPUT_LOCATOR= '//div[@id= "site-search"]//input[@id= "inputSearchAuto"]'
const LBL_RESULT_FOR_SEARCH_KEY_LOCATOR= '//div[@id= "search"]//p[@class= "subtext-result"]'
const LBL_PRODUCT_NAME_TEXT_LOCATOR= '//h3[@class= "pro-name"]//a'
const LBL_SEARCH_TEXT_LOCATOR= '//div[@class= "heading-page"]'
const LBL_NO_RESULTS_TEXT_LOCATOR= '//div[@id= "search"]//h2'
const LBL_DIDNT_HAVE_ANY_RESULTS_TEXT_LOCATOR= '//div[@class= "subtext"]//preceding-sibling::span[2]'
const LBL_CHECK_SPELLING_TEXT_LOCATOR= '//div[@class= "subtext"]//following-sibling::span[2]'

let textResultGetTxt
let productNameGetTxt
const productName= []

    export function clickSearchIcon() {
        cy.xpath(SVG_SEARCH_ICON_LOCATOR).click()
    }

    export function inputSearchText(searchKey) {
        cy.xpath(TXT_SEARCH_INPUT_LOCATOR).type(searchKey)
    }
    
    export function pressEnterKey() {
        cy.xpath(TXT_SEARCH_INPUT_LOCATOR).type('{enter}')
    }

    export function assertLblContainsSearchKey(searchKey) {
        //verify the "Tìm kiếm" label text
        cy.xpath(LBL_RESULT_FOR_SEARCH_KEY_LOCATOR).invoke('text').then((textResultGetTxt) => {
            if (cy.xpath(LBL_RESULT_FOR_SEARCH_KEY_LOCATOR).should('be.visible') &&
                expect(textResultGetTxt).to.contain(searchKey)) {
                cy.log(textResultGetTxt)
                cy.log('TEST CASE IS PASSED!')
            }
            else {
                cy.log(textResultGetTxt)
                cy.log('TEST CASE IS FAILED DUE TO AN UNEXPECTED ERROR!')
            }
        })
    }
    
    export function assertProductNameContainsSearchKey(searchKey) {
        cy.xpath(LBL_PRODUCT_NAME_TEXT_LOCATOR).then(productNameListing => {
            const productNameListingCounter = Cypress.$(productNameListing).length;
            expect(productNameListing).to.have.length(productNameListingCounter)
            cy.log("Total product found: "+ (productNameListingCounter))
            verifyProductName: for (var productIndex= 0; productIndex< productNameListingCounter; productIndex++) {
                cy.log("NAME OF PRODUCT NUMBER "+ (productIndex+1)+ ":\n")
                cy.xpath('//div'+ '['+ (productIndex+1)+ ']'+ '/div/div[2]/div/h3//child::a[@href]').invoke('text').then((productNameGetTxt)=> {
                    cy.log(productNameGetTxt)
                    if (expect(productNameGetTxt).to.contain(searchKey.toUpperCase())) {
                        cy.log("ASSERTED SUCCESSFULLY PRODUCT!")  
                    }
                    else {
                        cy.log("TEST CASE IS FAILED!")
                        cy.end()
                    }
                })
            }
        })
    }

    export function assertNoResultsFoundTexts(searchKey, expSearchTextLbl, noResultsFoundTxt, didntHaveAnyResultsTxt, checkSpellingLblTxt) {
        cy.xpath(LBL_SEARCH_TEXT_LOCATOR).invoke('text').then((actSearchTextLbl)=> {
            if (cy.xpath(LBL_SEARCH_TEXT_LOCATOR).should('be.visible') &&
            expect(actSearchTextLbl).to.contain(expSearchTextLbl)) {
            cy.log(actSearchTextLbl)
            cy.log('ASSERTED SUCCESSFULLY SEARCH TEXT LABEL!')
        }
    })
        cy.xpath(LBL_NO_RESULTS_TEXT_LOCATOR).invoke('text').then((noResultsGetTxt)=> {
            if (cy.xpath(LBL_NO_RESULTS_TEXT_LOCATOR).should('be.visible') &&
            expect(noResultsGetTxt).to.contain(noResultsFoundTxt)) {
            cy.log(noResultsGetTxt)
            cy.log('ASSERTED SUCCESSFULLY NO RESULTS FOUND LABEL!')
        }
    })
        cy.xpath(LBL_DIDNT_HAVE_ANY_RESULTS_TEXT_LOCATOR).invoke('text').then((didntHaveAnyResultsGetTxt)=> {
            if (cy.xpath(LBL_DIDNT_HAVE_ANY_RESULTS_TEXT_LOCATOR).should('be.visible') &&
            expect(didntHaveAnyResultsGetTxt).to.contain(didntHaveAnyResultsTxt) &&
            expect(didntHaveAnyResultsGetTxt).to.contain(searchKey)) {
                cy.log(didntHaveAnyResultsGetTxt)
                cy.log('ASSERTED SUCCESSFULLY THE "DIDNT HAVE RESULTS" LABEL!')
            }
        })
        cy.xpath(LBL_CHECK_SPELLING_TEXT_LOCATOR).invoke('text').then((checkSpellingLblGetTxt)=> {
            if (cy.xpath(LBL_CHECK_SPELLING_TEXT_LOCATOR).should('be.visible') &&
            expect(checkSpellingLblGetTxt).to.contain(checkSpellingLblTxt)) {
                cy.log(checkSpellingLblGetTxt)
                cy.log('ASSERTED SUCCESSFULLY THE CHECK SPELLING LABEL!')
            }
        })
}




