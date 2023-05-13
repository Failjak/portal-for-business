$(document).ready(function () {

    $('#alertbox').click(function(){
        $("#error").html("You Clicked on Click here Button");
        $('#myModal').modal("show");
    });


    $("#btn_search").click(async function (event) {
        event.preventDefault()
        let name = $("#container_row__search__input").val()
        sendRequest(`/products?name=${name}`, null, "get").then(products => generateTableProductRows(event, products))
    })

    $("#modal__send").click(async function (event) {
        event.preventDefault()
        let radioValues = {}
        $("input[name^='parameter_id-']:checked").each(function (el) {
            let id = this.name.split("-")[1]
            radioValues[id] = parseInt(this.value)
        })

        let product = JSON.parse($("#productModal").attr("product"))
        let body = {
            productId: product["id"],
            evaluation: radioValues,
            comment: $("#modal__product__message").val()
        }
        sendRequest($("#modal__form").prop("action"), body, $("#modal__form").prop("method")).then(r => console.log(r))
    })

    $(".products__buy").click(async function (event) {
        event.preventDefault()
        let productId = $(this).val()

        await sendRequest('/buy/', {'id': productId}, 'post').then(res => {
            activateAlertsModal(res)
        })
    })
})

function generateTableProductRows(event, products) {
    $("#products__tbody").html("")
    for (let product of products) {

        let row = document.createElement("tr")

        let td = document.createElement("td")

        let mainDiv = document.createElement("div")
        mainDiv.classList.add("product-cart", "d-flex")

        let div = document.createElement("div")
        div.classList.add("product-thumb")

        let img = document.createElement("img")
        $(img).attr("src", product.imagePath)

        let contentDiv = document.createElement("div")
        contentDiv.classList.add("product-content", "media-body")

        let h5 = document.createElement("h5")
        h5.classList.add("title")

        let a = document.createElement("a")
        $(a).attr("onclick", `showProductModal(event, ${product.json})`).text(product.name).attr("href", "#")

        let span = document.createElement("span")
        $(span).text(product.description)

        $(h5).append(a)
        $(contentDiv).append([h5, span])
        $(div).append(img)
        $(mainDiv).append([div, contentDiv])
        $(td).append(mainDiv)
        $(row).append(td)
        $("#products__tbody").append(row)
    }
}

async function showProductModal(event, product, isBought) {
    event.preventDefault();
    let modal = $("#productModal")
    $(modal).attr("product", JSON.stringify(product))
    $("#modal__product__message").val("")

    getAllCommentByProduct(JSON.stringify(product)).then(function (comments) {
        generateCommentsContent(comments)
    })

    if (isBought) {
        getParamsByProductType(product["type"]).then(function (parameters) {
            $("#product_evaluation__content").html("")
            for (let parameter of parameters["parameters"]) {
                generateProductEvaluationContent(parameter)
            }
        })
    }
    setBaseContentToModalProduct(product)
    modal.modal("show")
}

function setBaseContentToModalProduct(product) {
    $("#productModalLabel").text("Product: " + product["name"] + " --- " + product["type"])
    $("#modal__product_image").attr("src", getContextPath() + product["imagePath"]).attr("alt", product["name"])

    $("#modal__product_name").text("Name: " + product["name"])
    $("#modal__product_description").html("Description: <br>" + product["description"])
    $("#modal__product_type").text(product["type"])
    $("#modal__product_price").text("Price: " + product["price"] + " " + product["currencyType"])

}


async function getParamsByProductType(productType) {
    const url = `${window.location.origin}/parameter?` + new URLSearchParams({productType: productType})
    return await sendRequest(url, null, "GET")
}

async function getAllCommentByProduct(product) {
    const url = `${window.location.origin}/comment?` + new URLSearchParams({product: product})
    return await sendRequest(url, null, "GET")
}

