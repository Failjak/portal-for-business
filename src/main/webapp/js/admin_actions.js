$(document).ready(function () {
    for(let btn of $(".button_to_delete")) {
        btn.addEventListener('click', handleUserDeleteButton)
    }

    for(let btn of $(".button_to_block")) {
        btn.addEventListener('click', handleUserBlockButton)
    }

    function handleUserDeleteButton(el) {
        el.preventDefault();
        const btn = $(this).attr("id");
        let type = "user"
        if (btn.includes("seller"))
            type = "seller"
        if (btn.includes("parameter"))
            type = "parameter"

        const id = btn.split("_").pop();
        sendDeleteRequest(type, id)
    }

    function handleUserBlockButton(el) {
        el.preventDefault();
        const btn = $(this).attr("id");
        let type = "user"
        if (btn.includes("seller"))
            type = "seller"
        if (btn.includes("parameter"))
            type = "parameter"

        const id = btn.split("_").pop();
        sendDeleteRequest(type, id, true)
    }
})
