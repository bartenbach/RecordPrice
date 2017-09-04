    $.ajax({
            type: "POST",
            url: '/price',
            dataType: 'json',
            success: function (data) {
                $('#bitcoinRecord').html(data.records.btc);
                $('#litecoinRecord').html(data.records.ltc);
                $('#ethereumRecord').html(data.records.eth);
                $('#bitcoinCurrent').html(data.current.btc);
                $('#litecoinCurrent').html(data.current.ltc);
                $('#ethereumCurrent').html(data.current.eth);
            },
            error: function (jqXHR, exception) {
                var msg = '';
                if (jqXHR.status === 0) {
                    msg = 'Not connect.\n Verify Network.';
                } else if (jqXHR.status == 404) {
                    msg = 'Requested page not found. [404]';
                } else if (jqXHR.status == 500) {
                    msg = 'Internal Server Error [500].';
                } else if (exception === 'parsererror') {
                    msg = 'Requested JSON parse failed.';
                } else if (exception === 'timeout') {
                    msg = 'Time out error.';
                } else if (exception === 'abort') {
                    msg = 'Ajax request aborted.';
                } else {
                    msg = 'Uncaught Error.\n' + jqXHR.responseText;
                }
                console.log(msg);
            }
        });

(function poll() {
    setTimeout(function () {
        $.ajax({
            type: "POST",
            url: '/price',
            dataType: 'json',
            success: function (data) {
                $('#bitcoinAjaxResponse').html(data.btc);
                $('#litecoinAjaxResponse').html(data.ltc);
                $('#ethereumAjaxResponse').html(data.eth);
            },
            error: function (jqXHR, exception) {
                var msg = '';
                if (jqXHR.status === 0) {
                    msg = 'Not connect.\n Verify Network.';
                } else if (jqXHR.status == 404) {
                    msg = 'Requested page not found. [404]';
                } else if (jqXHR.status == 500) {
                    msg = 'Internal Server Error [500].';
                } else if (exception === 'parsererror') {
                    msg = 'Requested JSON parse failed.';
                } else if (exception === 'timeout') {
                    msg = 'Time out error.';
                } else if (exception === 'abort') {
                    msg = 'Ajax request aborted.';
                } else {
                    msg = 'Uncaught Error.\n' + jqXHR.responseText;
                }
                console.log(msg);
            }
        });
    }, 60000);
})();
