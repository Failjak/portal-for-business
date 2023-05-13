$(document).ready(function () {
    $("#edit__photo").change(function (event) {
        event.preventDefault()

        if (this.files && this.files[0]) {
            let reader = new FileReader();
            reader.onload = function (e) {
                $('#edit__avatar').attr('src', e.target.result);
            }
            reader.readAsDataURL(this.files[0]);
        }
    })

    $("#profile__upgrade").click(async function (event) {
        console.log('here')
        $("#profile__form__input_for_type").attr("name", "upgrade").attr("value", "true")
    })
});


