$(function () {
    $("#datepicker").datepicker();
});


$(function () {
    $("#detailDialog, #statisticsDialog, #moreDialog,#membersDialog, #dialog3").dialog({
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
        $('#spanDataObjectId').html(dataAttr.objectid);
        $('#spanDataUpdated').html(dataAttr.updated);
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



