<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/favicon.ico">

    <title>Oler117 Simple Dota2 Trader</title>

    <!-- Bootstrap core CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/signin.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script type="text/javascript" src="http://code.jquery.com/jquery-2.1.3.min.js"></script>
    <script type="text/javascript" src="<c:url value="js/mainController.js" />"></script>
</head>

<body>

<div class="container">
    <div class="page-header">
        <h1>Sticky footer</h1>
    </div>
</div>

<div class="container">
    <form class="form-horizontal">
        <div class="form-group">
            <label for="tradeid" class="control-label col-xs-2">Trade Id</label>

            <div class="col-xs-4">
                <input type="text" id="tradeid" class="form-control" placeholder="Trade ID" required autofocus>
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-offset-2 col-xs-10">
                <input type="button" id="get-trade-analysis-btn" class="btn btn-primary" value="Find out"></button>
            </div>
        </div>
    </form>
</div>

<div class="container">
    Trader Info:
    <div class="traderinfo">
        Steam name:
        <div id="trader-steam-name"></div>
        Profile URL:
        <div id="trader-steam-url"></div>
        Avatar:
        <div id="trader-steam-ava"></div>
    </div>
    <br/>
</div>

<div class="container">
    <div id="tradeitemsinfo"></div>
</div>

<div class="container">
    <div class="row">
        <strong>Offer:</strong>

        <div id="tradeitems-offer" class="col-md-4"></div>
        <strong>Wants:</strong>

        <div id="tradeitems-wants" class="col-md-8"></div>
    </div>
</div>

<footer class="footer">
    <div class="container">
        <p class="text-muted">&copy; oler117, All rights reserved - 2015</p>
    </div>
</footer>
</body>
</html>