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

    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.3/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/ui/1.11.3/jquery-ui.js"></script>
    <%--<link rel="stylesheet" href="/resources/demos/style.css">--%>
    <script>
        $(function () {
            $("#progressbar").progressbar({
                disabled: true
            });
            $("#progressbar").hide();
            progressbar = $("#progressbar");

            var enableProgressBar = function (event) {
                progressbar.progressbar("option", "disabled", false);
                progressbar.progressbar("option", "value", false);
                $("#progressbar").show();
            };

            $("#get-trade-analysis-btn").on("click", enableProgressBar);
            $("#find-profitable-btn").on("click", enableProgressBar);
        });
    </script>

    <style>
        #progressbar .ui-progressbar-value {
            background-color: #5c5;
        }
    </style>

    <script type="text/javascript" src="<c:url value="js/mainController.js" />"></script>

</head>

<body>

<div class="container">
    <div class="page-header">
        <div class="container">
            <div class="row">
                <%--TODO: download image and save it locally! --%>
                <img class="col-md-2" src="http://31.media.tumblr.com/avatar_43d6266dc7fc_64.png"/>

                <h1>Simple Dota2 Trader by oler117</h1>
            </div>
        </div>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col-md-8">
            <form class="form-horizontal">
                <div class="form-group">
                    <label for="tradeid" class="control-label col-xs-3">Trade Id</label>

                    <div class="col-xs-9">
                        <input type="text" id="tradeid" class="form-control" placeholder="Trade ID" required autofocus>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-offset-3 col-xs-8">
                        <input type="button" id="get-trade-analysis-btn" class="btn btn-primary"
                               value="Find out"></button>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-md-4">
            <input type="button" id="find-profitable-btn" class="btn btn-primary"
                   value="Find preferable"></button>

        </div>
        <div class="col-xs-offset-2 col-md-6">
            <div id="progressbar"></div>
        </div>
    </div>
</div>

<br/><br/>

<div class="all-trades"></div>

<footer class="footer">
    <div class="container">
        <p class="text-muted">&copy; oler117, All rights reserved - 2015</p>
    </div>
</footer>
</body>
</html>