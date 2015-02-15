$(document).ready ->
  d2lTradeUrl = "http://dota2lounge.com/trade?t="
  tradeBlock = '<div class="trade">
        <div class="container">
            <div class="traderinfo">
                <div class="row">
                    <label for="trader-steam-name" class="control-label col-md-2">Steam name:</label>
                    <div id="trader-steam-name" class="col-md-2"></div>
                    <label for="trader-steam-url" class="control-label col-md-2">Profile URL:</label>
                    <div id="trader-steam-url" class="col-md-2"></div>
                    <label for="trader-steam-ava" class="control-label col-md-2">Avatar:</label>
                    <div id="trader-steam-ava" class="col-md-2"></div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="tradeitemsinfo">
                <h3>
                    Revenue = <span></span>
                </h3>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-md-6"><strong>Offer:</strong></div>
                <div class="col-md-6"><strong>Wants:</strong></div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="tradeitems-offer col-md-6"></div>
                <div class="tradeitems-wants col-md-6"></div>
            </div>
        </div>
        <div class="container">
          <div class="d2l-tradeoffer-link">
            <a href="">View on DotaLounge</a>
          </div>
        </div>
        </br></br>
    </div>';

  $('#get-trade-analysis-btn').click ->
    $.ajax 'trade/' + $('#tradeid').val(),
      type: 'GET'
      success: (res, status, xhr) ->
        $('.all-trades').html ""
        $('.all-trades').append tradeBlock
        parsedTrader = JSON.parse(res).trader
        parsedOfferInfo = JSON.parse(res).tradeOffer
        $('.traderinfo #trader-steam-name').html parsedTrader.steamName
        $('.traderinfo #trader-steam-url').html parsedTrader.steamURL
        $('.traderinfo #trader-steam-ava').html parsedTrader.avatarURL
        revenue = parsedOfferInfo.summDifference
        $('.tradeitemsinfo span').html revenue + ' $'
        if revenue < 0
          $('.tradeitemsinfo span').addClass 'text-danger'
        else
          $('.tradeitemsinfo span').addClass 'text-success'
        table = $('<table/>').appendTo($('.tradeitems-offer'))
        $(parsedOfferInfo.offering).each (i, tradeitem) ->
          $('<tr/>').appendTo(table).append($('<td/>').addClass('name-td')
          .text(tradeitem.name)).append($('<td/>').addClass('price-td')
          .text(tradeitem.lowestPrice)).append($('<td/>').addClass('price-td')
          .text(tradeitem.mediumPrice))
          return
        table = $('<table/>').appendTo($('.tradeitems-wants'))
        $(parsedOfferInfo.wants).each (i, tradeitem) ->
          $('<tr/>').appendTo(table).append($('<td/>').addClass('name-td')
          .text(tradeitem.name)).append($('<td/>').addClass('price-td')
          .text(tradeitem.lowestPrice)).append($('<td/>').addClass('price-td')
          .text(tradeitem.mediumPrice))
          return
        $('.d2l-tradeoffer-link a').attr "href", d2lTradeUrl + parsedOfferInfo.id
        progressbar.hide()
        progressbar.progressbar "option", "disabled", true
        0
      error: (res, status, xhr) ->
        alert 'Error!'
        0
  $('#find-profitable-btn').click ->
    $.ajax 'trade/profitable',
      type: 'GET'
      success: (res, status, xhr) ->
        tradeOfferList = JSON.parse(res)
        for i in [0...tradeOfferList.length]
          $('.all-trades').append(tradeBlock)
        traderInfoBlocks = $('.traderinfo');
        tradeOfferRevenueBlocks = $('.tradeitemsinfo span')
        tradeItemsOfferBlocks = $('.tradeitems-offer')
        tradeItemsWantsBlocks = $('.tradeitems-wants')
        d2lLinkBlocks = $('.d2l-tradeoffer-link a');

        i = 0
        $(tradeOfferList).each (i, tradeOfferWrapper) ->
          tradeItemsOfferBlock = tradeItemsOfferBlocks[i]
          tradeOfferRevenueBlock = $(tradeOfferRevenueBlocks[i])
          d2lLinkBlock = $(d2lLinkBlocks[i])
          #        parsedTrader = JSON.parse(res).trader
          #        $('.traderinfo #trader-steam-name').html parsedTrader.steamName
          #        $('.traderinfo #trader-steam-url').html parsedTrader.steamURL
          #        $('.traderinfo #trader-steam-ava').html parsedTrader.avatarURL
          revenue = tradeOfferWrapper.tradeOffer.summDifference
          tradeOfferRevenueBlock.html revenue + ' $'
          if revenue < 0
            tradeOfferRevenueBlock.addClass 'text-danger'
          else
            tradeOfferRevenueBlock.addClass 'text-success'

          table = $('<table/>').appendTo(tradeItemsOfferBlock)
          $(tradeOfferWrapper.tradeOffer.offering).each (i, tradeitem) ->
            $('<tr/>').appendTo(tradeItemsOfferBlock).append($('<td/>').addClass('name-td')
            .text(tradeitem.name)).append($('<td/>').addClass('price-td')
            .text(tradeitem.lowestPrice)).append($('<td/>').addClass('price-td')
            .text(tradeitem.mediumPrice))
            return
          tradeItemsOfferBlock = tradeItemsWantsBlocks[i]
          table = $('<table/>').appendTo(tradeItemsOfferBlock)
          $(tradeOfferWrapper.tradeOffer.wants).each (i, tradeitem) ->
            $('<tr/>').appendTo(tradeItemsOfferBlock).append($('<td/>').addClass('name-td')
            .text(tradeitem.name)).append($('<td/>').addClass('price-td')
            .text(tradeitem.lowestPrice)).append($('<td/>').addClass('price-td')
            .text(tradeitem.mediumPrice))
            return
          d2lLinkBlock.attr "href", d2lTradeUrl + tradeOfferWrapper.tradeOffer.id
          return
        progressbar.hide()
        progressbar.progressbar "option", "disabled", true
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