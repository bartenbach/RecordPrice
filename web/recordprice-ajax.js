    $.ajax({
            type: "POST",
            // development url
            //url: '/price',
            // deployment url
            url: '/record/price',
            dataType: 'json',
            success: function (data) {
                // records
                $('#bitcoinRecord').html(parseFloat(data.records.btc).toFixed(2));
                $('#litecoinRecord').html(parseFloat(data.records.ltc).toFixed(2));
                $('#ethereumRecord').html(parseFloat(data.records.eth).toFixed(2));
                // current prices
                $('#bitcoinCurrent').html(parseFloat(data.current.btc)).toFixed(2);
                $('#litecoinCurrent').html(parseFloat(data.current.ltc).toFixed(2));
                $('#ethereumCurrent').html(parseFloat(data.current.eth).toFixed(2));
                // differences
                $('#bitcoinDifference').html(parseFloat(data.records.btc - data.current.btc).toFixed(2));
                $('#litecoinDifference').html(parseFloat(data.records.ltc - data.current.ltc).toFixed(2));
                $('#ethereumDifference').html(parseFloat(data.records.eth - data.current.eth).toFixed(2));
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
                // records
                $('#bitcoinRecord').html(parseFloat(data.records.btc).toFixed(2));
                $('#litecoinRecord').html(parseFloat(data.records.ltc).toFixed(2));
                $('#ethereumRecord').html(parseFloat(data.records.eth).toFixed(2));
                // current prices
                $('#bitcoinCurrent').html(parseFloat(data.current.btc)).toFixed(2);
                $('#litecoinCurrent').html(parseFloat(data.current.ltc).toFixed(2));
                $('#ethereumCurrent').html(parseFloat(data.current.eth).toFixed(2));
                // differences
                $('#bitcoinDifference').html(parseFloat(data.records.btc - data.current.btc).toFixed(2));
                $('#litecoinDifference').html(parseFloat(data.records.ltc - data.current.ltc).toFixed(2));
                $('#ethereumDifference').html(parseFloat(data.records.eth - data.current.eth).toFixed(2));
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
