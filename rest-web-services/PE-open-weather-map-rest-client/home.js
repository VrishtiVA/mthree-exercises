
function validateZipCode(zipCode) {
    
    //No zip code, then invalid.
    if (zipCode === undefined) return null;

    //Otherwise validate
    //Note: In JS, it is .length on strings too
    zipCode = zipCode.trim();
    if (zipCode.length != 5) {
        return null;
    } else {
        return zipCode;
    }
}

//Once file ready
$(document).ready(() => {
    
    $("#weatherFeedbackAlert").hide();

})

//Alright for now.
$("#weatherZipCode").on("input", () => {
    $("#weatherFeedbackAlert").hide();
});

//On weather submit
$("#weatherSubmitBtn").on("click", () => {
    
    //Validate fields
    let zipCode = $("#weatherZipCode").val();
    zipCode = validateZipCode(zipCode);

    if (zipCode == null) {
        $("#weatherFeedbackAlert").html("Zip code: please enter a 5-digit zip code.").show();
    }
})

