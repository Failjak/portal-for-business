$(document).ready(function () {
    for(let btn of $(".button_to_delete")) {
        btn.addEventListener('click', handleUserDeleteButton)
    }

    function handleUserDeleteButton(el) {
        el.preventDefault();
        const btn = $(this);
        const btnId = btn.attr("id");
        const productId = btnId.split("_").pop();

        sendDeleteRequest("product", productId)
    }
})
