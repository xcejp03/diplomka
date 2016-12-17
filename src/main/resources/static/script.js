/**
 * Created by pcejk on 16.12.2016.
 */


$(function () {
    $("#datepicker").datepicker();
});

$(function () {
    $("#dialog").dialog({
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

    $("#opener").on("click", function () {
        $("#dialog").dialog("open");
    });
});


