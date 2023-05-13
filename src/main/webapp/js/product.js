function generateProductForm() {
    let contendId = "#product__form__content";
    generateInputDiv(contendId, "Product Name", "name", "text")
    generateInputDiv(contendId, "Price", "price", "number")
    generateInputDiv(contendId, "Currency", "price", "number")
}

// $(document).ready(function () {
//     $("#button__create_product").click(function (event) {
//         let file = URL.createObjectURL($("#imageProduct").prop('files')[0])
//         console.log(file)
//     });
// })
