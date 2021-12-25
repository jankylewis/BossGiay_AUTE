let baseURL= 'https://bossgiay.vn/'

export function navigateToURL(appendedURL) {
    cy.visit(`${baseURL}${appendedURL}`)
}

export function TCTearDown() {
    //close browser
}