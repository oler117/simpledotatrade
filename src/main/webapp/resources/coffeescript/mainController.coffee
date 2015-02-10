$(document).ready ->
  $("#get-trade-analysis-btn").click ->
    $.ajax 'trade/'+$('#tradeid').val(),
      type: 'GET',
      success: (res, status, xhr) ->
        $('#traderinfo').html res.trader
        $('#tradeitemsinfo').html res.tradeOfferInfo
        alert "Success!"
        0
      error: (res, status, xhr) ->
        alert "Error!"
        0

###

$("main .box-shiny-alt .profilesmall")

<a href="<!!STEAM-PROFILE>" class="profilesmall">
    <div class="slvl"><!!LEVEL></div>
    <img src="<!!AVATAR>" alt="Avatar"/>
</a>

###


###

$("main #tradediv .tradepoll .tradecnt .left/.right .oitm")

<div class="name">
    <b> Blades of Voth Domosh</b> <br/>
    <span class="hero">Legion Commander</span>'s
    <span class="type">Twin Demon Blades</span>
    <small></small>

    <br><a href="http://steamcommunity.com/market/listings/570/Blades of Voth Domosh">Market</a>
</div>
<div class="item  itm559549132">
    <img class="smallimg" src="http://cdn.dota2lounge.com/img/items/5810.jpg" alt="Blades of Voth Domosh"/>
    <div class="rarity Arcana">&nbsp;Arcana&nbsp;</div>
</div>

###