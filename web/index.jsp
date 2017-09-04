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
    <script src="http://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
    <script src="http://alureon.net/record/recordprice-ajax.js" type="text/javascript"></script>
    <style>
        #cryptocurrencies {
            font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        #cryptocurrencies td, #cryptocurrencies th {
            border: 1px solid #ddd;
            padding: 8px;
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
    </style>
</head>
<body>
    <table id="cryptocurrencies">
        <tr>
            <th>Cryptocurrency</th>
            <th>Record Value</th>
            <th>Current Value</th>
        </tr>
        <tr>
            <td>Bitcoin</th>
            <td>$<span id="bitcoinRecord"></span></th>
            <td>$<span id="bitcoinCurrent"></span></th>
        </tr>
        <tr>
            <td>Litecoin</th>
            <td>$<span id="litecoinRecord"></span></th>
            <td>$<span id="litecoinCurrent"></span></th>
        </tr>
        <tr>
            <td>Ethereum</th>
            <td>$<span id="ethereumRecord"></span></th>
            <td>$<span id="ethereumCurrent"></span></td>
        </tr>
    </table>
</body>
</html>
