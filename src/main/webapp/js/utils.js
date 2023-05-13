function generateInputDiv(contentId, label, inputId, inputType) {
    let content = $(contentId)
    let div = generateBaseInputDiv()
    $(div).children("div")
        .children("input")
        .attr("type", inputType)
        .attr("id", inputId)
        .attr("name", inputId)
        .attr("placeholder", label)
    $(div).children("label").text(label).attr("for", inputId)
    content.append(div)
}

function generateBaseInputDiv() {
    let baseDiv = document.createElement("div")
    baseDiv.classList.add("row", "mb-3", "flex")

    let label = document.createElement("label")
    label.classList.add("col-auto", "col-form-label")

    let inputDiv = document.createElement("div")
    inputDiv.classList.add("col-auto")
    let input = document.createElement("input")
    input.classList.add("form-control")
    inputDiv.appendChild(input)

    baseDiv.append(label, inputDiv)
    return baseDiv
}

function generateProductEvaluationContent(parameter) {
    let formGroupDiv = document.createElement("div")
    formGroupDiv.classList.add("form-group")

    let startRatingLabel = document.createElement("h5")
    startRatingLabel.textContent = parameter["name"]
    formGroupDiv.appendChild(startRatingLabel)
    let starRating = generateStarRating(parameter)
    formGroupDiv.appendChild(starRating)

    $("#product_evaluation__content").append($(formGroupDiv))
}

function generateCommentsContent(comments) {
    $("#modal__comments").html("")
    for (let comment of comments) {
        $("#modal__comments").append(generateCommentDiv(comment))
    }
}

function generateCommentDiv(comment) {
    let mainDiv = document.createElement("div")
    $(mainDiv).addClass(["bg-white", "p-2"])

    let flexDiv = document.createElement("div")
    $(flexDiv).addClass(["d-flex", "flex-row", "user-info"])
    mainDiv.appendChild(flexDiv)

    let img = document.createElement("img")
    $(img).addClass(["rounded-circle"]).attr("src", comment["user"]["imagePath"]).css("width", 40)
    flexDiv.appendChild(img)

    let contentDiv = document.createElement("div")
    $(contentDiv).addClass(["d-flex", "flex-column", "justify-content-start", "ml-2"])

    let spanName = document.createElement("span")
    $(spanName).text(comment["user"]["username"]).addClass(["d-block", "font-weight-bold", "name"])

    let spanCommentTime = document.createElement("span")
    $(spanCommentTime).addClass(["d-block", "font-weight-bold", "name"]).text(comment[""])

    contentDiv.appendChild(spanName)
    contentDiv.appendChild(spanCommentTime)
    flexDiv.appendChild(contentDiv)

    let commentDiv = document.createElement("div")
    $(commentDiv).addClass(["mt-2"])

    let commentText = document.createElement("p")
    $(commentText).addClass(["comment-text"]).text(comment["comment"])

    commentDiv.appendChild(commentText)
    mainDiv.appendChild(commentDiv)
    mainDiv.appendChild(generateDivider())

    return mainDiv;
}

function generateDivider() {
    let divider = document.createElement("div")
    $(divider).addClass(["line"])
    return divider
}

function generateStarRating(parameter) {
    let starRating = document.createElement("div")
    starRating.classList.add("star-rating")

    let starRatingWrap = document.createElement("div")
    starRatingWrap.classList.add("star-rating__wrap")

    for (let i = 5; i > 0; i--) {
        let input, label
        [input, label] = createInputAndLabelForStarRating(`star-${i}__${parameter["id"]}`, i, `parameter_id-${parameter["id"]}`)
        starRatingWrap.appendChild(input)
        starRatingWrap.appendChild(label)
    }

    starRating.appendChild(starRatingWrap)
    return starRating
}

function createInputAndLabelForStarRating(id, value, name) {
    let input = document.createElement("input")
    $(input).attr("id", id).attr("type", "radio").attr("name", name).attr("value", value).addClass("star-rating__input")

    let label = document.createElement("label")
    $(label).attr("for", id).attr("title", value).addClass(["star-rating__ico", "fa", "fa-star-o", "fa-lg"])

    return [input, label]
}

function sendDeleteRequest(contentUrl, contentId, soft) {
    let url = `${window.location.origin}/${contentUrl}/${contentId}`
    if (soft === true)
        url += "?soft=True"

    fetch(url, {
        method: 'DELETE',
        headers: {
            'Content-type': 'application/json'
        }
    }).then((response) => handleResponse(url, response)).catch(err => console.log(err))

}
function handleResponse(url, response) {
    if (String(response.status).startsWith("2")) {
        location.reload();
    } else {
        console.log(`Invalid request to ${url}; Response: ${response}`)
    }
}

function getContextPath() {
    return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
}

async function sendRequest(url, data, method, skipCheckingResponse) {
    try {
        const res = await fetch(url, {
            method: method,
            body: data ? JSON.stringify(data) : data,
            headers: { 'Content-Type': 'application/json' }
        })
        if (!skipCheckingResponse) {
            return await res.json()
        }
    } catch (error) {
        console.error("Some error on client side: ", error);
    }
}

function activateAlertsModal(msg) {
    $("#alert-modal__msg").html(msg);
    $('#alert-modal').modal("show");
}