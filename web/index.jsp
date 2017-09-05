<%--
  Created by IntelliJ IDEA.
  User: blake
  Date: 0003, September 3, 2017
  Time: 10:57 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Record Prices</title>
    <meta name="description" content="Tracking dashboard for popular cryptocurrency record values">
    <meta name="keywords" content="bitcoin record price, litecoin record price, ethereum record price, record value, record price, bitcoin, cryptocurrency record price">
    <link href="http://alureon.net/img/bitcoin.png" rel="icon" type="image/x-icon">
    <script src="http://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
    <!-- deployment url (it cant find it any other way, i've tried) -->
    <script src="http://alureon.net/record/recordprice-ajax.js" type="text/javascript"></script>
    <!-- dev url -->
    <!--
    <script src="recordprice-ajax.js" type="text/javascript"></script>
    -->
    <style>
        #cryptocurrencies {
            font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        #cryptocurrencies td, #cryptocurrencies th {
            border: 1px solid #ddd;
            padding: 8px;
            font-size: 1.7em;
        }

        #cryptocurrencies tr:nth-child(even){background-color: #f2f2f2;}

        #cryptocurrencies tr:hover {background-color: #ddd;}

        #cryptocurrencies th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: #4CAF50;
            color: white;
        }

        .difference {
            color: red;
        }
    </style>
</head>
<body>
    <table id="cryptocurrencies">
        <tr>
            <th>Cryptocurrency</th>
            <th>Record Value</th>
            <th>Current Value</th>
            <th>Difference</th>
        </tr>
        <tr>
            <td><img src="http://alureon.net/img/bitcoinicon.png"> Bitcoin</th>
            <td>$<span id="bitcoinRecord"></span></th>
            <td>$<span id="bitcoinCurrent"></span></th>
            <td class="difference">-$<span id="bitcoinDifference"></span></td>
        </tr>
        <tr>
            <td><img src="http://alureon.net/img/litecoinicon.png"> Litecoin</th>
            <td>$<span id="litecoinRecord"></span></th>
            <td>$<span id="litecoinCurrent"></span></th>
            <td class="difference">-$<span id="litecoinDifference"></span></td>
        </tr>
        <tr>
            <td><img src="http://alureon.net/img/ethereumicon.png"> Ethereum</th>
            <td>$<span id="ethereumRecord"></span></th>
            <td>$<span id="ethereumCurrent"></span></td>
            <td class="difference">-$<span id="ethereumDifference"></span></td>
        </tr>
    </table>
</body>
</html>
