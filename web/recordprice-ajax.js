$(function poll() {
    setTimeout(function() {
        $.ajax({ url: "price", success: function(data) {
           document.getElementById("bitcoinAjaxResponse").innerHTML = "poop";
        }, complete: poll, dataType: "json" });
    }, 30000);
});