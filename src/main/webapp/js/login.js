$(document).ready(function () {
    $("#button__seller_registration").click(() => {
        $("#state").attr("value","seller")
        $("#button__seller_registration").hide()
        $("#button__user_registration").show()
    })

    $("#button__user_registration").hide().click(() => {
        $("#state").attr("value","user")
        $("#button__user_registration").hide()
        $("#button__seller_registration").show()
    })
});