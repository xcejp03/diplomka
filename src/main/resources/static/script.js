/**
 * Created by pcejk on 16.12.2016.
 */


$(function () {
    $("#datepicker").datepicker();
});

$(function () {
    $("#detailDialog").dialog({
        autoOpen: false,
        buttons: {
            Ok: function () {
                $(this).dialog("close");
            }
        },
        show: {
            effect: "fade",
            duration: 200
        },
        hide: {
            effect: "explode",
            duration: 350
        }
    });

    // $(".opener").on("click", function () {
    $(".opener").click(function () {
        var dataAttr = $(this).data();
        $('#spanDataName').html(dataAttr.name);
        $('#spanDataOwnerName').html(dataAttr.owner);
        // $('#spanDataDes').html(dataAttr.description);
        // $('#spanDataPrice').html(dataAttr.price);
        // $("#dialog-form").dialog("open");
        $("#detailDialog").dialog("open");
    });
});




