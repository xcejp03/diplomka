$(function () {
    $("#datepicker").datepicker();
});


$(function () {
    $("#detailDialog, #statisticsDialog, #dialog1,#dialog2, #dialog3").dialog({
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
        var dataAttr = $(this).data();
        $('#spanDataName').html(dataAttr.name);
        $('#spanDataOwnerName').html(dataAttr.owner);
        $(dataAttr.id).dialog("open");
    });
});


$(function () {
    $(".alertik").click(function () {
        swal("sssa ds");
    })
})

$(function () {
    $("#alerton").click(function () {
        swal("#alerton  preš click");
    })
})



