$(document).ready ->
  $('#get-trade-analysis-btn').click ->
    $.ajax 'trade/' + $('#tradeid').val(),
      type: 'GET'
      success: (res, status, xhr) ->
        parsedTrader = JSON.parse(res).trader
        parsedOfferInfo = JSON.parse(res).tradeOffer
        $('.traderinfo #trader-steam-name').html parsedTrader.steamName
        $('.traderinfo #trader-steam-url').html parsedTrader.steamURL
        $('.traderinfo #trader-steam-ava').html parsedTrader.avatarURL
        revenue = parsedOfferInfo.summDifference
        $('#tradeitemsinfo span').html revenue + ' $'
        if revenue < 0
          $('#tradeitemsinfo span').addClass 'text-danger'
        else
          $('#tradeitemsinfo span').addClass 'text-success'
        table = $('<table/>').appendTo($('#tradeitems-offer'))
        $(parsedOfferInfo.offering).each (i, tradeitem) ->
          $('<tr/>').appendTo(table).append($('<td/>').text(tradeitem.name)).append $('<td/>').text(tradeitem.lowestPrice)
          return
        table = $('<table/>').appendTo($('#tradeitems-wants'))
        $(parsedOfferInfo.wants).each (i, tradeitem) ->
          $('<tr/>').appendTo(table).append($('<td/>').text(tradeitem.name)).append $('<td/>').text(tradeitem.lowestPrice)
          return
        alert 'Success!'
        0
      error: (res, status, xhr) ->
        alert 'Error!'
        0
  $('#find-profitable-btn').click ->
    $.ajax 'trade/profitable',
      type: 'GET'
      success: (res, status, xhr) ->
        alert 'Success!'
        0
      error: (res, status, xhr) ->
        alert 'Error!'
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