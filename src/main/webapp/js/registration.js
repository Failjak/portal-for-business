$(document).ready(function () {
    $("#button__seller_registration").click(() => {
        $("#state").attr("value","seller")
        $("#registration__form__content").empty()
        generateSellerRegistrationForm();
    })

    $("#button__user_registration").click(() => {
        $("#state").attr("value","user")
        $("#registration__form__content").empty()
        generateUserRegistrationForm();
    })
});



function generateUserRegistrationForm() {
    let contentId = "#registration__form__content";
    generateInputDiv(contentId, "Username", "username", "text")
    generateInputDiv(contentId, "Password", "password", "password")

    $("#button__user_registration").hide()
    $("#button__seller_registration").show()
}

function generateSellerRegistrationForm() {
    let contentId = "#registration__form__content";
    generateInputDiv(contentId, "Store Name", "storeName", "text")
    generateInputDiv(contentId, "Username", "username", "text")
    generateInputDiv(contentId, "Password", "password", "password")

    $("#button__seller_registration").hide()
    $("#button__user_registration").show()
}
