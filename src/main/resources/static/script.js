/**
 * Created by pcejk on 16.12.2016.
 */


$(function () {
    $("#datepicker").datepicker();
});


$(function () {
    $("#detailDialog, #statisticsDialog, #dialog1,#dialog2, #dialog3").dialog({
    // $($(this).data('id')).dialog({ může to fungovat?
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

    $(".opener").click(function () {
        //takes the ID of appropriate dialogue
        var id = $(this).data('id');
        //open dialogue
        $(id).dialog("open");
    });
    // $(".opener").click(function () {
    //     var dataAttr = $(this).data();
    //     $('#spanDataName').html(dataAttr.name);
    //     $('#spanDataOwnerName').html(dataAttr.owner);
    //     $("#detailDialog").dialog("open");
    // });
    //
    // $(".opener").click(function () {
    //     var dataAttr = $(this).data();
    //     // $('#spanDataName').html(dataAttr.name);
    //     // $('#spanDataOwnerName').html(dataAttr.owner);
    //     $("#statisticsDialog").dialog("open");
    // });
});


