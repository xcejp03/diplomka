var editId = 'nenačetloseEditId';
var sourceId = 'nenačetloseSourceId';

$(function () {
    $("#datepicker").datepicker();
});

function addIdToURL(element) {
    var dataAttr = $(this).data();
    $(element).attr('href', function () {
        return this.href + editId;
    });
}

function addSourceIdToURL(element) {
    var dataAttr = $(this).data();
    $(element).attr('href', function () {
        return this.href + '&sourceid=' + sourceId;
    });
}


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
        $('#spanDataUpdated').html('sss' + dataAttr.updated);
        editId = dataAttr.objectid;
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

$(document).ready(function () {
    $('.datatable').DataTable();
});









